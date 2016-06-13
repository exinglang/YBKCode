package com.puxtech.ybk.jiaoyi.responsedata;


import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.jiaoyi.entitydata.InOutHistoryEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class InOutHistoryResponseData extends BaseResponseDataTrade {
    protected static final String TAG_MID = "MID";
    protected static final String TAG_TD = "TD";
    protected static final String TAG_BANK_NAME = "BANK_NAME";
    protected static final String TAG_TT = "TT";
    protected static final String TAG_AM = "AM";
    protected static final String TAG_TS = "TS";
    protected static final String TAG_REM = "REM";



//  MID;//流水号
//  TD;//操作时间，微秒
//  BANK_NAME;//银行名称
//  TT;//类型:I入金；O出金
//  AM;//金额
//  TS;//状态
//  REM;//备注


    ArrayList<InOutHistoryEntity> inOutHistoryEntityArrayList;

    public void parseXML( String jsString) {
        try {
            JSONObject root = checkFail(jsString);
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

            inOutHistoryEntityArrayList = new ArrayList<>();
            for (int j = 0; j < holdDetailRecordNumber; j++) {

                JSONObject rec = body.getJSONArray(TAG_RESULTLIST)
                        .getJSONObject(j);
//  MID;//流水号
//  TD;//操作时间，微秒
//  BANK_NAME;//银行名称
//  TT;//类型:I入金；O出金
//  AM;//金额
//  TS;//状态
//  REM;//备注


                InOutHistoryEntity inOutHistoryEntity = new InOutHistoryEntity();
                inOutHistoryEntity.setMid(rec.getString(TAG_MID));
                inOutHistoryEntity.setTd(rec.getString(TAG_TD));
                inOutHistoryEntity.setBank_name(rec.getString(TAG_BANK_NAME));
                inOutHistoryEntity.setTT(rec.getString(TAG_TT));
                inOutHistoryEntity.setAM(rec.getString(TAG_AM));
                inOutHistoryEntity.setTS(rec.getString(TAG_TS));
                inOutHistoryEntity.setREM(rec.getString(TAG_REM));

                inOutHistoryEntityArrayList.add(inOutHistoryEntity);
            }


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }

    public ArrayList<InOutHistoryEntity> getInOutHistoryEntityArrayList() {
        return inOutHistoryEntityArrayList;
    }
}
