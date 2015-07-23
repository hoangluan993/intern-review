/*
 * Custom Adapter to set values to ListView 
 */
package com.example.contactslist;

import java.util.ArrayList;

import model.Contacts;
import android.annotation.SuppressLint;
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

@SuppressLint("InflateParams")
public class ContactListAdapter extends BaseAdapter {

	// Declare Variables
	private ArrayList<Contacts> mContacts;
	private LayoutInflater mInflater;
	private MainActivity mContext;
	private ContactsFragment mContactsFragment;

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
	public ContactListAdapter(MainActivity context, MyDatabase data, ContactsFragment contactsFragment) {
		this.mContext = context;
		this.mContactsFragment = contactsFragment;
		this.mContacts = new ArrayList<Contacts>();
		this.mContacts.addAll(data.getContacts());
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_list_contacts, null);
			holder = new ViewHolder();
			// Locate the TextView, RelativeLayout, ImageView in
			// item_list_contacts.xml
			holder.rlavatar = (RelativeLayout) convertView
					.findViewById(R.id.rlAvatar);
			holder.userName = (TextView) convertView
					.findViewById(R.id.tvUserName);
			holder.btnEdit = (ImageView) convertView
					.findViewById(R.id.imgEdit);
			holder.btnDelete = (ImageView) convertView
					.findViewById(R.id.imgDelete);
			holder.btnEdit.setTag("edit");
			holder.btnDelete.setTag("delete");
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// Set the results into TextView, ImageView
		holder.rlavatar.setBackgroundResource(mContacts.get(position)
				.getAvatar());
		holder.userName.setText(mContacts.get(position).getUserName());
		holder.btnEdit.setOnClickListener(new OnClickListener() {
			// TODO Set event click button Edit
			@Override
			public void onClick(View v) {
				mContext.isfragmentDetail = true;
				mContactsFragment.setPositionContact(position);
				mContactsFragment.onClick(v);
			}
		});
		holder.btnDelete.setOnClickListener(new OnClickListener() {
			// TODO Set event click button Delete
			@Override
			public void onClick(View v) {
				mContactsFragment.setPositionContact(position);
				mContactsFragment.onClick(v);
			}
		});

		return convertView;
	}

	public static class ViewHolder {
		public TextView userName;
		public ImageView btnEdit, btnDelete;
		public RelativeLayout rlavatar;
	}

}
