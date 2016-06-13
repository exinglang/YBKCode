package com.puxtech.ybk.jiaoyi.page;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puxtech.ybk.BaseFragment;
import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.entitydata.CommodityData;
import com.puxtech.ybk.util.ActivityUtils;


public class TradeMaiRu extends TradeMai {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MAI_TYPE=MAI_RU;
        View view = super.onCreateView(inflater, container, savedInstanceState);
//        ActivityUtils.setEditTextSoftInputType(getActivity(), context, act_code, 2);
//        ActivityUtils.setEditTextSoftInputType(getActivity(), context, et_price, 2);
//        ActivityUtils.setEditTextSoftInputType(getActivity(), context, et_number, 2);
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden ) {

        super.onHiddenChanged(hidden);
    }
}
