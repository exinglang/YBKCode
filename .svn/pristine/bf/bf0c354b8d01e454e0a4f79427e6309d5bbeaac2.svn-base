package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.jiaoyi.entitydata.ThreeRowData;
import com.puxtech.ybk.jiaoyi.entitydata.UserBankData;

import java.util.ArrayList;

public class BankTransferBankAdapter extends BaseAdapter   {
    ArrayList<UserBankData> userBankDataArrayList;
    Context context;

    public BankTransferBankAdapter(Context context, ArrayList<UserBankData> userBankDataArrayList) {

        this.context = context;
        this.userBankDataArrayList = userBankDataArrayList;

    }


    @Override
    public int getCount() {
        return userBankDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userBankDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView text;
//        text = new TextView(context);
//        text.setText(userBankDataArrayList.get(position).getBANK_NAME());
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        TextView text= (TextView) convertView.findViewById(android.R.id.text1);
        text.setText(userBankDataArrayList.get(position).getBANK_NAME());
        return convertView;
    }




}
