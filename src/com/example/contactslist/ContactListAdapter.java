/*
 * Custom Adapter to set values to ListView 
 */
package com.example.contactslist;

import java.util.ArrayList;

import model.Contacts;
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
import database.MyDatabase;
import detail.DeleteDialogFragment;
import detail.DetailFragment;

@SuppressLint("InflateParams")
public class ContactListAdapter extends BaseAdapter {

	// Declare Variables
	private ArrayList<Contacts> mContacts;
	private LayoutInflater mInflater;
	private MainActivity mContext;
	private MyDatabase mData;

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
	public ContactListAdapter(MainActivity context, MyDatabase data) {
		this.mContext = context;
		this.mContacts = new ArrayList<Contacts>();
		this.mContacts.addAll(data.getContacts());
		this.mData = data;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mContacts.size();
	}

	@Override
	public String getItem(int position) {
		return mContacts.get(position).getUserName();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Init values for Item of ListView. Set event Click ImageView Edit and
	 * Delete.
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_list_contacts, null);
			viewHolder = new ViewHolder();
			// Locate the TextView, RelativeLayout, ImageView in
			// item_list_contacts.xml
			viewHolder.rlavatar = (RelativeLayout) convertView
					.findViewById(R.id.rlAvatar);
			viewHolder.userName = (TextView) convertView
					.findViewById(R.id.tvUserName);
			viewHolder.btnEdit = (ImageView) convertView
					.findViewById(R.id.imgEdit);
			viewHolder.btnDelete = (ImageView) convertView
					.findViewById(R.id.imgDelete);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Set the results into TextView, ImageView
		viewHolder.rlavatar.setBackgroundResource(mContacts.get(position)
				.getAvatar());
		viewHolder.userName.setText(mContacts.get(position).getUserName());
		viewHolder.btnEdit.setOnClickListener(new OnClickListener() {
			// TODO Set event click button Edit
			@Override
			public void onClick(View v) {
				mContext.isfragmentDetail = true;
				showDetail(position);
			}
		});
		viewHolder.btnDelete.setOnClickListener(new OnClickListener() {
			// TODO Set event click button Delete
			@Override
			public void onClick(View v) {
				new DeleteDialogFragment(mContext, position, mData).show(
						mContext.getFragmentManager(), "dialog");
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
	 * show Interface Edit Detail Fragment
	 */
	private void showDetail(int position) {
		FragmentTransaction fragtst = mContext.getFragmentManager()
				.beginTransaction();
		fragtst.replace(R.id.frameLayout, new DetailFragment(mContext,
				position, mData));
		fragtst.commit();
	}

}
