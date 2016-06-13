package com.puxtech.ybk.jiaoyi;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.entitydata.CommodityData;
import com.puxtech.ybk.jiaoyi.entitydata.HistoryDealData;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.entitydata.ShengGouOrderData;
import com.puxtech.ybk.jiaoyi.entitydata.TiHuoOrderData;
import com.puxtech.ybk.jiaoyi.entitydata.TodayDealData;
import com.puxtech.ybk.jiaoyi.entitydata.TodayOrderData;
import com.puxtech.ybk.jiaoyi.entitydata.TuoGuanOrderData;
import com.puxtech.ybk.util.AES;
import com.puxtech.ybk.util.ActivityUtils;
import com.puxtech.ybk.util.Base64;
import com.puxtech.ybk.util.SharedPreferenceManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Created by mac on 16/4/21.
 */
public class TradeHelper {
    public static final String PROMPT = "PROMPT";

    public static final String SELECTTIME_NOT_BEYOND_TODAY = "选择的时间不得晚于今天";
    public static final String STARTTIME_MORE_THAN_NOW = "开始日期不得晚于今天";
    public static final String ENDTIME_MORE_THAN_NOW = "结束日期不得晚于今天";
    public static final String FABUENDTIME_MORE_THAN_NOW = "发布结束日期不得晚于今天";
    public static final String START_NOT_EARLY_ONE_YEAR = "开始时间不得早于一年以前的日期";
    public static final String SELECT_TIME = "选择时间";
    public static final String CENTER_TIME_SHORT_ONT_MONTHS = "查询日期间隔不能大于三十一天";

    public static final String STARTTIME_MORE_ENDTIME_IN = "发布开始时间不得晚于结束时间";
    public static final String STARTTIME_MORE_ENDTIME = "开始时间不得晚于结束时间";
    public static final String STARTTIME_MORE_ENDTIME_UN = "失效开始时间不得晚于结束时间";
    MyApplication myApplication;


    public TradeHelper(Context context) {
        myApplication = (MyApplication) context.getApplicationContext();
    }

    //退出登录,
    public static void logoutOnly(Context context) {
        Intent intent = new Intent();
        logout(context, intent);

    }

    //退出登录,accountId:账号
    public static void logoutWithAccountId(Context context, String accountId) {
        Intent intent = new Intent();
        SharedPreferenceManager spf_WX = new SharedPreferenceManager(context, SharedPreferenceManager.LOGIN);
        spf_WX.putString(SharedPreferenceManager.LOGIN_LAST_ACCOUNT, accountId);
        logout(context, intent);
    }

    //退出登录,heartbeat过期
    public static void logoutWithprompt(Context context, String prompt) {
        Intent intent = new Intent();
        intent.putExtra(PROMPT, prompt);
//        SharedPreferenceManager spf_WX = new SharedPreferenceManager(context, SharedPreferenceManager.LOGIN);
//        spf_WX.putString(SharedPreferenceManager.LOGIN_LAST_ACCOUNT, accountId);
        logout(context, intent);
    }

    //    protected void FinishCurrentActivity(Context context) {
//        Activity activity=(Activity)context;
//        activity.finish();
//    }
    private static void logout(Context context, Intent intent) {
        MyApplication myApplication = (MyApplication) context.getApplicationContext();
        myApplication.getTradeEntity().setHasLogin(false);
        intent.setAction(Constant.LOGOFF);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static void userTouchReset(Context context) {
        Intent intent = new Intent();
        intent.setAction(Constant.USER_TOUCH_RESET);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);

//        Intent intent = new Intent();
//        intent.setAction(Constant.USER_TOUCH_RESET);
//        intent.setPackage(getPackageName());
//        sendBroadcast(intent);
    }


