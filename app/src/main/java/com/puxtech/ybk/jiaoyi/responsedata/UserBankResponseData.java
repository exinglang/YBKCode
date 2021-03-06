package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.entitydata.UserBankData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class UserBankResponseData extends BaseResponseDataTrade {

//    "BANK_ID": "银行ID",
//            "BANK_NAME": "银行名称",
//            "ST": "转账开始时间，字符串hh:mm:ss",
//            "ET": "转账结束时间，字符串hh:mm:ss",
//            "OPI": "出金密码类型(文本) ",
//            "IPI": "入金密码类型(文本) ",
//            "BBI": "查询银行卡余额密码类型(文本) "

    private static final String TAG_FUNDS_PWD = "FUNDS_PWD";

    private static final String TAG_BANK_ID = "BANK_ID";
    private static final String TAG_BANK_NAME = "BANK_NAME";
    private static final String TAG_ST = "ST";
    private static final String TAG_ET = "ET";
    private static final String TAG_OPI = "OPI";
    private static final String TAG_IPI = "IPI";
    private static final String TAG_BBI = "BBI";


    ArrayList<UserBankData> userBankDataArrayList;

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
            myApplication.getTradeEntity().getOtherData().setSetedFundPwd(body.getString(TAG_FUNDS_PWD).equals("Y") ? true : false);

            userBankDataArrayList = new ArrayList<>();
            for (int j = 0; j < holdDetailRecordNumber; j++) {

                JSONObject rec = body.getJSONArray(TAG_RESULTLIST)
                        .getJSONObject(j);
//                private static final String TAG_BANK_ID = "BANK_ID";
//                private static final String TAG_BANK_NAME = "BANK_NAME";
//                private static final String TAG_ST = "ST";
//                private static final String TAG_ET = "ET";
//                private static final String TAG_OPI = "OPI";
//                private static final String TAG_IPI= "IPI";
//                private static final String TAG_BBI= "BBI";


                UserBankData userBankData = new UserBankData();
                userBankData.setBANK_ID(rec.getString(TAG_BANK_ID));
                userBankData.setBANK_NAME(rec.getString(TAG_BANK_NAME));
                userBankData.setST(rec.getString(TAG_ST));
                userBankData.setET(rec.getString(TAG_ET));
                userBankData.setOPI(rec.getString(TAG_OPI));
                userBankData.setIPI(rec.getString(TAG_IPI));
                userBankData.setBBI(rec.getString(TAG_BBI));

                userBankDataArrayList.add(userBankData);
            }
            myApplication.getTradeEntity().getOtherData().setUserBankDataArrayList(userBankDataArrayList);


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }


    }
}
