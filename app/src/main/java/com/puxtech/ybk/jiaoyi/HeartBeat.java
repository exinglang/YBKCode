package com.puxtech.ybk.jiaoyi;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;


import com.puxtech.ybk.Constant;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.jiaoyi.entitydata.CommodityData;
import com.puxtech.ybk.jiaoyi.entitydata.HeartBeatData;
import com.puxtech.ybk.jiaoyi.entitydata.TodayDealData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerLogin;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerQuery;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerTrade;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.HeartBeatResponseData;
import com.puxtech.ybk.util.ActivityUtils;
import com.puxtech.ybk.util.SharedPreferenceManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p/>
 * 自动刷新最新价格的service，根据用户定义时间请求数据，然后发送广播，需要刷新价格的Activity自行注册广播接收器
 */
public class HeartBeat {
    public static final String HEART_BEAT = "HEART_BEAT";

    MyApplication myapp;
    Context context;

    public HeartBeat(Context context) {
        this.context = context;
        myapp = (MyApplication) context.getApplicationContext();
    }


    public void requestHeartBeat(final boolean isFirstLogin) {
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>() {
            HeartBeatResponseData heartBeatResponseData;
            SharedPreferenceManager spf = new SharedPreferenceManager(context, HEART_BEAT);
            long trade_cnt = spf.getLong(SharedPreferenceManager.TRADE_CNT, -1);
            long lastnew_id = spf.getLong(SharedPreferenceManager.LASTNEW_ID, 0);
            String clear_date = spf.getString(SharedPreferenceManager.CLEAR_DATE, "");

            //上面的值已在登录时赋值过.
            @Override
            protected Integer doInBackground(Void... arg0) {
                try {
                    HttpManagerLogin httpManagerlogin = new HttpManagerLogin(context);
                    //登录时已访问过一遍,所以传0即可
                    heartBeatResponseData = httpManagerlogin.requestHeartBeat(isFirstLogin ? "-1" : trade_cnt + "");
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
                return 0;
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);

                if (0 == heartBeatResponseData.getRetCode()) {
                    HeartBeatData heartBeatData = heartBeatResponseData.getHeartBeatData();
                    // 判断是否有新的广播
                    if (heartBeatData.getLASTNEW_ID() > lastnew_id) {

                        Toast.makeText(context, "您有新的公告  请注意查收", Toast.LENGTH_LONG)
                                .show();
                    }
                    //检测是否有新成交
                    checkNewDeal(heartBeatData);
                    if (heartBeatData.getDataArrayList().size() > 0) {
//有新成交 刷新持仓
                        requestHold();

                    }
                    //登录时已请求过TD,此处只需判断交易日是否切换.
                    // 交易日变化的话要退出重新登陆
                    if ((!heartBeatData.getCLEAR_DATE().equals(clear_date)) && !isFirstLogin) {
                        relogon();
                    }
                    //0:等待开市中,1:开市准备中,2:结算中,3:休市中,4:交易暂停中,5:交易中,6:节间休息中,7:准备结算中
                    if (heartBeatData.getSYSTEM_STATUS() == 0 || heartBeatData.getSYSTEM_STATUS() == 1 || heartBeatData.getSYSTEM_STATUS() == 3) {
                        myapp.getTradeEntity().setHeartBeatTradeState(false);
                    } else {
                        myapp.getTradeEntity().setHeartBeatTradeState(true);
                    }
                    //保存下次发送需要的数据
                    new TradeHelper(context).saveSpf(spf, heartBeatData.getTRADE_CNT(), heartBeatData.getLASTNEW_ID(), heartBeatData.getCLEAR_DATE());
                } else {
                    //重新登录
                    relogon();
                }

            }
        };
        task.execute();
    }

    private void requestHold() {


        AsyncTask<Void, Void, Integer> task2 = new AsyncTask<Void, Void, Integer>() {
            BaseResponseDataTrade responseData;

            @Override
            protected Integer doInBackground(Void... arg0) {
                try {
                    HttpManagerQuery httpManager = new HttpManagerQuery(context);

                    responseData = httpManager.requestHoldQuery();
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
                return 0;
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);

                if (0 == responseData.getRetCode()) {
                    Intent intent = new Intent();
                    intent.setAction(Constant.HEART_NEW_HOLD);
                    intent.setPackage(context.getPackageName());
                    context.sendBroadcast(intent);
                }
            }
        };
        task2.execute();
    }

    /**
     * 检查是否有新成交
     */
    private void checkNewDeal(HeartBeatData heartBeatData) {
        for (int i = 0; i < heartBeatData.getDataArrayList().size(); i++) {
            TodayDealData todayDealData = heartBeatData.getDataArrayList().get(i);
            String typ = todayDealData.getTR_T_CH();
            String bs = todayDealData.getBS_FLAG_CH();
            String sname = todayDealData.getCOMMODITY_NAME();
            Toast.makeText(context, todayDealData.getTRADE_NO() + "号委托成交" + "\n" + sname + " " + typ + bs + todayDealData.getQUANTITY() + "手", Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * 重新登录
     */
    private void relogon() {
        TradeHelper.logoutWithprompt(context, Constant.HEART_BEAT_ERROR_CODE_MESSAGE);
    }


}
