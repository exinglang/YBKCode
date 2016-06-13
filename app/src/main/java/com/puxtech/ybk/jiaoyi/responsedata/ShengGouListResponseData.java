package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.jiaoyi.entitydata.ShengGouListData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class ShengGouListResponseData extends BaseResponseDataTrade {

//    COMMODITY_ID	字符串
//    COMMODITY_NAME	字符串
//    PLAN_NO	字符串
//    STATUS	数字	发行状态
//    0 编辑中
//    1 开始执行
//    2 申购中
//    3 申购结束
//    4 抽签完成
//    5 发行完成
//    10 已取消发行
//    PRICE	数字
//    PURCHASE_ST	数字
//    PURCHASE_END	数字
//    LOTTERY_TIME	数字


    private static final String TAG_COMMODITY_ID = "COMMODITY_ID";
    private static final String TAG_COMMODITY_NAME = "COMMODITY_NAME";
    private static final String TAG_PLAN_NO = "PLAN_NO";
    private static final String TAG_STATUS = "STATUS";
    private static final String TAG_PRICE = "PRICE";
    private static final String TAG_PURCHASE_ST = "PURCHASE_ST";
    private static final String TAG_PURCHASE_END = "PURCHASE_END";
    private static final String TAG_LOTTERY_TIME = "LOTTERY_TIME";


    ArrayList<ShengGouListData> dataArrayList;


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


                ShengGouListData data = new ShengGouListData();


//                private static final String TAG_COMMODITY_ID = "COMMODITY_ID";
//                private static final String TAG_COMMODITY_NAME = "COMMODITY_NAME";
//                private static final String TAG_PLAN_NO = "PLAN_NO";
//                private static final String TAG_STATUS = "STATUS";
//                private static final String TAG_PRICE = "PRICE";
//                private static final String TAG_PURCHASE_ST = "PURCHASE_ST";
//                private static final String TAG_PURCHASE_END = "PURCHASE_END";
//                private static final String TAG_LOTTERY_TIME = "LOTTERY_TIME";

                data.setCOMMODITY_ID(rec.getString(TAG_COMMODITY_ID));
                data.setCOMMODITY_NAME(rec.getString(TAG_COMMODITY_NAME));
                data.setPLAN_NO(rec.getString(TAG_PLAN_NO));
                data.setSTATUS(rec.getString(TAG_STATUS));
                String state;
                switch (Integer.valueOf(rec.getString(TAG_STATUS))) {
                    case 0:
                        state = "编辑中";
                        break;
                    case 1:
                        state = "开始执行";

                        break;
                    case 2:
                        state = "申购中";

                        break;
                    case 3:
                        state = "申购结束";

                        break;
                    case 4:
                        state = "抽签完成";

                        break;
                    case 5:
                        state = "发行完成";

                        break;
                    case 10:
                        state = "已取消发行";

                        break;
                    default:
                        state = "未知状态";
                }
                data.setSTATUS_CH(state);


                data.setPRICE(rec.getString(TAG_PRICE));
                data.setPURCHASE_ST(ActivityUtils.getRealDataFixDay(rec.getString(TAG_PURCHASE_ST)));
                data.setPURCHASE_END(ActivityUtils.getRealDataFixDay(rec.getString(TAG_PURCHASE_END)));
                data.setLOTTERY_TIME(ActivityUtils.getRealDataFixDay(rec.getString(TAG_LOTTERY_TIME)));

                dataArrayList.add(data);
            }
//            myApplication.getTradeEntity().getTradeData().setHoldDetailDataList(holdDetailDataList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<ShengGouListData> getDataArrayList() {
        return dataArrayList;
    }
}
