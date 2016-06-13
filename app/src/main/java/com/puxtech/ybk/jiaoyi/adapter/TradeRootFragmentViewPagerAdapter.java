package com.puxtech.ybk.jiaoyi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/12.
 */

public class TradeRootFragmentViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fgList;
    String[] CONTENT={"持仓","买入","卖出","撤单","申购","托管","提货","查询"};//title
    public TradeRootFragmentViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fgList) {
        super(fm);
        this.fgList = fgList;
    }



    @Override
    public Fragment getItem(int position) {
        return fgList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length];
    }
    @Override
    public int getCount() {
        return CONTENT.length;
    }
}

