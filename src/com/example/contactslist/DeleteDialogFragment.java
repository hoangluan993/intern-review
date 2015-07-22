/*
 * Show Dialog delete.
 * To confirm you want to delete 
 */

package com.example.contactslist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeleteDialogFragment extends DialogFragment implements
		OnClickListener {
	private RelativeLayout mdelCancel;
	private TextView tvdelUserName;
	private AlertDialog.Builder mbuilder;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		mbuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater mInflater = getActivity().getLayoutInflater();
		View view = mInflater.inflate(R.layout.dialog_delete, null);
		mbuilder.setView(view);
		mdelCancel = (RelativeLayout) view.findViewById(R.id.rldelcancel);
		tvdelUserName = (TextView) view.findViewById(R.id.tvdelusername);
		tvdelUserName.setText(ContactListAdapter.getContacts().get(ContactListAdapter.sVitri).getUserName());
		mdelCancel.setOnClickListener(this);
		return mbuilder.create();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rldelcancel:
			DeleteDialogFragment.this.dismiss();
			break;

		default:
			break;
		}

	}

}
