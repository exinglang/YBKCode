package com.puxtech.ybk.hangqing.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.puxtech.ybk.MainActivity;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.R;
import com.puxtech.ybk.hangqing.HangQingLogger;
import com.puxtech.ybk.hangqing.HangQingMainFragment;
import com.puxtech.ybk.hangqing.detail.HangQingDetailActivity;
import com.puxtech.ybk.hangqing.error.ErrorCode;
import com.puxtech.ybk.hangqing.exception.HangQingBasicDataErrorException;
import com.puxtech.ybk.hangqing.exception.SessionErrorException;
import com.puxtech.ybk.hangqing.jsondata.RepData;
import com.puxtech.ybk.hangqing.jsondata.getcommodities.Commodity;
import com.puxtech.ybk.hangqing.jsondata.getpriceformcommodities.CommodityPrice;
import com.puxtech.ybk.hangqing.jsondata.getpriceformcommodities.GetPriceForMCommoditiesRepBody;
import com.puxtech.ybk.hangqing.list.ListLeftAdapter;
import com.puxtech.ybk.hangqing.list.ListRightAdapter;
import com.puxtech.ybk.hangqing.list.SyncHorizontalScrollView;
import com.puxtech.ybk.hangqing.model.ListRightModel;
import com.puxtech.ybk.hangqing.protocol.RequestManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fanshuo on 16/4/26.
 */
public class HangQingListController {

    Handler handler = new Handler();
    private Context context;
    private View container;
    private LinearLayout leftContainerView;
    private ListView leftListView;
    private LinearLayout rightContainerView;
    private ListView rightListView;
    private List<ListRightModel> models;//所有商品列表
    private List<ListRightModel> modelsForShow;//显示的商品列表
    private SyncHorizontalScrollView titleHorsv;
    private SyncHorizontalScrollView contentHorsv;
    private ListRightAdapter rightAdapter;
    private ListLeftAdapter leftAdapter;
    private Timer refreshPriceTimer;//盘口刷新timer

    public HangQingListController(Context context, View container) {
        this.context = context;
        this.container = container;
        init();
    }

    //private methods

