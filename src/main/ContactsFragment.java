/*
 * Initialization List Contacts
 * Display to interface 
 */
package main;

import java.util.ArrayList;

import com.example.contactslist.R;

import model.Contacts;
import adapter.ContactListAdapter;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import database.MyDatabase;

public class ContactsFragment extends Fragment {

	// Declare Variables.
	private ListView mListContact;
	private MyDatabase mData;
	private View mView;
	private MainActivity mContext;
	private ContactListAdapter mAdapter;
	private ArrayList<Contacts> mContacts;
	// Set the limit item show in ListView before load more.
	public int limit = 15;

	/**
	 * Init values start for List Contacts including database contains ArrayList
	 * include id, avatar, username, decription.
	 * 
	 * @param context
	 *            MainActivity this is context of MainActivity.
	 * @param data
	 *            MyDatabase this is database contains information contacts
	 *            including id, avatar, username, decription.
	 */
	public ContactsFragment(MainActivity context, MyDatabase data) {
		this.mContext = context;
		this.mData = data;
		this.mContacts = data.getContacts();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragment_contacts.xml.
		mView = inflater.inflate(R.layout.fragment_contact, container, false);
		init();
		return mView;
	}

	/**
	 * Locate the element in fragment_contact.xml Create ContactsListAdapter and
	 * Set Adapter for ListView Set event when stay at last contacts to load
	 * more.
	 */

	private void init() {
		// Locate the ListView in fragment_contact.xml.
		mListContact = (ListView) mView.findViewById(R.id.lvContact);
		mAdapter = new ContactListAdapter((MainActivity) getActivity(), mData,
				ContactsFragment.this, limit);
		mListContact.setAdapter(mAdapter);
		// Set Event when stay at last contacts.
		mListContact.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Last Events ListView.
				int threshold = 1;
				int count = mListContact.getCount();

				if (scrollState == SCROLL_STATE_IDLE) {
					if (mListContact.getLastVisiblePosition() >= count
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

	/**
	 * Thread load more contact And add contacts to list when stay at last
	 * contact Load more adding 10 element.
	 * 
	 * @author HoangLuan
	 *
	 */
	private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Show icon Loading more.
			mContext.showLoading();
		}

		/**
		 * Run in background
		 */
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Set add elements display in list view after Load more.
			// Set Delay 1s for show icon Loading more
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (limit + 10 < mContacts.size()) {
				limit = limit + 10;
			} else {
				limit = mContacts.size();
			}
			return null;
		}

		/**
		 * Update Interface.
		 */
		@Override
		protected void onPostExecute(Void result) {
			// After adding data elements to Get and add ListView.
			int position = mListContact.getLastVisiblePosition();
			// Set limit item show in ListView.
			mAdapter.setCount(limit);
			mAdapter = new ContactListAdapter(mContext, mData,
					ContactsFragment.this, limit);
			mListContact.setAdapter(mAdapter);
			mListContact.setSelectionFromTop(position, 0);
			// hide icon Loading more.
			mContext.hideLoading();
		}

	}

	/**
	 * Set event click button edit or delete in item of ListView Go to page Edit
	 * Detail or show dialog confirm delete.
	 * 
	 * @param v View
	 * @param position
	 *            set position for edit or delete in database and update
	 *            Interface.
	 */

	public void onClick(View v, int position) {
		// TODO Event click button Edit and delete.
		String mgetAction = (String) v.getTag();

		if (mgetAction.equals("edit")) {
			// TODO Edit contact profile.
			mContext.showDetail(position);
		} else {
			// TODO Delete contact.
			mContext.showDelete(position);
		}
	}

}
