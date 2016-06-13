package com.puxtech.ybk.jiaoyi.responsedata;


import com.puxtech.ybk.jiaoyi.entitydata.GongGaoData;
import com.puxtech.ybk.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class GongGaoResponseData extends BaseResponseDataTrade {
    private static final String TAG_ID = "ID";
    private static final String TAG_TITLE = "TITLE";
    private static final String TAG_TYPE = "TYPE";
    private static final String TAG_PUBLISH = "PUBLISH";
    private static final String TAG_SEND_TIME = "SEND_TIME";
    private static final String TAG_EXPIRY_TIME = "EXPIRY_TIME";
    public int recordNumber;//取得的记录数
    public ArrayList<GongGaoData> dataList;

    public void parseXML(String jsString) {
        try {
            JSONObject root = checkFail(jsString);
            if (retCode != 0) {
                return;
            }
            JSONObject body = root.getJSONObject(TAG_MMTS)
                    .getJSONObject(TAG_REP_BODY);

            try {
                recordNumber = body.getJSONArray(TAG_RESULTLIST).length();
            } catch (JSONException e) {
                e.printStackTrace();
                recordNumber = 0;
            }
            dataList = new ArrayList<>();
            for (int j = 0; j < recordNumber; j++) {

                JSONObject rec = body.getJSONArray(TAG_RESULTLIST)
                        .getJSONObject(j);
                GongGaoData hte = new GongGaoData();
                hte.setID(rec.getString(TAG_ID));
                hte.setTITLE(rec.getString(TAG_TITLE));
                hte.setTYPE(rec.getString(TAG_TYPE));
                hte.setPUBLISH(rec.getString(TAG_PUBLISH));
                hte.setSEND_TIME(ActivityUtils.getRealData(rec.getString(TAG_SEND_TIME)));
                hte.setEXPIRY_TIME(ActivityUtils.getRealData(rec.getString(TAG_EXPIRY_TIME)));

                dataList.add(hte);
            }


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<GongGaoData> getDataList() {
        return dataList;
    }
}