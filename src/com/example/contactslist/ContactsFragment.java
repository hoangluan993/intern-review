package com.example.contactslist;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ContactsFragment extends Fragment {
	private ArrayList<Integer> mAvatars;
	private ArrayList<String> mUserNames;
	private ListView mlistContact;

	/**
	 * Show List Contacts
	 * Create and Set Adapter for ListView
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		init();
		mlistContact = (ListView) view.findViewById(R.id.lvcontact);
		ContactListAdapter adapter = new ContactListAdapter(getActivity(),
				mAvatars, mUserNames);
		mlistContact.setAdapter(adapter);
		return view;
	}

	/**
	 * Init values for ArrayList mAvatars,mUserNames Set values for ListView
	 */
	private void init() {
		String userName = "Hoang Luan";
		mAvatars = new ArrayList<Integer>();
		mAvatars.add(R.drawable.img_avatar_1);
		mAvatars.add(R.drawable.img_avatar_2);
		mAvatars.add(R.drawable.img_avatar_3);
		mAvatars.add(R.drawable.img_avatar_4);
		mAvatars.add(R.drawable.img_avatar_5);
		mAvatars.add(R.drawable.img_avatar_6);
		mAvatars.add(R.drawable.img_avatar_7);
		mAvatars.add(R.drawable.img_avatar_1);
		mAvatars.add(R.drawable.img_avatar_2);
		mAvatars.add(R.drawable.img_avatar_3);
		mAvatars.add(R.drawable.img_avatar_4);
		mAvatars.add(R.drawable.img_avatar_5);
		mAvatars.add(R.drawable.img_avatar_6);
		mAvatars.add(R.drawable.img_avatar_7);
		mUserNames = new ArrayList<String>();
		for (int i = 0; i < 14; i++) {
			mUserNames.add(userName);
		}
	}
}
