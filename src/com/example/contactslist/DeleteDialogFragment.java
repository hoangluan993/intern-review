/*
 * Show Dialog delete.
 * To confirm you want to delete 
 */

package com.example.contactslist;

import java.util.ArrayList;

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
import android.widget.Toast;

public class DeleteDialogFragment extends DialogFragment implements
		OnClickListener {
	private RelativeLayout mdelCancel,mdelOk;
	private TextView tvdelUserName;
	private AlertDialog.Builder mbuilder;
	private ArrayList<Contacts> mContacts;
	private int mposition;
	private View mview;
	public DeleteDialogFragment(int position, ArrayList<Contacts> contacts) {
		mposition = position;
		this.mContacts = contacts;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		mbuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater mInflater = getActivity().getLayoutInflater();
		mview= mInflater.inflate(R.layout.dialog_delete, null);
		mbuilder.setView(mview);
		init();
		return mbuilder.create();
	}
	
	private void init(){
		mdelCancel = (RelativeLayout) mview.findViewById(R.id.rldelcancel);
		mdelOk = (RelativeLayout) mview.findViewById(R.id.rldelok);
		
		tvdelUserName = (TextView) mview.findViewById(R.id.tvdelusername);
		tvdelUserName.setText(mContacts.get(mposition).getUserName());
		mdelCancel.setOnClickListener(this);
		mdelOk.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rldelcancel:
			DeleteDialogFragment.this.dismiss();
			break;
		case R.id.rldelok:
			MainActivity.sdata.deleteContacts(mContacts.get(mposition).getUserName());
			DeleteDialogFragment.this.dismiss();
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
