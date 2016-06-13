package com.puxtech.ybk.jiaoyi.page;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.puxtech.ybk.BaseFragment;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowActionCheDanAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.entitydata.ThreeRowData;
import com.puxtech.ybk.jiaoyi.entitydata.TodayOrderData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerQuery;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerTrade;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.TodayOrderResponseData;
import com.puxtech.ybk.jiaoyi.view.TradePromptDialog;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class TradeCheDan extends BaseTradeFragment {
    View view;
    SwipeRefreshLayout srl_main;
    public static final int REQUEST_TYPE_INIT = 1;
    public static final int REQUEST_TYPE_CHE_DAN = 2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trade_che_dan, container, false);
        initWidget();
        request(REQUEST_TYPE_INIT, null, null);
        return view;
    }

    ListView listview;

    private void initWidget() {
        listview = (ListView) view.findViewById(R.id.listview);
        srl_main = (SwipeRefreshLayout) view.findViewById(R.id.srl_main);
        srl_main.setColorSchemeResources(R.color.red, R.color.black, R.color.hangqing_list_row_header_text_blue);
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                request(REQUEST_TYPE_INIT, null, null);
            }
        });

    }

    //查询今日订单
    public void request(final int requestType, final String orderId, final String commodityId) {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            BaseResponseDataTrade responseData;
//            Dialog dialog;

            protected void onPreExecute() {
//                dialog = ActivityUtils.showLoadingProgressDialog(context);
                super.onPreExecute();
            }

            protected Boolean doInBackground(Void... params) {
                try {

                    if (requestType == REQUEST_TYPE_INIT) {
                        HttpManagerQuery httpManager = new HttpManagerQuery(context);
                        responseData = httpManager.requestTodayOrder();

                    } else if (requestType == REQUEST_TYPE_CHE_DAN) {
                        HttpManagerTrade httpManager = new HttpManagerTrade(context);
                        responseData = httpManager.requestCheDan(orderId, commodityId);

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
//                dialog.dismiss();

                if (0 == responseData.getRetCode()) {
                    if (requestType == REQUEST_TYPE_INIT) {

                        ArrayList<TodayOrderData> dataArrayList = ((TodayOrderResponseData) responseData).getDataArrayList();

                        ArrayList<TodayOrderData> filterList = new ArrayList<>();
                        for (TodayOrderData entity : dataArrayList) {


                            if (entity.getSTATUS().equals("1") || entity.getSTATUS().equals("4")) {

                                filterList.add(entity);
                            } else {

                                continue;
                            }

                        }

                        final ThreeRowActionCheDanAdapter adapter = new ThreeRowActionCheDanAdapter(context, TradeCheDan.this, filterList);
                        listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                TodayOrderData item = adapter.getItem(position);
                                LinkedHashMap<String, String> map=TradeHelper.getCheDanDetailMap(context,item);
                                TradePromptDialog tradePromptDialog = new TradePromptDialog(context);
                                tradePromptDialog.getClickDetail(map);
                            }
                        });

                    } else if (requestType == REQUEST_TYPE_CHE_DAN) {
                        ActivityUtils.showCenterToast(context, "撤销成功", Toast.LENGTH_SHORT);

                        request(REQUEST_TYPE_INIT, null, null);
                    }

                } else {


                    ActivityUtils.showCenterToast(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")", Toast.LENGTH_SHORT);
                }
                srl_main.setRefreshing(false);
            }
        });
    }

}
