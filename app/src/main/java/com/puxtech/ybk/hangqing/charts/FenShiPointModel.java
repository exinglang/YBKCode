package com.puxtech.ybk.hangqing.charts;

import java.io.Serializable;

/**
 * Created by fanshuo on 16/5/3.
 */
public class FenShiPointModel implements Serializable{

    private long timeLong;
    private String timeStr;
    private float lastPrice;
    private float averagePrice;
    private int volume;
    private float pointX,pointY,averageY;

    public FenShiPointModel(long timeLong, String timeStr, float lastPrice, float averagePrice, int volume) {
        this.timeLong = timeLong;
        this.timeStr = timeStr;
        this.lastPrice = lastPrice;
        this.averagePrice = averagePrice;
        this.volume = volume;
    }

    public long getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(long timeLong) {
        this.timeLong = timeLong;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public float getPointX() {
        return pointX;
    }

    public void setPointX(float pointX) {
        this.pointX = pointX;
    }

    public float getPointY() {
        return pointY;
    }

    public void setPointY(float pointY) {
        this.pointY = pointY;
    }

    public float getAverageY() {
        return averageY;
    }

    public void setAverageY(float averageY) {
        this.averageY = averageY;
    }
}
