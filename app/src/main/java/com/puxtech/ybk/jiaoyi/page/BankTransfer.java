package com.puxtech.ybk.jiaoyi.page;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.BankTransferBankAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.UserBankData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerLogin;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerMoney;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerQuery;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.jiaoyi.responsedata.CheckCodeResponseData;
import com.puxtech.ybk.util.ActivityUtils;
import com.puxtech.ybk.util.SharedPreferenceManager;

public class BankTransfer extends BaseTradeActivity {
    Spinner sp_bank, sp_bizhong;
    EditText et_password, et_amount;
    Button bt_commit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.activity_banktransfer);


        String title = getIntent().getAction().equals(Bank.BANK_TO_TRADE) ? getString(R.string.bank_to_trade) : getString(R.string.trade_to_bank);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, title, (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
        initWidget();

    }

    private void initWidget() {
        sp_bank = (Spinner) findViewById(R.id.sp_bank);
        sp_bizhong = (Spinner) findViewById(R.id.sp_bizhong);
        et_password = (EditText) findViewById(R.id.et_password);
        et_amount = (EditText) findViewById(R.id.et_amount);
        bt_commit = (Button) findViewById(R.id.bt_commit);
        ActivityUtils.setEditTextSoftInputType(this, context, et_password, 2);
        ActivityUtils.setEditTextSoftInputType(this, context, et_amount, 2);


//绑定 银行列表

        BankTransferBankAdapter bankAdapter = new BankTransferBankAdapter(context, myApplication.getTradeEntity().getOtherData().getUserBankDataArrayList());
        sp_bank.setAdapter(bankAdapter);
        sp_bank.getSelectedItem();
        //绑定币种
        String[] mItems = {"人民币"};
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_bizhong.setAdapter(adapter);


        bt_commit.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if (getIntent().getAction().equals(Bank.BANK_TO_TRADE) && ((UserBankData) sp_bank.getSelectedItem()).getBANK_ID().equals("900")) {
                                                 ActivityUtils.showAlert(context,
                                                         "本版本暂不支持第三方支付入金功能");

                                                 return;

                                             }


                                             request();

                                         }
                                     }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return false;
    }

    private void request() {
        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
            BaseResponseDataTrade responseData;
            Dialog dialog;

            protected void onPreExecute() {

                dialog = ActivityUtils.showLoadingProgressDialog(context);


                super.onPreExecute();
            }

            @SuppressWarnings("ResourceType")
            protected Boolean doInBackground(Void... params) {
                try {
                    HttpManagerMoney httpManagerMoney = new HttpManagerMoney(context);
                    String type = getIntent().getAction().equals(Bank.BANK_TO_TRADE) ? "I" : "O";
                    responseData = httpManagerMoney.requestChuRuJin(((UserBankData) sp_bank.getSelectedItem()).getBANK_ID(), type, et_amount.getText().toString(), et_password.getText().toString());

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
                if (Constant.CODE_SUCCESS == responseData.getRetCode()) {
                    String type = getIntent().getAction().equals(Bank.BANK_TO_TRADE) ? "入金" : "出金";

                    ActivityUtils.showAlert(context, type + "成功");
                } else {
                    ActivityUtils.showAlert(context, responseData.getRetMessage() + "(" + responseData.getRetCode() + ")");
                }
            }


        });

    }
}