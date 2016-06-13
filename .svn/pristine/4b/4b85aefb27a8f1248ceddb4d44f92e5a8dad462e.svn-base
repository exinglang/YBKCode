package com.puxtech.ybk.jiaoyi.page;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;

public class TradeChaXunDangRiWeiTuo extends BaseTradeActivity {


Fragment fragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.cha_xun_dangriweituo);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, getString(R.string.chaxun_dangriweituo), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
//                initWidget();
//        fragment =(Fragment) findViewById(R.id.fragment);
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.layout_fragment, new TradeCheDan());
//        fragmentTransaction.commit();


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
      Fragment  mWeixin = new  TradeCheDan();
        transaction.replace(R.id.fragment, mWeixin);
        transaction.commit();
    }


}
