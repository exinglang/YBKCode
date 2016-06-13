package com.puxtech.ybk.jiaoyi.responsedata;

import android.content.Context;

import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class HoldQueryResponseData extends BaseResponseDataTrade {

    //                交易商ID	FIRMID
//                商品代码	COMMODITYID
//                持仓数量	HOLDQTY
//                持仓成本	HOLDCOST
//                持仓均价	EVENPRICE
//                冻结数量	FROZENQTY

    private static final String TAG_FIRMID = "FIRMID";
    private static final String TAG_COMMODITYID = "COMMODITYID";
    private static final String TAG_HOLDQTY = "HOLDQTY";
    private static final String TAG_HOLDCOST = "HOLDCOST";
    private static final String TAG_EVENPRICE = "EVENPRICE";
    private static final String TAG_FROZENQTY = "FROZENQTY";

    ArrayList<HoldDetailData> holdDetailDataList;

    public void parseXML(MyApplication myApplication, String jsString,String rName) {
        try {
            JSONObject root = checkFail(jsString,rName);
            if (retCode != 0) {
                return;
            }
            JSONObject body = root.getJSONObject(TAG_MMTS)
                    .getJSONObject(TAG_REP_BODY);

            int holdDetailRecordNumber;
            try {
                holdDetailRecordNumber = body.getJSONArray(TAG_RESULTLIST).length();
            } catch (JSONException e) {
                e.printStackTrace();
                holdDetailRecordNumber = 0;
            }
            holdDetailDataList = new ArrayList<>();
            for (int j = 0; j < holdDetailRecordNumber; j++) {

                JSONObject rec = body.getJSONArray(TAG_RESULTLIST)
                        .getJSONObject(j);
//                交易商ID	FIRMID
//                商品代码	COMMODITYID
//                持仓数量	HOLDQTY
//                持仓成本	HOLDCOST
//                持仓均价	EVENPRICE
//                冻结数量	FROZENQTY


                HoldDetailData holdDetailData = new HoldDetailData();
                holdDetailData.setFIRMID(rec.getString(TAG_FIRMID));
                holdDetailData.setCOMMODITYID(rec.getString(TAG_COMMODITYID));
                holdDetailData.setHOLDQTY(rec.getString(TAG_HOLDQTY));
                holdDetailData.setHOLDCOST(rec.getString(TAG_HOLDCOST));
                holdDetailData.setEVENPRICE(rec.getString(TAG_EVENPRICE));
                holdDetailData.setFROZENQTY(rec.getString(TAG_FROZENQTY));

                holdDetailDataList.add(holdDetailData);
            }
            myApplication.getTradeEntity().getTradeData().setHoldDetailDataList(holdDetailDataList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }
}
