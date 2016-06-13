package com.puxtech.ybk.jiaoyi.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.adapter.ListViewClickDetailAdapter;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class TradePromptDialog {
    private Context context;
    private MyApplication myapp;
    private PopupWindow mpopwindows;
    EditText et_number;

    public TradePromptDialog(Context context) {
        this.context = context;
        myapp = (MyApplication) context.getApplicationContext();
    }


    public PopupWindow getPopwindow() {
        return mpopwindows;
    }

    LinearLayout ll_edittext;


    public Button getTradePromptDialog(String title, String sb, boolean isHeightWrapContent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View popupWindow = layoutInflater.inflate(
                R.layout.popwindows, null);
        mpopwindows = new PopupWindow(popupWindow);

        TextView tv_content = (TextView) popupWindow.findViewById(R.id.tv_content);
        TextView tv_title = (TextView) popupWindow.findViewById(R.id.tv_title);
        ll_edittext = (LinearLayout) popupWindow.findViewById(R.id.ll_edittext);
        tv_title.setText(title);
        et_number = (EditText) popupWindow.findViewById(R.id.et_number);
        Button bt_ok = (Button) popupWindow.findViewById(R.id.bt_ok);
        Button bt_cancel = (Button) popupWindow.findViewById(R.id.bt_cancel);
//		tv_title.setText(title);
        tv_content.setText(sb);

        bt_cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mpopwindows.dismiss();
            }
        });
        bt_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mpopwindows.dismiss();


            }
        });
        mpopwindows.setFocusable(true);
        // 设置允许在外点击消失
        mpopwindows.setOutsideTouchable(true);
        // 刷新状态
        mpopwindows.setBackgroundDrawable(new BitmapDrawable());
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        mpopwindows.setWidth(width / 10 * 7);
        if (isHeightWrapContent) {
            mpopwindows.setHeight(LayoutParams.WRAP_CONTENT);

        } else {
            mpopwindows.setHeight(height / 10 * 7);
        }
        mpopwindows.showAtLocation(popupWindow, Gravity.CENTER, 0, 0);
        return bt_ok;
    }

    public Button getClickDetail(LinkedHashMap<String,String> map) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View popupWindow = layoutInflater.inflate(
                R.layout.popwindows_click_detail, null);
        mpopwindows = new PopupWindow(popupWindow);
        ListView listview= (ListView) popupWindow.findViewById(R.id.listview);
        ListViewClickDetailAdapter adapter = new ListViewClickDetailAdapter(context,map);
        listview.setAdapter(adapter);
//		TextView tv_content = (TextView) popupWindow.findViewById(R.id.tv_content);
//		 tv_title = (TextView) popupWindow.findViewById(R.id.tv_tradetype);
//		et_number=(EditText) popupWindow.findViewById(R.id.et_number);
        Button bt_ok = (Button) popupWindow.findViewById(R.id.bt_ok);
//		Button bt_cancel = (Button) popupWindow.findViewById(R.id.bt_cancel);
//		tv_title.setText(title);
//		tv_content.setText(sb);
//		bt_cancel.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				mpopwindows.dismiss();
//			}
//		});
        bt_ok.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mpopwindows.dismiss();
            }
        });
        mpopwindows.setFocusable(true);
        // 设置允许在外点击消失
        mpopwindows.setOutsideTouchable(true);
        // 刷新状态
        mpopwindows.setBackgroundDrawable(new BitmapDrawable());
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        mpopwindows.setWidth(width / 10 * 7);
        mpopwindows.setHeight(height/10*7);

        mpopwindows.showAtLocation(popupWindow, Gravity.CENTER, 0, 0);
        return bt_ok;

    }
    public EditText getEt_number() {

        return et_number;

    }

    public LinearLayout getLl_edittext() {
        return ll_edittext;
    }
}