    private void init(){
        leftContainerView = (LinearLayout) container.findViewById(R.id.left_container);
        leftListView = (ListView) container.findViewById(R.id.left_container_listview);
        rightContainerView = (LinearLayout) container.findViewById(R.id.right_container);
        rightListView = (ListView) container.findViewById(R.id.right_container_listview);
        titleHorsv = (SyncHorizontalScrollView) container.findViewById(R.id.title_horsv);
        contentHorsv = (SyncHorizontalScrollView) container.findViewById(R.id.content_horsv);
        // 设置两个水平控件的联动
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);
        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, HangQingDetailActivity.class);
                int cid = modelsForShow.get(position).getId();
                intent.putExtra(HangQingDetailActivity.BUNDLE_KEY_CID, cid);
                ((Activity) context).startActivityForResult(intent, MainActivity.REQUEST_CODE_DETAIL);
                refreshPriceTimer.cancel();
                HangQingLogger.d("onItemClick:" + position);
            }
        });
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, HangQingDetailActivity.class);
                int cid = modelsForShow.get(position).getId();
                intent.putExtra(HangQingDetailActivity.BUNDLE_KEY_CID, cid);
                ((Activity)context).startActivityForResult(intent, MainActivity.REQUEST_CODE_DETAIL);
                refreshPriceTimer.cancel();
                HangQingLogger.d("onItemClick:" + position);
            }
        });
        initTimer();
    }

    private void initTimer(){
        refreshPriceTimer = new Timer();
    }


    private void startRefreshPriceTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getPrice();
                    }
                });
            }
        };
        refreshPriceTimer.schedule(task,1000,2000);
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    /**
     * 根据商品信息创建列表初始数据，只有商品名称，没有价格
     */
    private void initListViewData() {
        MyApplication myApplication = (MyApplication) context.getApplicationContext();
        List<Commodity> commodities = myApplication.getHangQingData().getCommodities();

        models= new ArrayList<>();
        for (Commodity item:
                commodities) {
            models.add(new ListRightModel(item.getCnam(),item.getVid(), item.getCcod(), item.getPlate()));
        }
    }

    private void getPrice(){
        new AsyncTask<Void, Void, Boolean>() {
            String msg;
            List<CommodityPrice> commodityPriceList;
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    MyApplication mApplication = (MyApplication) context.getApplicationContext();
                    RequestManager requestManager = new RequestManager();
                    int marketId = mApplication.getHangQingData().getMarketId();
                    int panId = mApplication.getHangQingData().getPanId();
                    //只请求当前显示的商品
//                    int[] cids = new int[modelsForShow.size()];
//                    for (int i = 0; i < modelsForShow.size(); i++) {
//                        cids[i] = modelsForShow.get(i).getId();
//                    }
//                    JSONObject repJson = requestManager.getPriceForMCommodities(mApplication, marketId, panId, cids);
                    //按盘请求
                    JSONObject repJson = requestManager.getPriceForMarket(mApplication, marketId, panId);
                    Gson gson = new Gson();
                    TypeToken typeToken = new TypeToken<RepData<GetPriceForMCommoditiesRepBody>>(){};
                    RepData<GetPriceForMCommoditiesRepBody> repData = gson.fromJson(repJson.toString(),typeToken.getType());
                    int ret = repData.getMmts().getRepHeader().getRet();
                    if(ret == 0){
                        commodityPriceList = repData.getMmts().getRepBody().getCli();
                        mApplication.getHangQingData().setCommoditiesPrice(commodityPriceList);
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
                } catch (Exception e){
                    e.printStackTrace();
                    msg = "客户端未知异常";
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if(aBoolean){
                    //刷新行情列表
                    for (int i = 0; i < models.size(); i++) {
                        ListRightModel listRightModel = models.get(i);
                        for (int j = 0; j < commodityPriceList.size(); j++) {
                            CommodityPrice item = commodityPriceList.get(j);
                            if(item.getCid() == listRightModel.getId()){
                                listRightModel.setChengJiaoEText(item.getChengJiaoE());
                                listRightModel.setChengJiaoLiangText(item.getTvol() + "");
                                listRightModel.setXianJiaText(item.getZuiXinJia());
                                listRightModel.setZhangDieText(item.getZhangDieText());
                                listRightModel.setZhangFuText(item.getZhangFu());
                                listRightModel.setZhenFuText(item.getZhenFu());
                                listRightModel.setZuiDiText(item.getZuiDi());
                                listRightModel.setZuiGaoText(item.getZuiGao());
                                listRightModel.setZuoShouText(item.getZuoShou());

                                listRightModel.setXianJia(item.getLatp());
                                listRightModel.setZhangDie(item.getZhangDie());
                                listRightModel.setZuiGao(item.getHigp());
                                listRightModel.setZuiDi(item.getLowp());
                                listRightModel.setZuoShou(item.getYclo());
                            }
                        }
                    }
                    rightAdapter.notifyDataSetChanged();
                }
                else{
                    //TODO 提示获取盘口失败
                    HangQingLogger.e("盘口获取失败："+msg);
                }
            }
        }.execute();
    }

    //public methods

    public void initData(){
        // 添加内容数据
        initListViewData();

        modelsForShow = new ArrayList<>();
        leftAdapter=new ListLeftAdapter(context, modelsForShow);
        leftListView.setAdapter(leftAdapter);
        setListViewHeightBasedOnChildren(leftListView);

        rightAdapter=new ListRightAdapter(context, modelsForShow);
        rightListView.setAdapter(rightAdapter);
        setListViewHeightBasedOnChildren(rightListView);

        MyApplication myApplication = (MyApplication) context.getApplicationContext();
        if(myApplication.getHangQingData().getPlates() != null && myApplication.getHangQingData().getPlates().size() > 0){
            showListByPlate(myApplication.getHangQingData().getPlates().get(0).getPlat());
        }

        startRefreshPriceTimer();
    }

    public void showListByPlate(int plateId){
        modelsForShow.clear();
        for (int i = 0; i < models.size(); i++) {
            ListRightModel item = models.get(i);
            if(item.getPlateId() == plateId){
                modelsForShow.add(item);
            }

        }
        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(leftListView);
        setListViewHeightBasedOnChildren(rightListView);
    }

    public void onDestroy(){
        refreshPriceTimer.cancel();
    }

}
