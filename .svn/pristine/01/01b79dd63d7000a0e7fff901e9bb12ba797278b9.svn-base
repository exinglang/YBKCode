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
import com.puxtech.ybk.jiaoyi.entitydata.TuoGuanListData;
import com.puxtech.ybk.jiaoyi.page.TradeShengGou;
import com.puxtech.ybk.jiaoyi.page.TradeTuoGuan;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;

public class ThreeRowActionTuoGuanAdapter extends BaseAdapter {
    ArrayList<TuoGuanListData> list;
    Context context;
    TradeTuoGuan page;

    public ThreeRowActionTuoGuanAdapter(Context context, TradeTuoGuan page, ArrayList<TuoGuanListData> list) {
        super();
        this.context = context;
        this.list = list;
        this.page = page;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TuoGuanListData getItem(int position) {
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
            viewHolder.bt_commit.setText("托管");

//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//                    LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//            lp.addRule(RelativeLayout.BELOW,  childView2.getId());
//            lp.rightMargin=30;
//            lp.topMargin=30;
//            item4.setId(4);
//            item4.setLayoutParams(lp);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_1.setText(getItem(position).getCOMMODITY_NAME());
        viewHolder.tv_2.setText(getItem(position).getCOMMODITY_ID());
        viewHolder.tv_3.setText(getItem(position).getPLAN_NO());



//
        viewHolder.tv_4.setText(getItem(position).getSTATUS_CH());



        viewHolder.tv_5.setText(getItem(position).getTYPE_CH());
        viewHolder.tv_6.setText(getItem(position).getDESCRIPTION());
        if(!getItem(position).getSTATUS().equals("1")){
            viewHolder.bt_commit.setBackgroundResource(R.drawable.home_bt_selector_selected);
            viewHolder.bt_commit.setClickable(false);
        }
        viewHolder.bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                page.popWindows(getItem(position));
            }
        });

        return convertView;
    }


    static class ViewHolder {
        TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6;
        Button bt_commit;
    }



}
