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

public class DeleteDialogFragment extends DialogFragment implements
		OnClickListener {
	private RelativeLayout mCancel;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater mInflater = getActivity().getLayoutInflater();
		View view = mInflater.inflate(R.layout.dialog_delete, null);
		builder.setView(view);
		mCancel = (RelativeLayout) view.findViewById(R.id.rlcancel);
		mCancel.setOnClickListener(this);

		return builder.create();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rlcancel:

			break;

		default:
			break;
		}

	}

}
