package com.puxtech.ybk.shezhi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.preference.PreferenceManager;

import com.puxtech.ybk.util.ActivityUtils;
import com.puxtech.ybk.util.SharedPreferenceManager;

/**
 * Created by mac on 16/5/6.
 */
public class SheZhiHelper {


    //APP启动时,记录当前的流量
    public static void saveCurrentLiuLiang(Context context) {

        ApplicationInfo ai = null;
        try {
            PackageManager pm = context.getPackageManager();
            ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//                统计某一个进程的总接收量TrafficStats.getUidRxBytes(Uid));
//                统计某一个进程的总发送量TrafficStats.getUidTxBytes(Uid));
        long currentLiuLiang = TrafficStats.getUidRxBytes(ai.uid);
        SharedPreferenceManager sharedPreferences = new SharedPreferenceManager(context, SharedPreferenceManager.SHE_ZHI);
        sharedPreferences.putLong(SharedPreferenceManager.LIU_LIANG_APP_START, currentLiuLiang);
    }

    //得到本次APP启动的流量
    public static long getCurrentStartLiuLiang(Context context) {

        SharedPreferenceManager sharedPreferences = new SharedPreferenceManager(context, SharedPreferenceManager.SHE_ZHI);
        long startLiuLiang = sharedPreferences.getLong(SharedPreferenceManager.LIU_LIANG_APP_START, 0);
        return getOpenSystemToCurrentLiuLiang(context) - startLiuLiang;
    }

    //得到历史总流量
    public static long getHistoryLiuLiang(Context context) {
        SharedPreferenceManager sharedPreferences = new SharedPreferenceManager(context, SharedPreferenceManager.SHE_ZHI);

        long history = sharedPreferences.getLong(SharedPreferenceManager.LIU_LIANG_SYSTEM_SHUT_DOWN, 0);
        if(history==-1){

            return getCurrentStartLiuLiang(context);
        }
        return history + getOpenSystemToCurrentLiuLiang(context);
    }

    //用户清除流量
    public static void cleanLiuLiang(Context context) {
        SharedPreferenceManager sharedPreferences = new SharedPreferenceManager(context, SharedPreferenceManager.SHE_ZHI);
        //清除历史总流量
        sharedPreferences.putLong(SharedPreferenceManager.LIU_LIANG_SYSTEM_SHUT_DOWN, -1);
        sharedPreferences.putLong(SharedPreferenceManager.LIU_LIANG_APP_START, getOpenSystemToCurrentLiuLiang(context));
        String time = ActivityUtils.getRealDataFixDay(System.currentTimeMillis() + "");
        sharedPreferences.putString(SharedPreferenceManager.LIU_LIANG_CLEAN_TIME, time);
    }
    //用户上次清除流量时间
    public static String getLastCleanTime(Context context) {
        SharedPreferenceManager sharedPreferences = new SharedPreferenceManager(context, SharedPreferenceManager.SHE_ZHI);
        return sharedPreferences.getString(SharedPreferenceManager.LIU_LIANG_CLEAN_TIME, "无数据");
    }
    //关机时,将本次开机以后启动的流量统计,存入历史中
    public static void saveHistoryLiuLiang(Context context) {
        //取到本次开机以后启动的流量
        ApplicationInfo ai = null;
        try {
            PackageManager pm = context.getPackageManager();
            ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        long currentLiuLiang = TrafficStats.getUidRxBytes(ai.uid);
        //取到历史流量
        SharedPreferenceManager sharedPreferences = new SharedPreferenceManager(context, SharedPreferenceManager.SHE_ZHI);
        long historyLiuLiang = sharedPreferences.getLong(SharedPreferenceManager.LIU_LIANG_SYSTEM_SHUT_DOWN, 0);
        //本次和历史相加
        long total = currentLiuLiang + historyLiuLiang;
        //保存到历史流量
        sharedPreferences.putLong(SharedPreferenceManager.LIU_LIANG_SYSTEM_SHUT_DOWN, total);
    }

    //本次开机以后的所有流量
    public static long getOpenSystemToCurrentLiuLiang(Context context) {
        ApplicationInfo ai = null;
        try {
            PackageManager pm = context.getPackageManager();
            ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        long currentLiuLiang = TrafficStats.getUidRxBytes(ai.uid);
        return currentLiuLiang;
    }

}
