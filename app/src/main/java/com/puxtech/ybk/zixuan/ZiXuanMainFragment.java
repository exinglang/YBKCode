package com.puxtech.ybk.zixuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puxtech.ybk.BaseFragment;
import com.puxtech.ybk.R;

/**
 * Created by fanshuo on 16/4/20.
 */
public class ZiXuanMainFragment extends BaseFragment {

    public static Fragment getFragment(){
        ZiXuanMainFragment fragment = new ZiXuanMainFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.zixuan_fragment_main,container,false);

        return view;
    }

}
