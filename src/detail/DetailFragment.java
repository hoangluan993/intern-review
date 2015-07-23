/*
 * Displays detailed information of a person in the contacts
 * You can edit the information and store information
 */

package detail;

import java.util.ArrayList;

import model.Contacts;
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

import com.example.contactslist.ContactsFragment;
import com.example.contactslist.MainActivity;
import com.example.contactslist.R;

import database.MyDatabase;

public class DetailFragment extends Fragment implements OnClickListener {
	// Declare Variables
	private TextView mdetailCancel, mdetailSave, mdetailUsername,
			meditDescription;
	private EditText meditUsername;
	private RelativeLayout mdetailAvatar;
	private ArrayList<Contacts> mContacts;
	private MyDatabase mData;
	private int mPosition;
	private View mView;
	private MainActivity mContext;

	public DetailFragment(MainActivity context,int position, MyDatabase data) {
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
		mdetailAvatar = (RelativeLayout) mView
				.findViewById(R.id.rlDetailAvatar);
		mdetailCancel = (TextView) mView.findViewById(R.id.tvDetailCancel);
		mdetailSave = (TextView) mView.findViewById(R.id.tvDetailSave);
		mdetailUsername = (TextView) mView.findViewById(R.id.tvDetailUsername);
		meditUsername = (EditText) mView.findViewById(R.id.edtEditUserName);
		meditDescription = (TextView) mView
				.findViewById(R.id.edtEditDescription);
		// Set the results into TextView, RelativeLayout, EditText
		mdetailUsername.setText(mContacts.get(mPosition).getUserName());
		meditUsername.setText(mContacts.get(mPosition).getUserName());
		meditDescription.setText(mContacts.get(mPosition).getDecription());
		mdetailAvatar.setBackgroundResource(mContacts.get(mPosition).getAvatar());
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
		case R.id.tvDetailCancel:
			// TODO Set event click Cancel to don't save data edit to Database
			mContext.showContact();
			break;

		case R.id.tvDetailSave:
			// TODO Set event click Save to save data edit to Database
			mData.updateContacts(mContacts.get(mPosition).getId(),mContacts.get(mPosition).getAvatar(),
					meditUsername.getText().toString(), meditDescription
							.getText().toString());
			mContext.showContact();
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
		fragtst.replace(R.id.frameLayout, fragment);
		fragtst.commit();
	}
}
