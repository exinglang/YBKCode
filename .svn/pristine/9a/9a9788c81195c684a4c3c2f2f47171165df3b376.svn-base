package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.jiaoyi.entitydata.TiHuoOrderData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class TiHuoOrderResponseData extends BaseResponseDataTrade {

//    "ORDER_NO": 1101,
//            "COMMODITY_ID": "100003",
//            "COMMODITY_NAME": "大团结",
//            "HOUSE_ID": "1",
//            "QTY": 1,
//            "DELIVERY_DATE": 1449810026000,
//            "DELIVERY_PRICE": 1.00,
//            "STATUS": 0,
//            "TIME": 1449810026000,
//            " FROZEN_DELIVERY_FEE ": 200


    private static final String TAG_ORDER_NO="ORDER_NO";
          private static final String TAG_COMMODITY_ID="COMMODITY_ID";
          private static final String TAG_COMMODITY_NAME="COMMODITY_NAME";
          private static final String TAG_QTY="QTY";
          private static final String TAG_DELIVERY_DATE="DELIVERY_DATE";
          private static final String TAG_DELIVERY_PRICE="DELIVERY_PRICE";
          private static final String TAG_STATUS="STATUS";
          private static final String TAG_TIME="TIME";
          private static final String TAG_FROZEN_DELIVERY_FEE="FROZEN_DELIVERY_FEE";


    ArrayList<TiHuoOrderData> dataArrayList;


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


                TiHuoOrderData data = new TiHuoOrderData();

//                private static final String TAG_ORDER_NO="ORDER_NO";
//                private static final String TAG_COMMODITY_ID="COMMODITY_ID";
//                private static final String TAG_COMMODITY_NAME="COMMODITY_NAME";
//                private static final String TAG_HOUSE_ID="HOUSE_ID";
//                private static final String TAG_QTY="QTY";
//                private static final String TAG_DELIVERY_DATE="DELIVERY_DATE";
//                private static final String TAG_DELIVERY_PRICE="DELIVERY_PRICE";
//                private static final String TAG_STATUS="STATUS";


                data.setORDER_NO(rec.getString(TAG_ORDER_NO));
                data.setCOMMODITY_ID(rec.getString(TAG_COMMODITY_ID));
                data.setCOMMODITY_NAME(rec.getString(TAG_COMMODITY_NAME));
                data.setQTY(rec.getString(TAG_QTY));

                data.setDELIVERY_DATE(ActivityUtils.getRealDataFixDay(rec.getString(TAG_DELIVERY_DATE)));
                data.setDELIVERY_PRICE(rec.getString(TAG_DELIVERY_PRICE));



                data.setSTATUS(rec.getString(TAG_STATUS));
                String state;
//                0 待审核
//                1 待提货
//                2 已提货
//                10 已撤销


                switch (Integer.valueOf(rec.getString(TAG_STATUS))) {
                    case 0:
                        state = "待审核";

                        break;
                    case 1:
                        state = "待提货";

                        break;
                    case 2:
                        state = "已提货";

                        break;

                    case 10:
                        state = "已撤销";

                        break;

                    default:
                        state = "未知状态";
                }
                data.setSTATUS_CH(state);

                //                private static final String TAG_TIME="TIME";
//                private static final String TAG_FROZEN_DELIVERY_FEE="FROZEN_DELIVERY_FEE";
                data.setTIME(ActivityUtils.getRealData(rec.getString(TAG_TIME)));
                data.setFROZEN_DELIVERY_FEE(rec.getString(TAG_FROZEN_DELIVERY_FEE));


                dataArrayList.add(data);
            }
//            myApplication.getTradeEntity().getTradeData().setHoldDetailDataList(holdDetailDataList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<TiHuoOrderData> getDataArrayList() {
        return dataArrayList;
    }
}
