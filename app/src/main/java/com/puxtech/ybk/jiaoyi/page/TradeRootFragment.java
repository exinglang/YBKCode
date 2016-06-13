package com.puxtech.ybk.jiaoyi.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puxtech.ybk.BaseFragment;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.adapter.TradeRootFragmentViewPagerAdapter;
import com.puxtech.ybk.jiaoyi.view.BaseViewPager;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class TradeRootFragment extends BaseTradeFragment {
    View fView;


    public TradeRootFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextSizeHelper.setTextSize(context);

        context = getActivity();
        fView = inflater.inflate(R.layout.fragment_trade_root, container, false);
        initViewPager();
        (fView.findViewById(R.id.tv_kefu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(context,MoreTrade.class));
            }
        });



        return fView;
    }


    /**
     * initViewPager
     */
    private void initViewPager() {
        ArrayList<Fragment> fgList = getFragmentList();
        TabLayout mTabLayout = (TabLayout) fView.findViewById(R.id.tl);

        //加载四个页面
        FragmentPagerAdapter adapter = new TradeRootFragmentViewPagerAdapter(getChildFragmentManager(), fgList);
        pager = (BaseViewPager) fView.findViewById(R.id.pager);
        pager.setAdapter(adapter);
//       pager.setOffscreenPageLimit(6);
        mTabLayout.setupWithViewPager(pager);//将TabLayout和ViewPager关联起来。
//        mTabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
    }

    public BaseViewPager getPager() {
        return pager;
    }

    BaseViewPager pager;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;
    Fragment fragment5;
    Fragment fragment6;
    Fragment fragment7;
    Fragment fragment8;
//    Fragment fragment9;
    // Fragment fragment4;

    /**
     * 将framgment放到List中
     *
     * @return 放好的LIST
     */
    private ArrayList<Fragment> getFragmentList() {
        ArrayList<Fragment> fgList = new ArrayList<>();

        fragment1 = new TradeChiCang();
        fragment2 = new TradeMaiRu();
        fragment3 = new TradeMaiChu();
        fragment4 = new TradeCheDan();
        fragment5 = new TradeShengGou();
        fragment6= new TradeTuoGuan();
        fragment7  = new TradeTiHuo();

        fragment8  = new TradeChaXun();
//        fragment9    = new TradeQiTa();


        //   fragment4  =  new PanFragmentChengJiaoMingXi(context);
        fgList.add(fragment1);
        fgList.add(fragment2);
        fgList.add(fragment3);
        fgList.add(fragment4);
        fgList.add(fragment5);
        fgList.add(fragment6);
        fgList.add(fragment7);
        fgList.add(fragment8);
//        fgList.add(fragment9);
        //  fgList.add(fragment4);
        return fgList;
    }

    public Fragment getFragment2() {
        return fragment2;
    }

    public Fragment getFragment3() {
        return fragment3;
    }
}
