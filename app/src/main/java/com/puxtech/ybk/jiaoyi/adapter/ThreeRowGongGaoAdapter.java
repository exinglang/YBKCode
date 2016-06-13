package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.entitydata.GongGaoData;
import com.puxtech.ybk.jiaoyi.entitydata.HistoryDealData;

import java.util.ArrayList;

public class ThreeRowGongGaoAdapter extends BaseAdapter {
    ArrayList<GongGaoData> list;
    Context context;

    public ThreeRowGongGaoAdapter(Context context, ArrayList<GongGaoData> list) {
        super();
        this.context = context;
        this.list = list;


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public GongGaoData getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

            viewHolder.tv_2.setVisibility(View.GONE);
            viewHolder.tv_3.setVisibility(View.GONE);
            viewHolder.tv_4.setVisibility(View.GONE);
            viewHolder.tv_6.setVisibility(View.GONE);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GongGaoData data = list.get(position);

        viewHolder.tv_1.setText(data.getTITLE());
        viewHolder.tv_5.setText(data.getSEND_TIME());



        return convertView;
    }
    public static class ViewHolder {
        TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6;
    }

}


