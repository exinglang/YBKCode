package com.puxtech.ybk.hangqing.jsondata.getplates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 商品板块
 */
public class Plate {

    @Expose
    @SerializedName("PLAT")
    private int plat;
    @Expose
    @SerializedName("NAME")
    private String name;

    public int getPlat() {
        return plat;
    }

    public void setPlat(int plat) {
        this.plat = plat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
