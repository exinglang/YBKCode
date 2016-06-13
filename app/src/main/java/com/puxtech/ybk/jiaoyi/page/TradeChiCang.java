package com.puxtech.ybk.jiaoyi.page;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.HeartBeat;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowHoldDetailAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.FundInfoData;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerQuery;
import com.puxtech.ybk.jiaoyi.responsedata.FundQueryResponseData;
import com.puxtech.ybk.jiaoyi.view.TradePromptDialog;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;


public class TradeChiCang extends BaseTradeFragment {
    ListView listView;
    TextView tv_zongzichan, tv_yue, tv_shizhi, tv_kequ;
    View view;
    Button bt_bank;
    Timer timer;
    TimerTask task;
    HeartBeat heartBeat;
    public static int recLen = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trade_chi_cang, container, false);
        heartBeat = new HeartBeat(context);
        initWidget();

        initListView();
        setShowTopInfo();
        resetRecLen();
        startTimer();


        //        // 注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.USER_TOUCH_RESET);
        filter.addAction(Constant.HEART_NEW_HOLD);


        receiver = new mLogoutReceiver();
        context.registerReceiver(receiver, filter);

        return view;

    }

    mLogoutReceiver receiver;

    public class mLogoutReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Constant.USER_TOUCH_RESET)) {
                //用户解锁屏幕
                resetRecLen();
            } else if (intent.getAction().equals(Constant.HEART_NEW_HOLD)) {
                if (getUserVisibleHint()) {
                    threeRowAdapter.notifyDataSetChanged();
                }

            }
        }


    }

    //重设触摸倒计时
    private void resetRecLen() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //从seek.xml文件中取出之前保存的数据，如之前没有保存过数据，则默认进度为50，checkbox默认为true
        String min = sharedPreferences.getString(getString(R.string.pref_trade_lock_time_key), "10");
        recLen = Integer.valueOf(min) * 60;
//        recLen=5;
    }

    private void initListView() {
//        ArrayList<HoldDetailData> holdDetailDataArrayList = myApplication.getTradeEntity().getTradeData().getHoldDetailDataList();
//        ArrayList<ThreeRowData> threeRowDatas = new ArrayList<>();
//        for (HoldDetailData holdDetailData : holdDetailDataArrayList) {
//            ThreeRowData threeRowData = new ThreeRowData();
//            threeRowData.setS1(TradeHelper.getCommodityDataByCode(context, holdDetailData.getCOMMODITYID()).getName());
//            threeRowData.setS2(holdDetailData.getCOMMODITYID());
//            threeRowData.setS3(holdDetailData.getHOLDQTY());
//            threeRowData.setS4("" + (Double.valueOf(holdDetailData.getHOLDQTY()) - Double.valueOf(holdDetailData.getFROZENQTY())));
//            threeRowData.setS5(holdDetailData.getHOLDCOST());
//            threeRowData.setS6("--");
//            threeRowDatas.add(threeRowData);
//        }
        threeRowAdapter = new ThreeRowHoldDetailAdapter(context, myApplication.getTradeEntity().getTradeData().getHoldDetailDataList());
        listView.setAdapter(threeRowAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoldDetailData item = threeRowAdapter.getItem(position);
                LinkedHashMap<String, String> map = TradeHelper.getChiCangDetailMap(context, item);
                TradePromptDialog tradePromptDialog = new TradePromptDialog(context);
                tradePromptDialog.getClickDetail(map);
            }
        });
    }

    ThreeRowHoldDetailAdapter threeRowAdapter;

    private void initWidget() {
        bt_bank = (Button) view.findViewById(R.id.bt_bank);
        listView = (ListView) view.findViewById(R.id.listview);
        tv_zongzichan = (TextView) view.findViewById(R.id.tv_zongzichan);
        tv_yue = (TextView) view.findViewById(R.id.tv_yue);
        tv_shizhi = (TextView) view.findViewById(R.id.tv_shizhi);
        tv_kequ = (TextView) view.findViewById(R.id.tv_kequ);
        bt_bank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(context, Bank.class));
            }
        });
    }

    //刷新顶部信息
    private void request() {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            FundQueryResponseData responseData;

            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected Boolean doInBackground(Void... params) {
                try {
                    HttpManagerQuery httpManager = new HttpManagerQuery(context);
                    responseData = httpManager.requestFundQuery();

                } catch (Exception e) {
                    e.printStackTrace();
                    responseData.setRetCode(Constant.CODE_ERROR_UNKNOW);
                    responseData.setRetMessage(Constant.MESSAGE_ERROR_UNKNOW);
                }
                return true;
            }

            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);

                if (0 == responseData.getRetCode()) {
                    setShowTopInfo();
                }

            }
        });
    }

    /**
     * 显示顶部的资金信息
     */
    private void setShowTopInfo() {
        FundInfoData fundInfoData = myApplication.getTradeEntity().getOtherData().getFundInfoData();
        try {
            tv_yue.setText(fundInfoData.getDONEBALANCE());
        } catch (Exception e) {
            e.printStackTrace();
            tv_yue.setText("--");
        }
        //可取资金==PC 可用资金
        try {
            tv_kequ.setText(ActivityUtils.changeDouble(String.valueOf(Double.valueOf(fundInfoData.getDONEBALANCE()) - Double.valueOf(fundInfoData.getFROZENFUNDS()))));
        } catch (Exception e) {
            e.printStackTrace();
            tv_kequ.setText("--");
        }
        tv_shizhi.setText(TradeHelper.getMarketValue(context));
        try {

            tv_zongzichan.setText(ActivityUtils.changeDouble(String.valueOf(Double.valueOf(fundInfoData.getDONEBALANCE()) + Double.valueOf(TradeHelper.getMarketValue(context)))));
        } catch (Exception e) {
            e.printStackTrace();
            tv_zongzichan.setText("--");
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            try {
                threeRowAdapter.setList(myApplication.getTradeEntity().getTradeData().getHoldDetailDataList());
                threeRowAdapter.notifyDataSetChanged();
                ;
                threeRowAdapter.notifyDataSetInvalidated();

            } catch (Exception e) {

            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    // 开始一个TIMER
    public void startTimer() {
//        try {
//
//
//        stopTimer();;
//        }catch (Exception e){
//
//        }

        if (timer == null) {
            timer = new Timer();
        }
        if (task == null) {
            task = new TimerTask() {
                public void run() {
                    if (myApplication.getTradeEntity().isHasLogin()) {
                        request();// 实时刷新顶部信息
                        heartBeat.requestHeartBeat(false);

                        recLen--;
                        Logger.v(recLen + "");
                        if (recLen == 0) {
                            TradeHelper.logoutWithprompt(context, Constant.HEART_BEAT_ERROR_CODE_MESSAGE);
                        }

                    }
                }
            };

        }
        if (timer != null && task != null) {
            try {
                timer.schedule(task, 5000, 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Handler hd = new Handler() {

        public void handleMessage(Message msg) {
            threeRowAdapter.notifyDataSetChanged();
            ;

        }

    };

    // 结束一个TIMER
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
        ;
    }


}
