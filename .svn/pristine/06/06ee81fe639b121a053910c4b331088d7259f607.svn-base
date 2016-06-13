package com.puxtech.ybk.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.shezhi.SettingsActivity;

/**
 * Created by mac on 16/4/22.
 */
public class TextSizeHelper {
    //根据用户设置,设置字体大小
    public static void setTextSize(Context context) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String size = sp.getString(context.getString(R.string.pref_system_text_size_key), "5");
        if (size.equals("0")) {
            context.setTheme(R.style.text_size_0);
        } else if (size.equals("1")) {
            context.setTheme(R.style.text_size_1);
        } else if (size.equals("2")) {
            context.setTheme(R.style.text_size_2);
        } else if (size.equals("3")) {
            context.setTheme(R.style.text_size_3);
        } else if (size.equals("4")) {
            context.setTheme(R.style.text_size_4);
        } else if (size.equals("5")) {
            context.setTheme(R.style.text_size_5);
        } else if (size.equals("6")) {
            context.setTheme(R.style.text_size_6);
        } else if (size.equals("7")) {
            context.setTheme(R.style.text_size_7);
        } else if (size.equals("8")) {
            context.setTheme(R.style.text_size_8);
        } else if (size.equals("9")) {
            context.setTheme(R.style.text_size_9);
        } else if (size.equals("10")) {
            context.setTheme(R.style.text_size_10);
        }
    }
}
