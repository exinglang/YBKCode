package com.puxtech.ybk.hangqing.jsondata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 通用格式中的mmts
 */
public class RepMMTS<T> {

    @Expose
    @SerializedName("REPH")
    private RepHeader repHeader;
    @Expose
    @SerializedName("REPB")
    private T repBody;


    public RepHeader getRepHeader() {
        return repHeader;
    }

    public void setRepHeader(RepHeader repHeader) {
        this.repHeader = repHeader;
    }

    public T getRepBody() {
        return repBody;
    }

    public void setRepBody(T repBody) {
        this.repBody = repBody;
    }
}
