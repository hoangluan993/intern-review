package com.example.contacts;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity{
	private ArrayList<Integer> mAvatars;
	private ArrayList<String> mUserNames;
	private ListView mlistContact;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
	}

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
		mUserNames = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			mUserNames.add(userName);
		}
		mlistContact = (ListView) findViewById(R.id.lvcontact);
		ContactListAdapter adapter = new ContactListAdapter(
				this, mAvatars, mUserNames);
		mlistContact.setAdapter(adapter);
	
	}
}
