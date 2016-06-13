package com.puxtech.ybk.shezhi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.puxtech.ybk.jiaoyi.TradeHelper;

/**
 * Created by mac on 16/5/6.
 */
public class ShutdownReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        SheZhiHelper.saveHistoryLiuLiang(context);
        Log.i("MainActivity", "系统关闭关闭中...");
    }
}
