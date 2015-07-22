/*
 * Initialization List Contacts
 * Display to interface 
 */
package com.example.contactslist;

import java.util.ArrayList;
import java.util.Random;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ContactsFragment extends Fragment {
//	private ArrayList<Integer> mAvatars;
//	private ArrayList<Contacts> mUserNames;
	private ListView mlistContact;

	/**
	 * Show List Contacts Create and Set Adapter for ListView
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact, container,
				false);

		mlistContact = (ListView) view.findViewById(R.id.lvcontact);
		ContactListAdapter adapter = new ContactListAdapter((MainActivity) getActivity(), MainActivity.sAvatars,MainActivity.data.getContacts());
		mlistContact.setAdapter(adapter);
		return view;
	}

	/**
	 * Init values for ArrayList mAvatars,mUserNames use to Set values for ListView
	 */
//	private void init() {
//		String userName = "Hoang Luan";
//		String decription = "DECRIPTION";
//		mAvatars = new ArrayList<Integer>();
//		for (int i = 0; i < 20; i++) {
//			mAvatars.add(R.drawable.img_avatar_1);
//		}
////		mAvatars.add(R.drawable.img_avatar_1);
////		mAvatars.add(R.drawable.img_avatar_2);
////		mAvatars.add(R.drawable.img_avatar_3);
////		mAvatars.add(R.drawable.img_avatar_4);
////		mAvatars.add(R.drawable.img_avatar_5);
////		mAvatars.add(R.drawable.img_avatar_6);
////		mAvatars.add(R.drawable.img_avatar_7);
////		mAvatars.add(R.drawable.img_avatar_1);
////		mAvatars.add(R.drawable.img_avatar_2);
////		mAvatars.add(R.drawable.img_avatar_3);
////		mAvatars.add(R.drawable.img_avatar_4);
////		mAvatars.add(R.drawable.img_avatar_5);
////		mAvatars.add(R.drawable.img_avatar_6);
////		mAvatars.add(R.drawable.img_avatar_7);
////		mUserNames = new ArrayList<String>();
////		for (int i = 0; i < 14; i++) {
////			mUserNames.add(userName+" "+i);
////		}
//		data = new MyDatabase((MainActivity) getActivity());
//		for(int i=0;i<20;i++){
//			data.addContact(new Contacts(""+i, userName, decription));
//		}
//	}
	private static int rand(int min, int max) {
		try {
			Random rn = new Random();
			int range = max - min + 1;
			int randomNum = min + rn.nextInt(range);
			return randomNum;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
