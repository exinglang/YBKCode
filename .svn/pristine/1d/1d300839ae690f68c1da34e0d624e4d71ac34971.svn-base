package com.puxtech.ybk.hangqing.jsondata.getshorttimeline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fanshuo on 16/5/3.
 */
public class TradeDay {

    @Expose
    @SerializedName("TDAY")
    private String tday;//交易日yyyyMMdd
    @Expose
    @SerializedName("TDLL")
    private List<TradeDayRecord> tdll;//当前交易日记录列表

    public String getTday() {
        return tday;
    }

    public void setTday(String tday) {
        this.tday = tday;
    }

    public List<TradeDayRecord> getTdll() {
        return tdll;
    }

    public void setTdll(List<TradeDayRecord> tdll) {
        this.tdll = tdll;
    }
}
