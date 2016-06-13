package com.puxtech.ybk.jiaoyi.entitydata;

import java.io.Serializable;

/**
 * <b>Description:</b>
 * <p>
 * </p>
 */
public class TuoGuanOrderData implements Serializable {

    private String APPLY_ID;
    private String PLAN_NO;
    private String COMMODITY_ID;
    private String COMMODITY_NAME;
    private String HOUSE_ID;
    private String QTY;
    private String IN_QTY;
    private String LIST_QTY;
    private String ISSUE_QTY;
    private String LIMIT_QTY;
    private String STATUS;
    private String STATUS_CH;

    private String TIME;


    public String getAPPLY_ID() {
        return APPLY_ID;
    }

    public void setAPPLY_ID(String APPLY_ID) {
        this.APPLY_ID = APPLY_ID;
    }

    public String getPLAN_NO() {
        return PLAN_NO;
    }

    public void setPLAN_NO(String PLAN_NO) {
        this.PLAN_NO = PLAN_NO;
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

    public String getHOUSE_ID() {
        return HOUSE_ID;
    }

    public void setHOUSE_ID(String HOUSE_ID) {
        this.HOUSE_ID = HOUSE_ID;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getIN_QTY() {
        return IN_QTY;
    }

    public void setIN_QTY(String IN_QTY) {
        this.IN_QTY = IN_QTY;
    }

    public String getLIST_QTY() {
        return LIST_QTY;
    }

    public void setLIST_QTY(String LIST_QTY) {
        this.LIST_QTY = LIST_QTY;
    }

    public String getISSUE_QTY() {
        return ISSUE_QTY;
    }

    public void setISSUE_QTY(String ISSUE_QTY) {
        this.ISSUE_QTY = ISSUE_QTY;
    }

    public String getLIMIT_QTY() {
        return LIMIT_QTY;
    }

    public void setLIMIT_QTY(String LIMIT_QTY) {
        this.LIMIT_QTY = LIMIT_QTY;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTATUS_CH() {
        return STATUS_CH;
    }

    public void setSTATUS_CH(String STATUS_CH) {
        this.STATUS_CH = STATUS_CH;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }
}
