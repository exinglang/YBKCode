package com.puxtech.ybk.jiaoyi.httpmanager;

import android.content.Context;

import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.network.HttpSender;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.CheckCodeResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.CommodityResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.HeartBeatResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.TradeLogonResponseData;
import com.puxtech.ybk.util.ActivityUtils;
import com.puxtech.ybk.util.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by mac on 16/4/21.
 */
public class HttpManagerLogin extends HttpManagerBase {

    private static final String URL_TYPE = "login";


    public HttpManagerLogin(Context context) {
        super(context);


    }

    //登录验证码
    public CheckCodeResponseData requestCheckCode() {
      return  requestCheckCodeBase(getPublicUrl());
    }
    //登录验证码,带链接的,测速用
    public CheckCodeResponseData requestCheckCode(String url) {
        return  requestCheckCodeBase(url);
    }
    //登录验证码
    public CheckCodeResponseData requestCheckCodeBase(String url) {
        CheckCodeResponseData responseData = new CheckCodeResponseData();
        String requestData;
        String rName = "get_valcode";
        try {

            HashMap<String, String> map = new HashMap<>();
//            map.put("COMMODITY_ID", commodityCode);
//            map.put("A_N", "");
//            map.put("P_P", "");
            requestData = getRequestData(map, rName);
            outPutRequestLog(requestData);
        } catch (Exception e) {
            createError(responseData, e);
            return responseData;
        }
        HttpSender httpSender = new HttpSender();
        String responseStr;
        try {
            responseStr = httpSender.requestJson(requestData.getBytes(), url, URL_TYPE, rName);
        } catch (Exception e) {
            networkError(responseData, e);
            return responseData;
        }
        responseData.parseXML(responseStr);
        return responseData;
    }

    //登录交易
    public TradeLogonResponseData requestTradeLogin(String userId,String password,String checkCode,String checkCodeId) {
        TradeLogonResponseData responseData = new TradeLogonResponseData();
        String requestData;
        String rName = "login";
        byte[] logonKey;
        try {
            logonKey = MD5.getMD5(userId + password);// 登录加解密密钥
            long time = System.currentTimeMillis();

            HashMap<String, String> map = new HashMap<>();
            map.put("USER_ID", userId);
            map.put("VALID_CODE", checkCode);
            map.put("VALID_UUID",checkCodeId);
            map.put("VERSIONINFO", VERSION);
            map.put("LOGIN_TIME",time+"");
            map.put("CLT_TOKEN", TradeHelper.getToken(time, userId, password, logonKey));
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
        responseData.parseXML(myapp,responseStr, logonKey,rName);
        return responseData;
    }

    //心跳信号
    public HeartBeatResponseData requestHeartBeat(String tradeCnt) {
        HeartBeatResponseData responseData = new HeartBeatResponseData();
        String requestData;
        String rName = "heart_beat";

        try {

            HashMap<String, String> map = new HashMap<>();
            map.put("TRADE_CNT", tradeCnt);
            requestData = getRequestData(map, rName);
            if (Logger.is_time_show) {
                outPutRequestLog(requestData);
            }
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
        responseData.parseXML(responseStr,rName);
        return responseData;
    }
    //修改交易密码
    public BaseResponseDataTrade requestChangePasswordTrade(String oldPwd,String newPwd) {
        BaseResponseDataTrade responseData = new BaseResponseDataTrade();
        String requestData;
        String rName = "update_password";
        try {

            HashMap<String, String> map = new HashMap<>();
            map.put("PWD_TYPE","0");
            map.put("OLD_PASSWORD",MD5.getMD54String(myapp.getTradeEntity().getOtherData().getFirmId()+oldPwd));
            map.put("NEW_PASSWORD",MD5.getMD54String(myapp.getTradeEntity().getOtherData().getFirmId()+newPwd));



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
