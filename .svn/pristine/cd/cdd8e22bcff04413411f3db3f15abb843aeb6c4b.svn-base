package com.puxtech.ybk.hangqing.charts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by fanshuo on 16/5/3.
 */
public class TradeTimeManager {

    private long startTime;
    private long endTime;
    private String[] bottomTextArray;
    private int totalMinutes;
    private int[] bottomOffsetMinuteArray;

    public TradeTimeManager(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        init();
    }

    private void init() {
        totalMinutes = (int) ((endTime-startTime)/1000/60);

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));

        bottomTextArray = new String[2];
        bottomTextArray[0] = sdf.format(new Date(startTime));
        bottomTextArray[1] = sdf.format(new Date(endTime));

        bottomOffsetMinuteArray = new int[2];
        bottomOffsetMinuteArray[0] = 0;
        bottomOffsetMinuteArray[1] = totalMinutes;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String[] getBottomTextArray() {
        return bottomTextArray;
    }

    public void setBottomTextArray(String[] bottomTextArray) {
        this.bottomTextArray = bottomTextArray;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public int[] getBottomOffsetMinuteArray() {
        return bottomOffsetMinuteArray;
    }

    public void setBottomOffsetMinuteArray(int[] bottomOffsetMinuteArray) {
        this.bottomOffsetMinuteArray = bottomOffsetMinuteArray;
    }
}
