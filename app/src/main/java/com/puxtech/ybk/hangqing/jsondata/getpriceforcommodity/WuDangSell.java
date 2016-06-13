package com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fanshuo on 16/4/27.
 */
public class WuDangSell  implements Serializable {

    @Expose
    @SerializedName("SELP")
    private float selp;//买价
    @Expose
    @SerializedName("LIST")
    private int list;//挂牌
    @Expose
    @SerializedName("DLIS")
    private int dlis;//摘牌

    public float getSelp() {
        return selp;
    }

    public void setSelp(float selp) {
        this.selp = selp;
    }

    public int getList() {
        return list;
    }

    public void setList(int list) {
        this.list = list;
    }

    public int getDlis() {
        return dlis;
    }

    public void setDlis(int dlis) {
        this.dlis = dlis;
    }
    
}
