package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.jiaoyi.entitydata.GongGaoDetailData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONObject;

/**
 * Created by mac on 15/11/5.
 */
public class GongGaoDetailResponseData extends BaseResponseDataTrade {
    private static final String TAG_ID = "ID";
    private static final String TAG_TITLE = "TITLE";
    private static final String TAG_TYPE = "TYPE";
    private static final String TAG_PUBLISH = "PUBLISH";
    private static final String TAG_SEND_TIME = "SEND_TIME";
    private static final String TAG_EXPIRY_TIME = "EXPIRY_TIME";
    private static final String TAG_CONTENT = "CONTENT";
    GongGaoDetailData detailData;

    public void parseXML(String jsString) {
        try {
            JSONObject root = checkFail(jsString);
            if (retCode != 0) {
                return;
            }
            JSONObject rec = root.getJSONObject(TAG_MMTS)
                    .getJSONObject(TAG_REP_BODY);
//            JSONObject rec = rep.getJSONObject(Constant.TAG_LIST)
//                    .getJSONArray(Constant.TAG_REC).getJSONObject(0);
            detailData = new GongGaoDetailData();
            detailData.setID(rec.getString(TAG_ID));
            detailData.setTITLE(rec.getString(TAG_TITLE));
            detailData.setTYPE(rec.getString(TAG_TYPE));
            detailData.setPUBLISH(rec.getString(TAG_PUBLISH));
            detailData.setCONTENT(rec.getString(TAG_CONTENT));
            detailData.setSEND_TIME(ActivityUtils.getRealData(rec.getString(TAG_SEND_TIME)));
            detailData.setEXPIRY_TIME(ActivityUtils.getRealData(rec.getString(TAG_EXPIRY_TIME)));


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public GongGaoDetailData getDetailData() {
        return detailData;
    }
}