package com.puxtech.ybk.jiaoyi.page;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowActionShengGouOrderAdapter;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowDangRiChengJiaoAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.HistoryDealData;
import com.puxtech.ybk.jiaoyi.entitydata.ShengGouOrderData;
import com.puxtech.ybk.jiaoyi.entitydata.TodayDealData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerQuery;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerTrade;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.ShengGouOrderResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.TodayDealResponseData;
import com.puxtech.ybk.jiaoyi.view.TradePromptDialog;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TradeChaXunShengGouChaXun extends BaseTradeActivity {

    public static final int REQUEST_TYPE_INIT = 1;
    public static final int REQUEST_TYPE_CHE_DAN = 2;
    SwipeRefreshLayout srl_main;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.fragment_trade_sheng_gou_cha_xun);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, getString(R.string.chaxun_shenggouchaxun), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
        initWidget();
        request(REQUEST_TYPE_INIT, null);

    }

    ListView listview;

    private void initWidget() {
        listview = (ListView) findViewById(R.id.listview);
        srl_main = (SwipeRefreshLayout) findViewById(R.id.srl_main);
        srl_main.setColorSchemeResources(R.color.red, R.color.black, R.color.hangqing_list_row_header_text_blue);
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                request(REQUEST_TYPE_INIT, null);
            }
        });
    }

    //查询今日订单
    public void request(final int requestType,final String orderId) {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            BaseResponseDataTrade responseData;
            Dialog dialog;

            protected void onPreExecute() {
                dialog = ActivityUtils.showLoadingProgressDialog(context);
                super.onPreExecute();
            }

            protected Boolean doInBackground(Void... params) {
                try {
                    if (requestType == REQUEST_TYPE_INIT) {
                        HttpManagerQuery httpManager = new HttpManagerQuery(context);
                        responseData = httpManager.requestShengGouOrder();
                    } else if (requestType == REQUEST_TYPE_CHE_DAN) {
                        HttpManagerTrade httpManagerTrade = new HttpManagerTrade(context);
                        responseData = httpManagerTrade.requestShengGouOrderCancel(orderId);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    responseData.setRetCode(Constant.CODE_ERROR_UNKNOW);
                    responseData.setRetMessage(Constant.MESSAGE_ERROR_UNKNOW);
                }
                return true;
            }

            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                dialog.dismiss();

                if (0 == responseData.getRetCode()) {

                    if (requestType == REQUEST_TYPE_INIT) {

                        ArrayList<ShengGouOrderData> dataArrayList = ((ShengGouOrderResponseData) responseData).getDataArrayList();


                        if(dataArrayList.size()==0){
                            ActivityUtils.showCenterToast(context,Constant.NO_RECORD, Toast.LENGTH_SHORT);
                            return;
                        }
                        final ThreeRowActionShengGouOrderAdapter adapter = new ThreeRowActionShengGouOrderAdapter(context, TradeChaXunShengGouChaXun.this, dataArrayList);
                        listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ShengGouOrderData item = adapter.getItem(position);
                                LinkedHashMap<String, String> map = TradeHelper.getShengGouOrderDetailMap(item);
                                TradePromptDialog tradePromptDialog = new TradePromptDialog(context);
                                tradePromptDialog.getClickDetail(map);
                            }
                        });
                    } else if (requestType == REQUEST_TYPE_CHE_DAN) {
                        ActivityUtils.showCenterToast(context, "撤销成功", Toast.LENGTH_SHORT);

                        request(REQUEST_TYPE_INIT, null);


                    }

                } else {


                    ActivityUtils.showCenterToast(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")", Toast.LENGTH_SHORT);
                }
                srl_main.setRefreshing(false);
            }
        });
    }

}
