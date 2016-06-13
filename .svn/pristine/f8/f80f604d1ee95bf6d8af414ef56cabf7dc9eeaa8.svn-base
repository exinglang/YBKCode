package com.puxtech.ybk.jiaoyi.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.zip.Inflater;

public class Bank extends BaseTradeActivity {
    public static final String BANK_TO_TRADE = "BANK_TO_TRADE";
    public static final String TRADE_TO_BANK = "TRADE_TO_BANK";
    public static final int BANK = 1;

    TextView tv_tradetobank, tv_banktotrade, tv_history;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);
        setContentView(R.layout.activity_bank);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, "银行转账", (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));
        initWidget();

    }

    private void initWidget() {
        tv_tradetobank = (TextView) findViewById(R.id.tv_tradetobank);
        tv_banktotrade = (TextView) findViewById(R.id.tv_banktotrade);
        tv_history = (TextView) findViewById(R.id.tv_history);
        tv_tradetobank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BankTransfer.class);
                intent.setAction(TRADE_TO_BANK);
                startActivity(intent);
            }
        });
        tv_banktotrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BankTransfer.class);
                intent.setAction(BANK_TO_TRADE);
                startActivity(intent);
            }
        });
        tv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BankHistory.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!myApplication.getTradeEntity().getOtherData().isSetedFundPwd()) {
            //未初始化资金密码

            ActivityUtils.showCenterToast(context, Constant.NO_FUND_PWD,Toast.LENGTH_LONG);
            Intent intent = new Intent();
            intent.setClass(context, ChangePassword.class);
            startActivityForResult(intent, BANK);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BANK) {

            if (resultCode != 101) {
                //不为101,即未初始化密码
                finish();


            }
        }

    }

    //    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_home, menu);
//        super.onCreateOptionsMenu(menu);
//    }

}
