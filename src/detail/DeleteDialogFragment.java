/*
 * Show Dialog delete.
 * To confirm you want to delete 
 */

package detail;

import java.util.ArrayList;

import model.Contacts;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.contactslist.ContactsFragment;
import com.example.contactslist.MainActivity;
import com.example.contactslist.R;

import database.MyDatabase;

public class DeleteDialogFragment extends DialogFragment implements
		OnClickListener {
	private RelativeLayout mdelCancel, mdelOk;
	private TextView mdelUserName;
	private AlertDialog.Builder mBuilder;
	private ArrayList<Contacts> mContacts;
	private int mPosition;
	private View mView;
	private MyDatabase mData;
	private MainActivity mContext;

	public DeleteDialogFragment(MainActivity context,int position, MyDatabase data) {
		mPosition = position;
		this.mContacts = data.getContacts();
		this.mData = data;
		this.mContext =  context;
	}

	/**
	 * Init Dialog Delete from Layout custom
	 */

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Create Diallog delete
		mBuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater mInflater = getActivity().getLayoutInflater();
		mView = mInflater.inflate(R.layout.dialog_delete, null);
		mBuilder.setView(mView);
		init();
		return mBuilder.create();
	}

	/**
	 * Set values for Username get data from Database Create event Click button
	 * Cancel to Don't Confirm delete Create event Click button Ok to Confirm
	 * delete
	 */

	private void init() {
		mdelCancel = (RelativeLayout) mView.findViewById(R.id.rlDeleteCancel);
		mdelOk = (RelativeLayout) mView.findViewById(R.id.rlDeleteOk);

		mdelUserName = (TextView) mView.findViewById(R.id.tvDeleteUsername);
		mdelUserName.setText(mContacts.get(mPosition).getUserName());

		mdelCancel.setOnClickListener(this);
		mdelOk.setOnClickListener(this);
	}

	/**
	 * Event click button Cancel and Ok
	 */
	@Override
	public void onClick(View v) {
		// TODO Event when click Button
		switch (v.getId()) {
		case R.id.rlDeleteCancel:
			// TODO Set event click button Cancel in dialog DELETE
			DeleteDialogFragment.this.dismiss();
			break;

		case R.id.rlDeleteOk:
			// TODO Set event click button OK in dialog DELETE
			mData.deleteContacts(mContacts.get(mPosition)
					.getId());
			DeleteDialogFragment.this.dismiss();
			showContactFragment();
			break;

		default:
			break;
		}

	}

	/**
	 * Show Interface List Contacts Fragment
	 */
	public void showContactFragment() {
		FragmentTransaction fragtst = mContext.getFragmentManager()
				.beginTransaction();
		fragtst.replace(R.id.frameLayout, new ContactsFragment(mContext,mData));
		fragtst.commit();
	}

}
