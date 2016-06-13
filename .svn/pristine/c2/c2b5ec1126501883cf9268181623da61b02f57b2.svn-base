package com.puxtech.ybk.hangqing.jsondata.geteverypriceafterid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.Extend;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.FenBi;

import java.io.Serializable;
import java.util.List;

/**
 * 刷新分笔协议的返回类
 */
public class GetEveryPriceAfterIdRepBody implements Serializable{


    @Expose
    @SerializedName("MID")
    private int mid;//交易所标识
    @Expose
    @SerializedName("SMID")
    private int smid;//盘标识
    @Expose
    @SerializedName("CID")
    private int cid;//商品编号
    @Expose
    @SerializedName("MCU")
    private int mcu;//最小变动单位
    @Expose
    @SerializedName("RECL")
    private List<FenBi> recl;//分笔列表

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getSmid() {
        return smid;
    }

    public void setSmid(int smid) {
        this.smid = smid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getMcu() {
        return mcu;
    }

    public void setMcu(int mcu) {
        this.mcu = mcu;
    }

    public List<FenBi> getRecl() {
        return recl;
    }

    public void setRecl(List<FenBi> recl) {
        this.recl = recl;
    }
}
