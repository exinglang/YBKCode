package com.puxtech.ybk.jiaoyi.entitydata;

import java.io.Serializable;

/**
 * <b>Description:</b>
 * <p>
 * </p>
 */
public class TuoGuanListData implements Serializable {

//    商品ID	COMMODITY_ID	字符串
//    商品名称	COMMODITY_NAME	字符串
//    托管计划NO	PLAN_NO	字符串
//    托管状态	STATUS	数字	计划状态
//    0 编辑中
//    1 开始执行
//    2 托管申请期
//    3 托管申请结束
//    4 托管入库期
//    5 托管入库结束
//    6 托管计划完成
//    10 取消计划
//            计划类型
//    TYPE	数字	计划类型
//    1 普通托管
//    2 再托管
//    托管申请开始日期	APPLY_ST	数字
//    托管申请结束日期	APPLY_END	数字
//    托管开始日期	ENTRUST_ST	数字
//    托管结束日期	ENTRUST_END	数字


    private String COMMODITY_ID;
    private String COMMODITY_NAME;
    private String PLAN_NO ;
    private String STATUS;
    private String TYPE;
    private String STATUS_CH;
    private String TYPE_CH;
    private String DESCRIPTION;
    private String APPLY_ST;
    private String APPLY_END;
    private String ENTRUST_ST;
    private String ENTRUST_END;


    private String number;//进行申购请求时,用户输入的申购数量

    public String getTYPE_CH() {
        return TYPE_CH;
    }

    public void setTYPE_CH(String TYPE_CH) {
        this.TYPE_CH = TYPE_CH;
    }

    public String getSTATUS_CH() {
        return STATUS_CH;
    }

    public void setSTATUS_CH(String STATUS_CH) {
        this.STATUS_CH = STATUS_CH;
    }

    public String getCOMMODITY_ID() {
        return COMMODITY_ID;
    }

    public void setCOMMODITY_ID(String COMMODITY_ID) {
        this.COMMODITY_ID = COMMODITY_ID;
    }

    public String getCOMMODITY_NAME() {
        return COMMODITY_NAME;
    }

    public void setCOMMODITY_NAME(String COMMODITY_NAME) {
        this.COMMODITY_NAME = COMMODITY_NAME;
    }

    public String getPLAN_NO() {
        return PLAN_NO;
    }

    public void setPLAN_NO(String PLAN_NO) {
        this.PLAN_NO = PLAN_NO;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getAPPLY_ST() {
        return APPLY_ST;
    }

    public void setAPPLY_ST(String APPLY_ST) {
        this.APPLY_ST = APPLY_ST;
    }

    public String getAPPLY_END() {
        return APPLY_END;
    }

    public void setAPPLY_END(String APPLY_END) {
        this.APPLY_END = APPLY_END;
    }

    public String getENTRUST_ST() {
        return ENTRUST_ST;
    }

    public void setENTRUST_ST(String ENTRUST_ST) {
        this.ENTRUST_ST = ENTRUST_ST;
    }

    public String getENTRUST_END() {
        return ENTRUST_END;
    }

    public void setENTRUST_END(String ENTRUST_END) {
        this.ENTRUST_END = ENTRUST_END;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
