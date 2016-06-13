package com.puxtech.ybk.hangqing.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.hangqing.jsondata.getpriceforcommodity.FenBi;
import com.puxtech.ybk.hangqing.util.PriceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;

/**
 * Created by fanshuo on 16/5/9.
 */
public class MingXiListAdapter extends BaseAdapter {

    private Context context;
    private int mcu;//最小变动价位
    private List<FenBi> fenBiList;

    public MingXiListAdapter(Context context, List<FenBi> fenBiList, int mcu) {
        this.context = context;
        this.fenBiList = fenBiList;
        this.mcu = mcu;
    }

    @Override
    public int getCount() {
        return fenBiList.size();
    }

    @Override
    public FenBi getItem(int position) {
        return fenBiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setFenBiList(List<FenBi> fenBiList) {
        this.fenBiList = fenBiList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.hangqing_mingxi_list_item, null);
            viewHolder.tvTime =(TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tvPrice =(TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.tvVolume =(TextView) convertView.findViewById(R.id.tv_volume);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        FenBi item = fenBiList.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = new Date(item.getTime());

        viewHolder.tvTime.setText(dateFormat.format(date));
        viewHolder.tvPrice.setText(PriceUtil.keepPrecision(item.getPri(),mcu));
        viewHolder.tvVolume.setText(item.getTvol()+"");
        return convertView;
    }

    static class ViewHolder{
        TextView tvTime,tvPrice,tvVolume;
    }

}
