package com.puxtech.ybk.jiaoyi.page;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.BaseFragment;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.KeyboardUtil;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.HeartBeat;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerLogin;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerMoney;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerQuery;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.CheckCodeResponseData;
import com.puxtech.ybk.util.ActivityUtils;
import com.puxtech.ybk.util.SharedPreferenceManager;

import java.util.Random;
import java.util.Set;

public class LoginFragment extends BaseTradeFragment {
    private static int REQUEST_CHECK_CODE = 1;
    private static int REQUEST_LOGIN = 2;
    View view;
    Spinner sp_tradetype, sp_accounttype;
    AutoCompleteTextView act_account;
    EditText et_password, et_checkcode;
    ImageView img_checkcode;
    TextView tv_reset;
    CheckBox cb_remember;
    Button bt_cancel, bt_login;
    ProgressBar bar;
    JiaoYiMainFragment jiaoYiMainFragment;

    public LoginFragment(JiaoYiMainFragment fragment) {
        this.jiaoYiMainFragment = fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextSizeHelper.setTextSize(context);

        view = inflater.inflate(R.layout.fragment_login, container, false);

        initWidget();
        requestCheckCodeImg();



        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.LOGOFF);
        receiver = new mLogoutReceiver();
        context.registerReceiver(receiver, filter);
        return view;
    }


    mLogoutReceiver receiver;

    public class mLogoutReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Constant.LOGOFF)) {
//              showWhichView();
                if(intent.hasExtra(TradeHelper.PROMPT)){
                    ActivityUtils.showAlert(context,intent.getStringExtra(TradeHelper.PROMPT));

                    new Thread(){
                        public void run(){
                            //不延时的话.loginFragmen可能未处于活动状态,会报错崩溃
                            try {
                                Thread.sleep(1000);
                                jiaoYiMainFragment.showWhichView();;
                            } catch (InterruptedException e) { }
                        }
                    }.start();
//
                }
            }
        }


    }

    private void initWidget() {


        sp_tradetype = (Spinner) view.findViewById(R.id.sp_tradetype);
        sp_accounttype = (Spinner) view.findViewById(R.id.sp_accounttype);
        act_account = (AutoCompleteTextView) view.findViewById(R.id.act_account);
        et_password = (EditText) view.findViewById(R.id.et_password);
        et_checkcode = (EditText) view.findViewById(R.id.et_checkcode);
        ActivityUtils.setEditTextSoftInputType(getActivity(), context, et_checkcode,2);
        ActivityUtils.setEditTextSoftInputType(getActivity(), context, et_password,2);
        ActivityUtils.setEditTextSoftInputType(getActivity(), context, act_account,2);

//        et_checkcode.setInputType(InputType.TYPE_NULL);




        img_checkcode = (ImageView) view.findViewById(R.id.img_checkcode);
        tv_reset = (TextView) view.findViewById(R.id.tv_reset);
        cb_remember = (CheckBox) view.findViewById(R.id.cb_remember);
        bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
        bt_login = (Button) view.findViewById(R.id.bt_login);
        bar = (ProgressBar) view.findViewById(R.id.bar);
        et_password.requestFocus();
        setSoftKeyDone(et_checkcode, bt_login);
        SharedPreferenceManager spf_WX = new SharedPreferenceManager(context, SharedPreferenceManager.LOGIN);
        Set<String> siteno = spf_WX.getStringSet(SharedPreferenceManager.LOGIN_ACCOUNT_ARRAY);
        String[] arr = siteno.toArray(new String[siteno.size()]);
//        String [] arr={"aa","aab","aac"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, arr);
        act_account.setAdapter(arrayAdapter);
        act_account.setThreshold(1);
        act_account.setText(spf_WX.getString(SharedPreferenceManager.LOGIN_LAST_ACCOUNT, ""));


        tv_reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                request(REQUEST_CHECK_CODE);
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(et_checkcode.getText().toString().equals("")||et_password.getText().toString().equals(""))
                {
                    ActivityUtils.showCenterToast(context,"请检查数据", Toast.LENGTH_SHORT);
                    return;
                }
                request(REQUEST_LOGIN);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferenceManager spf_WX = new SharedPreferenceManager(context, SharedPreferenceManager.LOGIN);
        act_account.setText(spf_WX.getString(SharedPreferenceManager.LOGIN_LAST_ACCOUNT, ""));
        et_password.setText("");
    }

    //请求验证码
    private void requestCheckCodeImg() {
        et_checkcode.setText("");

        request(REQUEST_CHECK_CODE);
    }


    private void request(final int type) {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            BaseResponseDataTrade responseData;
            Dialog dialog;

            protected void onPreExecute() {
                if (type == REQUEST_CHECK_CODE) {
                    bar.setVisibility(View.VISIBLE);
                    img_checkcode.setVisibility(View.GONE);
//                    startNum=1;
//                    InOutHistoryAdapter adapter = new InOutHistoryAdapter(context, new ArrayList<InOutHistoryEntity>());
//                    recyclerView.setAdapter(adapter);
                } else {
                    dialog = ActivityUtils.showLoadingProgressDialog(context);
                }

                super.onPreExecute();
            }

            @SuppressWarnings("ResourceType")
            protected Boolean doInBackground(Void... params) {
                try {
                    HttpManagerLogin httpManagerLogin = new HttpManagerLogin(context);
                    HttpManagerQuery httpManagerQuery = new HttpManagerQuery(context);
                    HttpManagerMoney httpManagerMoney = new HttpManagerMoney(context);

                    if (type == REQUEST_CHECK_CODE) {
                        responseData = httpManagerLogin.requestCheckCode();
                    } else if (type == REQUEST_LOGIN) {
                        responseData = httpManagerLogin.requestTradeLogin(act_account.getText().toString(), et_password.getText().toString(), et_checkcode.getText().toString(), String.valueOf(et_checkcode.getTag()));

                        if (responseData.getRetCode() != 0) {
                            return false;
                        }
                        responseData = httpManagerQuery.requestCommodity("");
                        if (responseData.getRetCode() != 0) {
                            return false;
                        }
                        responseData = httpManagerQuery.requestHoldQuery();
                        if (responseData.getRetCode() != 0) {
                            return false;
                        }
                        responseData = httpManagerMoney.requestUserBank();
                        if (responseData.getRetCode() != 0) {
                            return false;
                        }
                        responseData = httpManagerQuery.requestFundQuery();
                        if (responseData.getRetCode() != 0) {
                            return false;
                        }
                        responseData = httpManagerQuery.requestCommodityFee();
                        if (responseData.getRetCode() != 0) {
                            return false;
                        }
//                        SharedPreferenceManager spf = new SharedPreferenceManager(context, HeartBeat.HEART_BEAT );
//                        //为心跳信号做准备,第一次,需要传-1
//                        spf.putLong(SharedPreferenceManager.TRADE_CNT, -1);
                        HeartBeat heartBeat = new HeartBeat(context);
                        heartBeat.requestHeartBeat(true);

//                        responseData = httpManager.requestInOutHistory(startTime, endTime, startNum);
                    }

                    //获取开户信息®

                } catch (Exception e) {
                    e.printStackTrace();
                    responseData.setRetCode(Constant.CODE_ERROR_UNKNOW);
                    responseData.setRetMessage(Constant.MESSAGE_ERROR_UNKNOW);

                }
                return true;
            }

            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (!(type == REQUEST_CHECK_CODE)) {
                    dialog.dismiss();
                }


                if (Constant.CODE_SUCCESS == responseData.getRetCode()) {
                    if (type == REQUEST_CHECK_CODE) {
                        if((((CheckCodeResponseData) responseData).getImgData()).equals("")){
                            checkCodeSetErrorImg();
                        }else {
                            bar.setVisibility(View.GONE);
                            img_checkcode.setVisibility(View.VISIBLE);

                            img_checkcode.setImageBitmap(ActivityUtils.getBitmap(((CheckCodeResponseData) responseData).getImgData()));
                            et_checkcode.setTag(((CheckCodeResponseData) responseData).getCheckCodeId());


                        }

                    } else if (type == REQUEST_LOGIN) {
//                        requestCheckCodeImg();
                        if (cb_remember.isChecked()) {
                            SharedPreferenceManager spf_WX = new SharedPreferenceManager(context, SharedPreferenceManager.LOGIN);
                            spf_WX.putStringSet(SharedPreferenceManager.LOGIN_ACCOUNT_ARRAY, act_account.getText().toString());
                            //+ new Random().nextInt(100)
                            spf_WX.putString(SharedPreferenceManager.LOGIN_LAST_ACCOUNT, act_account.getText().toString());
                        }
                        myApplication.getTradeEntity().setHasLogin(true);


                        TradeHelper.userTouchReset(context);

//                        ((JiaoYiMainFragment) getParentFragment()).showWhichView();
                        jiaoYiMainFragment.showWhichView();
                        ;
                        ;
                    }
                } else {

                    if (type == REQUEST_CHECK_CODE) {
                        checkCodeSetErrorImg();

                    } else {
                        requestCheckCodeImg();
                        ActivityUtils.showAlert(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")");
                    }
                }
            }
        });
    }

    private void checkCodeSetErrorImg() {
        bar.setVisibility(View.GONE);
        img_checkcode.setVisibility(View.VISIBLE);
        img_checkcode.setImageResource(R.drawable.failcode_ny);
    }

    protected void setSoftKeyDone(EditText editText, final Button ok) {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }

                    ok.performClick();

                    return true;
                }
                return false;
            }
        });
    }
}
