/*
 * Custom Adapter to set values to ListView 
 */
package com.example.contactslist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ContactListAdapter extends BaseAdapter {

	public static ArrayList<Integer> sAvatars;
	public static ArrayList<Contacts> suserNames;
	private LayoutInflater mInflater;
	private MainActivity mContext;
	public static int sVitri;
	/**
	 * @param context
	 *            application context
	 * 
	 * @param mAvatars
	 *            ArrayList<Integer> set Avatar for Contacts
	 * 
	 * @param mUserNames
	 *            ArrayList<String> set UserName for Contacts
	 */
	public ContactListAdapter(MainActivity context,
			ArrayList<Integer> mAvatars, ArrayList<Contacts> mUserNames) {
		this.mContext = context;
		this.sAvatars = mAvatars;
		this.suserNames = mUserNames;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return sAvatars.size();
	}

	@Override
	public String getItem(int position) {
		return suserNames.get(position).getUserName();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_list_contacts, null);
			viewHolder = new ViewHolder();
			viewHolder.rlavatar = (RelativeLayout) convertView
					.findViewById(R.id.rlavatar);
			viewHolder.userName = (TextView) convertView
					.findViewById(R.id.tvusername);
			viewHolder.btnEdit = (ImageView) convertView
					.findViewById(R.id.imgedit);
			viewHolder.btnDelete = (ImageView) convertView
					.findViewById(R.id.imgdelete);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.rlavatar.setBackgroundResource(sAvatars.get(position));
		viewHolder.userName.setText(suserNames.get(position).getUserName());
		viewHolder.btnEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.scheckBack = true;
				showDetailFragment();
				sVitri= position;
			}
		});
		viewHolder.btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new DeleteDialogFragment().show(mContext.getFragmentManager(),
						"dialog");
				sVitri= position;
			}
		});
		
		return convertView;
	}

	public static class ViewHolder {
		public TextView userName;
		public ImageView btnEdit, btnDelete;
		public RelativeLayout rlavatar;
	}
	/**
	 * show Detail Fragment
	 */
	private void showDetailFragment() {
		FragmentTransaction fragtst = MainActivity.sfragmentmng
				.beginTransaction();
		fragtst.replace(R.id.container_fragment, new DetailFragment());
		fragtst.commit();
	}

}
