package com.puxtech.ybk.hangqing.jsondata.getcommodities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 商品
 */
public class Commodity {

    @Expose
    @SerializedName("VID")
    private int vid;//商品编号
    @Expose
    @SerializedName("CCOD")
    private String ccod;//商品代码
    @Expose
    @SerializedName("CNAM")
    private String cnam;//商品名称
    @Expose
    @SerializedName("MCU")
    private int mcu;//最小变动价位
    @Expose
    @SerializedName("CTT")
    private int ctt;//商品交易类型
    @Expose
    @SerializedName("PLATE")
    private int plate;//商品板块
    @Expose
    @SerializedName("URL")
    private String url;//商品详情地址


    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getCcod() {
        return ccod;
    }

    public void setCcod(String ccod) {
        this.ccod = ccod;
    }

    public String getCnam() {
        return cnam;
    }

    public void setCnam(String cnam) {
        this.cnam = cnam;
    }

    public int getMcu() {
        return mcu;
    }

    public void setMcu(int mcu) {
        this.mcu = mcu;
    }

    public int getCtt() {
        return ctt;
    }

    public void setCtt(int ctt) {
        this.ctt = ctt;
    }

    public int getPlate() {
        return plate;
    }

    public void setPlate(int plate) {
        this.plate = plate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
