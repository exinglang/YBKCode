package com.puxtech.ybk.jiaoyi.httpmanager;

import android.content.Context;

import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.entitydata.GongGaoData;
import com.puxtech.ybk.jiaoyi.entitydata.GongGaoDetailData;
import com.puxtech.ybk.jiaoyi.network.HttpSender;
import com.puxtech.ybk.jiaoyi.responsedata.CommodityFeeResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.CommodityResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.FundQueryResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.GongGaoDetailResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.GongGaoResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.HoldQueryResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.ShengGouListResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.ShengGouOrderResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.TiHuoOrderResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.TodayDealResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.TodayOrderResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.TuoGuanListResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.TuoGuanOrderResponseData;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.HashMap;

/**
 * Created by mac on 16/4/21.
 */
public class HttpManagerNotice extends HttpManagerBase {

    private static final String URL_TYPE = "notice";


    public HttpManagerNotice(Context context) {
        super(context);


    }



    //公告列表
    public GongGaoResponseData requestGongGaoList(String startTime,String endTime) {
        GongGaoResponseData responseData = new GongGaoResponseData();
        String requestData;
        String rName = "notice_list_query";
        try {

            HashMap<String, String> map = new HashMap<>();
            map.put("EFFECTIVE", "1");

            map.put("TYPE", "");
            map.put("ST", startTime);
            map.put("ET", endTime);
            map.put("EXPIRE_ST", "");
            map.put("EXPIRE_ET", "");
            map.put("TITLE", "");
            map.put("STARTNUM", "1");
            map.put("RECCNT","20");
            map.put("ISDESC", "");
            map.put("SORTFLD", "");
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

    //公告详细内容
    public GongGaoDetailResponseData requestGongGaoDetail(GongGaoData gongGaoData) {
        GongGaoDetailResponseData responseData = new GongGaoDetailResponseData();
        String requestData;
        String rName = "notice_content_query";
        try {

            HashMap<String, String> map = new HashMap<>();
            map.put("NOTICE_ID", gongGaoData.getID());
            map.put("TYPE", gongGaoData.getTYPE());
            map.put("EFFECTIVE", "1");
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
        responseData.parseXML( responseStr);
        return responseData;
    }
}
