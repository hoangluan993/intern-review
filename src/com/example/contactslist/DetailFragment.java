/*
 * Displays detailed information of a person in the contacts
 * You can edit the information and store information
 */

package com.example.contactslist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailFragment extends Fragment implements OnClickListener {
	private TextView mdetailCancel, mdetailUsername, mdetaildecription;
	private EditText edteditUsername;
	private RelativeLayout rldetailAvatar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_detail, container, false);
		mdetailCancel = (TextView) view.findViewById(R.id.tvcancel);
		mdetailUsername = (TextView) view.findViewById(R.id.tvdetailusername);
		edteditUsername = (EditText) view.findViewById(R.id.edteditusername);
		rldetailAvatar = (RelativeLayout) view.findViewById(R.id.rldetailavatar);
		mdetaildecription = (TextView) view.findViewById(R.id.tvdecription);
		
		mdetailUsername.setText(ContactListAdapter.getContacts().get(ContactListAdapter.sVitri).getUserName());
		edteditUsername.setText(ContactListAdapter.getContacts().get(ContactListAdapter.sVitri).getUserName());
		mdetaildecription.setText(ContactListAdapter.getContacts().get(ContactListAdapter.sVitri).getDecription());
		rldetailAvatar.setBackgroundResource(ContactListAdapter.getAvatars().get(ContactListAdapter.sVitri));
		mdetailCancel.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvcancel:
			MainActivity.showContactFragment();
			break;

		default:
			break;
		}

	}
}
