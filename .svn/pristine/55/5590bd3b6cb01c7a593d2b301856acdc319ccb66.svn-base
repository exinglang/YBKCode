package com.puxtech.ybk.jiaoyi.page;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.puxtech.ybk.BaseFragment;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.MainActivity;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.receiver.LogoutReceiver;

import static android.view.GestureDetector.*;

/**
 * Created by fanshuo on 16/4/20.
 */
public class JiaoYiMainFragment extends BaseFragment

{
    FragmentTransaction fragmentTransaction;

    public static Fragment getFragment() {
        JiaoYiMainFragment fragment = new JiaoYiMainFragment();
        return fragment;
    }

    LoginFragment loginFragment;
    TradeRootFragment tradeRootFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.jiaoyi_fragment_main, container, false);
        FragmentManager manager = getFragmentManager();
        fragmentTransaction = manager.beginTransaction();

        loginFragment = new LoginFragment(this);
        tradeRootFragment = new TradeRootFragment();
        fragmentTransaction.add(R.id.content, loginFragment);
        fragmentTransaction.add(R.id.content, tradeRootFragment);
        fragmentTransaction.commit();


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        showWhichView();
    }


    public void showWhichView() {

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        if (myApplication.getTradeEntity().isHasLogin()) {
            //已经登录,进入交易首页

            fragmentTransaction.show(tradeRootFragment);

            fragmentTransaction.hide(loginFragment);

        } else {
            //未登录,进入登录页面
            fragmentTransaction.show(loginFragment);

            fragmentTransaction.hide(tradeRootFragment);
        }
        fragmentTransaction.commit();

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        showWhichView();

        super.onHiddenChanged(hidden);
    }

    public TradeRootFragment getTradeRootFragment() {
        return tradeRootFragment;
    }
}
