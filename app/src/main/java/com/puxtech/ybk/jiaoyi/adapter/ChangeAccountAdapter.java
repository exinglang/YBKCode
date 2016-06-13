package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.entitydata.CommodityData;
import com.puxtech.ybk.jiaoyi.entitydata.ThreeRowData;

import java.util.ArrayList;

public class ChangeAccountAdapter extends BaseAdapter {
    String[] list;
    Context context;
    MyApplication myApplication;
    public ChangeAccountAdapter(Context context,String[] list) {
        super();
        this.context = context;
        myApplication=(MyApplication)context.getApplicationContext();
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public String getItem(int position) {
        return list[position];
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            viewHolder.tv_1 = (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(getItem(position).equals(myApplication.getTradeEntity().getOtherData().getFirmId())) {
            viewHolder.tv_1.setText("普通交易:" + getItem(position)+"(已登录)");
        }else{
            viewHolder.tv_1.setText("普通交易:" + getItem(position));

        }

        return convertView;
    }

    static class ViewHolder {
        TextView tv_1;
    }

}
