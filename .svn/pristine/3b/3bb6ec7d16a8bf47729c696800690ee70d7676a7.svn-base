package com.puxtech.ybk.jiaoyi.page;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowHoldDetailAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.view.TradePromptDialog;

import java.util.LinkedHashMap;

public class TradeChaXunShangPinChiCang extends BaseTradeActivity {


    ListView listview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.cha_xun_shangpingchicang);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, getString(R.string.chaxun_shangpinchicang), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
        initWidget();

    }

    private void initWidget() {
        listview = (ListView) findViewById(R.id.listview);
     final   ThreeRowHoldDetailAdapter threeRowAdapter = new ThreeRowHoldDetailAdapter(context, myApplication.getTradeEntity().getTradeData().getHoldDetailDataList());
        listview.setAdapter(threeRowAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoldDetailData item = threeRowAdapter.getItem(position);
                LinkedHashMap<String,String> map= TradeHelper.getChiCangDetailMap(context,item);
                TradePromptDialog tradePromptDialog = new TradePromptDialog(context);
                tradePromptDialog.getClickDetail(map);
            }
        });
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
