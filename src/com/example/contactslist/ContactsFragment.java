/*
 * Initialization List Contacts
 * Display to interface 
 */
package com.example.contactslist;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ContactsFragment extends Fragment {
	private ListView mlistContact;
	private ArrayList<Integer> mAvatars;
	private MyDatabase mData;
	private View mView;

	public ContactsFragment(ArrayList<Integer> avatars, MyDatabase data) {
		this.mAvatars = avatars;
		this.mData = data;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_contact, container, false);
		init();
		return mView;
	}

	/**
	 * Init ListView and set Adapter for ListView
	 */
	private void init() {
		mlistContact = (ListView) mView.findViewById(R.id.lvcontact);
		ContactListAdapter adapter = new ContactListAdapter(
				(MainActivity) getActivity(), mAvatars,mData);
		mlistContact.setAdapter(adapter);
	}
}
