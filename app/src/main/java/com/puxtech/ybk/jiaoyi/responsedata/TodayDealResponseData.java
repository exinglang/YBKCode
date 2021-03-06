package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.jiaoyi.entitydata.TodayDealData;
import com.puxtech.ybk.jiaoyi.entitydata.TodayOrderData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class TodayDealResponseData extends BaseResponseDataTrade {


//    "CLEAR_DATE": "结算日期，日期型，毫秒",
//            "TRADE_NO": "成交单号",
//            "COMMODITY_NAME": "商品",
//            "QUANTITY": "成交量",
//            "PRICE": "成交价",
//            "TRADE_FEE": "手续费",
//            "HOLD_TIME": "成交时间，毫秒",
//            "BS_FLAG": "买卖标志：1买；2卖",
//            "TR_T":1


//    private static final String TAG_CLEAR_DATE = "CLEAR_DATE";
    private static final String TAG_TRADE_NO = "TRADE_NO";
    private static final String TAG_COMMODITY_NAME = "COMMODITY_NAME";
    private static final String TAG_QUANTITY = "QUANTITY";
    private static final String TAG_PRICE = "PRICE";
    private static final String TAG_TRADE_FEE = "TRADE_FEE";
    private static final String TAG_TRADE_TIME = "TRADE_TIME";
    private static final String TAG_BS_FLAG = "BS_FLAG";
    private static final String TAG_TR_T = "TR_T";



    ArrayList<TodayDealData> dataArrayList;


    public void parseXML(String jsString) {
        try {
            JSONObject root = checkFail(jsString);
            if (retCode != 0) {
                return;
            }
            JSONObject body = root.getJSONObject(TAG_MMTS)
                    .getJSONObject(TAG_REP_BODY);

            int recordNumber;
            try {
                recordNumber = body.getJSONArray(TAG_RESULTLIST).length();
            } catch (JSONException e) {
                e.printStackTrace();
                recordNumber = 0;
            }
            dataArrayList = new ArrayList<>();
            for (int j = 0; j < recordNumber; j++) {

                JSONObject rec = body.getJSONArray(TAG_RESULTLIST)
                        .getJSONObject(j);


                TodayDealData data = new TodayDealData();
//                private static final String TAG_CLEAR_DATE="CLEAR_DATE";
//                private static final String TAG_TRADE_NO="TRADE_NO";
//                private static final String TAG_COMMODITY_NAME="COMMODITY_NAME";
//                private static final String TAG_QUANTITY="QUANTITY";
//                private static final String TAG_PRICE="PRICE";
//                private static final String TAG_TRADE_FEE="TRADE_FEE";
//                private static final String TAG_HOLD_TIME="HOLD_TIME";
//                private static final String TAG_BS_FLAG="BS_FLAG";
//                private static final String TAG_TR_T="TR_T";


//                data.setCLEAR_DATE(rec.getString(TAG_CLEAR_DATE));
                data.setTRADE_NO(rec.getString(TAG_TRADE_NO));
                data.setCOMMODITY_NAME(rec.getString(TAG_COMMODITY_NAME));
                data.setQUANTITY(rec.getString(TAG_QUANTITY));
                data.setPRICE(rec.getString(TAG_PRICE));
                data.setTRADE_FEE(rec.getString(TAG_TRADE_FEE));
                data.setTRADE_TIME(ActivityUtils.getRealData(rec.getString(TAG_TRADE_TIME)));
                data.setBS_FLAG(rec.getString(TAG_BS_FLAG));
                data.setBS_FLAG_CH(rec.getString(TAG_BS_FLAG).equals("1") ? Constant.MAI_RU : Constant.MAI_CHU);

//                BS_FLAG 数字 1 - 买 2 - 卖
//                TR_T 数字 1 - 市价成交，用户下单；2 - 市价成交，电话下单；3 - 市价成交，系统下单；4 - 自动强平，系统下单；5 - 手动强平，系统下单；
//                6 - 指价成交，系统下单；
//                7 - 指价成交，电话下单；8 - 指价成交，批量指价下单
                String type="";
                switch (rec.getInt(TAG_TR_T)) {

                    case 1:
                        type="市价成交，用户下单";
                        break;
                    case 2:
                        type="市价成交，电话下单";
                        break;
                    case 3:
                        type="市价成交，系统下单";
                        break;
                    case 4:
                        type="自动强平，系统下单";
                        break;
                    case 5:
                        type="手动强平，系统下单";
                        break;
                    case 6:
                        type="指价成交，系统下单";
                        break;
                    case 7:
                        type="指价成交，电话下单";
                        break;
                    case 8:
                        type="指价成交，批量指价下单";
                        break;
                  default:
                      type="未知类型";
                      break;
                }
                data.setTR_T_CH(type);
                data.setTR_T(rec.getString(TAG_TR_T));

                dataArrayList.add(data);
            }
//            myApplication.getTradeEntity().getTradeData().setHoldDetailDataList(holdDetailDataList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<TodayDealData> getDataArrayList() {
        return dataArrayList;
    }
}
