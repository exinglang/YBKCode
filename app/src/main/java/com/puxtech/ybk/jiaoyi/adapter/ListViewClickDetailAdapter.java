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
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ListViewClickDetailAdapter extends BaseAdapter {

    private LinkedHashMap<String, String> mData = new LinkedHashMap<>();
    private String[] mKeys;
    Context context;
    public ListViewClickDetailAdapter(Context context,LinkedHashMap<String, String> data){
        this.context=context;
        mData  = data;

        mKeys = mData.keySet().toArray(new String[data.size()]);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(mKeys[position]);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        String key = mKeys[pos];
        String Value = getItem(pos).toString();
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.popwindows_click_detail_item, parent, false);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(key);
        viewHolder.tv_content.setText(Value);

        return convertView;
    }
        static class ViewHolder {
        TextView tv_title,tv_content;
    }
}
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.threerowadapter_item, parent, false);
//            viewHolder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
//            viewHolder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
//            viewHolder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
//            viewHolder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
//            viewHolder.tv_5 = (TextView) convertView.findViewById(R.id.tv_5);
//            viewHolder.tv_6 = (TextView) convertView.findViewById(R.id.tv_6);
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.tv_1.setText(getItem(position).getS1());
//        viewHolder.tv_2.setText(getItem(position).getS2());
//        viewHolder.tv_3.setText(getItem(position).getS3());
//        viewHolder.tv_4.setText(getItem(position).getS4());
//        viewHolder.tv_5.setText(getItem(position).getS5());
//        viewHolder.tv_6.setText(getItem(position).getS6());
//
//        return convertView;
//    }
//
//    static class ViewHolder {
//        TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6;
//    }
//
//}
