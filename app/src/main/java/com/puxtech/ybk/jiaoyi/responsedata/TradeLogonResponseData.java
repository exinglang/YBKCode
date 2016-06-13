package com.puxtech.ybk.jiaoyi.responsedata;


import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.jiaoyi.entitydata.TradeEntity;
import com.puxtech.ybk.util.AES;
import com.puxtech.ybk.util.Base64;

import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by mac on 15/11/5.
 */
public class TradeLogonResponseData extends BaseResponseDataTrade {

//    private static final String TAG_SRV_KEY = "SRV_KEY";
    private static final String TAG_CLT_KEY = "CLT_KEY";
    private static final String TAG_IS_ENCRYPT = "IS_ENCRYPT";
    private static final String TAG_CHG_PWD = "CHG_PWD";
    private static final String TAG_IP = "LAST_IP";
    private static final String TAG_TIME = "LAST_TIME";
    private static final String LAST_MODULE = "LAST_MODULE";
    private static final String TAG_FIRM_ID = "FIRM_ID";



    Boolean forceChangePW;


//    String ip, module, time;

    public void parseXML(MyApplication myapp, String jsString, byte[] logonKey,String rName) {

        try {
            JSONObject root = checkFail(jsString,rName);
            if (retCode != 0) {
                return;
            }

            JSONObject rep = root.getJSONObject(TAG_MMTS).getJSONObject(TAG_REP_BODY);

            TradeEntity tradeEntity = myapp.getTradeEntity();

// 判断是否需要修改密码
            if (rep.getString(TAG_CHG_PWD).equals("1")) {// 强制修改密码
                forceChangePW = true;
            }
            tradeEntity.getOtherData()
                    .setFirmId(rep.getString(TAG_FIRM_ID));

            // 设置是否加密
//            tradeEntity.getOtherData()
//                    .setAes(rep.getString(TAG_IS_ENCRYPT).equals("1"));
            ByteBuffer buffer = ByteBuffer.wrap(AES.decrypt(
                    Base64.decode(rep.getString(TAG_CLT_KEY)), logonKey));
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            byte[] key = new byte[buffer.getShort()];
            buffer.get(key);
            tradeEntity.getOtherData().setKey(key);// 会话密钥
            key = new byte[buffer.getShort()];
            buffer.get(key);
            tradeEntity.getOtherData()
                    .setSid(new String(key, ENCODE));// sessionID
            tradeEntity.getOtherData().setClt_key(rep.getString(TAG_CLT_KEY));


        } catch (Exception e) {
            e.printStackTrace();
            parseError();

        }

    }


}
