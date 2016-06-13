package com.puxtech.ybk.jiaoyi.page;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.hangqing.HangQingData;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.GetPriceForCommodityRepBody;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.WuDangBuy;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.WuDangSell;
import com.puxtech.ybk.hangqing.jsondata.getpriceformcommodities.CommodityPrice;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.ActCodeAdapter;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowHoldDetailAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.CommodityData;
import com.puxtech.ybk.jiaoyi.entitydata.CommodityFeeData;
import com.puxtech.ybk.jiaoyi.entitydata.FundInfoData;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerQuery;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerTrade;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;


public class TradeMai extends BaseTradeFragment {
    View mView;
    protected static final int MAI_RU = 1;
    protected static final int MAI_CHU = 2;

    protected static final int XIA_DAN = 3;
    protected static final int SHUA_XIN = 4;
    protected int MAI_TYPE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_trade_mai_ru, container, false);
        initWidget();
        initListView();

        // 注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(HangQingData.ACTION_PRICE_FOR_COMMODITY);
        WuDangReceiver receiver = new WuDangReceiver();
      context.registerReceiver(receiver, filter);
        return mView;
    }

    EditText et_price, et_number;
    AutoCompleteTextView act_code;
    ImageView img_code_clear, img_jian, img_jia;
    TextView tv_name, tv_kemai, tv_number_1, tv_number_2, tv_number_3, tv_number_4;
    Button bt_commit;
    TextView sell5_price_tv, sell5_amount_tv, sell4_price_tv, sell4_amount_tv, sell3_price_tv, sell3_amount_tv, sell2_price_tv, sell2_amount_tv,
            sell1_price_tv, sell1_amount_tv,
            buy5_price_tv, buy5_amount_tv, buy4_price_tv, buy4_amount_tv, buy3_price_tv, buy3_amount_tv, buy2_price_tv, buy2_amount_tv, buy1_price_tv, buy1_amount_tv;
    TextView tv_zhangtingjia, tv_dietingjia, kemai;
    ArrayList<TextView> wuDangTextPriceViewList;
    ArrayList<TextView> wuDangTextAmountViewList;

    protected void initWidget() {
        sell5_price_tv = (TextView) mView.findViewById(R.id.sell5_price_tv);
        sell5_amount_tv = (TextView) mView.findViewById(R.id.sell5_amount_tv);
        sell4_price_tv = (TextView) mView.findViewById(R.id.sell4_price_tv);
        sell4_amount_tv = (TextView) mView.findViewById(R.id.sell4_amount_tv);
        sell3_price_tv = (TextView) mView.findViewById(R.id.sell3_price_tv);
        sell3_amount_tv = (TextView) mView.findViewById(R.id.sell3_amount_tv);
        sell2_price_tv = (TextView) mView.findViewById(R.id.sell2_price_tv);
        sell2_amount_tv = (TextView) mView.findViewById(R.id.sell2_amount_tv);
        sell1_price_tv = (TextView) mView.findViewById(R.id.sell1_price_tv);
        sell1_amount_tv = (TextView) mView.findViewById(R.id.sell1_amount_tv);
        buy5_price_tv = (TextView) mView.findViewById(R.id.buy5_price_tv);
        buy5_amount_tv = (TextView) mView.findViewById(R.id.buy5_amount_tv);
        buy4_price_tv = (TextView) mView.findViewById(R.id.buy4_price_tv);
        buy4_amount_tv = (TextView) mView.findViewById(R.id.buy4_amount_tv);
        buy3_price_tv = (TextView) mView.findViewById(R.id.buy3_price_tv);
        buy3_amount_tv = (TextView) mView.findViewById(R.id.buy3_amount_tv);
        buy2_price_tv = (TextView) mView.findViewById(R.id.buy2_price_tv);
        buy2_amount_tv = (TextView) mView.findViewById(R.id.buy2_amount_tv);
        buy1_price_tv = (TextView) mView.findViewById(R.id.buy1_price_tv);
        buy1_amount_tv = (TextView) mView.findViewById(R.id.buy1_amount_tv);

        wuDangTextPriceViewList = new ArrayList<>();
        wuDangTextPriceViewList.add(sell1_price_tv);
        wuDangTextPriceViewList.add(sell2_price_tv);
        wuDangTextPriceViewList.add(sell3_price_tv);
        wuDangTextPriceViewList.add(sell4_price_tv);
        wuDangTextPriceViewList.add(sell5_price_tv);
        wuDangTextPriceViewList.add(buy1_price_tv);
        wuDangTextPriceViewList.add(buy2_price_tv);
        wuDangTextPriceViewList.add(buy3_price_tv);
        wuDangTextPriceViewList.add(buy4_price_tv);
        wuDangTextPriceViewList.add(buy5_price_tv);
        wuDangTextAmountViewList = new ArrayList<>();
        wuDangTextAmountViewList.add(sell1_amount_tv);
        wuDangTextAmountViewList.add(sell2_amount_tv);
        wuDangTextAmountViewList.add(sell3_amount_tv);
        wuDangTextAmountViewList.add(sell4_amount_tv);
        wuDangTextAmountViewList.add(sell5_amount_tv);
        wuDangTextAmountViewList.add(buy1_amount_tv);
        wuDangTextAmountViewList.add(buy2_amount_tv);
        wuDangTextAmountViewList.add(buy3_amount_tv);
        wuDangTextAmountViewList.add(buy4_amount_tv);
        wuDangTextAmountViewList.add(buy5_amount_tv);


        tv_zhangtingjia = (TextView) mView.findViewById(R.id.tv_zhangtingjia);
        tv_dietingjia = (TextView) mView.findViewById(R.id.tv_dietingjia);


        act_code = (AutoCompleteTextView) mView.findViewById(R.id.act_code);
        et_price = (EditText) mView.findViewById(R.id.et_price);
        et_number = (EditText) mView.findViewById(R.id.et_number);
        img_code_clear = (ImageView) mView.findViewById(R.id.img_code_clear);
        img_jian = (ImageView) mView.findViewById(R.id.img_jian);
        img_jia = (ImageView) mView.findViewById(R.id.img_jia);
        tv_name = (TextView) mView.findViewById(R.id.tv_name);
        tv_kemai = (TextView) mView.findViewById(R.id.tv_kemai);
        bt_commit = (Button) mView.findViewById(R.id.bt_commit);
        tv_number_1 = (TextView) mView.findViewById(R.id.tv_number_1);
        tv_number_2 = (TextView) mView.findViewById(R.id.tv_number_2);
        tv_number_3 = (TextView) mView.findViewById(R.id.tv_number_3);
        tv_number_4 = (TextView) mView.findViewById(R.id.tv_number_4);
        kemai = (TextView) mView.findViewById(R.id.kemai);

        ActivityUtils.setEditTextSoftInputType(this, context,mView, act_code, 2);
        ActivityUtils.setEditTextSoftInputType(this, context, mView,et_price, 2);
        ActivityUtils.setEditTextSoftInputType(this, context,mView, et_number, 2);


        bt_commit.setText(MAI_TYPE == MAI_RU ? "买入" : "卖出");

        kemai.setText(MAI_TYPE == MAI_RU ? "可买" : "可卖");
        img_code_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_code.setText("");

            }
        });
        img_jian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    et_price.setText(ActivityUtils.changeDouble(String.valueOf(Double.valueOf(et_price.getText().toString()) - 0.01)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        img_jia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    et_price.setText(ActivityUtils.changeDouble(String.valueOf(Double.valueOf(et_price.getText().toString()) + 0.01)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (act_code.getText().toString().equals("") || et_number.getText().toString().equals("") || et_price.getText().toString().equals("")) {
                    ActivityUtils.showCenterToast(context, "请检查数据", Toast.LENGTH_SHORT);
                    return;

                }
                request(XIA_DAN);
            }
        });
        initACTCode();
        tv_number_1
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        et_number.setText(getKeMaiNum(1));
                    }
                });
        //1/2
        tv_number_2
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        et_number.setText(getKeMaiNum(2));

                    }
                });
        //1/3
        tv_number_3
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        et_number.setText(getKeMaiNum(3));

                    }
                });
        //1/4
        tv_number_4
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        et_number.setText(getKeMaiNum(4));

                    }
                });
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CommodityData commodityData = TradeHelper.getCommodityDataByCode(context, act_code.getText().toString());
                setKeMaiNumber(commodityData);
            }
        });

    }



    private String getKeMaiNum(double v) {
        long number;
        try {
            int kemaiMax = Integer.valueOf(tv_kemai.getText().toString());
            number = Math.round(Math.floor(kemaiMax / v));
        } catch (Exception e) {

            e.printStackTrace();
            return "0";
        }
        if (number < 0) {
            number = 0;
        }

        return number + "";
    }

    private void request(final int type) {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            BaseResponseDataTrade responseData;
            Dialog dialog;

            protected void onPreExecute() {
                dialog = ActivityUtils.showLoadingProgressDialog(context);
                super.onPreExecute();
            }

            @SuppressWarnings("ResourceType")
            protected Boolean doInBackground(Void... params) {
                try {
                    if (type == XIA_DAN) {
                        HttpManagerTrade httpManagerTrade = new HttpManagerTrade(context);
//                    tring type,String number ,String price,String commodityId
                        responseData = httpManagerTrade.requestOrder(String.valueOf(MAI_TYPE), et_number.getText().toString(), et_price.getText().toString(), act_code.getText().toString());
                    } else if (type == SHUA_XIN) {
                        HttpManagerQuery httpManager = new HttpManagerQuery(context);

                        responseData = httpManager.requestHoldQuery();

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
                if (Constant.CODE_SUCCESS == responseData.getRetCode()) {
                    if (type == XIA_DAN) {
                        ActivityUtils.showCenterToast(context, "交易成功", Toast.LENGTH_SHORT);

                        request(SHUA_XIN);
                    } else if (type == SHUA_XIN) {
                        threeRowAdapter.setList(myApplication.getTradeEntity().getTradeData().getHoldDetailDataList());
                        threeRowAdapter.notifyDataSetChanged();
                        threeRowAdapter.notifyDataSetInvalidated();
                        CommodityData commodityData = TradeHelper.getCommodityDataByCode(context, act_code.getText().toString());
                        setKeMaiNumber(commodityData);
                    }

                } else {
                    ActivityUtils.showAlert(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")");
                }
            }


        });

    }

    private void initACTCode() {
        final ActCodeAdapter arrayAdapter = new ActCodeAdapter(context, myApplication.getTradeEntity().getTradeData().getCommodityDataList());
        act_code.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }


            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                try {
                    onACTCodeChange();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
        act_code.setDropDownHeight(height / 10 * 3);
        act_code.setAdapter(arrayAdapter);
        act_code.setThreshold(0);

    }

    private void onACTCodeChange() {
        clearWidget();

        try {
            myApplication.getHangQingData().stopRefreshHangQingDetail();

        } catch (Exception e) {
            e.printStackTrace();
        }

//                if (TradeHelper.getCommodityDataByCode(context, act_code.getText().toString()) == null || act_code.getText().toString().equals("")) {
//
//                    try {
//                        myApplication.getHangQingData().stopRefreshHangQingDetail();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
        CommodityData commodityData = TradeHelper.getCommodityDataByCode(context, act_code.getText().toString());
        selectedCommodity(commodityData);

//                }


    }

    ThreeRowHoldDetailAdapter threeRowAdapter;
    ListView listView;

    private void initListView() {
        listView = (ListView) mView.findViewById(R.id.listview);
        threeRowAdapter = new ThreeRowHoldDetailAdapter(context, myApplication.getTradeEntity().getTradeData().getHoldDetailDataList());
        listView.setAdapter(threeRowAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoldDetailData item = threeRowAdapter.getItem(position);
                act_code.setText(item.getCOMMODITYID());
//                LinkedHashMap<String, String> map = TradeHelper.getChiCangDetailMap(context, item);
//                TradePromptDialog tradePromptDialog = new TradePromptDialog(context);
//                tradePromptDialog.getClickDetail(map);
            }
        });
    }

    protected void clearWidget() {

        resetWuDangData();
        tv_name.setText("");
        et_price.setText("");
        tv_kemai.setText("");

    }

    protected void selectedCommodity(CommodityData commodityData) {

        tv_name.setText(commodityData.getName());
        (mView.findViewById(R.id.keyboard_view)).setVisibility(View.GONE);
//        act_code.set
        try {
            CommodityPrice cp = myApplication.getHangQingData().getCommodityPriceByCommodityCode(commodityData.getCode());
            et_price.setText(cp.getZuiXinJia());

//            } else {
//                et_price.setText(ActivityUtils.changeDouble(String.valueOf(myApplication.getHangQingData().getCommodityPriceByCommodityCode(commodityData.getCode()).getLatp())));
//
//
//            }
            try {
                myApplication.getHangQingData().stopRefreshHangQingDetail();
            } catch (Exception e) {
                e.printStackTrace();
                ;

            }
            myApplication.getHangQingData().startRefreshHangQingDetail(commodityData.getCode());

        } catch (Exception e) {
            e.printStackTrace();
        }

        setKeMaiNumber(commodityData);


    }

    private void setKeMaiNumber(CommodityData commodityData) {
        try {
            tv_kemai.setText(getMaxKeMai(commodityData));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //计算最大可买/可卖
    private String getMaxKeMai(CommodityData commodityData) {
        int keSell = 0;
        ArrayList<HoldDetailData> holdDetailDataList = myApplication.getTradeEntity().getTradeData().getHoldDetailDataList();
        for (HoldDetailData data : holdDetailDataList) {
            if (data.getCOMMODITYID().equals(commodityData.getCode())) {
                keSell = Integer.valueOf(data.getHOLDQTY()) - Integer.valueOf(data.getFROZENQTY());
            }
        }
        String keBuy = "0";
        ArrayList<CommodityFeeData> dataList = myApplication.getTradeEntity().getTradeData().getCommodityFeeDataList();
        FundInfoData fundInfoData = myApplication.getTradeEntity().getOtherData().getFundInfoData();
        double keyongzijin = Double.valueOf(fundInfoData.getDONEBALANCE()) - Double.valueOf(fundInfoData.getFROZENFUNDS());
        for (CommodityFeeData data : dataList) {
            if (data.getCOMMODITY_ID().equals(commodityData.getCode())&&data.getFEE_ITEM().equals("TRADEFEE")) {
                try {

                    double fee = Double.valueOf(data.getBUY_FEERATE());
                    if (data.getFEE_ALGR().equals("1")) {  //1:比例;2:固定值
//                        Math.round(Math.floor(kemaiMax * v)) ;
                        Logger.v("1" + keyongzijin + "," + et_price.getText().toString() + ","+data.getCOMMODITY_ID()+":" + fee);
                        keBuy = String.valueOf(Math.round(Math.floor((keyongzijin / (Double.valueOf(et_price.getText().toString()) * (1 + fee))))));
                    } else {
                        Logger.v("2" + keyongzijin + "," + et_price.getText().toString() + "," + fee);

                        keBuy = String.valueOf(Math.round(Math.floor((keyongzijin / (Double.valueOf(et_price.getText().toString()) + fee)))));
                    }
                } catch (Exception e) {
//                    e.printStackTrace();
                    if (MAI_TYPE == MAI_RU) {
                        return "0";
                    }
                }
            }

        }


        String result;
        if (MAI_TYPE == MAI_RU) {
            result = keBuy + "";
        } else {

            result = keSell + "";
        }

        return result;
    }


    public class WuDangReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            if(!getUserVisibleHint()){
                return;
            }
            resetWuDangData();

            if (intent.getAction().equals(HangQingData.ACTION_PRICE_FOR_COMMODITY)) {
                GetPriceForCommodityRepBody rep = (GetPriceForCommodityRepBody) intent.getSerializableExtra(HangQingData.BUNDLE_KEY_PRICE_FOR_COMMODITY);
                List<WuDangBuy> buyList = rep.getExtd().getFivd().getBuyd();
                for (int i = 0; i < buyList.size(); i++) {
                    WuDangBuy data = buyList.get(i);
                    if (i == 0) {
                        buy1_price_tv.setText(ActivityUtils.changeDouble(data.getBuyp() + ""));
                        buy1_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(buy1_price_tv, rep.getYclo(), data.getBuyp());

                    } else if (i == 1) {
                        buy2_price_tv.setText(ActivityUtils.changeDouble(data.getBuyp() + ""));
                        buy2_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(buy2_price_tv, rep.getYclo(), data.getBuyp());
                    } else if (i == 2) {
                        buy3_price_tv.setText(ActivityUtils.changeDouble(data.getBuyp() + ""));
                        buy3_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(buy3_price_tv, rep.getYclo(), data.getBuyp());

                    } else if (i == 3) {
                        buy4_price_tv.setText(ActivityUtils.changeDouble(data.getBuyp() + ""));
                        buy4_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(buy4_price_tv, rep.getYclo(), data.getBuyp());

                    } else if (i == 4) {
                        buy5_price_tv.setText(ActivityUtils.changeDouble(data.getBuyp() + ""));
                        buy5_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(buy5_price_tv, rep.getYclo(), data.getBuyp());


                    }

                }
                List<WuDangSell> sellList = rep.getExtd().getFivd().getSeld();
                for (int i = 0; i < sellList.size(); i++) {
                    WuDangSell data = sellList.get(i);
                    if (i == 0) {
                        sell1_price_tv.setText(ActivityUtils.changeDouble(data.getSelp() + ""));
                        sell1_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(sell1_price_tv, rep.getYclo(), data.getSelp());

                    } else if (i == 1) {
                        sell2_price_tv.setText(ActivityUtils.changeDouble(data.getSelp() + ""));
                        sell2_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(sell2_price_tv, rep.getYclo(), data.getSelp());

                    } else if (i == 2) {
                        sell3_price_tv.setText(ActivityUtils.changeDouble(data.getSelp() + ""));
                        sell3_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(sell3_price_tv, rep.getYclo(), data.getSelp());

                    } else if (i == 3) {
                        sell4_price_tv.setText(ActivityUtils.changeDouble(data.getSelp() + ""));
                        sell4_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(sell4_price_tv, rep.getYclo(), data.getSelp());

                    } else if (i == 4) {
                        sell5_price_tv.setText(ActivityUtils.changeDouble(data.getSelp() + ""));
                        sell5_amount_tv.setText(ActivityUtils.changeDouble(data.getList() + ""));
                        setPriceColor(sell5_price_tv, rep.getYclo(), data.getSelp());

                    }

                }

                tv_zhangtingjia.setText(ActivityUtils.changeDouble((rep.getYclo() + (rep.getYclo() * 0.1) + "")));
                tv_dietingjia.setText(ActivityUtils.changeDouble((rep.getYclo() - (rep.getYclo() * 0.1) + "")));

            }
        }

    }

    /**
     * @param textview textview
     * @param zuoShou  昨收价
     * @param price    现价
     */
    //根据价格设置颜色
    private void setPriceColor(TextView textview, float zuoShou, float price) {
        if (price > zuoShou) {
            textview.setTextColor(getResources().getColor(R.color.red));
        } else if (price == zuoShou) {
            textview.setTextColor(getResources().getColor(R.color.black));
        } else if (price < zuoShou) {
            textview.setTextColor(getResources().getColor(R.color.green));
        }
    }


    //将五档数据设成--
    private void resetWuDangData() {
        for (TextView tv : wuDangTextPriceViewList) {
            tv.setText("--");
            tv.setTextColor(getResources().getColor(R.color.black));
        }

        for (TextView tv : wuDangTextAmountViewList) {
            tv.setText("--");
        }

        tv_dietingjia.setText("--");
        tv_zhangtingjia.setText("--");

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        try {
            if (isVisibleToUser) {
                threeRowAdapter.setList(myApplication.getTradeEntity().getTradeData().getHoldDetailDataList());
                threeRowAdapter.notifyDataSetChanged();
                ;
                threeRowAdapter.notifyDataSetInvalidated();
                onACTCodeChange();


            }
        } catch (Exception e) {

        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public AutoCompleteTextView getAct_code() {
        return act_code;
    }
}
