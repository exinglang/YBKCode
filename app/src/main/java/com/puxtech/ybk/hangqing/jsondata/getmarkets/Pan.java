package com.puxtech.ybk.hangqing.jsondata.getmarkets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 市场下面的盘
 */
public class Pan {

    @Expose
    @SerializedName("SMID")
    private int pandId;
    @Expose
    @SerializedName("SMNA")
    private String panName;

    public int getPandId() {
        return pandId;
    }

    public void setPandId(int pandId) {
        this.pandId = pandId;
    }

    public String getPanName() {
        return panName;
    }

    public void setPanName(String panName) {
        this.panName = panName;
    }
}
