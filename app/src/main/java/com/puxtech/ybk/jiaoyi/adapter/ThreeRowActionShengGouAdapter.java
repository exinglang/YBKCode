package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.entitydata.ShengGouListData;
import com.puxtech.ybk.jiaoyi.entitydata.ThreeRowData;
import com.puxtech.ybk.jiaoyi.page.TradeCheDan;
import com.puxtech.ybk.jiaoyi.page.TradeShengGou;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;

public class ThreeRowActionShengGouAdapter extends BaseAdapter {
    ArrayList<ShengGouListData> list;
    Context context;
    TradeShengGou tradeCheDan;

    public ThreeRowActionShengGouAdapter(Context context, TradeShengGou tradeCheDan, ArrayList<ShengGouListData> list) {
        super();
        this.context = context;
        this.list = list;
        this.tradeCheDan = tradeCheDan;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ShengGouListData getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.threerowadapter_button_item, parent, false);
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
            viewHolder.bt_commit = (Button) convertView.findViewById(R.id.bt_commit);
            viewHolder.bt_commit.setText("申购");
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_1.setText(getItem(position).getCOMMODITY_NAME());
        viewHolder.tv_2.setText(getItem(position).getCOMMODITY_ID());
        viewHolder.tv_3.setText(getItem(position).getPLAN_NO());



        viewHolder.tv_4.setText(getItem(position).getSTATUS_CH());
        viewHolder.tv_5.setText(getItem(position).getPRICE());
        viewHolder.tv_6.setText(getItem(position).getLOTTERY_TIME());

        viewHolder.bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                clickCheDan();
//                ActivityUtils.showAlertWithConfirmText(context, "是否撤销此订单", "确定", new Runnable() {
//                    @Override
//                    public void run() {
//                        tradeCheDan.request(TradeCheDan.REQUEST_TYPE_CHE_DAN, getItem(position).getS7(),getItem(position).getS2());
//
//                    }
//                });
                tradeCheDan.popWindows(getItem(position));
            }
        });

        return convertView;
    }


    static class ViewHolder {
        TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6;
        Button bt_commit;
    }



}
