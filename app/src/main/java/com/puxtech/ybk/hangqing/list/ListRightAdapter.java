package com.puxtech.ybk.hangqing.list;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puxtech.ybk.R;
import com.puxtech.ybk.hangqing.model.ListRightModel;


public class ListRightAdapter extends BaseAdapter {
	private Context context;
	List<ListRightModel> list;

	public ListRightAdapter(Context context, List<ListRightModel> models) {
		super();
		this.context = context;
		this.list = models;
	}
	
	@Override
	public int getCount() {
		if (list!=null) {
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (list!=null) {
			return list.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHold viewHold;
		if (convertView==null) {
			viewHold=new ViewHold();
			convertView=LayoutInflater.from(context).inflate(R.layout.hangqing_list_right_item, null);
			viewHold.tvXianJia =(TextView) convertView.findViewById(R.id.right_item_xianjia);
			viewHold.tvZhangDie =(TextView) convertView.findViewById(R.id.right_item_zhangdie);
			viewHold.tvZhangFu =(TextView) convertView.findViewById(R.id.right_item_zhangfu);
			viewHold.tvZuoShou =(TextView) convertView.findViewById(R.id.right_item_zuoshou);
			viewHold.tvChengJiaoLiang =(TextView) convertView.findViewById(R.id.right_item_chengjiaoliang);
			viewHold.tvChengJiaoE =(TextView) convertView.findViewById(R.id.right_item_chengjiaoe);
			viewHold.tvZuiGao =(TextView) convertView.findViewById(R.id.right_item_zuigao);
			viewHold.tvZuiDi =(TextView) convertView.findViewById(R.id.right_item_zuidi);
			viewHold.tvZhenFu =(TextView) convertView.findViewById(R.id.right_item_zhenfu);
			convertView.setTag(viewHold);
		}else {
			viewHold=(ViewHold) convertView.getTag();
		}
		
		viewHold.tvXianJia.setText(list.get(position).getXianJiaText());
		viewHold.tvZhangDie.setText(list.get(position).getZhangDieText());
		viewHold.tvZhangFu.setText(list.get(position).getZhangFuText());
		viewHold.tvZuoShou.setText(list.get(position).getZuoShouText());
		viewHold.tvChengJiaoLiang.setText(list.get(position).getChengJiaoLiangText());
		viewHold.tvChengJiaoE.setText(list.get(position).getChengJiaoEText());
		viewHold.tvZuiGao.setText(list.get(position).getZuiGaoText());
		viewHold.tvZuiDi.setText(list.get(position).getZuiDiText());
		viewHold.tvZhenFu.setText(list.get(position).getZhenFuText());

		//设置颜色
        //最新价
        if (list.get(position).getXianJia() > list.get(position).getZuoShou()){
            viewHold.tvXianJia.setTextColor(context.getResources().getColor(R.color.hangqing_red_text));
        }
        else if (list.get(position).getXianJia() == list.get(position).getZuoShou()){
            viewHold.tvXianJia.setTextColor(context.getResources().getColor(R.color.hangqing_white_text));
        }
        else{
            viewHold.tvXianJia.setTextColor(context.getResources().getColor(R.color.hangqing_green_text));
        }
        //涨跌涨幅
        if (list.get(position).getZhangDie() > 0){
            viewHold.tvZhangDie.setTextColor(context.getResources().getColor(R.color.hangqing_red_text));
            viewHold.tvZhangFu.setTextColor(context.getResources().getColor(R.color.hangqing_red_text));
        }
        else if (list.get(position).getZhangDie() == 0){
            viewHold.tvZhangDie.setTextColor(context.getResources().getColor(R.color.hangqing_white_text));
            viewHold.tvZhangFu.setTextColor(context.getResources().getColor(R.color.hangqing_white_text));
        }
        else{
            viewHold.tvZhangDie.setTextColor(context.getResources().getColor(R.color.hangqing_green_text));
            viewHold.tvZhangFu.setTextColor(context.getResources().getColor(R.color.hangqing_green_text));
        }
        //最高
        if (list.get(position).getZuiGao() > list.get(position).getZuoShou()){
            viewHold.tvZuiGao.setTextColor(context.getResources().getColor(R.color.hangqing_red_text));
        }
        else if (list.get(position).getZuiGao() == list.get(position).getZuoShou()){
            viewHold.tvZuiGao.setTextColor(context.getResources().getColor(R.color.hangqing_white_text));
        }
        else if (list.get(position).getZuiGao() < list.get(position).getZuoShou() && list.get(position).getZuiGao() > 0){
            viewHold.tvZuiGao.setTextColor(context.getResources().getColor(R.color.hangqing_green_text));
        }
        //最低
        if (list.get(position).getZuiDi() > list.get(position).getZuoShou()){
            viewHold.tvZuiDi.setTextColor(context.getResources().getColor(R.color.hangqing_red_text));
        }
        else if (list.get(position).getZuiDi() == list.get(position).getZuoShou()){
            viewHold.tvZuiDi.setTextColor(context.getResources().getColor(R.color.hangqing_white_text));
        }
        else if (list.get(position).getZuiDi() < list.get(position).getZuoShou() && list.get(position).getZuiDi() > 0){
            viewHold.tvZuiDi.setTextColor(context.getResources().getColor(R.color.hangqing_green_text));
        }

		return convertView;
	}
	
	static class ViewHold{
		TextView tvXianJia, tvZhangDie, tvZhangFu, tvZuoShou, tvChengJiaoLiang, tvChengJiaoE, tvZuiGao, tvZuiDi, tvZhenFu;
	}

}
