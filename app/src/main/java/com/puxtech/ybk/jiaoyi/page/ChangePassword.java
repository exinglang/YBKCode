package com.puxtech.ybk.jiaoyi.page;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerLogin;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerMoney;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.util.ActivityUtils;

public class ChangePassword extends BaseTradeActivity {


    TextInputLayout til_oldpwd, til_newpwd, til_copypwd;
    EditText et_oldpwd, et_newpwd, et_copypwd;
    Button bt_commit;
    Spinner sp_type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.activity_changepassword);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, "密码修改", (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
        initWidget();

    }

    private void initWidget() {
        til_oldpwd = (TextInputLayout) findViewById(R.id.til_oldpwd);
        til_newpwd = (TextInputLayout) findViewById(R.id.til_newpwd);
        til_copypwd = (TextInputLayout) findViewById(R.id.til_copypwd);
        et_oldpwd = (EditText) findViewById(R.id.et_oldpwd);
        et_newpwd = (EditText) findViewById(R.id.et_newpwd);
        et_copypwd = (EditText) findViewById(R.id.et_copypwd);

        ActivityUtils.setEditTextSoftInputType(this, context, et_oldpwd, 2);
        ActivityUtils.setEditTextSoftInputType(this, context, et_newpwd, 2);
        ActivityUtils.setEditTextSoftInputType(this, context, et_copypwd, 2);
        bt_commit = (Button) findViewById(R.id.bt_commit);

        sp_type = (Spinner) findViewById(R.id.sp_type);

        String[] mItems = {"交易密码", "资金密码"};
// 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);

        sp_type.setAdapter(adapter);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1) {
                    if (!myApplication.getTradeEntity().getOtherData().isSetedFundPwd()) {
                        til_oldpwd.setErrorEnabled(false);
                        til_newpwd.setErrorEnabled(false);
                        til_copypwd.setErrorEnabled(false);

                        ActivityUtils.showCenterToast(context, Constant.NO_FUND_PWD, Toast.LENGTH_LONG);
                        LinearLayout ll_old = (LinearLayout) findViewById(R.id.ll_old);
                        ll_old.setVisibility(View.GONE);

                    }

                } else if (position == 0) {
                    til_oldpwd.setErrorEnabled(true);
                    til_newpwd.setErrorEnabled(true);
                    til_copypwd.setErrorEnabled(true);
                    LinearLayout ll_old = (LinearLayout) findViewById(R.id.ll_old);
                    ll_old.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        sp_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//            }
//        });


        til_oldpwd.setErrorEnabled(true);
        til_newpwd.setErrorEnabled(true);
        til_copypwd.setErrorEnabled(true);

        addTextChangedListener();
        // 两次输入的新密码不一致
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!til_oldpwd.isErrorEnabled() && !til_newpwd.isErrorEnabled() && !til_copypwd.isErrorEnabled()) {
                    request();
                } else {
                    ActivityUtils.showCenterToast(context, "请检查输入的数据", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    /**
     * 请求
     */
    private void request() {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            BaseResponseDataTrade responseData;
            Dialog dialog;

            protected void onPreExecute() {
                dialog = ActivityUtils.showLoadingProgressDialog(context);
                super.onPreExecute();
            }

            @SuppressWarnings("ResourceType")
            protected Boolean doInBackground(Void... params) {
                try {

                    if (sp_type.getSelectedItemPosition() == 0) {
                        //交易密码
                        HttpManagerLogin httpManager = new HttpManagerLogin(context);

                        responseData = httpManager.requestChangePasswordTrade(et_oldpwd.getText().toString(), et_newpwd.getText().toString());

                    } else if (sp_type.getSelectedItemPosition() == 1) {
                        HttpManagerMoney managerMoney = new HttpManagerMoney(context);

                        responseData = managerMoney.requestChangePasswordMoney(et_oldpwd.getText().toString(), et_newpwd.getText().toString());


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    responseData.setRetCode(Constant.CODE_ERROR_UNKNOW);
                    responseData.setRetMessage(Constant.MESSAGE_ERROR_UNKNOW);
                }
                return true;
            }

            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                dialog.dismiss();

                if (0 == responseData.getRetCode()) {

                    if (sp_type.getSelectedItemPosition() == 0) {

                    } else if (sp_type.getSelectedItemPosition() == 1) {
                        myApplication.getTradeEntity().getOtherData().setSetedFundPwd(true);
                        setResult(101);

                    }
                    ActivityUtils.showAlert_onlyOk(context, "密码修改成功", new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    });
                } else {
                    ActivityUtils.showAlert(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")");

                }
            }
        });
    }

    /**
     * 添加对输入字符串的判断
     */
    private void addTextChangedListener() {
        et_oldpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    til_oldpwd.setError("请输入原密码");
                } else {
                    til_oldpwd.setErrorEnabled(false);
                }

            }
        });
        et_newpwd.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    til_newpwd.setError("请输入新密码");

                } else if (s.toString().equals(et_oldpwd.getText().toString())) {

                    til_newpwd.setError("请输入与旧密码不同的新密码");

                } else {
                    til_newpwd.setErrorEnabled(false);
                }

            }
        });
        et_copypwd.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    til_copypwd.setError("请重复新密码");

                } else if (!s.toString().equals(et_newpwd.getText().toString())) {

                    til_copypwd.setError("两次输入的新密码不一致");

                } else {
                    til_copypwd.setErrorEnabled(false);
                }

            }
        });
    }


}
