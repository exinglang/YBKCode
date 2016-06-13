package com.puxtech.ybk.hangqing.jsondata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 返回的json通用数据格式
 */
public class RepData<T> {

    @Expose
    @SerializedName("MMTS")
    private RepMMTS<T> mmts;

    public RepMMTS<T> getMmts() {
        return mmts;
    }

    public void setMmts(RepMMTS<T> mmts) {
        this.mmts = mmts;
    }
}
