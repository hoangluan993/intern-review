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

public class DetailFragment extends Fragment implements OnClickListener {
	private TextView mdetailCancel, mdetailSave, mdetailUsername,
			meditDescription;
	private EditText meditUsername;
	private RelativeLayout detailAvatar;
	private ArrayList<Contacts> mContacts;
	private ArrayList<Integer> mAvatars;
	private MyDatabase mData;
	private int mPosition;
	private View mView;
	private MainActivity mContext;

	public DetailFragment(MainActivity context,int position,
			ArrayList<Integer> avatar, MyDatabase data) {
		this.mContext = context;
		this.mPosition = position;
		this.mContacts = data.getContacts();
		this.mData = data;
		this.mAvatars = avatar;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_detail, container, false);
		init();
		return mView;
	}

	/**
	 * Init values for Detail User name, Edit Username, Description Create event
	 * click Cancel and Save
	 */
	private void init() {
		detailAvatar = (RelativeLayout) mView
				.findViewById(R.id.rldetailavatar);
		mdetailCancel = (TextView) mView.findViewById(R.id.tvdetailcancel);
		mdetailSave = (TextView) mView.findViewById(R.id.tvdetailsave);
		mdetailUsername = (TextView) mView.findViewById(R.id.tvdetailusername);
		meditUsername = (EditText) mView.findViewById(R.id.edteditusername);
		meditDescription = (TextView) mView
				.findViewById(R.id.edteditdecription);

		mdetailUsername.setText(mContacts.get(mPosition).getUserName());
		meditUsername.setText(mContacts.get(mPosition).getUserName());
		meditDescription.setText(mContacts.get(mPosition).getDecription());
		detailAvatar.setBackgroundResource(mAvatars.get(mPosition));
		mdetailSave.setOnClickListener(this);
		mdetailCancel.setOnClickListener(this);
	}

	/**
	 * Create event click Cancel, Save Data to Database
	 */
	@Override
	public void onClick(View v) {
		// TODO Event when click Button
		switch (v.getId()) {
		case R.id.tvdetailcancel:
			// TODO Set event click Cancel to don't save data edit to Database
			showContactFragment(new ContactsFragment(mAvatars, mData));
			break;

		case R.id.tvdetailsave:
			// TODO Set event click Save to save data edit to Database
			mData.updateContacts(mContacts.get(mPosition).getId(),
					meditUsername.getText().toString(), meditDescription
							.getText().toString());
			showContactFragment(new ContactsFragment(mAvatars, mData));
			break;

		default:
			break;
		}

	}

	/**
	 * Display Interface List contacts after completion of operation Show
	 * ListView with the data has changed
	 */
	public void showContactFragment(Fragment fragment) {
		FragmentTransaction fragtst = mContext.getFragmentManager()
				.beginTransaction();
		fragtst.replace(R.id.container_fragment, fragment);
		fragtst.commit();
	}
}
