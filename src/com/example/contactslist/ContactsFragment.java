/*
 * Initialization List Contacts
 * Display to interface 
 */
package com.example.contactslist;

import java.util.ArrayList;

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

	private ListView mlistContact;
	private MyDatabase mData;
	private View mView;
	private ArrayList<Contacts> mContacts;
	private MainActivity mContext;
	private ContactListAdapter mAdapter;
	private boolean isLoading = false;
	ProgressDialog mProgressDialog;
	public int limit = 10;

	public ContactsFragment(MainActivity context, MyDatabase data) {
		this.mContext = context;
		this.mData = data;
		this.mContacts = data.getContacts();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_contact, container, false);
		//init();
		new LoadMoreTask().execute();
		return mView;
	}

	/**
	 * Init ListView and set Adapter for ListView
	 */
	private void init() {
		mlistContact = (ListView) mView.findViewById(R.id.lvContact);
		mAdapter = new ContactListAdapter((MainActivity) getActivity(), mData);
		mlistContact.setAdapter(mAdapter);
	}
	private class LoadMoreTask extends AsyncTask<Void, Void, Void>
	{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mlistContact = (ListView) mView.findViewById(R.id.lvContact);
			mAdapter = new ContactListAdapter((MainActivity) getActivity(), mData);
			mlistContact.setAdapter(mAdapter);
			mlistContact.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					// TODO Auto-generated method stub
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
					// TODO Auto-generated method stub
					if (firstVisibleItem + visibleItemCount == totalItemCount) {
						
//							new LoadMoreDataTask().execute();
			
					}
				}
			});
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	private class LoadMoreDataTask extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(mContext);
			mProgressDialog.setTitle("Parse.com Load More Tutorial");
			mProgressDialog.setMessage("Loading more...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			int position = mlistContact.getLastVisiblePosition();
			mAdapter = new ContactListAdapter(mContext, mData);
			mlistContact.setAdapter(mAdapter);
			mlistContact.setSelectionFromTop(position, 0);
			mProgressDialog.dismiss();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Log.d("limit",""+limit);
			String userName = "Hoang Luan";
			String decription = "DECRIPTION";
			int[] avatars = { R.drawable.img_avatar_1, R.drawable.img_avatar_2,
					R.drawable.img_avatar_3, R.drawable.img_avatar_4 };
			for (int i = limit; i < (limit+10); i++) {
				mData.addContact(new Contacts(""+i, avatars[i % 4], userName+" "+i, decription+" "+i));
				Log.d("insert", "thanh cong");
			}
			limit = limit+10;
			return null;
		}
		
	}

}
