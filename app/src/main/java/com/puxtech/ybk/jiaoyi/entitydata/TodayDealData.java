package com.puxtech.ybk.jiaoyi.entitydata;

import java.io.Serializable;

/**
 * <b>Description:</b>
 * <p>
 * </p>
 */
public class TodayDealData implements Serializable {

//    总记录条	TTLREC	数字
//    结算日期	CLEAR_DATE	数字
//    成交单号	TRADE_NO	字符串
//    商品	COMMODITY_NAME	数字
//    成交量	QUANTITY	数字
//    成交价	PRICE	数字
//    成交金额	TRADE_FUNDS	数字
//    手续费	TRADE_FEE	数字
//    成交时间	TRADE_TIME	数字
//    买卖标记	BS_FLAG	数字	1-买 2-卖
//    成交类型	TR_T	数字	1-市价成交，用户下单；2-市价成交，电话下单； 3-市价成交，系统下单；4-自动强平，系统下单； 5-手动强平，系统下单；6-指价成交 ，系统下单； 7-指价成交，电话下单；8-指价成交，批量指价下单

//    private String CLEAR_DATE;
    private String TRADE_NO;
    private String COMMODITY_NAME;
    private String QUANTITY;
    private String PRICE;
    private String TRADE_FEE;
    private String TRADE_TIME;
    private String BS_FLAG;
    private String TR_T;
    private String BS_FLAG_CH;
    private String TR_T_CH;




    public String getBS_FLAG_CH() {
        return BS_FLAG_CH;
    }

    public void setBS_FLAG_CH(String BS_FLAG_CH) {
        this.BS_FLAG_CH = BS_FLAG_CH;
    }

    public String getTR_T_CH() {
        return TR_T_CH;
    }

    public void setTR_T_CH(String TR_T_CH) {
        this.TR_T_CH = TR_T_CH;
    }

//
//    public String getCLEAR_DATE() {
//        return CLEAR_DATE;
//    }
//
//    public void setCLEAR_DATE(String CLEAR_DATE) {
//        this.CLEAR_DATE = CLEAR_DATE;
//    }

    public String getTRADE_NO() {
        return TRADE_NO;
    }

    public void setTRADE_NO(String TRADE_NO) {
        this.TRADE_NO = TRADE_NO;
    }

    public String getCOMMODITY_NAME() {
        return COMMODITY_NAME;
    }

    public void setCOMMODITY_NAME(String COMMODITY_NAME) {
        this.COMMODITY_NAME = COMMODITY_NAME;
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



    public String getBS_FLAG() {
        return BS_FLAG;
    }

    public void setBS_FLAG(String BS_FLAG) {
        this.BS_FLAG = BS_FLAG;
    }

    public String getTR_T() {
        return TR_T;
    }

    public void setTR_T(String TR_T) {
        this.TR_T = TR_T;
    }

    public String getTRADE_TIME() {
        return TRADE_TIME;
    }

    public void setTRADE_TIME(String TRADE_TIME) {
        this.TRADE_TIME = TRADE_TIME;
    }
}
