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


public class ListLeftAdapter extends BaseAdapter {
	
	private Context context;
	private List<ListRightModel> list;

	public ListLeftAdapter(Context context, List<ListRightModel> list) {
		super();
		this.context = context;
		this.list = list;
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
		ViewHold hold;
		if (convertView==null) {
			hold=new ViewHold();
			convertView=LayoutInflater.from(context).inflate(R.layout.hangqing_list_left_item, null);
			hold.tvName =(TextView) convertView.findViewById(R.id.left_tv_name);
			hold.tvCode =(TextView) convertView.findViewById(R.id.left_tv_code);
			convertView.setTag(hold);
		}else {
			hold=(ViewHold) convertView.getTag();
		}
		hold.tvName.setText(list.get(position).getName());
		hold.tvCode.setText(list.get(position).getCode());
		return convertView;
	}
	
	static class ViewHold{
		TextView tvName,tvCode;
	}

}
