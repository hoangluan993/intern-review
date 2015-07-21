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
import android.widget.TextView;

public class DetailFragment extends Fragment implements OnClickListener {
	private TextView mdetailCancel,mdetailUsername;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_detail, container, false);
		mdetailCancel = (TextView) view.findViewById(R.id.tvcancel);
		mdetailUsername = (TextView) view.findViewById(R.id.tvdetailusername);
		mdetailUsername.setText(ContactListAdapter.mUserNames.get(ContactListAdapter.sVitri));
		
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
