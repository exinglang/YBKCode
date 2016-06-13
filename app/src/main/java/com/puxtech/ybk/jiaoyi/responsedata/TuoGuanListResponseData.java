package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.jiaoyi.entitydata.ShengGouListData;
import com.puxtech.ybk.jiaoyi.entitydata.TuoGuanListData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class TuoGuanListResponseData extends BaseResponseDataTrade {

//    商品ID	COMMODITY_ID	字符串
//    商品名称	COMMODITY_NAME	字符串
//    托管计划NO	PLAN_NO	字符串
//    托管状态	STATUS	数字	计划状态
//    0 编辑中
//    1 开始执行
//    2 托管申请期
//    3 托管申请结束
//    4 托管入库期
//    5 托管入库结束
//    6 托管计划完成
//    10 取消计划
//            计划类型
//    TYPE	数字	计划类型
//    1 普通托管
//    2 再托管
//    托管申请开始日期	APPLY_ST	数字
//    托管申请结束日期	APPLY_END	数字
//    托管开始日期	ENTRUST_ST	数字
//    托管结束日期	ENTRUST_END	数字
//            字


    private static final String TAG_COMMODITY_ID = "COMMODITY_ID";
    private static final String TAG_COMMODITY_NAME = "COMMODITY_NAME";
    private static final String TAG_PLAN_NO = "PLAN_NO";
    private static final String TAG_STATUS = "STATUS";
    private static final String TAG_TYPE = "TYPE";
    private static final String TAG_DESCRIPTION = "DESCRIPTION";
    private static final String TAG_APPLY_ST = "APPLY_ST";
    private static final String TAG_APPLY_END = "APPLY_END";
    private static final String TAG_ENTRUST_ST = "ENTRUST_ST";
    private static final String TAG_ENTRUST_END = "ENTRUST_END";


    ArrayList<TuoGuanListData> dataArrayList;


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
                TuoGuanListData data = new TuoGuanListData();
                data.setCOMMODITY_ID(rec.getString(TAG_COMMODITY_ID));
                data.setCOMMODITY_NAME(rec.getString(TAG_COMMODITY_NAME));
                data.setPLAN_NO(rec.getString(TAG_PLAN_NO));


                String status;
//                                计划状态
//                                0 编辑中
//                                1 开始执行
//                                2 托管申请期
//                                3 托管申请结束
//                                4 托管入库期
//                                5 托管入库结束
//                                6 托管计划完成
//                                10 取消计划

                switch (Integer.valueOf(rec.getString(TAG_STATUS))) {
                    case 0:
                        status = "编辑中";
                        break;
                    case 1:
                        status = "开始执行";

                        break;
                    case 2:
                        status = "托管申请期";

                        break;
                    case 3:
                        status = "托管申请结束";

                        break;
                    case 4:
                        status = "托管入库期";

                        break;
                    case 5:
                        status = "托管入库结束";

                        break;
                    case 6:
                        status = "托管计划完成";

                        break;
                    case 10:
                        status = "取消计划";

                        break;
                    default:
                        status = "未知状态";


                }
               
                data.setSTATUS_CH(status);
                data.setSTATUS(rec.getString(TAG_STATUS));
                data.setTYPE(rec.getString(TAG_TYPE));
                data.setTYPE_CH(rec.getString(TAG_TYPE).equals("1") ? "普通托管" : "再托管");
                data.setDESCRIPTION(rec.getString(TAG_DESCRIPTION));
                data.setAPPLY_ST(ActivityUtils.getRealDataFixDay(rec.getString(TAG_APPLY_ST)));
                data.setAPPLY_END(ActivityUtils.getRealDataFixDay(rec.getString(TAG_APPLY_END)));
                data.setENTRUST_ST(ActivityUtils.getRealDataFixDay(rec.getString(TAG_ENTRUST_ST)));
                data.setENTRUST_END(ActivityUtils.getRealDataFixDay(rec.getString(TAG_ENTRUST_END)));

                dataArrayList.add(data);
            }
//            myApplication.getTradeEntity().getTradeData().setHoldDetailDataList(holdDetailDataList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<TuoGuanListData> getDataArrayList() {
        return dataArrayList;
    }
}
