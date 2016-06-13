package com.puxtech.ybk.jiaoyi.httpmanager;

import android.content.Context;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseData;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by mac on 15/11/6.
 */
public class HttpManagerBase {
    protected static final String TAG_MMTS = "MMTS";
    protected static final String TAG_REQ_HEADER = "REQ_HEADER";
    protected static final String TAG_CLT_VERSION = "CLT_VERSION";
    protected static final String TAG_CLT_TIMESTAMP = "CLT_TIMESTAMP";
    protected static final String TAG_CLT_SEQ = "CLT_SEQ";
    protected static final String TAG_REQ_BODY = "REQ_BODY";

    protected static final String VERSION = "1.0.0";


    protected Context context;
    MyApplication myapp;


    public HttpManagerBase(Context context) {
        this.context = context;
        myapp = (MyApplication) context.getApplicationContext();
        //currentTradeEntity=myapp.getCurrentTradeEntity();


    }

    public HttpManagerBase() {

    }

    void outPutRequestLog(String requestData) {
        Logger.d("requestStr = " + requestData);
    }

    protected void createError(BaseResponseDataTrade responseData, Exception e) {
        e.printStackTrace();
        responseData.setRetCode(Constant.CODE_ERROR_CERATE);
        responseData.setRetMessage(Constant.MESSAGE_ERROR_CERATE);
    }

    protected void networkError(BaseResponseDataTrade responseData, Exception e) {
        e.printStackTrace();
        responseData.setRetCode(Constant.CODE_ERROR_INTERNET);
        responseData.setRetMessage(Constant.MESSAGE_ERROR_INTERNET);
    }

    protected void unknowError(BaseResponseDataTrade responseData, Exception e) {
        e.printStackTrace();
        responseData.setRetCode(Constant.CODE_ERROR_UNKNOW);
        responseData.setRetMessage(Constant.MESSAGE_ERROR_UNKNOW);
    }

    // 将map中的值,组成json
    protected String getRequestData(HashMap<String, String> map, String rName) {
        JSONObject json = new JSONObject();
        String req = null;
        try {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> m = it.next();
                String key = m.getKey();
                String value = m.getValue();
                json.put(key, value);
            }
            req = jsonRequestPutHead(json, rName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return req;

    }

    // 给一些复用的KEY赋值
    protected String jsonRequestPutHead(JSONObject bodyJson, String name)
            throws JSONException {


        JSONObject headerJson = new JSONObject();
        headerJson.put(TAG_CLT_VERSION, VERSION);
        headerJson.put(TAG_CLT_TIMESTAMP, System.currentTimeMillis());
        headerJson.put(TAG_CLT_SEQ, System.currentTimeMillis());


////        json.put("name", name);
        if (!(name.equals("get_version") || name.equals("login"))) {
            // get_version和logon没有这两个属性
            bodyJson.put("SESSION_ID", myapp.getTradeEntity().getOtherData().getSid());
            bodyJson.put("USER_ID", myapp.getTradeEntity().getOtherData().getFirmId());
        }
//        if (!name.equals("get_version")) {
//            // get_version没有此键值
//            rep.put("version", "1.0");
//        }
//        JSONObject mmts = new JSONObject();
//        mmts.put("MMTS", rep);
//       TradeLogRecorder tlr = new TradeLogRecorder();
//        tlr.saveLog(myapp, "请求:"+json.toString());
        JSONObject mmtsBody = new JSONObject();
        mmtsBody.put(TAG_REQ_BODY, bodyJson);
        mmtsBody.put(TAG_REQ_HEADER, headerJson);

        JSONObject mmts = new JSONObject();
        mmts.put(TAG_MMTS, mmtsBody);
        return mmts.toString();
    }

    protected String getPublicUrl() {
//        getServiceList
        return myapp.getQiDongData().getTradeFastLinkEntity().getUrl();
//        return myapp.getQiDongData().getContentsServerEntity().getYwSystemEntity().getJysList().get(0).getEnvList().get(0).getYwList().get(0).getLlList().get(0).getUrl();
//        return "http://172.31.100.233:8208/tradeweb_ybk";
    }
}
