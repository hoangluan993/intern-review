/*
 * Initialization List Contacts
 * Display to interface 
 */
package com.example.contactslist;

import model.Contacts;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import database.MyDatabase;

public class ContactsFragment extends Fragment {

	// Declare Variables
	private ListView mlistContact;
	private MyDatabase mData;
	private View mView;
	private MainActivity mContext;
	private ContactListAdapter mAdapter;
	private ProgressDialog mProgressDialog;
	// Set the limit for load more
	public int limit = 20;

	public ContactsFragment(MainActivity context, MyDatabase data) {
		this.mContext = context;
		this.mData = data;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragment_contacts.xml
		mView = inflater.inflate(R.layout.fragment_contact, container, false);
		new LoadMoreTask().execute();
		return mView;
	}

	/**
	 * Thread create event when stay at last contact
	 * 
	 * @author HoangLuan
	 *
	 */
	private class LoadMoreTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			// Locate the ListView in fragment_contact.xml
			mlistContact = (ListView) mView.findViewById(R.id.lvContact);
			mAdapter = new ContactListAdapter((MainActivity) getActivity(),
					mData);
			mlistContact.setAdapter(mAdapter);
			mlistContact.setOnScrollListener(new OnScrollListener() {

				@Override
				public void onScrollStateChanged(AbsListView view,
						int scrollState) {
					// TODO Last Events ListView
					int threshold = 1;
					int count = mlistContact.getCount();

					if (scrollState == SCROLL_STATE_IDLE) {
						if (mlistContact.getLastVisiblePosition() >= count
								- threshold) {
							new LoadMoreDataTask().execute();
						}
					}
				}

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
				}
			});
		}

		@Override
		protected Void doInBackground(Void... params) {
			return null;
		}

	}

	/**
	 * Thread load more contact And add contacts to list when stay at last
	 * contact
	 * 
	 * @author HoangLuan
	 *
	 */
	private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create and show Dialog Loading more
			mProgressDialog = new ProgressDialog(mContext);
			mProgressDialog.setTitle("Please wait");
			mProgressDialog.setMessage("Loading data more...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Add elements to data.
			String userName = "Hoang Luan";
			String decription = "DECRIPTION";
			int[] avatars = { R.drawable.img_avatar_1, R.drawable.img_avatar_2,
					R.drawable.img_avatar_3, R.drawable.img_avatar_4 };
			for (int i = limit; i < (limit + 10); i++) {
				mData.addContact(new Contacts("" + i, avatars[i % 4], userName
						+ " " + i, decription + " " + i));
				Log.d("insert", "thanh cong");
			}
			limit = limit + 10;
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// After adding data elements to Get and add ListView
			int position = mlistContact.getLastVisiblePosition();
			mAdapter = new ContactListAdapter(mContext, mData);
			mlistContact.setAdapter(mAdapter);
			mlistContact.setSelectionFromTop(position, 0);
			mProgressDialog.dismiss();
		}

	}

}
