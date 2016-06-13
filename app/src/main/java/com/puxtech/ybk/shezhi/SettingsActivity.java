package com.puxtech.ybk.shezhi;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.view.TradePromptDialog;
import com.puxtech.ybk.qidong.CheckUpdateManager;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        this.context = this;
        TextSizeHelper.setTextSize(context);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.zixuan_fragment_main);

        addPreferencesFromResource(R.xml.pref_headers);
//        TextSizeHelper.setTextSize(context);




//        setupActionBar();
//        TextSizeHelper.setTextSize(context);
//
//        setContentView(R.layout.linkspeed);
//        TradeHelper.initToolBarWithSubTitleAndFinishIcon(SettingsActivity.this, getString(R.string.pref_link_data_title), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));

//        setupActionBar();
        EditTextPreference pre_lock_time = (EditTextPreference) findPreference(getString(R.string.pref_trade_lock_time_key));
        pre_lock_time.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                TradeHelper.userTouchReset(SettingsActivity.this);
                return true;
            }
        });
        setupActionBar();

//        Preference pref_system_text_size_key = findPreference(getString(R.string.pref_reset_data_key));
//        pref_system_text_size_key.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                ActivityUtils.showCenterToast(context, "应用字体需要重启应用程序", Toast.LENGTH_SHORT);
//
//                return true;
//            }
//        });
//        Preference pref_reset_data_key = findPreference(getString(R.string.pref_reset_data_key));
//        pref_reset_data_key.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                ActivityUtils.showAlertWithConfirmText(SettingsActivity.this, "重置系统数据会清除已经保存的数据(如自选股等用户数据),确定执行?", "确定", new Runnable() {
//                    @Override
//                    public void run() {
//                        CleanAppDateManager.cleanApplicationData(SettingsActivity.this);
//                        ActivityUtils.showCenterToast(context, "已清除数据", Toast.LENGTH_SHORT);
//
//                    }
//                });
//
//                return true;
//            }
//        });

        //测速
        Preference pref_link_data_key = findPreference(getString(R.string.pref_link_data_key));
        pref_link_data_key.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(context, LinkSpeed.class));

                return true;
            }
        });
        //
        Preference pref_query_flux_key = findPreference(getString(R.string.pref_query_flux_key));
        pref_query_flux_key.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
//                LinkedHashMap map = new LinkedHashMap<>();
//                map.put("本次访问流量", SheZhiHelper.getCurrentStartLiuLiang(SettingsActivity.this));
//                map.put("历史访问总流量", SheZhiHelper.getHistoryLiuLiang(SettingsActivity.this));
//                map.put("上次重置时间", );
//                TradePromptDialog tradePromptDialog = new TradePromptDialog(SettingsActivity.this);
//                tradePromptDialog.getClickDetail(map);
                ActivityUtils.showAlert(context, "本次访问流量:" + ActivityUtils.bytes2kb(SheZhiHelper.getCurrentStartLiuLiang(SettingsActivity.this)) + "\n" + "历史访问总流量:" + ActivityUtils.bytes2kb(SheZhiHelper.getHistoryLiuLiang(context)) +
                        "\n" + "上次重置时间:" + SheZhiHelper.getLastCleanTime(SettingsActivity.this));
                return true;
            }
        });
        Preference pref_reset_flux_key = findPreference(getString(R.string.pref_reset_flux_key));
        pref_reset_flux_key.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                ActivityUtils.showAlertWithConfirmText(SettingsActivity.this, "确定重置流量统计?", "确定", new Runnable() {
                    public void run() {
                        SheZhiHelper.cleanLiuLiang(SettingsActivity.this);

                    }
                });

                return true;
            }
        });
        //检查更新

        Preference pref_evaluation_key = findPreference("pref_evaluation_key");
        pref_evaluation_key.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                CheckUpdateManager checkUpdateManager = new CheckUpdateManager(SettingsActivity.this);
                checkUpdateManager.checkUpdate();
                return true;
            }
        });

        //关于
        Preference pref_reset_about_key = findPreference(getString(R.string.pref_reset_about_key));
        pref_reset_about_key.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(context, About.class));

                return true;
            }
        });

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {


        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
//    private void setupActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            // Show the Up button in the action bar.
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//
//        return false;
//    }
//
//    @Override
//    public void setActionBar(android.widget.Toolbar toolbar) {
//        super.setActionBar(toolbar);
//    }

    private void setupActionBar() {
        Toolbar toolbar;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewGroup root = (ViewGroup) findViewById(android.R.id.list).getParent().getParent().getParent();
            toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.toolbar_only, root, false);
            root.addView(toolbar, 0);
        } else {
            ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
            ListView content = (ListView) root.getChildAt(0);
            root.removeAllViews();
            toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.toolbar_only, root, false);
            int height;
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
                height = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            } else {
                height = toolbar.getHeight();
            }
            content.setPadding(0, height, 0, 0);
            root.addView(content);
            root.addView(toolbar);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ( (TextView) findViewById(R.id.tv_toolbar)).setText("设置");
        toolbar.setNavigationIcon(R.drawable.home_title_btn_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               finish();
            }
        });
    }
    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        return super.onPreferenceStartFragment(caller, pref);
    }
}
