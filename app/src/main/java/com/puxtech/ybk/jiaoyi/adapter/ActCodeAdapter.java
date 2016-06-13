package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.puxtech.ybk.jiaoyi.entitydata.CommodityData;
import com.puxtech.ybk.jiaoyi.entitydata.UserBankData;

import java.util.ArrayList;

public class ActCodeAdapter extends BaseAdapter implements Filterable {
    ArrayList<CommodityData> allList;
//    ArrayList<CommodityData> unFilteruserBankDataArrayList;
//    ArrayList<CommodityData> showList;
ArrayList<CommodityData> filterList;

    Context context;
    private ArrayFilter mFilter;

    public ActCodeAdapter(Context context, ArrayList<CommodityData> allList) {

        this.context = context;
        this.allList = allList;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {


        return super.getDropDownView(position, convertView, parent);
    }

    public int getCount() {
        return filterList.size();
    }

    @Override
    public String getItem(int position) {
        return filterList.get(position).getCode();
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
        TextView text = (TextView) convertView.findViewById(android.R.id.text1);
        text.setText(filterList.get(position).getCode() + "-" + filterList.get(position).getName());
        return convertView;
    }


    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    private class ArrayFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

//            if (unFilteruserBankDataArrayList == null) {
//                unFilteruserBankDataArrayList = new ArrayList<>(allList);
//            } else
            if (prefix == null || prefix.length() == 0) {
                ArrayList<CommodityData> list = allList;
                results.values = list;
                results.count = list.size();
            } else {
                String prefixString = prefix.toString().toLowerCase();

                ArrayList<CommodityData> unfilteredValues = allList;
                int count = unfilteredValues.size();

                ArrayList<CommodityData> newValues = new ArrayList<>(count);

                for (int i = 0; i < count; i++) {
                    CommodityData pc = unfilteredValues.get(i);
                    if (pc != null) {

                        if (pc.getCode() != null && pc.getCode().toString().toLowerCase().contains(prefixString)) {

                            newValues.add(pc);
                        }
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
//                showList=newValues;
            }
            return results;
        }



        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterList = (ArrayList<CommodityData>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }

        }
    }
//    public ArrayList<CommodityData> getList(){
//        return showList;
//    }

}
