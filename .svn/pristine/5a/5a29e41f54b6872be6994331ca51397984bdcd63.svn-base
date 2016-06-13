package com.puxtech.ybk.jiaoyi.httpmanager;

import android.content.Context;

import com.puxtech.ybk.jiaoyi.network.HttpSender;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.HistoryDealResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.InOutHistoryResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.UserBankResponseData;

import java.util.HashMap;

/**
 * Created by mac on 16/4/21.
 */
public class HttpManagerReport extends HttpManagerBase {

    private static final String URL_TYPE = "report";


    public HttpManagerReport(Context context) {
        super(context);


    }



    //报表.历史成交
    public HistoryDealResponseData requestHistoryDeal(String startTime,String endTime) {
        HistoryDealResponseData responseData = new HistoryDealResponseData();
        String requestData;
        String rName = "trade_report";
        try {

            HashMap<String, String> map = new HashMap<>();
            map.put("ST", startTime);
            map.put("ET", endTime);
            map.put("STARTNUM", "0");
            map.put("RECCNT", "100");
            map.put("SORTFLD", "");
            map.put("ISDESC", "1");
//
//            "ST": 123,
//                    "ET": 123,
//                    "STARTNUM":1,
//                    "RECCNT": 222,
//                    "SORTFLD": "",
//                    "ISDESC": 0

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




}
