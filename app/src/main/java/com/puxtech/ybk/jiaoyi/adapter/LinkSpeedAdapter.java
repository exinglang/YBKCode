package com.puxtech.ybk.jiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.R;
import com.puxtech.ybk.qidong.entity.ContentsServerLlEntity;

import java.util.List;

public class LinkSpeedAdapter extends BaseAdapter {
	MyApplication myapp;
	private LayoutInflater inflater;
	List<ContentsServerLlEntity> useEnvList;
	int fastLlCode;
	public LinkSpeedAdapter(Context context,
							List<ContentsServerLlEntity> useEnvList,int fastLlCode) {
		this.useEnvList = useEnvList;
		this.inflater = LayoutInflater.from(context);
		this.fastLlCode=fastLlCode;
		myapp = (MyApplication) context.getApplicationContext();
	}
	public void setFastLlCode(int fastLlCode){
		this.fastLlCode=fastLlCode;
	}
	@Override
	public int getCount() {
		return useEnvList.size();
	}

	@Override
	public ContentsServerLlEntity getItem(int position) {
		// return getView(position,null,null);
		return useEnvList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.linkspeed_item,null);
			holder = new ViewHolder();
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_speed = (TextView) convertView.findViewById(R.id.tv_speed);
			holder.cb_ll = (CheckBox) convertView.findViewById(R.id.cb_ll);
			holder.bar = (ProgressBar) convertView.findViewById(R.id.bar);

			convertView.setTag(holder);
		} else {

			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_name.setText(useEnvList.get(position).getName());
		holder.cb_ll.setChecked(false);
		if (useEnvList.get(position).getCode() == fastLlCode) {

			holder.cb_ll.setChecked(true);
		}
		boolean isCeSuing = useEnvList.get(position).isCeSu();
		if (isCeSuing) {
			holder.bar.setVisibility(View.VISIBLE);
			holder.tv_speed.setVisibility(View.GONE);
		} else {
			holder.bar.setVisibility(View.GONE);
			holder.tv_speed.setVisibility(View.VISIBLE);
			try {
				holder.tv_speed.setText(useEnvList.get(position).getMs());
			} catch (Exception e) {

				holder.tv_speed.setText("--");
			}

		}

		return convertView;
	}

	class ViewHolder {
		TextView tv_name;
		TextView tv_speed;
		CheckBox cb_ll;
		ProgressBar bar;

	}
}
