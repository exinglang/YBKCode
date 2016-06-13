package com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanshuo on 16/4/27.
 */
public class WuDangData  implements Serializable {

    @Expose
    @SerializedName("FIVN")
    private int fivn;//五档序号
    @Expose
    @SerializedName("BUYD")
    private List<WuDangBuy> buyd;//买价数据
    @Expose
    @SerializedName("SELD")
    private List<WuDangSell> seld;//卖价数据

    public int getFivn() {
        return fivn;
    }

    public void setFivn(int fivn) {
        this.fivn = fivn;
    }

    public List<WuDangBuy> getBuyd() {
        return buyd;
    }

    public void setBuyd(List<WuDangBuy> buyd) {
        this.buyd = buyd;
    }

    public List<WuDangSell> getSeld() {
        return seld;
    }

    public void setSeld(List<WuDangSell> seld) {
        this.seld = seld;
    }
}
