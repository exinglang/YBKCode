package com.puxtech.ybk.shezhi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

import com.puxtech.ybk.R;
import com.puxtech.ybk.util.ActivityUtils;

public class SettingsBarPreference extends DialogPreference {

    Context con;
    SeekBar sRingtone;

    public SettingsBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造器中为con赋值
        con = context;
    }

    /**
     * 在对话框弹出时执行取数据并给进度条和复选框设置值的操作
     */
    @Override
    protected View onCreateDialogView() {
        //通过上下方取得SharedPreferences对象
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(con);
        //从seek.xml文件中取出之前保存的数据，如之前没有保存过数据，则默认进度为50，checkbox默认为true
        String ringtone = sharedPreferences.getString(this.con.getString(R.string.pref_system_text_size_key), "5");
        //通过转换器将布局转换成View对象并return
        View view = LayoutInflater.from(con).inflate(R.layout.seek, null);
        //取得进度条和复选框对象
        sRingtone = (SeekBar) view.findViewById(R.id.ringtone);
        //将从seek.xml文件中取出的数据设置给进度条和复选框 ，如之前没有保存过数据，则默认进度为50，checkbox默认为true
        sRingtone.setProgress(Integer.valueOf(ringtone));
        sRingtone.setMax(10);
        return view;
    }

    /**
     * 在点击对话框ok按钮时取得进度条的值和复选框的状态，保存到seek.xml文件中
     */
    @Override
    protected void onDialogClosed(boolean positiveResult) {
        //positiveResult为true则表示点击了ok按钮
        if(positiveResult){
            //取得进度条当前的进度和复选框的状态
            int ringtone = sRingtone.getProgress();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(con);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(this.con.getString(R.string.pref_system_text_size_key), ringtone + "");
            //提交数据
            editor.commit();
        }
        ActivityUtils.showCenterToast(con, "重设字体大小需要重启应用程序", Toast.LENGTH_SHORT);

        super.onDialogClosed(positiveResult);
    }

    @Override
    public void setOnPreferenceChangeListener(OnPreferenceChangeListener onPreferenceChangeListener) {
        super.setOnPreferenceChangeListener(onPreferenceChangeListener);

    }
}