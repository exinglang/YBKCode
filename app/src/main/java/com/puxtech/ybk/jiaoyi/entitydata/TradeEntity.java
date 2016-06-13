package com.puxtech.ybk.jiaoyi.entitydata;

import android.content.Context;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mac on 15/11/5.
 */
public class TradeEntity {
    private boolean hasLogin=false;//是否已经登录了交易
    private boolean heartBeatTradeState=false;

    //    public PriceData priceData;
    private TradeData tradeData;
    private OtherData otherData;
//    private boolean isTradeState;//根据心跳信号返回的值,检测是否账号处于可交易状态
//    private String tradeUrl, reportUrl, moneyUrl;//交易,报表,出入金.
//    private String userId="";
//    private int envirNumber, platformNumber;
//    private boolean hasLogin;
//
//
//
//    public ArrayList<SysInfoData> infoList;//首页需要显示的公告列表
//    private ArrayList<HashMap<String, String>> openaccountlist;// 可开户盘的列表

    //    public TradeEntity(Context context) {
//        priceData = new PriceData(context);
//        tradeData = new TradeData();
//        otherData = new OtherData();
//
//    }
    public TradeEntity() {
        otherData = new OtherData();
        tradeData= new TradeData();
    }

    public boolean isHeartBeatTradeState() {
        return heartBeatTradeState;
    }

    public void setHeartBeatTradeState(boolean heartBeatTradeState) {
        this.heartBeatTradeState = heartBeatTradeState;
    }

    public OtherData getOtherData() {
        return otherData;
    }

    public boolean isHasLogin() {
        return hasLogin;
    }


    public TradeData getTradeData() {
        return tradeData;
    }

    public void setHasLogin(boolean hasLogin) {
        this.hasLogin = hasLogin;
    }

}
