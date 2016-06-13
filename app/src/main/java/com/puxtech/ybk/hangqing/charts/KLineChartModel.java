package com.puxtech.ybk.hangqing.charts;

import java.util.List;

/**
 * Created by fanshuo on 16/5/12.
 */
public class KLineChartModel {

    private int mcu;//最小变动价位
    private List<KLinePointModel> points;

    //public methods

    public int searchHightestVolume(){
        int highestVolume=-1;
        for (int i = 0; i < points.size(); i++) {
            int volume = points.get(i).getVolume();
            if (volume > highestVolume) {
                highestVolume = volume;
            }
        }
        return highestVolume;
    }

    //getters and setters

    public int getMcu() {
        return mcu;
    }

    public void setMcu(int mcu) {
        this.mcu = mcu;
    }

    public List<KLinePointModel> getPoints() {
        return points;
    }

    public void setPoints(List<KLinePointModel> points) {
        this.points = points;
    }
}
