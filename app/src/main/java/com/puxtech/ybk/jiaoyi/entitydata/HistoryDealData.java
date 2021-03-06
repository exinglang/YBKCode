package com.puxtech.ybk.jiaoyi.entitydata;

import java.io.Serializable;

/**
 * <b>Description:</b>
 * <p>
 * </p>
 */
public class HistoryDealData implements Serializable {

    private String TRADE_NO;
    private String COMMODITY_ID;
    private String QUANTITY;
    private String PRICE;
    private String TRADE_FEE;
    private String HOLD_TIME;
    private String BS_FLAG;
    private String TRADE_TYPE;
    private String BS_FLAG_CH;
    private String TRADE_TYPE_CH;

//    总记录条	TTLREC
//    成交量总计	QUANTITY_SUM
//    成交金额总计	TRADE_FUNDS_SUM
//    手续费总计	TRADE_FEE_SUM

//    成交单号	TRADE_NO
//    商品ID	COMMODITY_ID
//    成交量	QUANTITY
//    成交价	PRICE
//    手续费	TRADE_FEE
//    成交时间	TRADE_TIME
//    买卖标记	BS_FLAG
//    成交类型	TRADE_TYPE


    public String getTRADE_NO() {
        return TRADE_NO;
    }

    public void setTRADE_NO(String TRADE_NO) {
        this.TRADE_NO = TRADE_NO;
    }

    public String getCOMMODITY_ID() {
        return COMMODITY_ID;
    }

    public void setCOMMODITY_ID(String COMMODITY_ID) {
        this.COMMODITY_ID = COMMODITY_ID;
    }

    public String getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getTRADE_FEE() {
        return TRADE_FEE;
    }

    public void setTRADE_FEE(String TRADE_FEE) {
        this.TRADE_FEE = TRADE_FEE;
    }

    public String getHOLD_TIME() {
        return HOLD_TIME;
    }

    public void setHOLD_TIME(String HOLD_TIME) {
        this.HOLD_TIME = HOLD_TIME;
    }

    public String getBS_FLAG() {
        return BS_FLAG;
    }

    public void setBS_FLAG(String BS_FLAG) {
        this.BS_FLAG = BS_FLAG;
    }

    public String getTRADE_TYPE() {
        return TRADE_TYPE;
    }

    public void setTRADE_TYPE(String TRADE_TYPE) {
        this.TRADE_TYPE = TRADE_TYPE;
    }

    public String getBS_FLAG_CH() {
        return BS_FLAG_CH;
    }

    public void setBS_FLAG_CH(String BS_FLAG_CH) {
        this.BS_FLAG_CH = BS_FLAG_CH;
    }

    public String getTRADE_TYPE_CH() {
        return TRADE_TYPE_CH;
    }

    public void setTRADE_TYPE_CH(String TRADE_TYPE_CH) {
        this.TRADE_TYPE_CH = TRADE_TYPE_CH;
    }
}
