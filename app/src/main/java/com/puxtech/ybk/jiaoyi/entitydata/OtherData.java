package com.puxtech.ybk.jiaoyi.entitydata;

import java.util.ArrayList;

/**
 * Created by mac on 15/11/5.
 */
public class OtherData {
    private boolean setedFundPwd;

    private FundInfoData fundInfoData;
    private  ArrayList<UserBankData> userBankDataArrayList;
    private String version="1";
    private String clt_key;//用户解锁时使用

    private String sid;// sessionId

    private String firmId;

    private byte[] key;// 会话ID
    private byte[] token;// 服务器token
    private Boolean aes;

    private boolean isEncrypt;
    private String RSA="";

    public String getClt_key() {
        return clt_key;
    }

    public void setClt_key(String clt_key) {
        this.clt_key = clt_key;
    }

    public String getRSA() {
        return RSA;
    }

    public void setRSA(String RSA) {
        this.RSA = RSA;
    }

    public boolean isEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(boolean isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public Boolean getAes() {
        return aes;
    }

    public void setAes(Boolean aes) {
        this.aes = aes;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public FundInfoData getFundInfoData() {
        return fundInfoData;
    }

    public void setFundInfoData(FundInfoData fundInfoData) {
        this.fundInfoData = fundInfoData;
    }

    public ArrayList<UserBankData> getUserBankDataArrayList() {
        return userBankDataArrayList;
    }

    public void setUserBankDataArrayList(ArrayList<UserBankData> userBankDataArrayList) {
        this.userBankDataArrayList = userBankDataArrayList;
    }

    public boolean isSetedFundPwd() {
        return setedFundPwd;
    }

    public void setSetedFundPwd(boolean setedFundPwd) {
        this.setedFundPwd = setedFundPwd;
    }
}
