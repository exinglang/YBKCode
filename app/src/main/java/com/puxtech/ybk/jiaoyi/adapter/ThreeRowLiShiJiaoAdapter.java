package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.entitydata.HistoryDealData;

import java.util.ArrayList;

public class ThreeRowLiShiJiaoAdapter extends BaseAdapter {
    ArrayList<HistoryDealData> list;
    Context context;

    public ThreeRowLiShiJiaoAdapter(Context context, ArrayList<HistoryDealData> list) {
        super();
        this.context = context;
        this.list = list;


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HistoryDealData getItem(int position) {
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

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HistoryDealData data = list.get(position);

        viewHolder.tv_1.setText(TradeHelper.getCommodityDataByCode(context,data.getCOMMODITY_ID()).getName());
        viewHolder.tv_2.setText(data.getCOMMODITY_ID());
//                        1-已委托 2-已成交
//                        3-已撤单 4-部分成交
//                        5-部分成交后撤单
//        if ((data.getSTATUS().equals("1") || (data.getSTATUS().equals("4")) {
        viewHolder.tv_3.setText(data.getPRICE());
        viewHolder.tv_4.setText(data.getQUANTITY());
        viewHolder.tv_5.setText(data.getTRADE_TYPE_CH());
        viewHolder.tv_6.setText(data.getHOLD_TIME());
//            viewHolder.tv_7.setText(entity.getORDERNO());//单号.不显示.
//            getrowlist.add(getrow);
//            viewHolder.tv_1.setText(getItem(position).getS1());
//            viewHolder.tv_2.setText(getItem(position).getS2());
//            viewHolder.tv_3.setText(getItem(position).getS3());
//            viewHolder.tv_4.setText(getItem(position).getS4());
//            viewHolder.tv_5.setText(getItem(position).getS5());
//            viewHolder.tv_6.setText(getItem(position).getS6());



        return convertView;
    }
    public static class ViewHolder {
        TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6;
    }

}


