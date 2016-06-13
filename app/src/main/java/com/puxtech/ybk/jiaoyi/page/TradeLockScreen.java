package com.puxtech.ybk.jiaoyi.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.BankTransferBankAdapter;
import com.puxtech.ybk.jiaoyi.entitydata.UserBankData;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerMoney;
import com.puxtech.ybk.jiaoyi.responsedata.BaseResponseDataTrade;
import com.puxtech.ybk.util.AES;
import com.puxtech.ybk.util.ActivityUtils;
import com.puxtech.ybk.util.Base64;
import com.puxtech.ybk.util.MD5;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class TradeLockScreen extends BaseTradeActivity {
    TextView tv_id;
    EditText et_password;
    Button bt_unlock, bt_logout;
//    EditText et_password, et_amount;
//    Button bt_commit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.activity_trade_lock_screen);
        initWidget();

//        String title = getIntent().getAction().equals(Bank.BANK_TO_TRADE) ? getString(R.string.bank_to_trade) : getString(R.string.trade_to_bank);
        TradeHelper.initToolBarWithSubTitle(this, "已锁定", (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
//        initWidget();

    }

    private void initWidget() {

        tv_id = (TextView) findViewById(R.id.tv_id);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_unlock = (Button) findViewById(R.id.bt_unlock);
        bt_logout = (Button) findViewById(R.id.bt_logout);
        tv_id.setText("用户名:" + myApplication.getTradeEntity().getOtherData().getFirmId());
        bt_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] logonKey = MD5.getMD5(myApplication.getTradeEntity().getOtherData().getFirmId() + et_password.getText().toString());// 登录加解密密钥
                ByteBuffer buffer;
                try {
                    buffer = ByteBuffer.wrap(AES.decrypt(
                            Base64.decode(myApplication.getTradeEntity().getOtherData().getClt_key()), logonKey));
                } catch (Exception e) {
                    e.printStackTrace();
                    ActivityUtils.showAlert(context, "密码错误");
                    return;
                }

//                ByteBuffer buffer = ByteBuffer.wrap(AES.decrypt(
//                        Base64.decode(rep.getString(TAG_CLT_KEY)), logonKey));
//                buffer.order(ByteOrder.LITTLE_ENDIAN);
//                byte[] key = new byte[buffer.getShort()];
//                buffer.get(key);

                buffer.order(ByteOrder.LITTLE_ENDIAN);
                byte[] key = new byte[buffer.getShort()];
                buffer.get(key);
                if (Arrays.equals(myApplication.getTradeEntity().getOtherData().getKey(), key)) {
                    TradeHelper.userTouchReset(context);
                    finish();
                } else {
                    ActivityUtils.showAlert(context, "密码错误");


                }

            }
        });
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TradeHelper.ZhuXiao(context);


            }
        });

    }

    @Override
    public void onUserInteraction() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


}