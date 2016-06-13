package com.puxtech.ybk.hangqing.charts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by fanshuo on 16/5/12.
 */
public class KLinePointModel {

    private long time;
    private float openPrice;
    private float closePrice;
    private float highestPrice;
    private float lowestPrice;
    private int volume;
    private int priceId;

    //十字线使用
    private float centerX;
    private float closePriceY;
    private String change = "0.00%";// 改变的比率
    private int upOrDown = 0;// 涨1/跌-1/平0
    private int highUpOrDown = 0; // 涨1/跌-1/平0;
    private int lowUpOrDown = 0; // 涨1/跌-1/平0;
    private int openUpOrDown = 0; // 涨1/跌-1/平0;

    //pma
    private float pma5,pma10,pma30;
    // MACD
    private float shortEMA;
    private float longEMA;
    private float dif;
    private float dea;
    private float macd;

    // KDJ
    private float rsv;
    private float k;
    private float d;
    private float j;

    public KLinePointModel(long time, float openPrice, float closePrice, float highestPrice, float lowestPrice, int volume, int priceId) {
        this.time = time;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.volume = volume;
        this.priceId = priceId;
    }

    //public methods
    public String getFormatDate() {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            String t = format.format(new Date(this.time));
            return t.split(" ")[0];
        } catch (Exception e) {
            e.printStackTrace();
            return "0000-00-00";
        }
    }

    /**
     * 返回HH:mm
     *
     * @return
     */
    public String getFormatTime() {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            format.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            String t = format.format(new Date(this.time));
            String timeStr = t.split(" ")[1];
            return timeStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "00:00";
        }
    }

    //getters and setters

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(float openPrice) {
        this.openPrice = openPrice;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(float closePrice) {
        this.closePrice = closePrice;
    }

    public float getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(float highestPrice) {
        this.highestPrice = highestPrice;
    }

    public float getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(float lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getClosePriceY() {
        return closePriceY;
    }

    public void setClosePriceY(float closePriceY) {
        this.closePriceY = closePriceY;
    }

    public float getPma5() {
        return pma5;
    }

    public void setPma5(float pma5) {
        this.pma5 = pma5;
    }

    public float getPma10() {
        return pma10;
    }

    public void setPma10(float pma10) {
        this.pma10 = pma10;
    }

    public float getPma30() {
        return pma30;
    }

    public void setPma30(float pma30) {
        this.pma30 = pma30;
    }

    public float getShortEMA() {
        return shortEMA;
    }

    public void setShortEMA(float shortEMA) {
        this.shortEMA = shortEMA;
    }

    public float getLongEMA() {
        return longEMA;
    }

    public void setLongEMA(float longEMA) {
        this.longEMA = longEMA;
    }

    public float getDif() {
        return dif;
    }

    public void setDif(float dif) {
        this.dif = dif;
    }

    public float getDea() {
        return dea;
    }

    public void setDea(float dea) {
        this.dea = dea;
    }

    public float getMacd() {
        return macd;
    }

    public void setMacd(float macd) {
        this.macd = macd;
    }

    public float getRsv() {
        return rsv;
    }

    public void setRsv(float rsv) {
        this.rsv = rsv;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public float getD() {
        return d;
    }

    public void setD(float d) {
        this.d = d;
    }

    public float getJ() {
        return j;
    }

    public void setJ(float j) {
        this.j = j;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public int getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(int upOrDown) {
        this.upOrDown = upOrDown;
    }

    public int getHighUpOrDown() {
        return highUpOrDown;
    }

    public void setHighUpOrDown(int highUpOrDown) {
        this.highUpOrDown = highUpOrDown;
    }

    public int getLowUpOrDown() {
        return lowUpOrDown;
    }

    public void setLowUpOrDown(int lowUpOrDown) {
        this.lowUpOrDown = lowUpOrDown;
    }

    public int getOpenUpOrDown() {
        return openUpOrDown;
    }

    public void setOpenUpOrDown(int openUpOrDown) {
        this.openUpOrDown = openUpOrDown;
    }
}