    /**
     * 初始化TitleBar 有子标题和后退按钮
     *
     * @param appCompatActivity appCompatActivity
     * @param subTitle          子标题
     * @param toolbar           titlebar
     */
    public static void initToolBarWithSubTitleAndFinishIcon(final AppCompatActivity appCompatActivity, String subTitle, Toolbar toolbar, TextView tv_toolbar) {
        toolbar.setTitle("");
        tv_toolbar.setText(subTitle);
//        toolbar.setHorizontalAlignment(SwingConstants.CENTER);
//        toolbar.setSubtitle(subTitle);
        toolbar.setNavigationIcon(R.drawable.home_title_btn_back);
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appCompatActivity.setResult(Constant.CODE_FAIL);
                appCompatActivity.finish();
            }
        });
    }

    /**
     * 初始化TitleBar 有子标题和后退按钮
     *
     * @param appCompatActivity appCompatActivity
     * @param subTitle          子标题
     * @param toolbar           titlebar
     */
    public static void initToolBarWithSubTitle(final AppCompatActivity appCompatActivity, String subTitle, Toolbar toolbar, TextView tv_toolbar) {
        toolbar.setTitle("");
        tv_toolbar.setText(subTitle);
//        toolbar.setHorizontalAlignment(SwingConstants.CENTER);
//        toolbar.setSubtitle(subTitle);
//        toolbar.setNavigationIcon(R.drawable.ic_back);
        appCompatActivity.setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                appCompatActivity.setResult(Constant.CODE_FAIL);
//                appCompatActivity.finish();
//            }
//        });
    }
    //根据商品代码 得到商品名称

    /**
     * 根据传进来的商品代码得到交易商品实体类s
     *
     * @param code 商品代码
     * @return 商品名称
     */
    public static CommodityData getCommodityDataByCode(Context context, String code) {
        for (CommodityData cd : ((MyApplication) context.getApplicationContext()).getTradeEntity().getTradeData().getCommodityDataList()) {
            if (cd.getCode().equals(code)) {
                return cd;
            }
        }
        return null;
    }


    // 获取token字段。 二进制
    public static String getToken(long time, String username, String pwd, byte[] logonKey) {

        // 登录加解密密钥，并不上传密码
        String result = "";
        try {
            byte[] data = username.getBytes(Constant.ENCODE);
            ByteBuffer buffer = ByteBuffer.allocate(data.length + 10);
            // 小段序，服务器在写入写出字节流时，全部为小段序。客户端需要与服务器统一
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            // 明文
//            if (Double
//                    .valueOf(myapp.getNyTrade().getSessionData().getVersion()) > 1.5) {
//                buffer.putShort((short) 0);
//                buffer.putLong(time);
//
//                buffer.putShort((short) data.length);
//                buffer.put(data);
//
//            }
            buffer.putShort((short) data.length);
            buffer.put(data);
            buffer.putLong(time);
            // 密文，并用base64编码
            result = Base64.encode(AES.encrypt(buffer.array(), logonKey));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void selectData(final Context context, final View view, final boolean needPanDuan) {
//        final int id = view.getId();
        ActivityUtils mActivityUtils = new ActivityUtils();
        final Calendar initCalendar = getCalendarOfString(((TextView) view).getText().toString());
        DatePickerDialog ds = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year,
                                          int monthOfYear, int dayOfMonth) {
                        GregorianCalendar selectGregorianCalendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        if (needPanDuan) {
                            //开始和结束日期都需要判断
                            if (isBeyondToday(selectGregorianCalendar)) {
                                //选择的日期是否超过了今天
                                ActivityUtils.showCenterToast(context,
                                        SELECTTIME_NOT_BEYOND_TODAY, Toast.LENGTH_LONG);
                                return;
                            } else if (isBeyondOneYear(selectGregorianCalendar, new GregorianCalendar())) {
                                //选择的日期是否超过了一年之前
                                ActivityUtils.showCenterToast(context,
                                        START_NOT_EARLY_ONE_YEAR, Toast.LENGTH_LONG);
                                return;
                            }
                        }
                        ((TextView) view).setText(ActivityUtils.getYYYYMMDDforTimeMillis(selectGregorianCalendar.getTimeInMillis()));
                    }
                }, initCalendar.get(Calendar.YEAR), initCalendar.get(Calendar.MONTH), initCalendar.get(Calendar.DAY_OF_MONTH));


        ds.setTitle(SELECT_TIME);
        ds.show();
    }

    //计算市值
    static public String getMarketValue(Context context) {

        try {


            MyApplication myApplication = (MyApplication) context.getApplicationContext();
            ArrayList<HoldDetailData> holdDetailDataList = myApplication.getTradeEntity().getTradeData().getHoldDetailDataList();
            double values = 0;
            for (HoldDetailData holdDetailData : holdDetailDataList) {
                double singleValue;
                CommodityData commodityData = TradeHelper.getCommodityDataByCode(context, holdDetailData.getCOMMODITYID());
                if (commodityData.getStatus().equals("1")) {
                    //不正确,暂时使用
                    singleValue = Double.valueOf(holdDetailData.getHOLDQTY()) * Double.valueOf(myApplication.getHangQingData().getCommodityPriceByCommodityCode(holdDetailData.getCOMMODITYID()).getZuiXinJia());
                } else if (commodityData.getStatus().equals("0")) {
                    singleValue =Double.valueOf(holdDetailData.getHOLDCOST());
                } else {
                    singleValue = Double.valueOf(holdDetailData.getHOLDQTY()) * Double.valueOf(myApplication.getHangQingData().getCommodityPriceByCommodityCode(holdDetailData.getCOMMODITYID()).getZuiXinJia());
                }

                values = values + singleValue;
            }
            return ActivityUtils.changeDouble(values + "");
        } catch (Exception e) {
           e.printStackTrace();
            return "--";
        }
    }

    /**
     * 判断传入的日期是否晚于今天
     *
     * @param selectData
     * @return
     */
    public static boolean isBeyondToday(GregorianCalendar selectData) {

        GregorianCalendar end = new GregorianCalendar();
        end.add(Calendar.DAY_OF_MONTH, -1);
        return isDateOneAfterDateTwo(selectData, end);

    }

    /**
     * 判断两个日期是否差距超过31天
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isDateOneBeyongMonthDateTwo(Calendar start, Calendar end) {
        return isBeyondSpecificDays(start, end, 31);
    }

    /**
     * 判断两个日期是否超过了一年
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isBeyondOneYear(GregorianCalendar start, GregorianCalendar end) {
        return isBeyondSpecificDays(start, end, 365);
    }

    /**
     * 判断两个日期是否超过指定的天数
     */
    public static boolean isBeyondSpecificDays(Calendar start, Calendar end, int specificDays) {
        if (start.after(end)) {
            return false;
        }
        long sl = start.getTimeInMillis();
        long el = end.getTimeInMillis();
        long days = ((el - sl) / (1000 * 60 * 60 * 24));
        if (days >= specificDays) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断第一个日期是否超过了第二个日期
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isDateOneAfterDateTwo(Calendar start, Calendar end) {
        end.add(Calendar.DAY_OF_MONTH, 1);
        if (end.after(start)) {
            return false;
        } else {

            return true;
        }
    }

    /**
     * 从yyyy-mm-dd的string格式,转换为calendar
     *
     * @return
     */
    public static Calendar getCalendarOfString(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//        String date = df.format(data);
//        System.out.println(date);

        Long timeMillis1 = null;
        try {
            timeMillis1 = df.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis1);

        return calendar;
    }

    public static LinkedHashMap<String, String> getChiCangDetailMap(Context context, HoldDetailData item) {
        LinkedHashMap map = new LinkedHashMap<>();
//                    交易商ID	FIRMID
//    商品代码	COMMODITYID
//    持仓数量	HOLDQTY
//    持仓成本	HOLDCOST
//    持仓均价	EVENPRICE
//    冻结数量	FROZENQTY
        map.put("商品名称", TradeHelper.getCommodityDataByCode(context, item.getCOMMODITYID()).getName());
        map.put("商品代码", item.getCOMMODITYID());
        map.put("持仓数量", item.getHOLDQTY());
        map.put("持仓成本", item.getHOLDCOST());
        map.put("持仓均价", item.getEVENPRICE());
        map.put("冻结数量", item.getFROZENQTY());
        map.put("交易商", item.getFIRMID());
        return map;
    }

    public static LinkedHashMap<String, String> getHistoryDealDetailMap(Context context, HistoryDealData item) {


        LinkedHashMap map = new LinkedHashMap<>();
//        总记录条	TTLREC
//        成交量总计	QUANTITY_SUM
//        成交金额总计	TRADE_FUNDS_SUM
//        手续费总计	TRADE_FEE_SUM

//        成交单号	TRADE_NO
//        商品ID	COMMODITY_ID
//        成交量	QUANTITY
//        成交价	PRICE
//        手续费	TRADE_FEE
//        成交时间	TRADE_TIME
//        买卖标记	BS_FLAG
//        成交类型	TRADE_TYPE

        map.put("商品名称", TradeHelper.getCommodityDataByCode(context, item.getCOMMODITY_ID()).getName());
        map.put("商品代码", item.getCOMMODITY_ID());
        map.put("成交单号", item.getTRADE_NO());
        map.put("成交量", item.getQUANTITY());
        map.put("成交价", item.getPRICE());
        map.put("手续费", item.getTRADE_FEE());
        map.put("成交时间", item.getHOLD_TIME());
        map.put("买卖标记", item.getBS_FLAG_CH());
        map.put("成交类型", item.getTRADE_TYPE_CH());

        return map;
    }

    public static LinkedHashMap<String, String> getCheDanDetailMap(Context context, TodayOrderData item) {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//                                委托单号	ORDERNO
//                                交易商ID	FIRMID
//                                分会员ID	SUB_M_FIRMID

//                                经纪会员ID	B_FIRMID
//                                商品代码	COMMODITYID

//                                买卖标记	BS_FLAG
//                                委托类型	ORDERTYPE
//                                委托状态	STATUS
//                                委托数量	QUANTITY
//                                未成交数字	UNTRADEQTY
//                                委托时间	ORDERTIME
//                                价格	PRICE
//                                交易员ID	TRADERID
//                                撤单时间	WITHDRAWTIME
//                                撤单类型	WITHDRAWTYPE
//                                委托员ID	CONSIGNERID
//                                撤单员ID	WITHDRAWERID

        map.put("商品名称", TradeHelper.getCommodityDataByCode(context, item.getCOMMODITYID()).getName());
        map.put("商品代码", item.getCOMMODITYID());
        map.put("委托单号", item.getORDERNO());
        map.put("交易商", item.getFIRMID());
//        map.put("分会员", item.getSUB_M_FIRMID());

        map.put("经纪会员", item.getB_FIRMID());
        map.put("买卖方向", item.getBS_FLAG_CH());
        map.put("委托类型", item.getORDERTYPE_CH());
        map.put("委托状态", item.getSTATUS_CH());
        map.put("委托数量", item.getQUANTITY());
        map.put("未成交数字", item.getUNTRADEQTY());
        map.put("委托时间", item.getORDERTIME());
        map.put("价格", item.getPRICE());
        map.put("交易员", item.getTRADERID());
        map.put("撤单时间", item.getWITHDRAWTIME());
        map.put("撤单类型", item.getWITHDRAWTYPE_CH());
        map.put("委托员", item.getCONSIGNERID());
        map.put("撤单员", item.getWITHDRAWERID());
        return map;
    }

    public static LinkedHashMap<String, String> getDangRiChengJiaoDetailMap(TodayDealData item) {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//        总记录条	TTLREC	数字
//        结算日期	CLEAR_DATE	数字
//        成交单号	TRADE_NO	字符串
//        商品	COMMODITY_NAME	数字
//        成交量	QUANTITY	数字
//        成交价	PRICE	数字
//        成交金额	TRADE_FUNDS	数字
//        手续费	TRADE_FEE	数字
//        成交时间	TRADE_TIME	数字
//        买卖标记	BS_FLAG	数字	1-买 2-卖
//        成交类型	TR_T	数字	1-市价成交，用户下单；2-市价成交，电话下单； 3-市价成交，系统下单；4-自动强平，系统下单； 5-手动强平，系统下单；6-指价成交 ，系统下单； 7-指价成交，电话下单；8-指价成交，批量指价下单


        map.put("商品名称", item.getCOMMODITY_NAME());
//        map.put("结算日期", item.getCLEAR_DATE());
        map.put("成交单号", item.getTRADE_NO());
        map.put("成交量", item.getQUANTITY());
        map.put("成交价", item.getPRICE());

        map.put("手续费", item.getTRADE_FEE());
        map.put("成交时间", item.getTRADE_TIME());
        map.put("买卖标记", item.getBS_FLAG_CH());
        map.put("成交类型", item.getTR_T_CH());

        return map;
    }

    public static LinkedHashMap<String, String> getShengGouOrderDetailMap(ShengGouOrderData item) {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//        委托单号	ORDER_NO
//        申购计划NO	PLAN_NO
//        商品代码	COMMODITY_ID
//        商品名称	COMMODITY_NAME
//        委托状态	STATUS
//        委托数量	QUANTITY

//        未成交数字	UNTRADEQTY
//        委托时间	ORDERTIME
//        价格	PRICE
//        交易员ID	TRADERID
//        撤单时间	WITHDRAWTIME

//        撤单类型	WITHDRAWTYPE
//        委托员ID	CONSIGNERID
//        撤单员ID	WITHDRAWERID


        map.put("商品名称", item.getCOMMODITY_NAME());
        map.put("商品代码", item.getCOMMODITY_ID());
        map.put("委托单号", item.getORDER_NO());
        map.put("申购计划编号", item.getPLAN_NO());
        map.put("委托状态", item.getSTATUS_CH());
        map.put("委托数量", item.getQUANTITY());

        map.put("未成交数字", item.getUNTRADEQTY());
        map.put("委托时间", item.getORDERTIME());
        map.put("价格", item.getPRICE());
        map.put("交易员编号", item.getTRADERID());
        map.put("撤单时间", item.getWITHDRAWTIME());

        map.put("撤单类型", item.getWITHDRAWTYPE_CH());
        map.put("委托员编号", item.getCONSIGNERID());
        map.put("撤单员编号", item.getWITHDRAWERID());

        return map;
    }

    public static LinkedHashMap<String, String> getTuoGuanOrderDetailMap(TuoGuanOrderData item) {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//        托管申请ID	APPLY_ID
//        托管计划NO	PLAN_NO
//        商品ID	COMMODITY_ID
//        商品名称	COMMODITY_NAME
//        仓库ID	HOUSE_ID
//        申请数量	QTY

//        入库数量	IN_QTY
//        已挂牌数量	LIST_QTY
//        发行数量	ISSUE_QTY
//        限售数量	LIMIT_QTY
//        处理状态	STATUS
//        申请时间	TIME
        map.put("商品名称", item.getCOMMODITY_NAME());
        map.put("商品代码", item.getCOMMODITY_ID());
        map.put("托管申请编号", item.getAPPLY_ID());
        map.put("托管计划编号", item.getPLAN_NO());
        map.put("仓库编号", item.getHOUSE_ID());
        map.put("申请数量", item.getQTY());
        map.put("入库数量", item.getIN_QTY());
        map.put("已挂牌数量", item.getLIST_QTY());
        map.put("发行数量", item.getISSUE_QTY());
        map.put("限售数量", item.getLIMIT_QTY());
        map.put("处理状态", item.getSTATUS_CH());
        map.put("申请时间", item.getTIME());
        return map;
    }

    public static LinkedHashMap<String, String> getTiHuoOrderDetailMap(TiHuoOrderData item) {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //        商品ID	COMMODITY_ID
//        商品名称	COMMODITY_NAME
//        提货申请ID	ORDER_NO
//        仓库ID	HOUSE_ID
//        申请数量	QTY

//        提货日期	DELIVERY_DATE
//        提货价钱	DELIVERY_PRICE
//        处理状态	STATUS
//        申请时间	TIME
//        冻结提货单费	FROZEN_DELIVERY_FEE

        map.put("商品名称", item.getCOMMODITY_NAME());
        map.put("商品代码", item.getCOMMODITY_ID());
        map.put("提货申请编号", item.getORDER_NO());
//        map.put("仓库编号", item.getHOUSE_ID());
        map.put("申请数量", item.getQTY());

        map.put("提货日期", item.getDELIVERY_DATE());
        map.put("提货价钱", item.getDELIVERY_PRICE());
        map.put("处理状态", item.getSTATUS_CH());
        map.put("申请时间", item.getTIME());
        map.put("冻结提货单费", item.getFROZEN_DELIVERY_FEE());
        return map;
    }

    /**
     * 保存所需的sharedPerference文件数据
     */
    public void saveSpf(SharedPreferenceManager spf, long trade_cnt, long lastnew_id, String clear_date) {

//        long trade_cnt = spf.getLong(context, SharedPreferenceManager.TRADE_CNT, -1);
//        long lastnew_id = spf.getLong(context, SharedPreferenceManager.LASTNEW_ID, 0);
//        String clear_date = spf.getString( SharedPreferenceManager.CLEAR_DATE, "");

        spf.putLong(SharedPreferenceManager.TRADE_CNT, trade_cnt);
        spf.putLong(SharedPreferenceManager.LASTNEW_ID, lastnew_id);
        spf.putString(SharedPreferenceManager.CLEAR_DATE, clear_date);
    }

    //注销账号
    public static void ZhuXiao(final Context context) {
        ActivityUtils.showAlertWithConfirmText(context, "是否注销当前账号?", "确定", new Runnable() {
            @Override
            public void run() {
                TradeHelper.logoutOnly(context);

            }
        });
    }
}
