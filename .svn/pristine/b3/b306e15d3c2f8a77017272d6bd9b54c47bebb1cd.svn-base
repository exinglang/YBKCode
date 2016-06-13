package com.puxtech.ybk.shouye;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puxtech.ybk.BaseFragment;
import com.puxtech.ybk.MainActivity;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.shezhi.SettingsActivity;

/**
 * Created by fanshuo on 16/4/20.
 */
public class ShouYeMainFragment extends BaseFragment {


    public static Fragment getFragment() {
        ShouYeMainFragment fragment = new ShouYeMainFragment();
        return fragment;
    }

    TextView tv_zixuan, tv_hangqing, tv_jiaoyi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextSizeHelper.setTextSize(this.getActivity());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.shouye_fragment_main, container, false);
        tv_zixuan = (TextView) view.findViewById(R.id.tv_zixuan);
        tv_hangqing = (TextView) view.findViewById(R.id.tv_hangqin);
        tv_jiaoyi = (TextView) view.findViewById(R.id.tv_jiaoyi);
        tv_zixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).updateSelectedItem(1);
            }
        });

        tv_hangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).updateSelectedItem(1);
            }
        });
        tv_jiaoyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).updateSelectedItem(2);
            }
        });
        view.findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });
        return view;
    }
}
