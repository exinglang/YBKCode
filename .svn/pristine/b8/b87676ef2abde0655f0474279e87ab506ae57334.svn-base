package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.entitydata.HoldDetailData;
import com.puxtech.ybk.jiaoyi.entitydata.ThreeRowData;

import java.util.ArrayList;

public class TiHuoCommodityNameAdapter extends BaseAdapter {
    ArrayList<HoldDetailData> list;
    Context context;
    public TiHuoCommodityNameAdapter(Context context, ArrayList<HoldDetailData> list) {
        super();
        this.context = context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HoldDetailData getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            viewHolder.tv_1 = (TextView) convertView.findViewById(android.R.id.text1);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_1.setText(TradeHelper.getCommodityDataByCode(context,getItem(position).getCOMMODITYID()).getName());


        return convertView;
    }

    static class ViewHolder {
        TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6;
    }

}
