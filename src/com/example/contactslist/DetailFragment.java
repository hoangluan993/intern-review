/*
 * Displays detailed information of a person in the contacts
 * You can edit the information and store information
 */

package com.example.contactslist;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Fragment implements OnClickListener {
	private TextView mdetailCancel,mdetailSave, mdetailUsername, meditDecription;
	private EditText edteditUsername;
	private RelativeLayout rldetailAvatar;
	private ArrayList<Contacts> mContacts;
	private int mposition;
	private View mview;
	
	public DetailFragment(int position, ArrayList<Contacts> contacts) {
		this.mposition = position;
		this.mContacts = contacts;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mview = inflater
				.inflate(R.layout.fragment_detail, container, false);
		init();
		return mview;
	}
	
	private void init(){
		rldetailAvatar = (RelativeLayout) mview.findViewById(R.id.rldetailavatar);
		mdetailCancel = (TextView) mview.findViewById(R.id.tvdetailcancel);
		mdetailSave = (TextView) mview.findViewById(R.id.tvdetailsave);
		mdetailUsername = (TextView) mview.findViewById(R.id.tvdetailusername);
		edteditUsername = (EditText) mview.findViewById(R.id.edteditusername);
		meditDecription = (TextView) mview.findViewById(R.id.edteditdecription);
		
		mdetailUsername.setText(ContactListAdapter.getContacts().get(mposition).getUserName());
		edteditUsername.setText(ContactListAdapter.getContacts().get(mposition).getUserName());
		meditDecription.setText(ContactListAdapter.getContacts().get(mposition).getDecription());
		rldetailAvatar.setBackgroundResource(ContactListAdapter.getAvatars().get(mposition));
		mdetailSave.setOnClickListener(this);
		mdetailCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvdetailcancel:
			MainActivity.showContactFragment();
			break;
		case R.id.tvdetailsave:
			MainActivity.sdata.updateContacts(ContactListAdapter.getContacts().get(mposition).getId(), edteditUsername.getText().toString(), meditDecription.getText().toString());
			showContactFragment();
			break;

		default:
			break;
		}

	}
	public static void showContactFragment() {
		FragmentTransaction fragtst = MainActivity.sfragmentmng.beginTransaction();
		fragtst.replace(R.id.container_fragment, new ContactsFragment(MainActivity.getAvatars(),MainActivity.getData()));
		fragtst.commit();
	}
}
