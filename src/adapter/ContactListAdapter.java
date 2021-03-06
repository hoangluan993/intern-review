/*
 * Custom Adapter to set values to ListView 
 * Set event click button edit and delele in item of ListView 
 */
package adapter;

import java.util.ArrayList;

import main.ContactsFragment;
import main.MainActivity;
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

import com.example.contactslist.R;

import database.MyDatabase;

@SuppressLint("InflateParams")
public class ContactListAdapter extends BaseAdapter {

	// Declare Variables
	private ArrayList<Contacts> mContacts;
	private LayoutInflater mInflater;
	private MainActivity mContext;
	private ContactsFragment mContactsFragment;
	private int mCount;

	/**
	 * Init values start for Adapter, data container ArrayList contacts include
	 * id, avatar, username, description, And context of MainActivity. Count to
	 * set limit item show in list view.
	 * 
	 * @param context
	 *            MainActivity this is context of MainActivity.
	 * @param data
	 *            MyDatabase this is database contains ArrayList contacts.
	 * @param contactsFragment
	 *            ContactsFragment this is context of ContactsFragment.
	 * @param count
	 *            Set limit show item for Adapter.
	 */
	public ContactListAdapter(MainActivity context, MyDatabase data,
			ContactsFragment contactsFragment, int count) {
		this.mContext = context;
		this.mContactsFragment = contactsFragment;
		this.mContacts = new ArrayList<Contacts>();
		this.mContacts.addAll(data.getContacts());
		this.mCount = count;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// Set count for ListView display.
	public void setCount(int count) {
		this.mCount = count;
	}

	@Override
	public int getCount() {
		return mCount;
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
	 * Locate the TextView, RelativeLayout, ImageView in item_list_contacts.xml.
	 * Init values for Item of ListView include Avatar, UerName. Set event Click
	 * ImageView Edit and Delete.
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_list_contacts, null);
			holder = new ViewHolder();
			// Locate the TextView, RelativeLayout, ImageView in
			// item_list_contacts.xml.
			holder.rlavatar = (RelativeLayout) convertView
					.findViewById(R.id.rlAvatar);
			holder.userName = (TextView) convertView
					.findViewById(R.id.tvUserName);
			holder.btnEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
			holder.btnDelete = (ImageView) convertView
					.findViewById(R.id.imgDelete);
			holder.btnEdit.setTag("edit");
			holder.btnDelete.setTag("delete");
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// Set the results into TextView, ImageView.
		holder.rlavatar.setBackgroundResource(mContacts.get(position)
				.getAvatar());
		holder.userName.setText(mContacts.get(position).getUserName());
		holder.btnEdit.setOnClickListener(new OnClickListener() {
			// TODO Set event click button Edit.
			@Override
			public void onClick(View v) {
				mContactsFragment.onClick(v, position);
			}
		});
		holder.btnDelete.setOnClickListener(new OnClickListener() {
			// TODO Set event click button Delete.
			@Override
			public void onClick(View v) {
				mContactsFragment.onClick(v, position);
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
