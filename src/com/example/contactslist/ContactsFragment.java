/*
 * Initialization List Contacts
 * Display to interface 
 */
package com.example.contactslist;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ContactsFragment extends Fragment {
	private ListView mlistContact;
	private MainActivity mContext;
	private ArrayList<Integer> mAvatars;
	private MyDatabase mdata;
	/**
	 * Show List Contacts Create and Set Adapter for ListView
	 */
//	public ContactsFragment(Ma context, ArrayList<Integer> avatars, MyDatabase data){
//		this.mContext = context;
//		this.mAvatars = avatars;
//		this.mdata = data;
//	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact, container,
				false);

		mlistContact = (ListView) view.findViewById(R.id.lvcontact);
		ContactListAdapter adapter = new ContactListAdapter(
				(MainActivity) getActivity(), MainActivity.getAvatars(),
				MainActivity.getData().getContacts());
		mlistContact.setAdapter(adapter);
		return view;
	}
}
