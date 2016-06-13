package com.puxtech.ybk.hangqing.jsondata.getshortkline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fanshuo on 16/5/12.
 */
public class GetShortKLineRepBody {

    @Expose
    @SerializedName("MID")
    private int mid;//市场标识
    @Expose
    @SerializedName("SMID")
    private int smid;//盘标识
    @Expose
    @SerializedName("CID")
    private int cid;
    @Expose
    @SerializedName("MCU")
    private int mcu;
    @Expose
    @SerializedName("PER")
    private int per;
    @Expose
    @SerializedName("TDLL")
    private List<KPointRecord> tdll;

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

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public List<KPointRecord> getTdll() {
        return tdll;
    }

    public void setTdll(List<KPointRecord> tdll) {
        this.tdll = tdll;
    }
}
