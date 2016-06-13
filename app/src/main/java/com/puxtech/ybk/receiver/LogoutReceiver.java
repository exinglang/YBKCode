package com.puxtech.ybk.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.puxtech.ybk.Constant;

/**
 * Created by mac on 16/5/4.
 */
public class LogoutReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Constant.LOGOFF)) {
            FinishCurrentActivity(context);
        }
    }

    protected void FinishCurrentActivity(Context context) {
        Activity activity=(Activity)context;
        activity.finish();
    }
}
