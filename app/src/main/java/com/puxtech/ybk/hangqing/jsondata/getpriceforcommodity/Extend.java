package com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanshuo on 16/4/27.
 */
public class Extend  implements Serializable {

    @Expose
    @SerializedName("TBP")
    private float tbp;//当前结算价
    @Expose
    @SerializedName("SPRE")
    private float spre;//涨跌幅
    @Expose
    @SerializedName("OPEA")
    private int opea;//建仓量
    @Expose
    @SerializedName("CLOA")
    private int cloa;//平仓量
    @Expose
    @SerializedName("HOLA")
    private int hola;//总持仓量
    @Expose
    @SerializedName("HOLC")
    private int holc;//仓差
    @Expose
    @SerializedName("CURA")
    private int cura;//现量
    @Expose
    @SerializedName("OUTA")
    private int outa;//外盘
    @Expose
    @SerializedName("INA")
    private int ina;//内盘
    @Expose
    @SerializedName("FIVD")
    private WuDangData fivd;//五档数据

    public float getTbp() {
        return tbp;
    }

    public void setTbp(float tbp) {
        this.tbp = tbp;
    }

    public float getSpre() {
        return spre;
    }

    public void setSpre(float spre) {
        this.spre = spre;
    }

    public int getOpea() {
        return opea;
    }

    public void setOpea(int opea) {
        this.opea = opea;
    }

    public int getCloa() {
        return cloa;
    }

    public void setCloa(int cloa) {
        this.cloa = cloa;
    }

    public int getHola() {
        return hola;
    }

    public void setHola(int hola) {
        this.hola = hola;
    }

    public int getHolc() {
        return holc;
    }

    public void setHolc(int holc) {
        this.holc = holc;
    }

    public int getCura() {
        return cura;
    }

    public void setCura(int cura) {
        this.cura = cura;
    }

    public int getOuta() {
        return outa;
    }

    public void setOuta(int outa) {
        this.outa = outa;
    }

    public int getIna() {
        return ina;
    }

    public void setIna(int ina) {
        this.ina = ina;
    }

    public WuDangData getFivd() {
        return fivd;
    }

    public void setFivd(WuDangData fivd) {
        this.fivd = fivd;
    }
}
