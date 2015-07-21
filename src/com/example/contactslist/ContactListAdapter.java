package com.example.contactslist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ContactListAdapter extends BaseAdapter {

	private ArrayList<Integer> mAvatars;
	private ArrayList<String> mUserNames;
	private LayoutInflater mInflater;
	private Context mContext;

	/**
	 * @param context application context
	 * 
	 * @param mAvatars ArrayList<Integer> set Avatar for Contacts
	 * 
	 * @param mUserNames ArrayList<String> set UserName for Contacts
	 */
	public ContactListAdapter(Context context, ArrayList<Integer> mAvatars,
			ArrayList<String> mUserNames) {
		this.mContext = context;
		this.mAvatars = mAvatars;
		this.mUserNames = mUserNames;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_list_contacts, null);
			viewHolder = new ViewHolder();
			viewHolder.imgAvatar = (ImageView) convertView
					.findViewById(R.id.imgavatar);
			viewHolder.userName = (TextView) convertView
					.findViewById(R.id.tvusername);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.imgAvatar.setImageResource(mAvatars.get(position));
		viewHolder.userName.setText(mUserNames.get(position));

		return convertView;
	}

	public static class ViewHolder {
		public TextView userName;
		public ImageView imgAvatar;
	}

}
