package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.entitydata.ThreeRowData;

import java.util.ArrayList;
import java.util.List;

public class ThreeRowAdapter extends BaseAdapter {
    ArrayList<ThreeRowData> list;
    Context context;
    public ThreeRowAdapter(Context context,ArrayList<ThreeRowData> list) {
        super();
        this.context = context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ThreeRowData getItem(int position) {
        return list.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.threerowadapter_item, parent, false);
            viewHolder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
            viewHolder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
            viewHolder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
            viewHolder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
            viewHolder.tv_5 = (TextView) convertView.findViewById(R.id.tv_5);
            viewHolder.tv_6 = (TextView) convertView.findViewById(R.id.tv_6);
            viewHolder.tv_1.setSelected(true);
            viewHolder.tv_2.setSelected(true);
            viewHolder.tv_3.setSelected(true);
            viewHolder.tv_4.setSelected(true);
            viewHolder.tv_5.setSelected(true);
            viewHolder.tv_6.setSelected(true);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_1.setText(getItem(position).getS1());
        viewHolder.tv_2.setText(getItem(position).getS2());
        viewHolder.tv_3.setText(getItem(position).getS3());
        viewHolder.tv_4.setText(getItem(position).getS4());
        viewHolder.tv_5.setText(getItem(position).getS5());
        viewHolder.tv_6.setText(getItem(position).getS6());

        return convertView;
    }

    static class ViewHolder {
        TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6;
    }

}
