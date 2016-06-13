package com.puxtech.ybk.hangqing.jsondata.gettradetime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fanshuo on 16/4/22.
 */
public class GetTradeTimeRepBody {

    @Expose
    @SerializedName("MID")
    private int mid;
    @Expose
    @SerializedName("SMID")
    private int smid;
    @Expose
    @SerializedName("TDAY")
    private long tday;
    @Expose
    @SerializedName("STAT")
    private long stat;
    @Expose
    @SerializedName("ENDT")
    private long endt;
    @Expose
    @SerializedName("LATT")
    private long latt;

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

    public long getTday() {
        return tday;
    }

    public void setTday(long tday) {
        this.tday = tday;
    }

    public long getStat() {
        return stat;
    }

    public void setStat(long stat) {
        this.stat = stat;
    }

    public long getEndt() {
        return endt;
    }

    public void setEndt(long endt) {
        this.endt = endt;
    }

    public long getLatt() {
        return latt;
    }

    public void setLatt(long latt) {
        this.latt = latt;
    }
}
