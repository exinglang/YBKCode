package com.puxtech.ybk.hangqing.detail;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.R;
import com.puxtech.ybk.hangqing.HangQingLogger;
import com.puxtech.ybk.hangqing.error.ErrorCode;
import com.puxtech.ybk.hangqing.exception.HangQingBasicDataErrorException;
import com.puxtech.ybk.hangqing.exception.SessionErrorException;
import com.puxtech.ybk.hangqing.jsondata.RepData;
import com.puxtech.ybk.hangqing.jsondata.getcommodities.Commodity;
import com.puxtech.ybk.hangqing.jsondata.geteverypriceafterid.GetEveryPriceAfterIdRepBody;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.FenBi;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.GetPriceForCommodityRepBody;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.WuDangBuy;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.WuDangSell;
import com.puxtech.ybk.hangqing.jsondata.getpriceformcommodities.GetPriceForMCommoditiesRepBody;
import com.puxtech.ybk.hangqing.protocol.RequestManager;
import com.puxtech.ybk.hangqing.util.PriceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 负责控制五档明细所有内容
 */
public class WuDangMingXiViewController {

    private Context context;
    private Commodity commodity;
    //五档views
    private LinearLayout layoutPanKou;
    private TextView[] wdBuyPriceArray;
    private TextView[] wdBuyVolumeArray;
    private TextView[] wdSellPriceArray;
    private TextView[] wdSellVolumeArray;
    //明细views
    private LinearLayout layoutMingXi;
    private ListView lvMingXi;
    private MingXiListAdapter mingXiListAdapter;
    private LinkedList<FenBi> fenBiLinkedList;
    private int fenBiListSize;
    private Timer fenBiTimer;
    //状态
    private static final int STATUS_MINGXI_READY = 1;
    private static final int STATUS_MINGXI_REFRESH = 2;
    private int mingXiStatus = 1;

    public WuDangMingXiViewController(Context context, View parent, Commodity commodity) {
        this.context = context;
        this.commodity = commodity;
        initWuDangViews(parent);
        initMingXiViews(parent);
    }

    //private methods

    private void initWuDangViews(View parent){
        layoutPanKou = (LinearLayout) parent.findViewById(R.id.layout_pankou);
        wdBuyPriceArray = new TextView[5];
        wdBuyPriceArray[0] = (TextView) parent.findViewById(R.id.wd_buy_1_price);
        wdBuyPriceArray[1] = (TextView) parent.findViewById(R.id.wd_buy_2_price);
        wdBuyPriceArray[2] = (TextView) parent.findViewById(R.id.wd_buy_3_price);
        wdBuyPriceArray[3] = (TextView) parent.findViewById(R.id.wd_buy_4_price);
        wdBuyPriceArray[4] = (TextView) parent.findViewById(R.id.wd_buy_5_price);

        wdBuyVolumeArray = new TextView[5];
        wdBuyVolumeArray[0] = (TextView) parent.findViewById(R.id.wd_buy_1_volume);
        wdBuyVolumeArray[1] = (TextView) parent.findViewById(R.id.wd_buy_2_volume);
        wdBuyVolumeArray[2] = (TextView) parent.findViewById(R.id.wd_buy_3_volume);
        wdBuyVolumeArray[3] = (TextView) parent.findViewById(R.id.wd_buy_4_volume);
        wdBuyVolumeArray[4] = (TextView) parent.findViewById(R.id.wd_buy_5_volume);

        wdSellPriceArray = new TextView[5];
        wdSellPriceArray[0] = (TextView) parent.findViewById(R.id.wd_sell_1_price);
        wdSellPriceArray[1] = (TextView) parent.findViewById(R.id.wd_sell_2_price);
        wdSellPriceArray[2] = (TextView) parent.findViewById(R.id.wd_sell_3_price);
        wdSellPriceArray[3] = (TextView) parent.findViewById(R.id.wd_sell_4_price);
        wdSellPriceArray[4] = (TextView) parent.findViewById(R.id.wd_sell_5_price);

        wdSellVolumeArray = new TextView[5];
        wdSellVolumeArray[0] = (TextView) parent.findViewById(R.id.wd_sell_1_volume);
        wdSellVolumeArray[1] = (TextView) parent.findViewById(R.id.wd_sell_2_volume);
        wdSellVolumeArray[2] = (TextView) parent.findViewById(R.id.wd_sell_3_volume);
        wdSellVolumeArray[3] = (TextView) parent.findViewById(R.id.wd_sell_4_volume);
        wdSellVolumeArray[4] = (TextView) parent.findViewById(R.id.wd_sell_5_volume);
    }

    private void initMingXiViews(View parent){
        lvMingXi = (ListView) parent.findViewById(R.id.lv_mingxi);
        layoutMingXi = (LinearLayout) parent.findViewById(R.id.layout_mingxi);


    }

