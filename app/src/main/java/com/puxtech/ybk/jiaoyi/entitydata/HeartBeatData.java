package com.puxtech.ybk.jiaoyi.entitydata;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <b>Description:</b>
 * <p>
 * </p>
 */
public class HeartBeatData implements Serializable {

    private String CLEAR_DATE;
    private long LASTNEW_ID;
    private String NEW_TRADES;
    private int SYSTEM_STATUS;
    private String SYSTEM_TIME;
    private long TRADE_CNT;
    ArrayList<TodayDealData> dataArrayList;

    public ArrayList<TodayDealData> getDataArrayList() {
        return dataArrayList;
    }

    public void setDataArrayList(ArrayList<TodayDealData> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    public String getCLEAR_DATE() {
        return CLEAR_DATE;
    }

    public void setCLEAR_DATE(String CLEAR_DATE) {
        this.CLEAR_DATE = CLEAR_DATE;
    }



    public String getNEW_TRADES() {
        return NEW_TRADES;
    }

    public void setNEW_TRADES(String NEW_TRADES) {
        this.NEW_TRADES = NEW_TRADES;
    }

    public int getSYSTEM_STATUS() {
        return SYSTEM_STATUS;
    }

    public void setSYSTEM_STATUS(int SYSTEM_STATUS) {
        this.SYSTEM_STATUS = SYSTEM_STATUS;
    }

    public String getSYSTEM_TIME() {
        return SYSTEM_TIME;
    }

    public void setSYSTEM_TIME(String SYSTEM_TIME) {
        this.SYSTEM_TIME = SYSTEM_TIME;
    }

    public long getTRADE_CNT() {
        return TRADE_CNT;
    }

    public void setTRADE_CNT(long TRADE_CNT) {
        this.TRADE_CNT = TRADE_CNT;
    }

    public long getLASTNEW_ID() {
        return LASTNEW_ID;
    }

    public void setLASTNEW_ID(long LASTNEW_ID) {
        this.LASTNEW_ID = LASTNEW_ID;
    }
}
