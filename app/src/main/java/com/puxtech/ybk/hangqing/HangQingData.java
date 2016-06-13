package com.puxtech.ybk.hangqing;

import android.content.Context;

import com.puxtech.ybk.hangqing.controller.HangQingDetailController;
import com.puxtech.ybk.hangqing.jsondata.getcommodities.Commodity;
import com.puxtech.ybk.hangqing.jsondata.getmarkets.Market;
import com.puxtech.ybk.hangqing.jsondata.getplates.Plate;
import com.puxtech.ybk.hangqing.jsondata.getpriceformcommodities.CommodityPrice;
import com.puxtech.ybk.hangqing.jsondata.gettradetime.GetTradeTimeRepBody;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fanshuo on 16/4/21.
 */
public class HangQingData {

    /**
     * 获取行情详情（价格、五档明细等）的广播action
     */
    public static final String ACTION_PRICE_FOR_COMMODITY = "HangQingData.ACTION_PRICE_FOR_COMMODITY";

    /**
     * 按数量查分时的广播action
     */
    public static final String ACTION_SHORT_TIME_LINE = "HangQingData.ACTION_SHORT_TIME_LINE";

    /**
     * 向右刷新分时的广播action
     */
    public static final String ACTION_NEW_SHORT_TIME_LINE = "HangQingData.ACTION_NEW_SHORT_TIME_LINE";

    /**
     * 获取行情详情（价格、五档明细等）的Bundle key，value的类型为{@linkplain com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.GetPriceForCommodityRepBody GetPriceForCommodityRepBody}
     */
    public static final String BUNDLE_KEY_PRICE_FOR_COMMODITY = "HangQingData.BUNDLE_KEY_PRICE_FOR_COMMODITY";

    /**
     * 获取分时的Bundle key，value的类型为{@linkplain com.puxtech.ybk.hangqing.charts.FenShiChartModel FenShiChartModel}
     */
    public static final String BUNDLE_KEY_FENSHI_CHART_MODEL = "HangQingData.BUNDLE_KEY_FENSHI_CHART_MODEL";

    private String sessionId;//行情session
    private int marketId;//市场id
    private int panId;//盘id
    private List<Plate> plates;//板块信息
    private List<Commodity> commodities;//商品列表
    private HashMap<String,Integer> commodityCodeIdMap;//商品code和商品id映射表
    private List<CommodityPrice> commoditiesPrice;//商品盘口信息
    private HangQingDetailController hangQingDetailController;//行情详情控制器
    private GetTradeTimeRepBody tradeTime;//交易时间

    public void init(Context context){
        hangQingDetailController = new HangQingDetailController(context);
        commodityCodeIdMap = new HashMap<>();
    }

    //public methods

    /**
     * 开始刷新商品详情，调用者的不再需要时，务必调用{@link #stopRefreshHangQingDetail()}
     * @param commodityId 商品id
     */
    public void startRefreshHangQingDetail(int commodityId){
        hangQingDetailController.startRequestPriceForCommodity(commodityId);
    }

    /**
     * 开始刷新商品详情，调用者的不再需要时，务必调用{@link #stopRefreshHangQingDetail()}
     * @param commodityCode 商品code
     */
    public void startRefreshHangQingDetail(String commodityCode){
        //如果没有找到就不刷新
        if(commodityCodeIdMap == null){
            return;
        }
        Integer commodityId = commodityCodeIdMap.get(commodityCode);
        if(commodityId == null){
            return;
        }
        startRefreshHangQingDetail(commodityId);
    }

    /**
     * 停止刷新商品详情
     */
    public void stopRefreshHangQingDetail(){
        hangQingDetailController.stopRequestPriceForCommodity();
    }

    /**
     * 根据商品编号获取一口价信息
     * @return <b>如果没找到就返回null，请做好处理<b/>
     */
    public CommodityPrice getCommodityPriceByCommodityCode(String code){

        if(commodityCodeIdMap == null || commodityCodeIdMap.size() == 0){
            return null;
        }

        if(commoditiesPrice == null || commoditiesPrice.size() == 0){
            return null;
        }
        int commodityId = commodityCodeIdMap.get(code);
        for (int i = 0; i < commoditiesPrice.size(); i++) {
            CommodityPrice commodityPrice = commoditiesPrice.get(i);
            if(commodityPrice.getCid() == commodityId){
                return commodityPrice;
            }
        }
        return null;
    }

    /**
     * 根据商品编号获取商品信息
     */
    public Commodity getCommodityById(int commodityId){
        if(commodities== null || commodities.size() == 0){
            return null;
        }
        for (int i = 0; i < commodities.size(); i++) {
            Commodity commodity = commodities.get(i);
            if(commodity.getVid() == commodityId){
                return commodity;
            }
        }
        return null;
    }

    //getters and seters
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public int getPanId() {
        return panId;
    }

    public void setPanId(int panId) {
        this.panId = panId;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    public List<CommodityPrice> getCommoditiesPrice() {
        return commoditiesPrice;
    }

    public void setCommoditiesPrice(List<CommodityPrice> commoditiesPrice) {
        this.commoditiesPrice = commoditiesPrice;
    }

    public HashMap<String, Integer> getCommodityCodeIdMap() {
        return commodityCodeIdMap;
    }

    public void setCommodityCodeIdMap(HashMap<String, Integer> commodityCodeIdMap) {
        this.commodityCodeIdMap = commodityCodeIdMap;
    }

    public GetTradeTimeRepBody getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(GetTradeTimeRepBody tradeTime) {
        this.tradeTime = tradeTime;
    }

    public HangQingDetailController getHangQingDetailController() {
        return hangQingDetailController;
    }
}