    private void refreshWuDangData(GetPriceForCommodityRepBody rep){
        List<WuDangBuy> wuDangBuyList = rep.getExtd().getFivd().getBuyd();
        List<WuDangSell> wuDangSellList = rep.getExtd().getFivd().getSeld();
        for (int i = 0; i < wuDangBuyList.size(); i++) {
            WuDangBuy wuDangBuy = wuDangBuyList.get(i);
            String price = "--";
            String volume = "--";
            if(wuDangBuy.getBuyp() != 0){
                price = PriceUtil.keepPrecision(wuDangBuy.getBuyp(), commodity.getMcu());
                volume = wuDangBuy.getList() + "";
            }
            wdBuyPriceArray[i].setText(price);
            wdBuyVolumeArray[i].setText(volume);
        }
        for (int i = 0; i < wuDangSellList.size(); i++) {
            WuDangSell wuDangSell = wuDangSellList.get(i);
            String price = "--";
            String volume = "--";
            if(wuDangSell.getSelp() != 0){
                price = PriceUtil.keepPrecision(wuDangSell.getSelp(), commodity.getMcu());
                volume = wuDangSell.getList() + "";
            }
            wdSellPriceArray[i].setText(price);
            wdSellVolumeArray[i].setText(volume);
        }
    }

    private void refreshMingXiData(GetPriceForCommodityRepBody rep){
        if(mingXiStatus == STATUS_MINGXI_REFRESH){
            return;
        }
        //计算list的最大可显示数量
        int layoutHeight = layoutMingXi.getMeasuredHeight();
        List<FenBi> fenBiList = new ArrayList<>();
        FenBi fenBi = new FenBi();
        fenBiList.add(fenBi);
        mingXiListAdapter = new MingXiListAdapter(context, fenBiList, commodity.getMcu());
        lvMingXi.setAdapter(mingXiListAdapter);
        View itemView = mingXiListAdapter.getView(0, null, lvMingXi);
        itemView.measure(0,0);
        int itemHeight = itemView.getMeasuredHeight();
        fenBiListSize = layoutHeight/(itemHeight+2);//这里的2是分割线的高度



        fenBiLinkedList = new LinkedList<>();
        fenBiList = rep.getDivl();
        if(fenBiList.size() > fenBiListSize){
            for (int i = fenBiList.size()-1; i > fenBiList.size()-1-fenBiListSize; i--) {
                FenBi item = fenBiList.get(i);
                fenBiLinkedList.add(item);
            }
        }
        else{
            fenBiLinkedList.addAll(fenBiList);
        }
        mingXiListAdapter.setFenBiList(fenBiLinkedList);
        mingXiListAdapter.notifyDataSetChanged();
        //TODO 暂时不使用此协议刷新分笔，现在只靠接收盘口协议刷新分笔
//        if(fenBiList.size() > 0){
//            mingXiStatus = STATUS_MINGXI_REFRESH;
//            startRefreshFenbi();
//        }
    }

    private void getNewFenBi(){
        new AsyncTask<Void,Void,Boolean>(){

            String msg;

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    MyApplication mApplication = (MyApplication) context.getApplicationContext();
                    RequestManager requestManager = new RequestManager();
                    int marketId = mApplication.getHangQingData().getMarketId();
                    int panId = mApplication.getHangQingData().getPanId();

                    JSONObject repJson = requestManager.getEveryPriceAfterId(mApplication, marketId, panId, commodity.getVid(), fenBiLinkedList.getLast().getIdx(), 100);
                    Gson gson = new Gson();
                    TypeToken typeToken = new TypeToken<RepData<GetEveryPriceAfterIdRepBody>>(){};
                    RepData<GetEveryPriceAfterIdRepBody> repData = gson.fromJson(repJson.toString(),typeToken.getType());
                    int ret = repData.getMmts().getRepHeader().getRet();
                    if(ret == 0){
                        List<FenBi> fenBiList = repData.getMmts().getRepBody().getRecl();
                        for (int i = 0; i < fenBiList.size(); i++) {
                            FenBi item = fenBiList.get(i);
                            fenBiLinkedList.addLast(item);
                            fenBiLinkedList.removeFirst();
                        }
                        return true;
                    }
                    else{
                        msg = repData.getMmts().getRepHeader().getMsg();
                        return false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    msg = "（"+ ErrorCode.ERROR_JSON_PARSE+"）";
                    return false;
                } catch (SessionErrorException e) {
                    e.printStackTrace();
                    msg = e.getMessage();
                    return false;
                } catch (HangQingBasicDataErrorException e) {
                    e.printStackTrace();
                    msg = e.getMessage();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if(result){
                    mingXiListAdapter.notifyDataSetChanged();
                }
                else{
                    HangQingLogger.d(msg);
                }

            }
        }.execute();
    }

    //public methods

    public void refreshData(GetPriceForCommodityRepBody rep){
        refreshWuDangData(rep);
        refreshMingXiData(rep);
    }

    public void startRefreshFenbi(){
        fenBiTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getNewFenBi();
            }
        };
        fenBiTimer.schedule(task,100,2000);
    }

    public void onDestroy(){
        if(fenBiTimer != null){
            fenBiTimer.cancel();
        }
    }


    //ineer classes

}
