package com.puxtech.ybk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puxtech.ybk.hangqing.HangQingLogger;
import com.puxtech.ybk.hangqing.HangQingMainFragment;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.page.JiaoYiMainFragment;
import com.puxtech.ybk.jiaoyi.page.TradeMaiChu;
import com.puxtech.ybk.jiaoyi.page.TradeMaiRu;
import com.puxtech.ybk.jiaoyi.page.TradeRootFragment;
import com.puxtech.ybk.jiaoyi.view.BaseViewPager;
import com.puxtech.ybk.shouye.ShouYeMainFragment;
import com.puxtech.ybk.util.ActivityUtils;
import com.puxtech.ybk.zixuan.ZiXuanMainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_DETAIL = 1;
    public static final String KEY_COMMODITY_CODE = "MainActivity.KEY_COMMODITY_CODE";//商品code
    public static final String KEY_TRADE_FLAG = "MainActivity.KEY_TRADE_FLAG";//买卖标识
    private TabLayout tabLayout;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        fragments = new ArrayList<>();
        fragments.add(ShouYeMainFragment.getFragment());
//        fragments.add(ZiXuanMainFragment.getFragment());
        fragments.add(HangQingMainFragment.getFragment());
        fragments.add(JiaoYiMainFragment.getFragment());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_fragment, fragments.get(0));
        fragmentTransaction.add(R.id.layout_fragment, fragments.get(1));
        fragmentTransaction.add(R.id.layout_fragment, fragments.get(2));
//        fragmentTransaction.add(R.id.layout_fragment, fragments.get(3));
        fragmentTransaction.commit();


        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                updateSelectedItem(tab.getPosition());
//                if (tab.getPosition() == 0) {
//                    quickTrade(1, "100000");
//                }
//                if (tab.getPosition() == 1) {
//                    quickTrade(2, "100000");
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        for (int i = 0; i < 3; i++) {
            View tabItemView = getTabView(i);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabItemView));
        }
        updateSelectedItem(0);//默认选择0
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("退出")
                .setMessage("确定要退出吗？")
                .setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            finish();
                            System.exit(0);
                        }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nothing
                    }
                })
                .show();
    }

    private void updateSelectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position) {
                fragmentTransaction.show(fragments.get(i));
            } else {
                fragmentTransaction.hide(fragments.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    public void updateSelectedItem(int position) {
        tabLayout.getTabAt(position).select();
        setIconImageResource(position);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                LinearLayout linearLayout = (LinearLayout) tab.getCustomView().findViewById(R.id.id_ll_tab_bg);
                TextView textView = (TextView) linearLayout.findViewById(R.id.tv);

                if (i == position) {
                    textView.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    textView.setTextColor(Color.parseColor("#000000"));

                }


            }
        }
        updateSelectedFragment(position);
    }

    /**
     * 快卖快买
     *
     * @param buy  1买 2卖
     * @param code
     */
    public void quickTrade(boolean buy, String code) {


        updateSelectedItem(2);

        MyApplication myApplication = (MyApplication) this.getApplicationContext();
        if (myApplication.getTradeEntity().isHasLogin()) {
            TradeRootFragment tradeRootFragment = ((JiaoYiMainFragment) fragments.get(2)).getTradeRootFragment();
            BaseViewPager baseViewPager = tradeRootFragment.getPager();

            if (buy) {
                baseViewPager.setCurrentItem(1);
                ((TradeMaiRu) tradeRootFragment.getFragment2()).getAct_code().setText(code);
            } else  {

                baseViewPager.setCurrentItem(2);
                ((TradeMaiChu) tradeRootFragment.getFragment3()).getAct_code().setText(code);
            }
        }


    }

    private void setIconImageResource(int position) {

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            LinearLayout linearLayout = (LinearLayout) tab.getCustomView().findViewById(R.id.id_ll_tab_bg);
            ImageView iv = (ImageView) linearLayout.findViewById(R.id.iv);
            if (i == 0) {
                iv.setImageResource(R.drawable.mi_btn_shouye);
            } else if (i == 1) {
                iv.setImageResource(R.drawable.common_button_bt_selector);

            } else if (i == 2) {
                iv.setImageResource(R.drawable.mi_btn_jiaoyi);

            }
        }
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        LinearLayout linearLayout = (LinearLayout) tab.getCustomView().findViewById(R.id.id_ll_tab_bg);
        ImageView iv = (ImageView) linearLayout.findViewById(R.id.iv);
        if (position == 0) {
            iv.setImageResource(R.drawable.mi_btn_shouye_highlighted);
        } else if (position == 1) {
            iv.setImageResource(R.drawable.mi_btn_hangqing_highlighted);
        } else if (position == 2) {
            iv.setImageResource(R.drawable.mi_btn_jiaoyi_highlighted);
        }
    }


    private View getTabView(int position) {
        View v = LayoutInflater.from(this).inflate(R.layout.customer_tab_item, null);
        TextView tv = (TextView) v.findViewById(R.id.tv);
        ImageView img = (ImageView) v.findViewById(R.id.iv);

        if (position == 0) {
            tv.setText("首页");
            img.setImageResource(R.drawable.mi_btn_shouye);
        } else if (position == 1) {
            img.setImageResource(R.drawable.common_button_bt_selector);

            tv.setText("行情");
        } else if (position == 2) {
            img.setImageResource(R.drawable.mi_btn_jiaoyi);

            tv.setText("交易");
        }
        return v;
    }


    /**
     * 以下的几个方法用来，让fragment能够监听touch事件
     */
    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<>(
            10);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TradeHelper.userTouchReset(this);

        for (MyOnTouchListener listener : onTouchListeners) {
            listener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }


    public interface MyOnTouchListener {
        public boolean onTouch(MotionEvent ev);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DETAIL){
            if(resultCode == Activity.RESULT_OK){
                //直接调用updateSelectedItem会出现异常
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String code = data.getStringExtra(KEY_COMMODITY_CODE);
                        boolean buy = data.getBooleanExtra(KEY_TRADE_FLAG, true);//买是true，卖是false
                        quickTrade(buy, code);
                    }
                },100);
            }
        }
    }
}
