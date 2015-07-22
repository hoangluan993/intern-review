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
	private MyDatabase mdata;
	private View mview;

	public ContactsFragment(ArrayList<Integer> avatars, MyDatabase data) {
		this.mAvatars = avatars;
		this.mdata = data;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mview = inflater.inflate(R.layout.fragment_contact, container, false);
		init();
		return mview;
	}

	/**
	 * Init ListView and set Adapter for ListView
	 */
	private void init() {
		mlistContact = (ListView) mview.findViewById(R.id.lvcontact);
		ContactListAdapter adapter = new ContactListAdapter(
				(MainActivity) getActivity(), mAvatars, mdata.getContacts());
		mlistContact.setAdapter(adapter);
	}
}
