package com.puxtech.ybk.jiaoyi.responsedata;


import com.puxtech.ybk.Constant;
import com.puxtech.ybk.jiaoyi.Logger;

import org.json.JSONObject;

/**
 * Created by mac on 15/11/4.
 */
public class BaseResponseDataTrade extends BaseResponseData {
    public static String ENCODE = "UTF-8";

    protected int retCode;//返回码
    protected String retMessage;//返回信息
    protected String protocolName = "";//协议名称

    protected static final String TAG_MMTS = "MMTS";
    protected static final String TAG_MESSAGE = "MESSAGE";
    protected static final String TAG_REP_HEADER = "REP_HEADER";
    protected static final String TAG_RETCODE = "RETCODE";

    protected static final String TAG_REP_BODY = "REP_BODY";

    protected static final String TAG_TTLREC = "TTLREC";

    protected static final String TAG_RESULTLIST = "RESULTLIST";


    public JSONObject checkFail(String jsString) throws Exception {

        return getFailMessage(jsString,"");
    }

    public JSONObject checkFail(String jsString,String rName) throws Exception {

        return getFailMessage(jsString,rName);
    }

    private JSONObject getFailMessage(String jsString,String rName) throws Exception {
        JSONObject root;
        JSONObject rep;

        if (rName.equals("fund_query") || rName.equals("heart_beat")) {
            if (Logger.is_time_show) {
                root = outPutJson(jsString);//输出日志过长时,日志无法完全显示,在此处进行分割.

            }
            else{

                root=outPutJsonNoLogger(jsString);
            }
        } else {

            root = outPutJson(jsString);//输出日志过长时,日志无法完全显示,在此处进行分割.

        }

        rep = root.getJSONObject(TAG_MMTS).getJSONObject(TAG_REP_HEADER);
        if (rep.has(TAG_RETCODE)) {

            retCode = rep.getInt(TAG_RETCODE);
            if (retCode != Constant.CODE_SUCCESS) {
                retMessage = rep.getString(TAG_MESSAGE)+rName;
            }
        }
        return root;
    }


    /**
     * 设置解析错误
     */
    public void parseError() {
        setRetCode(Constant.CODE_ERROR_PRASE);
        setRetMessage(Constant.MESSAGE_ERROR_PRASE);
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    //输入日志过长时,日志无法完全显示,在此处进行分割.
    public JSONObject outPutJson(String jsString) throws Exception {
        StringBuffer sb = new StringBuffer(jsString);
        for (int i = 0; i <= sb.length(); i = i + 2000)
        {
            int end;
            if (i + 2000 > sb.length()) {
                end = sb.length();
            } else {
                end = i + 2000;
            }
            String s = sb.substring(i, end);
            Logger.v("responseStr:" + s);

        }
        return new JSONObject(sb.toString());
    }

    //输入日志过长时,日志无法完全显示,在此处进行分割.
    public JSONObject outPutJsonNoLogger(String jsString) throws Exception {
        StringBuffer sb = new StringBuffer(jsString);
        for (int i = 0; i <= sb.length(); i = i + 2000)
        {
            int end;
            if (i + 2000 > sb.length()) {
                end = sb.length();
            } else {
                end = i + 2000;
            }
            String s = sb.substring(i, end);

        }
        return new JSONObject(sb.toString());
    }
}
