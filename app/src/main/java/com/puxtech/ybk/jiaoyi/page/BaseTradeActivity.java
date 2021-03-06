package com.puxtech.ybk.jiaoyi.page;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.receiver.LogoutReceiver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fanshuo on 16/4/20.
 */
public class BaseTradeActivity extends BaseActivity {


    LogoutReceiver receiver;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.LOGOFF);
        receiver = new LogoutReceiver();
        registerReceiver(receiver, filter);

    }
    public void onDestroy() {
        super.onDestroy();
        clearAsyncTask();
        // 注销广播
//        if (isRegisterReceiver)// 已经注册了
        unregisterReceiver(receiver);

    }

    @Override
    public void onUserInteraction() {
        if(!(TradeChiCang.recLen==0)) {
            TradeHelper.userTouchReset(context);
        }}

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return super.onTouchEvent(event);
    }

}
