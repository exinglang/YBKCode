package com.puxtech.ybk.qidong;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.jiaoyi.Logger;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.shezhi.SheZhiHelper;

/**
 * Created by fanshuo on 16/4/20.
 */
public class WelcomeActivity extends BaseActivity {


    private AbstractWelcomeController welcomeController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomeController = new WelcomeController(this);
        setContentView(welcomeController.createContentView());
        welcomeController.startTaskQueue();

        SheZhiHelper.saveCurrentLiuLiang(context);//记录当前启动时流量

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(welcomeController != null){
            welcomeController.onDestroy();
        }
    }
}
