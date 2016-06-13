package com.puxtech.ybk.shezhi;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.R;
import com.puxtech.ybk.hangqing.protocol.RequestManager;
import com.puxtech.ybk.helper.TextSizeHelper;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.adapter.LinkSpeedAdapter;
import com.puxtech.ybk.jiaoyi.httpmanager.HttpManagerLogin;
import com.puxtech.ybk.jiaoyi.page.BaseTradeActivity;
import com.puxtech.ybk.jiaoyi.responsedata.CheckCodeResponseData;
import com.puxtech.ybk.qidong.QiDongData;
import com.puxtech.ybk.qidong.SharedPreferenceManager;
import com.puxtech.ybk.qidong.entity.ContentsServerLlEntity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class About extends BaseTradeActivity {
    // private Dialog pd;
    LinkSpeedAdapter tradeAdapter, hangQinAdapter;
    ArrayList<ContentsServerLlEntity> tradeLlList, hangqinLlList;
    ArrayList<ContentsServerLlEntity> allList;
    private TextView  tv_name;
    private ImageView iv_icon;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TextSizeHelper.setTextSize(context);

        setContentView(R.layout.about);
        TradeHelper.initToolBarWithSubTitleAndFinishIcon(this, getString(R.string.pref_about_title), (Toolbar) findViewById(R.id.toolbar), (TextView) findViewById(R.id.tv_toolbar));


        TextView tv_url= (TextView) findViewById(R.id.tv_url);
        tv_url.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                String url = "http://" + getString(R.string.home_url); // web address
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_name = (TextView) findViewById(R.id.tv_name);
        iv_icon.setImageResource(R.drawable.ic_launcher);
        tv_name.setText(R.string.app_name);
    }



}
