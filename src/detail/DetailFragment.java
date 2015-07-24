/*
 * Displays detailed information of a person in the contacts
 * You can edit the information and store information
 */

package detail;

import java.util.ArrayList;

import model.Contacts;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.contactslist.MainActivity;
import com.example.contactslist.R;

import database.MyDatabase;

public class DetailFragment extends Fragment implements OnClickListener {
	// Declare Variables
	private TextView mDetailCancel, mDetailSave, mDetailUsername,
			mEditDescription;
	private EditText mEditUsername;
	private RelativeLayout mDetailAvatar;
	private ArrayList<Contacts> mContacts;
	private MyDatabase mData;
	private int mPosition;
	private View mView;
	private MainActivity mContext;

	/**
	 * Init values start for Detail
	 * 
	 * @param context
	 *            MainActivity
	 * @param position
	 *            positon in List
	 * @param data
	 *            MyDatabase
	 */
	public DetailFragment(MainActivity context, int position, MyDatabase data) {
		this.mContext = context;
		this.mPosition = position;
		this.mContacts = data.getContacts();
		this.mData = data;
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
		// Locate the TextView, RelativeLayout, EditText in
		// fragment_detail.xml
		mDetailAvatar = (RelativeLayout) mView
				.findViewById(R.id.rlDetailAvatar);
		mDetailCancel = (TextView) mView.findViewById(R.id.tvDetailCancel);
		mDetailSave = (TextView) mView.findViewById(R.id.tvDetailSave);
		mDetailUsername = (TextView) mView.findViewById(R.id.tvDetailUsername);
		mEditUsername = (EditText) mView.findViewById(R.id.edtEditUserName);
		mEditDescription = (TextView) mView
				.findViewById(R.id.edtEditDescription);
		// Set the results into TextView, RelativeLayout, EditText
		mDetailUsername.setText(mContacts.get(mPosition).getUserName());
		mEditUsername.setText(mContacts.get(mPosition).getUserName());
		mEditDescription.setText(mContacts.get(mPosition).getDecription());
		mDetailAvatar.setBackgroundResource(mContacts.get(mPosition)
				.getAvatar());
		// Set event click for button save and cancel after edit info detail
		mDetailSave.setOnClickListener(this);
		mDetailCancel.setOnClickListener(this);
	}

	/**
	 * Create event click Cancel, Save Data to Database
	 */
	@Override
	public void onClick(View v) {
		// TODO Event when click Button
		switch (v.getId()) {
		case R.id.tvDetailCancel:
			// TODO Set event click Cancel to don't save data edit to Database
			mContext.showContact();
			break;

		case R.id.tvDetailSave:
			// TODO Set event click Save to save data edit to Database
			mData.updateContacts(mContacts.get(mPosition).getId(), mContacts
					.get(mPosition).getAvatar(), mEditUsername.getText()
					.toString(), mEditDescription.getText().toString());
			// Display Interface List contacts after completion Edit
			mContext.showContact();
			break;

		default:
			break;
		}

	}

}
