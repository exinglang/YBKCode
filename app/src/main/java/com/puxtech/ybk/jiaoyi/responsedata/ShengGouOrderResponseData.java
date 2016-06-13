package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.jiaoyi.entitydata.ShengGouOrderData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class ShengGouOrderResponseData extends BaseResponseDataTrade {

    private static final String TAG_ORDER_NO = "ORDER_NO";
    private static final String TAG_PLAN_NO = "PLAN_NO";
    private static final String TAG_COMMODITY_ID = "COMMODITY_ID";
    private static final String TAG_COMMODITY_NAME = "COMMODITY_NAME";
    private static final String TAG_STATUS = "STATUS";
    private static final String TAG_QUANTITY = "QUANTITY";
    private static final String TAG_UNTRADEQTY = "UNTRADEQTY";
    private static final String TAG_ORDERTIME = "ORDERTIME";
    private static final String TAG_PRICE = "PRICE";
    private static final String TAG_TRADERID = "TRADERID";
    private static final String TAG_WITHDRAWTIME = "WITHDRAWTIME";
    private static final String TAG_WITHDRAWTYPE = "WITHDRAWTYPE";
    private static final String TAG_CONSIGNERID = "CONSIGNERID";
    private static final String TAG_WITHDRAWERID = "WITHDRAWERID";


    ArrayList<ShengGouOrderData> dataArrayList;


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


                ShengGouOrderData data = new ShengGouOrderData();


//                private static final String TAG_ORDER_NO="ORDER_NO";
//                private static final String TAG_PLAN_NO="PLAN_NO";
//                private static final String TAG_COMMODITY_ID="COMMODITY_ID";
//                private static final String TAG_COMMODITY_NAME="COMMODITY_NAME";
//                private static final String TAG_STATUS="STATUS";

                data.setORDER_NO(rec.getString(TAG_ORDER_NO));
                data.setPLAN_NO(rec.getString(TAG_PLAN_NO));
                data.setCOMMODITY_ID(rec.getString(TAG_COMMODITY_ID));
                data.setCOMMODITY_NAME(rec.getString(TAG_COMMODITY_NAME));

                data.setSTATUS(rec.getString(TAG_STATUS));
                String state;
//                1-已委托 2-已成交
//                3-已撤单 4-部分成交
//                5-部分成交后撤单

                switch (Integer.valueOf(rec.getString(TAG_STATUS))) {

                    case 1:
                        state = "已委托";

                        break;
                    case 2:
                        state = "已成交";

                        break;
                    case 3:
                        state = "已撤单";

                        break;
                    case 4:
                        state = "部分成交";

                        break;
                    case 5:
                        state = "部分成交后撤单";

                        break;

                    default:
                        state = "未知状态";
                }
                data.setSTATUS_CH(state);
//                private static final String TAG_QUANTITY="QUANTITY";
//                private static final String TAG_UNTRADEQTY="UNTRADEQTY";
//                private static final String TAG_ORDERTIME="ORDERTIME";
//                private static final String TAG_PRICE="PRICE";

//                private static final String TAG_TRADERID="TRADERID";
//                private static final String TAG_WITHDRAWTIME="WITHDRAWTIME";
//                private static final String TAG_WITHDRAWTYPE="WITHDRAWTYPE";
//                private static final String TAG_CONSIGNERID="CONSIGNERID";
//                private static final String TAG_WITHDRAWERID="WITHDRAWERID";

                data.setQUANTITY(rec.getString(TAG_QUANTITY));
                data.setUNTRADEQTY(rec.getString(TAG_UNTRADEQTY));
                data.setORDERTIME(ActivityUtils.getRealData(rec.getString(TAG_ORDERTIME)));
                data.setPRICE(rec.getString(TAG_PRICE));
                data.setTRADERID(rec.getString(TAG_TRADERID));
                data.setWITHDRAWTIME(rec.getString(TAG_WITHDRAWTIME));
                data.setWITHDRAWTYPE(rec.getString(TAG_WITHDRAWTYPE));
                String type;
//                1-手动撤单 2-自动撤单
//                3-闭市自动撤单
//                4持仓已评
                String withDrawType=rec.getString(TAG_WITHDRAWTYPE);

                if(withDrawType.equals("")){
                    withDrawType="0";
                }
                switch (Integer.valueOf(withDrawType)) {

                    case 1:
                        type = "已委托";

                        break;
                    case 2:
                        type = "已成交";

                        break;
                    case 3:
                        type = "已撤单";

                        break;
                    case 4:
                        type = "部分成交";

                        break;
                    case 5:
                        type = "部分成交后撤单";

                        break;

                    default:
                        type = "未知状态";
                }
                data.setWITHDRAWTYPE_CH(type);

                data.setCONSIGNERID(rec.getString(TAG_CONSIGNERID));
                data.setWITHDRAWERID(rec.getString(TAG_WITHDRAWERID));

                dataArrayList.add(data);
            }
//            myApplication.getTradeEntity().getTradeData().setHoldDetailDataList(holdDetailDataList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<ShengGouOrderData> getDataArrayList() {
        return dataArrayList;
    }
}
