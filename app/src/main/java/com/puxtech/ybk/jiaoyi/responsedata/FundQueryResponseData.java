package com.puxtech.ybk.jiaoyi.responsedata;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.entitydata.FundInfoData;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.entitydata.TradeEntity;
import com.puxtech.ybk.util.AES;
import com.puxtech.ybk.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class FundQueryResponseData extends BaseResponseDataTrade {

//    交易商ID	FIRMID	字符串
//    交易商名称	NAME	字符串
//    账号余额	DONEBALANCE	数字
//    冻结资金	FROZENFUNDS	数字


    private static final String TAG_FIRMID = "FIRMID";
    private static final String TAG_NAME = "NAME";
    private static final String TAG_DONEBALANCE = "DONEBALANCE";
    private static final String TAG_FROZENFUNDS = "FROZENFUNDS";


    public void parseXML(MyApplication myApplication, String jsString, String rName) {
        try {
            JSONObject root = checkFail(jsString, rName);
            if (retCode != 0) {
                return;
            }
            JSONObject rep = root.getJSONObject(TAG_MMTS).getJSONObject(TAG_REP_BODY);
            FundInfoData fundInfoData = new FundInfoData();


            fundInfoData.setFIRMID(rep.getString(TAG_FIRMID));
            fundInfoData.setNAME(rep.getString(TAG_NAME));
            fundInfoData.setDONEBALANCE(rep.getString(TAG_DONEBALANCE));
            fundInfoData.setFROZENFUNDS(rep.getString(TAG_FROZENFUNDS));

            myApplication.getTradeEntity().getOtherData().setFundInfoData(fundInfoData);
        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }

    }

    public static String ENCODE = "UTF-8";

    protected int retCode;//返回码
    protected String retMessage;//返回信息
    protected String protocolName = "";//协议名称

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
        //  String log = new String(sb.toString());
//        TradeLogRecorder tlr = new TradeLogRecorder();
//        tlr.saveLog(context,"返回:"+ log);
        return new JSONObject(sb.toString());
    }


}
