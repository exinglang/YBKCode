package com.puxtech.ybk.hangqing.jsondata.getshorttimeline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fanshuo on 16/5/3.
 */
public class TradeDayRecord {

    @Expose
    @SerializedName("DIVT")
    private String divt;//分时时间hhmm或hmm
    @Expose
    @SerializedName("LATP")
    private float latp;//周期内最后一笔报价
    @Expose
    @SerializedName("AVGP")
    private float avgp;//周期内算数平均价
    @Expose
    @SerializedName("NVOL")
    private int nvol;//现手，默认0
    @Expose
    @SerializedName("PRID")
    private int prid;//价格编号

    //public methods

    /**
     * 将json数据中的时间的小时和分钟之间添加冒号
     */
    public String getFormatedDivt(){
        if(divt== null || divt.length() < 2){
            return "--";
        }
        String hour = divt.substring(0,divt.length()-2);
        String minutes = divt.substring(divt.length()-2);
        return hour+":"+minutes;
    }

    //getters and setters
    public String getDivt() {
        return divt;
    }

    public void setDivt(String divt) {
        this.divt = divt;
    }

    public float getLatp() {
        return latp;
    }

    public void setLatp(float latp) {
        this.latp = latp;
    }

    public float getAvgp() {
        return avgp;
    }

    public void setAvgp(float avgp) {
        this.avgp = avgp;
    }

    public int getNvol() {
        return nvol;
    }

    public void setNvol(int nvol) {
        this.nvol = nvol;
    }

    public int getPrid() {
        return prid;
    }

    public void setPrid(int prid) {
        this.prid = prid;
    }
}
