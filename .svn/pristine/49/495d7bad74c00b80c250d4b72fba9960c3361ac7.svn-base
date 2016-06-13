package com.puxtech.ybk.jiaoyi.page;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.entitydata.FundInfoData;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerTrade;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.util.ActivityUtils;

public class TradeChaXunZiJinChaXun extends BaseTradeActivity {


    TextView tv_bizhongleibie, tv_zijinyue, tv_keyongyue,tv_kequzijin,tv_shizhi,tv_zongzichan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.cha_xun_zijinchaxun);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, getString(R.string.chaxun_zijinchaxun), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
        initWidget();
//        request();

    }

    private void initWidget() {
        tv_bizhongleibie = (TextView) findViewById(R.id.tv_bizhongleibie);
        tv_zijinyue = (TextView) findViewById(R.id.tv_zijinyue);
        tv_keyongyue = (TextView) findViewById(R.id.tv_keyongyue);
        tv_kequzijin = (TextView) findViewById(R.id.tv_kequzijin);
        tv_shizhi = (TextView) findViewById(R.id.tv_shizhi);
        tv_zongzichan = (TextView) findViewById(R.id.tv_zongzichan);

        FundInfoData fundInfoData=myApplication.getTradeEntity().getOtherData().getFundInfoData();

        tv_bizhongleibie.setText("人民币");
        tv_zijinyue.setText(fundInfoData.getDONEBALANCE());
        tv_keyongyue.setText("error");
        tv_kequzijin.setText(ActivityUtils.changeDouble(String.valueOf(Double.valueOf(fundInfoData.getDONEBALANCE()) - Double.valueOf(fundInfoData.getFROZENFUNDS()))));
        tv_shizhi.setText(TradeHelper.getMarketValue(context));
        try {
            tv_zongzichan.setText(ActivityUtils.changeDouble(String.valueOf(Double.valueOf(fundInfoData.getDONEBALANCE()) + Double.valueOf(TradeHelper.getMarketValue(context)))));
        }catch (Exception e){
            tv_zongzichan.setText("--");
        }
    }

//    //tijoao
//    public void request() {
//        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
//            BaseResponseDataTrade responseData;
//            Dialog dialog;
//            protected void onPreExecute() {
//                dialog = ActivityUtils.showLoadingProgressDialog(context);
//                super.onPreExecute();
//            }
//            protected Boolean doInBackground(Void... params) {
//                try {
//                    HttpManagerTrade httpManagerTrade = new HttpManagerTrade(context);
//                    responseData = httpManagerTrade.requestTiHuo(((HoldDetailData) sp_commodity.getSelectedItem()).getCOMMODITYID(), et_tihuoshuliang.getText().toString(), ActivityUtils.getTimeMillisFromYYYYMMDD(tv_date.getText().toString()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    responseData.setRetCode(Constant.CODE_ERROR_UNKNOW);
//                    responseData.setRetMessage(Constant.MESSAGE_ERROR_UNKNOW);
//                }
//                return true;
//            }
//            protected void onPostExecute(Boolean result) {
//                super.onPostExecute(result);
//                dialog.dismiss();
//                if (0 == responseData.getRetCode()) {
//                    ActivityUtils.showAlert(context, "操作成功");
//                } else {
//                    ActivityUtils.showCenterToast(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")", Toast.LENGTH_SHORT);
//                }
//            }
//        });
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return false;
    }
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_home, menu);
//        super.onCreateOptionsMenu(menu);
//    }

}
