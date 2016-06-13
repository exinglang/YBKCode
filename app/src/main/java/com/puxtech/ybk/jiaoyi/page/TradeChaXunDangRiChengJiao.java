package com.puxtech.ybk.jiaoyi.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;

public class TradeChaXunDangRiChengJiao extends BaseTradeActivity {


    TextView tv_tradetobank, tv_banktotrade, tv_history;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.cha_xun_dangrichengjiao);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this,getString(R.string.chaxun_dangrichengjiao), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment mWeixin = new DangRiChengJiao();
        transaction.replace(R.id.fragment, mWeixin);
        transaction.commit();

    }

    private void initWidget() {
        tv_tradetobank = (TextView) findViewById(R.id.tv_tradetobank);
        tv_banktotrade = (TextView) findViewById(R.id.tv_banktotrade);
        tv_history = (TextView) findViewById(R.id.tv_history);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return false;
    }
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_home, menu);
//        super.onCreateOptionsMenu(menu);
//    }

}
