package com.puxtech.ybk.jiaoyi.page;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.ThreeRowAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.InOutHistoryEntity;
import com.puxtech.ybk.jiaoyi.entitydata.ThreeRowData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerMoney;
import com.puxtech.ybk.jiaoyi.responsedata.InOutHistoryResponseData;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class BankHistory extends BaseTradeActivity implements
        View.OnClickListener {
    ListView listView;
    TextView tv_start, tv_end;
    Button bt_commit;
    ArrayList<ThreeRowData> threeRowDatas;
    ThreeRowAdapter threeRowAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.bank_history);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, getString(R.string.transfer_history), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));

        initWidget();
        setClickListener();
    }

    private void initWidget() {
        listView = (ListView) findViewById(R.id.listView);


//        View headView = LayoutInflater.from(context).inflate(R.layout.bank_history, null);

//        recyclerView.addHeaderView(headView);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_end = (TextView) findViewById(R.id.tv_end);
        bt_commit = (Button)findViewById(R.id.bt_commit);
        String date = ActivityUtils.getYYYYMMDDforTimeMillis(System.currentTimeMillis());
        tv_start.setText(date);
        tv_end.setText(date);
        tv_start.setOnClickListener(this);
        tv_end.setOnClickListener(this);


        threeRowDatas  = new ArrayList<>();
         threeRowAdapter = new ThreeRowAdapter(context,threeRowDatas);
        listView.setAdapter(threeRowAdapter);

    }




    @Override
    public void onClick(View v) {
        TradeHelper.selectData(context, v, true);
    }

    private void setClickListener() {
        bt_commit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar startCalendar = TradeHelper.getCalendarOfString(tv_start.getText().toString());
                Calendar endCalendar = TradeHelper.getCalendarOfString(tv_end.getText().toString());
                if (TradeHelper.isDateOneAfterDateTwo(startCalendar, endCalendar)) {
                    //开始日期是否晚于结束日期
                    ActivityUtils.showCenterToast(context,
                            TradeHelper.STARTTIME_MORE_ENDTIME, Toast.LENGTH_LONG);
                    return;
                }
                if (TradeHelper.isDateOneBeyongMonthDateTwo(startCalendar, endCalendar)) {
                    ActivityUtils.showCenterToast(context,
                            TradeHelper.CENTER_TIME_SHORT_ONT_MONTHS, Toast.LENGTH_LONG);
                    return;
                }
//                initHaveTimeNeedData();
                request();
            }
        });
    }

    private void request() {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            InOutHistoryResponseData responseData;
            Dialog dialog;
            String startTime, endTime;//请求的开始和结束时间

            protected void onPreExecute() {
                dialog = ActivityUtils.showLoadingProgressDialog(context);

                startTime = ActivityUtils.getTimeMillisFromYYYYMMDDset000000(tv_start.getText().toString(), false);
                endTime = ActivityUtils.getTimeMillisFromYYYYMMDDset235959(tv_end.getText().toString(), false);


                threeRowDatas.clear();
                threeRowAdapter.notifyDataSetChanged();
                super.onPreExecute();
            }

            @SuppressWarnings("ResourceType")
            protected Boolean doInBackground(Void... params) {
                try {
                    HttpManagerMoney httpManager = new HttpManagerMoney(context);


                    responseData = httpManager.requestInOutHistory(startTime, endTime);


                    //获取开户信息

                } catch (Exception e) {
                    e.printStackTrace();
                    responseData.setRetCode(Constant.CODE_ERROR_UNKNOW);
                    responseData.setRetMessage(Constant.MESSAGE_ERROR_UNKNOW);
                }
                return true;
            }

            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                dialog.dismiss();

                if (0 == responseData.getRetCode()) {
                    ArrayList<InOutHistoryEntity> inOutHistoryEntities = responseData.getInOutHistoryEntityArrayList();

                    if(inOutHistoryEntities.size()==0){
                        ActivityUtils.showCenterToast(context,Constant.NO_RECORD,Toast.LENGTH_SHORT);
                        return;
                    }
                    ArrayList<ThreeRowData> getrowlist = new ArrayList<>();
                    for (InOutHistoryEntity entity : inOutHistoryEntities) {
                        ThreeRowData getrow = new ThreeRowData();
//                        String mid;//流水号
//                        String td;//操作时间，微秒
//                        String bank_name;//银行名称
//                        String TT;//类型:I入金；O出金
//                        String AM;//金额
//                        String TS;//状态
//                        String REM;//备注
                        getrow.setS1(entity.getMid());
                        getrow.setS2(entity.getTT().equals("I")?"入金":"出金");
                        getrow.setS3(entity.getTd());
                        getrow.setS4(entity.getBank_name());
                        getrow.setS5(entity.getAM());
                        getrow.setS6(entity.getTS());
                        getrowlist.add(getrow);

                    }


                    threeRowDatas.addAll(getrowlist);
                    threeRowAdapter.notifyDataSetChanged();
                    //   isLoadingMore=false;

                } else {


                    ActivityUtils.showAlert(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")");
                }
            }
        });
    }


}