package com.puxtech.ybk;

import android.app.Application;

import com.puxtech.ybk.hangqing.HangQingData;
import com.puxtech.ybk.jiaoyi.entitydata.TradeEntity;
import com.puxtech.ybk.qidong.QiDongData;

/**
 * Created by fanshuo on 16/4/20.
 */
public class MyApplication extends Application {

    QiDongData qiDongData;
    TradeEntity tradeEntity;
    HangQingData hangQingData;
    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    /**
     * 初始化数据，只调用各数据类的公开接口，不写具体逻辑。
     */
    private void initData() {
        qiDongData = new QiDongData();
        tradeEntity=new TradeEntity();
        qiDongData.init(this);
        hangQingData = new HangQingData();
        hangQingData.init(this);
    }

    public QiDongData getQiDongData() {
        return qiDongData;
    }

    public TradeEntity getTradeEntity() {
        return tradeEntity;
    }

    public HangQingData getHangQingData() {
        return hangQingData;
    }
}
