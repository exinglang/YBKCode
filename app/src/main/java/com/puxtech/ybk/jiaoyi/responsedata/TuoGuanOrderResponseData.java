package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.jiaoyi.entitydata.TuoGuanOrderData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class TuoGuanOrderResponseData extends BaseResponseDataTrade {

    private static final String TAG_APPLY_ID = "APPLY_ID";
    private static final String TAG_PLAN_NO = "PLAN_NO";
    private static final String TAG_COMMODITY_ID = "COMMODITY_ID";
    private static final String TAG_COMMODITY_NAME = "COMMODITY_NAME";
    private static final String TAG_HOUSE_ID = "HOUSE_ID";
    private static final String TAG_QTY = "QTY";
    private static final String TAG_IN_QTY = "IN_QTY";
    private static final String TAG_LIST_QTY = "LIST_QTY";
    private static final String TAG_ISSUE_QTY = "ISSUE_QTY";
    private static final String TAG_LIMIT_QTY = "LIMIT_QTY";
    private static final String TAG_STATUS = "STATUS";
    private static final String TAG_TIME = "TIME";


    ArrayList<TuoGuanOrderData> dataArrayList;


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


                TuoGuanOrderData data = new TuoGuanOrderData();

//
//                private static final String TAG_APPLY_ID = "APPLY_ID";
//                private static final String TAG_PLAN_NO = "PLAN_NO";
//                private static final String TAG_COMMODITY_ID = "COMMODITY_ID";
//                private static final String TAG_COMMODITY_NAME = "COMMODITY_NAME";
//                private static final String TAG_HOUSE_ID = "HOUSE_ID";
//                private static final String TAG_QTY = "QTY";
//                private static final String TAG_IN_QTY = "IN_QTY ";
//                private static final String TAG_LIST_QTY = "LIST_QTY";
//                private static final String TAG_ISSUE_QTY = "ISSUE_QTY";
//                private static final String TAG_LIMIT_QTY = "LIMIT_QTY";
//                private static final String TAG_STATUS = "STATUS";
//                private static final String TAG_TIME = "TIME";

                data.setAPPLY_ID(rec.getString(TAG_APPLY_ID));
                data.setPLAN_NO(rec.getString(TAG_PLAN_NO));
                data.setCOMMODITY_ID(rec.getString(TAG_COMMODITY_ID));
                data.setCOMMODITY_NAME(rec.getString(TAG_COMMODITY_NAME));
                data.setHOUSE_ID(rec.getString(TAG_HOUSE_ID));
                data.setQTY(rec.getString(TAG_QTY));
                data.setIN_QTY(rec.getString(TAG_IN_QTY));
                data.setLIST_QTY(rec.getString(TAG_LIST_QTY));
                data.setISSUE_QTY(rec.getString(TAG_ISSUE_QTY));
                data.setLIMIT_QTY(rec.getString(TAG_LIMIT_QTY));

                data.setSTATUS(rec.getString(TAG_STATUS));
                String state;
//                申请处理状态
//                0 已申请
//                1 已通过（等待入库）
//                2 已入库（等待挂牌）
//                3  已挂牌
//                4 已设置减持
//                5 已发行
//                10 已撤销


                switch (Integer.valueOf(rec.getString(TAG_STATUS))) {
                    case 0:
                        state = "已申请";

                        break;
                    case 1:
                        state = "已通过（等待入库）";

                        break;
                    case 2:
                        state = "已入库（等待挂牌）";

                        break;
                    case 3:
                        state = "已挂牌";

                        break;
                    case 4:
                        state = "已设置减持";

                        break;
                    case 5:
                        state = "已发行";

                        break;
                    case 10:
                        state = "已撤销";

                        break;

                    default:
                        state = "未知状态";
                }
                data.setSTATUS_CH(state);
                data.setTIME(ActivityUtils.getRealData(rec.getString(TAG_TIME)));

                dataArrayList.add(data);
            }
//            myApplication.getTradeEntity().getTradeData().setHoldDetailDataList(holdDetailDataList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<TuoGuanOrderData> getDataArrayList() {
        return dataArrayList;
    }
}
