package com.puxtech.ybk.shezhi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.hangqing.error.ErrorCode;
import com.puxtech.ybk.hangqing.exception.ServerException;
import com.puxtech.ybk.hangqing.exception.SessionErrorException;
import com.puxtech.ybk.hangqing.protocol.RequestManager;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.LinkSpeedAdapter;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerLogin;
import com.puxtech.ybk.jiaoyi.page.BaseTradeActivity;
import com.puxtech.ybk.jiaoyi.responsedata.CheckCodeResponseData;
import com.puxtech.ybk.qidong.QiDongData;
import com.puxtech.ybk.qidong.SharedPreferenceManager;
import com.puxtech.ybk.qidong.entity.ContentsServerLlEntity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class LinkSpeed extends BaseTradeActivity {
    // private Dialog pd;
    LinkSpeedAdapter tradeAdapter, hangQinAdapter;
    ArrayList<ContentsServerLlEntity> tradeLlList, hangqinLlList;
    ArrayList<ContentsServerLlEntity> allList;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);

        setContentView(R.layout.linkspeed);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, getString(R.string.pref_link_data_title), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));


        Button bt_ok = (Button) findViewById(R.id.bt_ok);
        allList = new ArrayList<>();
        tradeLlList = myApplication.getQiDongData().getAllContentServerLlEntity().get(QiDongData.JIAO_YI);
        hangqinLlList = myApplication.getQiDongData().getAllContentServerLlEntity().get(QiDongData.HANG_QIN);
        allList.addAll(tradeLlList);
        allList.addAll(hangqinLlList);

        hangQinAdapter = new LinkSpeedAdapter(context, hangqinLlList, myApplication.getQiDongData().getHangQinFastLinkEntity().getCode());
        tradeAdapter = new LinkSpeedAdapter(context, tradeLlList, myApplication.getQiDongData().getTradeFastLinkEntity().getCode());

        final ListView hangqinlistView = (ListView) findViewById(R.id.listView_hangqin);
        hangqinlistView.setAdapter(hangQinAdapter);
        hangqinlistView.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context, SharedPreferenceManager.LINK);
                sharedPreferenceManager.putInt(context, SharedPreferenceManager.LINK_HANGQIN, position);
                myApplication.getQiDongData().setFastLink();
                hangQinAdapter.setFastLlCode(myApplication.getQiDongData().getHangQinFastLinkEntity().getCode());
                hangQinAdapter.notifyDataSetChanged();
                Toast.makeText(context, "已保存链路", Toast.LENGTH_SHORT).show();
            }
        });
        ListView tradelistView = (ListView) findViewById(R.id.listView_trade);
        tradelistView.setAdapter(tradeAdapter);
        tradelistView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context, SharedPreferenceManager.LINK);
                sharedPreferenceManager.putInt(context, SharedPreferenceManager.LINK_JIAOYI, position);

                myApplication.getQiDongData().setFastLink();
                tradeAdapter.setFastLlCode(myApplication.getQiDongData().getTradeFastLinkEntity().getCode());

                tradeAdapter.notifyDataSetChanged();
                Toast.makeText(context, "已保存链路", Toast.LENGTH_SHORT).show();

            }
        });
        bt_ok.setOnClickListener(new OnClickListener() {


            public void onClick(View arg0) {
                clearAsyncTask();
                for (ContentsServerLlEntity ll : allList) {

                    ll.setCeSu(true);

                }
                tradeAdapter.notifyDataSetChanged();
                hangQinAdapter.notifyDataSetChanged();

                for (ContentsServerLlEntity ll : allList) {
                    TestLinkLagTask asyncTask = new TestLinkLagTask(ll);
                    mPutAsyncTask(asyncTask);

                }
//                for (int i = allList.size(); i >= 1; i--) {
//
//                    TestLinkLagTask asyncTask = new TestLinkLagTask(allList.get(i-1));
//                    mPutAsyncTask(asyncTask);
//                }

            }
        });
    }

    // 测试链路速度
    class TestLinkLagTask extends AsyncTask<Handler, Integer, String> {
        ContentsServerLlEntity llentity;
        long startMs, endMs;
        CheckCodeResponseData checkCodeResponseData;

        public TestLinkLagTask(ContentsServerLlEntity llentity) {
            // TODO Auto-generated constructor stub
            this.llentity = llentity;
        }

        protected void onPreExecute() {


        }

        protected String doInBackground(Handler... params) {

            HttpManagerLogin loginManager = new HttpManagerLogin(context);
            if (llentity.getType() == QiDongData.HANG_QIN) {
                JSONObject rep = null;
                RequestManager requestManager = new RequestManager();
                try {
                    startMs = System.currentTimeMillis();
                    rep = requestManager.requestLogin(llentity.getUrl());
                    endMs = System.currentTimeMillis();

                    JSONObject mmts = rep.getJSONObject("MMTS");
                    JSONObject reph = mmts.getJSONObject("REPH");
                    int ret = reph.getInt("RET");
                    String msg = reph.getString("MSG");


                    return "0";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "-1";

                }


            } else if (llentity.getType() == QiDongData.JIAO_YI) {
                startMs = System.currentTimeMillis();
                checkCodeResponseData = loginManager.requestCheckCode(llentity.getUrl());
                endMs = System.currentTimeMillis();
                if (checkCodeResponseData.getRetCode() == Constant.CODE_SUCCESS) {

                    return "0";
                }
            }
//			hm = lm.handleVersion(result);

            return "-1";
        }

        protected void onPostExecute(String result) {
            llentity.setCeSu(false);
            if (result.equals("0")) {
                llentity.setMs((endMs - startMs) + "ms");
            } else {
                llentity.setMs("--");
            }
            tradeAdapter.notifyDataSetChanged();
            hangQinAdapter.notifyDataSetChanged();
        }

    }

    protected List<AsyncTask<Handler, Integer, String>> asyncTaskList = new ArrayList<AsyncTask<Handler, Integer, String>>();

    protected void mPutAsyncTask(AsyncTask<Handler, Integer, String> task) {
        asyncTaskList.add(task.execute());
    }

    protected void clearAsyncTask() {
        Iterator<AsyncTask<Handler, Integer, String>> iterator = asyncTaskList
                .iterator();
        while (iterator.hasNext()) {
            AsyncTask<Handler, Integer, String> asyncTask = iterator.next();
            if (asyncTask != null && !asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }
        }
        asyncTaskList.clear();
    }

}
