package com.example.contacts;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactListAdapter extends BaseAdapter {

	private ArrayList<Integer> mAvatars;
	private ArrayList<String> mUserNames;
	private LayoutInflater mInflater;
	private MainActivity mContext;

	public ContactListAdapter(Context context, ArrayList<Integer> mAvatar,
			ArrayList<String> mUserName) {
		this.mContext = (MainActivity) context;
		this.mAvatars = mAvatar;
		this.mUserNames = mUserName;
		mInflater = LayoutInflater.from(this.mContext);
	}

	@Override
	public int getCount() {
		return mAvatars.size();
	}

	@Override
	public String getItem(int position) {
		return mUserNames.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		final int VITRI = position;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_list_contacts,null);
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.imgAvatar = (ImageView) convertView
				.findViewById(R.id.imgavatar);
		viewHolder.userName = (TextView) convertView
				.findViewById(R.id.tvusername);
		viewHolder.actionArrow = (ImageView) convertView
				.findViewById(R.id.imgarrow);

		viewHolder.imgAvatar.setImageResource(mAvatars.get(position));
		viewHolder.userName.setText(mUserNames.get(position));
		viewHolder.actionArrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, ""+VITRI, Toast.LENGTH_LONG).show();
				final Dialog dialog = new Dialog(mContext);
				dialog.setContentView(R.layout.custom_dialog);
				dialog.setTitle("Dialog");
				Button btnOk = (Button) dialog.findViewById(R.id.dialogButtonOK);
				btnOk.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						switch (v.getId()) {
						case R.id.dialogButtonOK:
							dialog.dismiss();
							break;
						default:
							break;
						}
						
					}
				});
	 
				dialog.show();
			}
		});
		return convertView;
	}

	public static class ViewHolder {
		public TextView userName;
		public ImageView imgAvatar,actionArrow;
	}

}
