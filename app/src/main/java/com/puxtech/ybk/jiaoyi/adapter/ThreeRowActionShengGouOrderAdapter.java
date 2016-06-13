package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.TradeHelper;
import com.puxtech.ybk.jiaoyi.entitydata.ShengGouOrderData;
import com.puxtech.ybk.jiaoyi.entitydata.ShengGouOrderData;
import com.puxtech.ybk.jiaoyi.page.TradeChaXunShengGouChaXun;
import com.puxtech.ybk.jiaoyi.page.TradeCheDan;
import com.puxtech.ybk.util.ActivityUtils;

import java.util.ArrayList;

public class ThreeRowActionShengGouOrderAdapter extends BaseAdapter {
    ArrayList<ShengGouOrderData> list;
    Context context;
    TradeChaXunShengGouChaXun pager;

    public ThreeRowActionShengGouOrderAdapter(Context context, TradeChaXunShengGouChaXun pager, ArrayList<ShengGouOrderData> list) {
        super();
        this.context = context;
        this.list = list;
        this.pager = pager;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ShengGouOrderData getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
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
            viewHolder.bt_commit.setText("撤销");

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShengGouOrderData data = list.get(position);

        viewHolder.tv_1.setText(data.getCOMMODITY_NAME());
        viewHolder.tv_2.setText(data.getCOMMODITY_ID());
        viewHolder.tv_3.setText(data.getORDER_NO());
        viewHolder.tv_4.setText(data.getPLAN_NO());
        viewHolder.tv_5.setText(data.getSTATUS_CH());
        viewHolder.tv_6.setText(data.getQUANTITY());

        viewHolder.bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                clickCheDan();

                ActivityUtils.showAlertWithConfirmText(context, "是否撤销此申购", "确定", new Runnable() {
                    @Override
                    public void run() {
                        pager.request(TradeChaXunShengGouChaXun.REQUEST_TYPE_CHE_DAN, getItem(position).getORDER_NO());

                    }
                });
            }
        });

        return convertView;
    }
    public static class ViewHolder {
        TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6;
        Button bt_commit;
    }

}


