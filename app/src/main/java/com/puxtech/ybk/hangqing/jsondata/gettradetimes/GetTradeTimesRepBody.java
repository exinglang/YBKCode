package com.puxtech.ybk.hangqing.jsondata.gettradetimes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 交易节查询返回的rep
 */
public class GetTradeTimesRepBody {

    @Expose
    @SerializedName("TTL")
    private List<TradeTimes> ttl;


    public List<TradeTimes> getTtl() {
        return ttl;
    }

    public void setTtl(List<TradeTimes> ttl) {
        this.ttl = ttl;
    }
}
