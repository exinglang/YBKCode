package com.puxtech.ybk.jiaoyi.httpmanager;

import android.content.Context;

import com.puxtech.ybk.jiaoyi.entitydata.InOutHistoryEntity;
import com.puxtech.ybk.jiaoyi.network.HttpSender;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.CommodityResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.FundQueryResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.HoldQueryResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.InOutHistoryResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.UserBankResponseData;
import com.puxtech.ybk.util.MD5;

import java.util.HashMap;

/**
 * Created by mac on 16/4/21.
 */
public class HttpManagerMoney extends HttpManagerBase {

    private static final String URL_TYPE = "money";


    public HttpManagerMoney(Context context) {
        super(context);


    }
    //修改资金密码
    public BaseResponseDataTrade requestChangePasswordMoney(String oldPwd,String newPwd) {
        BaseResponseDataTrade responseData = new BaseResponseDataTrade();
        String requestData;
        String rName = "money_modpwd";
        try {

            HashMap<String, String> map = new HashMap<>();
            map.put("OPWD",oldPwd);
            map.put("NPWD",newPwd);
            requestData = getRequestData(map, rName);
            outPutRequestLog(requestData);
        } catch (Exception e) {
            createError(responseData, e);
            return responseData;
        }
        HttpSender httpSender = new HttpSender();
        String responseStr;
        try {
            responseStr = httpSender.requestJson(requestData.getBytes(), getPublicUrl(), URL_TYPE, rName);
        } catch (Exception e) {
            networkError(responseData, e);
            return responseData;
        }
        try {
            responseData.checkFail(responseStr);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.parseError();

        }
        return responseData;
    }
    //银行列表
    public UserBankResponseData requestUserBank() {
        UserBankResponseData responseData = new UserBankResponseData();
        String requestData;
        String rName = "money_userinfo_query";
        try {

            HashMap<String, String> map = new HashMap<>();
            requestData = getRequestData(map, rName);
            outPutRequestLog(requestData);
        } catch (Exception e) {
            createError(responseData, e);
            return responseData;
        }
        HttpSender httpSender = new HttpSender();
        String responseStr;
        try {
            responseStr = httpSender.requestJson(requestData.getBytes(), getPublicUrl(), URL_TYPE, rName);
        } catch (Exception e) {
            networkError(responseData, e);
            return responseData;
        }
        responseData.parseXML(myapp, responseStr,rName);
        return responseData;
    }

    //出入金历史记录
    public InOutHistoryResponseData requestInOutHistory(String startTime,String endTime) {
        InOutHistoryResponseData responseData = new InOutHistoryResponseData();
        String requestData;
        String rName = "money_iom_query";
        try {

            HashMap<String, String> map = new HashMap<>();
            map.put("ST", startTime);
            map.put("ET", endTime);
            map.put("STARTNUM", "0");
            map.put("RECCNT", "100");
            map.put("SORTFLD", "MID");
            map.put("ISDESC", "1");
            requestData = getRequestData(map, rName);
            outPutRequestLog(requestData);
        } catch (Exception e) {
            createError(responseData, e);
            return responseData;
        }
        HttpSender httpSender = new HttpSender();
        String responseStr;
        try {
            responseStr = httpSender.requestJson(requestData.getBytes(), getPublicUrl(), URL_TYPE, rName);
        } catch (Exception e) {
            networkError(responseData, e);
            return responseData;
        }
        responseData.parseXML(responseStr);
        return responseData;
    }



    //出入金
    public BaseResponseDataTrade requestChuRuJin(String bankId,String type,String amount,String pwd) {
        BaseResponseDataTrade responseData = new BaseResponseDataTrade();
        String requestData;
        String rName = "money_transfer";
        try {

            HashMap<String, String> map = new HashMap<>();
            map.put("BANK_ID",bankId);
            map.put("TYPE",type);
            map.put("AMOUNT",amount);
            map.put("PWD",pwd);
            requestData = getRequestData(map, rName);
            outPutRequestLog(requestData);
        } catch (Exception e) {
            createError(responseData, e);
            return responseData;
        }
        HttpSender httpSender = new HttpSender();
        String responseStr;
        try {
            responseStr = httpSender.requestJson(requestData.getBytes(), getPublicUrl(), URL_TYPE, rName);
        } catch (Exception e) {
            networkError(responseData, e);
            return responseData;
        }
        try {
            responseData.checkFail(responseStr);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.parseError();

        }
        return responseData;
    }

}
