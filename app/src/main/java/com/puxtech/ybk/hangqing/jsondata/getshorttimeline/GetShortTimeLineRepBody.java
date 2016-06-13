package com.puxtech.ybk.hangqing.jsondata.getshorttimeline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fanshuo on 16/5/3.
 */
public class GetShortTimeLineRepBody {

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
    @SerializedName("OPEP")
    private float opep;
    @Expose
    @SerializedName("YCLO")
    private float yclo;
    @Expose
    @SerializedName("HIGP")
    private float higp;
    @Expose
    @SerializedName("HPRT")
    private long hprt;
    @Expose
    @SerializedName("LOWP")
    private float lowp;
    @Expose
    @SerializedName("LPRT")
    private long lprt;
    @Expose
    @SerializedName("YCLE")
    private float ycle;
    @Expose
    @SerializedName("TDLI")
    private List<TradeDay> tdli;

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

    public float getOpep() {
        return opep;
    }

    public void setOpep(float opep) {
        this.opep = opep;
    }

    public float getYclo() {
        return yclo;
    }

    public void setYclo(float yclo) {
        this.yclo = yclo;
    }

    public float getHigp() {
        return higp;
    }

    public void setHigp(float higp) {
        this.higp = higp;
    }

    public long getHprt() {
        return hprt;
    }

    public void setHprt(long hprt) {
        this.hprt = hprt;
    }

    public float getLowp() {
        return lowp;
    }

    public void setLowp(float lowp) {
        this.lowp = lowp;
    }

    public long getLprt() {
        return lprt;
    }

    public void setLprt(long lprt) {
        this.lprt = lprt;
    }

    public float getYcle() {
        return ycle;
    }

    public void setYcle(float ycle) {
        this.ycle = ycle;
    }

    public List<TradeDay> getTdli() {
        return tdli;
    }

    public void setTdli(List<TradeDay> tdli) {
        this.tdli = tdli;
    }
}
