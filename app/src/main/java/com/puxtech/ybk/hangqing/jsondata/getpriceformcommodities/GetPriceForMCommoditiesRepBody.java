package com.puxtech.ybk.hangqing.jsondata.getpriceformcommodities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.puxtech.ybk.hangqing.jsondata.getcommodities.Commodity;

import java.util.List;

/**
 * getPriceForMCommodities返回的repb
 */
public class GetPriceForMCommoditiesRepBody {

    @Expose
    @SerializedName("MID")
    private int mid;//市场标识
    @Expose
    @SerializedName("SMID")
    private int smid;//盘标识
    @Expose
    @SerializedName("CLI")
    private List<CommodityPrice> cli;//商品列表

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

    public List<CommodityPrice> getCli() {
        return cli;
    }

    public void setCli(List<CommodityPrice> cli) {
        this.cli = cli;
    }
}
