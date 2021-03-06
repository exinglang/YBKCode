package com.puxtech.ybk.hangqing.model;

/**
 * Created by fanshuo on 16/4/26.
 */
public class ListRightModel {

    private String name;//商品名称
    private int id;//商品编号
    private String code;//商品编码
    private int plateId;//板块id
    private String xianJiaText = "--";
    private String zhangDieText = "--";
    private String zhangFuText = "--";
    private String zuoShouText = "--";
    private String chengJiaoLiangText = "--";
    private String chengJiaoEText = "--";
    private String zuiGaoText = "--";
    private String zuiDiText = "--";
    private String zhenFuText = "--";
    private float xianJia;
    private float zhangDie;
    private float zuiGao;
    private float zuiDi;
    private float zuoShou;

    public ListRightModel(String name, int id, String code, int plateId) {
        this.name = name;
        this.id = id;
        this.code = code;
        this.plateId = plateId;
    }

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXianJiaText() {
        return xianJiaText;
    }

    public void setXianJiaText(String xianJiaText) {
        this.xianJiaText = xianJiaText;
    }

    public String getZhangDieText() {
        return zhangDieText;
    }

    public void setZhangDieText(String zhangDieText) {
        this.zhangDieText = zhangDieText;
    }

    public String getZhangFuText() {
        return zhangFuText;
    }

    public void setZhangFuText(String zhangFuText) {
        this.zhangFuText = zhangFuText;
    }

    public String getZuoShouText() {
        return zuoShouText;
    }

    public void setZuoShouText(String zuoShouText) {
        this.zuoShouText = zuoShouText;
    }

    public String getChengJiaoLiangText() {
        return chengJiaoLiangText;
    }

    public void setChengJiaoLiangText(String chengJiaoLiangText) {
        this.chengJiaoLiangText = chengJiaoLiangText;
    }

    public String getChengJiaoEText() {
        return chengJiaoEText;
    }

    public void setChengJiaoEText(String chengJiaoEText) {
        this.chengJiaoEText = chengJiaoEText;
    }

    public String getZuiGaoText() {
        return zuiGaoText;
    }

    public void setZuiGaoText(String zuiGaoText) {
        this.zuiGaoText = zuiGaoText;
    }

    public String getZuiDiText() {
        return zuiDiText;
    }

    public void setZuiDiText(String zuiDiText) {
        this.zuiDiText = zuiDiText;
    }

    public String getZhenFuText() {
        return zhenFuText;
    }

    public void setZhenFuText(String zhenFuText) {
        this.zhenFuText = zhenFuText;
    }

    public float getXianJia() {
        return xianJia;
    }

    public void setXianJia(float xianJia) {
        this.xianJia = xianJia;
    }

    public float getZhangDie() {
        return zhangDie;
    }

    public void setZhangDie(float zhangDie) {
        this.zhangDie = zhangDie;
    }

    public float getZuiGao() {
        return zuiGao;
    }

    public void setZuiGao(float zuiGao) {
        this.zuiGao = zuiGao;
    }

    public float getZuiDi() {
        return zuiDi;
    }

    public void setZuiDi(float zuiDi) {
        this.zuiDi = zuiDi;
    }

    public float getZuoShou() {
        return zuoShou;
    }

    public void setZuoShou(float zuoShou) {
        this.zuoShou = zuoShou;
    }
}
