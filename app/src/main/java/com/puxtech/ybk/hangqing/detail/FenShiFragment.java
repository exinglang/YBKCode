package com.puxtech.ybk.hangqing.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puxtech.ybk.R;
import com.puxtech.ybk.hangqing.BaseHangQingFragment;
import com.puxtech.ybk.hangqing.charts.FenShiChartModel;
import com.puxtech.ybk.hangqing.charts.FenShiView;
import com.puxtech.ybk.hangqing.charts.TradeTimeManager;

/**
 * Created by fanshuo on 16/5/5.
 */
public class FenShiFragment extends BaseHangQingFragment {

    private CustomViewPager parentViewPager;
    private FenShiView fenShiView;

    public static FenShiFragment getFragment(){
        FenShiFragment fragment = new FenShiFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hangqing_fragment_fenshi, container, false);
        fenShiView = (FenShiView) view.findViewById(R.id.fenshi_view);
        fenShiView.setOnShowListener(new FenShiView.OnShowListener() {
            @Override
            public void onShow() {
                parentViewPager.setCanScroll(false);
            }

            @Override
            public void onHide() {
                parentViewPager.setCanScroll(true);
            }
        });
        fenShiView.setTradeTimeManager(new TradeTimeManager(myApplication.getHangQingData().getTradeTime().getStat(), myApplication.getHangQingData().getTradeTime().getEndt()));
        fenShiView.invalidate();
        return view;
    }

    public void onReceivedFenShiData(final FenShiChartModel fenShiChartModel){
        try {
            fenShiView.setFenShiChartModel(fenShiChartModel);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        fenShiView.calPointXY();
        fenShiView.invalidate();
    }

    public void onReceivedNewFenShiData(FenShiChartModel fenShiChartModel){
        fenShiView.addNewPoints(fenShiChartModel.getPoints());
        fenShiView.invalidate();
    }

    public void setParentViewPager(CustomViewPager parentViewPager) {
        this.parentViewPager = parentViewPager;
    }
}
