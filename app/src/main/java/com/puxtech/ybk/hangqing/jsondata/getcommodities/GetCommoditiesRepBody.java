package com.puxtech.ybk.hangqing.jsondata.getcommodities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * getCommodities返回的repb
 */
public class GetCommoditiesRepBody {

    @Expose
    @SerializedName("MID")
    private int mid;//市场标识
    @Expose
    @SerializedName("SMID")
    private int smid;//盘标识
    @Expose
    @SerializedName("CLI")
    private List<Commodity> cli;//商品列表

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

    public List<Commodity> getCli() {
        return cli;
    }

    public void setCli(List<Commodity> cli) {
        this.cli = cli;
    }
}
