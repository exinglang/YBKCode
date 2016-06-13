package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.jiaoyi.entitydata.HistoryDealData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class HistoryDealResponseData extends BaseResponseDataTrade {


    private static final String TAG_TTLREC = "TTLREC";
    private static final String TAG_QUANTITY_SUM = "QUANTITY_SUM";
    private static final String TAG_TRADE_FUNDS_SUM = "TRADE_FUNDS_SUM";
    private static final String TAG_TRADE_FEE_SUM = "TRADE_FEE_SUM";


    private static final String TAG_TRADE_NO = "TRADE_NO";
    private static final String TAG_COMMODITY_ID = "COMMODITY_ID";
    private static final String TAG_QUANTITY = "QUANTITY";
    private static final String TAG_PRICE = "PRICE";
    private static final String TAG_TRADE_FEE = "TRADE_FEE";
    private static final String TAG_HOLD_TIME = "HOLD_TIME";
    private static final String TAG_BS_FLAG = "BS_FLAG";
    private static final String TAG_TRADE_TYPE = "TRADE_TYPE";


    ArrayList<HistoryDealData> dataArrayList;


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


                HistoryDealData data = new HistoryDealData();

//                private static final String TAG_TRADE_NO="TRADE_NO";
//                private static final String TAG_COMMODITY_ID="COMMODITY_ID";
//                private static final String TAG_QUANTITY="QUANTITY";
//                private static final String TAG_PRICE="PRICE";
//                private static final String TAG_TRADE_FEE="TRADE_FEE";
//                private static final String TAG_TRADE_TIME="TRADE_TIME";
//                private static final String TAG_BS_FLAG="BS_FLAG";
//                private static final String TAG_TRADE_TYPE="TRADE_TYPE";


                data.setTRADE_NO(rec.getString(TAG_TRADE_NO));
                data.setCOMMODITY_ID(rec.getString(TAG_COMMODITY_ID));
                data.setQUANTITY(rec.getString(TAG_QUANTITY));
                data.setPRICE(rec.getString(TAG_PRICE));
                data.setTRADE_FEE(rec.getString(TAG_TRADE_FEE));
                data.setHOLD_TIME(ActivityUtils.getRealData(rec.getString(TAG_HOLD_TIME)));
                data.setBS_FLAG(rec.getString(TAG_BS_FLAG));
                data.setBS_FLAG_CH(rec.getString(TAG_BS_FLAG).equals("1") ? Constant.MAI_RU : Constant.MAI_CHU);


                data.setTRADE_TYPE(rec.getString(TAG_TRADE_TYPE));
//                1 - 市价成交，用户下单；2 - 市价成交，电话下单；3 - 市价成交，系统下单；4 - 自动强平，系统下单；5 - 手动强平，系统下单；6 - 指价成交，系统下单；
//                7 - 指价成交，电话下单；8 - 指价成交，批量指价下单
//                9 限价成交 用户下单
//                10 限价成交 电话下单
//                11 限价成交 System
//                12 交收成交 system

                String type = "";
                switch (rec.getInt(TAG_TRADE_TYPE)) {

                    case 1:
                        type = "市价成交，用户下单";
                        break;
                    case 2:
                        type = "市价成交，电话下单";
                        break;
                    case 3:
                        type = "市价成交，系统下单";
                        break;
                    case 4:
                        type = "自动强平，系统下单";
                        break;
                    case 5:
                        type = "手动强平，系统下单";
                        break;
                    case 6:
                        type = "指价成交，系统下单";

                        //                7 - 指价成交，电话下单；8 - 指价成交，批量指价下单
//                9 限价成交 用户下单
//                10 限价成交 电话下单
//                11 限价成交 System
//                12 交收成交 system
                        break;
                    case 7:
                        type = "指价成交，电话下单";
                        break;
                    case 8:
                        type = "指价成交，批量指价下单";
                        break;
                    case 9:
                        type = "限价成交 用户下单";
                        break;
                    case 10:
                        type = "限价成交 电话下单";
                        break;
                    case 11:
                        type = "限价成交,系统下单";
                        break;
                    case 12:
                        type = "交收成交,系统下单";
                        break;
                    default:
                        type = "未知类型";
                        break;
                }
                data.setTRADE_TYPE_CH(type);

                dataArrayList.add(data);
            }
//            myApplication.getTradeEntity().getTradeData().setHoldDetailDataList(holdDetailDataList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<HistoryDealData> getDataArrayList() {
        return dataArrayList;
    }
}
