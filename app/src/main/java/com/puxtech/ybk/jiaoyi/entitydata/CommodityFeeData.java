package com.puxtech.ybk.jiaoyi.entitydata;

import java.io.Serializable;

/**
 * <b>Description:</b>
 * <p>
 * </p>
 *
 * @Package com.happyinsource.htjy.android.entity
 */
public class CommodityFeeData implements Serializable {

    //                商品ID	COMMODITY_ID
//                分组ID	GROUP_ID
//                费用项目	FEE_ITEM
//                买费用系数	BUY_FEERATE
//                卖费用系数	SELL_FEERATE
//                市场留存系数	MKT_FEERATE
//                手续费算法		FEE_ALGR

    String COMMODITY_ID;
  String GROUP_ID ;
  String FEE_ITEM;
  String BUY_FEERATE;
  String SELL_FEERATE;
  String MKT_FEERATE;
  String FEE_ALGR;


    public String getCOMMODITY_ID() {
        return COMMODITY_ID;
    }

    public void setCOMMODITY_ID(String COMMODITY_ID) {
        this.COMMODITY_ID = COMMODITY_ID;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(String GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getFEE_ITEM() {
        return FEE_ITEM;
    }

    public void setFEE_ITEM(String FEE_ITEM) {
        this.FEE_ITEM = FEE_ITEM;
    }

    public String getBUY_FEERATE() {
        return BUY_FEERATE;
    }

    public void setBUY_FEERATE(String BUY_FEERATE) {
        this.BUY_FEERATE = BUY_FEERATE;
    }

    public String getSELL_FEERATE() {
        return SELL_FEERATE;
    }

    public void setSELL_FEERATE(String SELL_FEERATE) {
        this.SELL_FEERATE = SELL_FEERATE;
    }

    public String getMKT_FEERATE() {
        return MKT_FEERATE;
    }

    public void setMKT_FEERATE(String MKT_FEERATE) {
        this.MKT_FEERATE = MKT_FEERATE;
    }

    public String getFEE_ALGR() {
        return FEE_ALGR;
    }

    public void setFEE_ALGR(String FEE_ALGR) {
        this.FEE_ALGR = FEE_ALGR;
    }
}
