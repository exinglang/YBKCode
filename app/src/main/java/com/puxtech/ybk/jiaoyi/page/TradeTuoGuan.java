package com.puxtech.ybk.jiaoyi.page;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.puxtech.ybk.BaseFragment;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowActionShengGouAdapter;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowActionTuoGuanAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.ShengGouListData;
import com.puxtech.ybk.jiaoyi.entitydata.TuoGuanListData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerQuery;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerTrade;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.ShengGouListResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.TuoGuanListResponseData;
import com.puxtech.ybk.jiaoyi.view.TradePromptDialog;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class TradeTuoGuan extends BaseTradeFragment {
    View view;
    SwipeRefreshLayout srl_main;
    public static final int REQUEST_TYPE_INIT = 1;
    public static final int REQUEST_TYPE_TUO_GUAN = 2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trade_tuo_guan, container, false);
        initWidget();
        request(REQUEST_TYPE_INIT, null);
        return view;
    }

    ListView listview;

    private void initWidget() {
        listview = (ListView) view.findViewById(R.id.listview);
        srl_main = (SwipeRefreshLayout) view.findViewById(R.id.srl_main);
        srl_main.setColorSchemeResources(R.color.red, R.color.black, R.color.hangqing_list_row_header_text_blue);
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                request(REQUEST_TYPE_INIT, null);
            }
        });
    }

    //查询今日订单
    public void request(final int requestType, final TuoGuanListData tuoGuanListData) {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            BaseResponseDataTrade responseData;

            //            Dialog dialog;
            protected void onPreExecute() {
//                dialog = ActivityUtils.showLoadingProgressDialog(context);
                srl_main.setRefreshing(false);

                super.onPreExecute();
            }

            protected Boolean doInBackground(Void... params) {
                try {
                    HttpManagerQuery httpManagerQuery = new HttpManagerQuery(context);
                    HttpManagerTrade httpManagerTrade = new HttpManagerTrade(context);

                    if (requestType == REQUEST_TYPE_INIT) {
                        responseData = httpManagerQuery.requestTuoGuanList();
                    } else if (requestType == REQUEST_TYPE_TUO_GUAN) {
                        responseData = httpManagerTrade.requestTuoGuan(tuoGuanListData);
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

                        ArrayList<TuoGuanListData> dataArrayList = ((TuoGuanListResponseData) responseData).getDataArrayList();


                        final ThreeRowActionTuoGuanAdapter adapter = new ThreeRowActionTuoGuanAdapter(context, TradeTuoGuan.this, dataArrayList);
                        listview.setAdapter(adapter);

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                TuoGuanListData item = adapter.getItem(position);
                                LinkedHashMap<String, String> map = new LinkedHashMap<>();
//                                商品ID	COMMODITY_ID
//                                商品名称	COMMODITY_NAME
//                                托管计划NO	PLAN_NO
//                                托管状态	STATUS
//                                计划类型
//                                        TYPE


                                map.put("商品名称", TradeHelper.getCommodityDataByCode(context, item.getCOMMODITY_ID()).getName());
                                map.put("商品代码", item.getCOMMODITY_ID());
                                map.put("托管计划编号", item.getPLAN_NO());

                                map.put("托管状态", item.getSTATUS_CH());
                                map.put("计划类型", item.getTYPE_CH());
                                map.put("托管申请开始日期", item.getAPPLY_ST());
                                map.put("托管申请结束日期", item.getAPPLY_END());
                                map.put("托管开始日期", item.getENTRUST_ST());
                                map.put("托管结束日期", item.getENTRUST_END());
//                                map.put("发行数量", item.getSTATUS());
//                                map.put("限售数量", item.getQUANTITY());
//                                map.put("处理状态", item.getUNTRADEQTY());
//                                map.put("申请时间", item.getORDERTIME());

                                TradePromptDialog tradePromptDialog = new TradePromptDialog(context);
                                tradePromptDialog.getClickDetail(map);
                            }
                        });
                    } else if (requestType == REQUEST_TYPE_TUO_GUAN) {
                        ActivityUtils.showCenterToast(context, "托管成功", Toast.LENGTH_SHORT);

                        request(REQUEST_TYPE_INIT, null);
                    }

                } else {


                    ActivityUtils.showCenterToast(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")", Toast.LENGTH_SHORT);
                }
                srl_main.setRefreshing(false);
            }
        });
    }

    //弹出申购框
    public void popWindows(final TuoGuanListData item) {
        StringBuffer sb = new StringBuffer();
//        商品ID	COMMODITY_ID
//        商品名称	COMMODITY_NAME
//        托管计划NO	PLAN_NO
//        托管状态	STATUS
//        计划类型
//                TYPE
//        托管申请开始日期	APPLY_ST
//        托管申请结束日期	APPLY_END
//        托管开始日期	ENTRUST_ST
//        托管结束日期	ENTRUST_END
        sb.append("商品代码                :  " + item.getCOMMODITY_ID()).append(Constant.NEW_LINE);
        sb.append("商品名称                :  " + item.getCOMMODITY_NAME()).append(Constant.NEW_LINE);
        sb.append("托管计划编码        :  " + item.getPLAN_NO()).append(Constant.NEW_LINE);
        sb.append("托管状态                :  " + item.getSTATUS()).append(Constant.NEW_LINE);
        sb.append("托管申请开始日期:  " + item.getENTRUST_ST()).append(Constant.NEW_LINE);
        sb.append("托管申请结束日期:  " + item.getENTRUST_END()).append(Constant.NEW_LINE);
        sb.append("托管开始日期        :  " + item.getAPPLY_ST()).append(Constant.NEW_LINE);
        sb.append("托管结束日期        :  " + item.getAPPLY_END()).append(Constant.NEW_LINE);
        final TradePromptDialog tradePromptDialog = new TradePromptDialog(context);
        Button bt_ok = tradePromptDialog.getTradePromptDialog("",
                sb.toString(),true);

        bt_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                item.setNumber(tradePromptDialog.getEt_number().getText().toString());
                tradePromptDialog.getPopwindow().dismiss();
                request(REQUEST_TYPE_TUO_GUAN, item);

            }
        });

    }
}
