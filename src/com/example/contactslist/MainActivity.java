package com.example.contactslist;

import model.Contacts;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import database.MyDatabase;
import detail.DeleteDialogFragment;
import detail.DetailFragment;

public class MainActivity extends Activity implements OnClickListener {

	// Declare Variables
	public boolean isfragmentDetail = false;
	private ImageView mbuttonBack;
	private Fragment mcontactsFragment;
	private final int TIME_INTERVAL = 2000;
	private long mBackPressed;
	public MyDatabase myData;

	/**
	 * Show ContactsFragment
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Set data for Database
		setDatabase();
		mcontactsFragment = new ContactsFragment(this, myData);
		showContact();
		mbuttonBack = (ImageView) findViewById(R.id.imgBack);
		mbuttonBack.setOnClickListener(this);
	}

	/**
	 * Set event for action back
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBack:
			if (isfragmentDetail) {
				showContact();
				isfragmentDetail = false;
			} else {
				setDoubleClick();
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Function set Double Click to exit
	 */
	@Override
	public void onBackPressed() {
		if (isfragmentDetail) {
			showContact();
			isfragmentDetail = false;
		} else {
			setDoubleClick();
		}
	};

	/**
	 * Set doubleClick to exit appilation. milliseconds, desired time passed
	 * between two back presses.
	 */
	private void setDoubleClick() {
		if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
			super.onBackPressed();
			return;
		} else {
			Toast.makeText(getBaseContext(), "Press again to exit.",
					Toast.LENGTH_SHORT).show();
		}

		mBackPressed = System.currentTimeMillis();
	}

	/**
	 * Init values for ArrayList mAvatars. Create Database and set values for
	 * database
	 */
	private void setDatabase() {
		String userName = "Hoang Luan";
		String decription = "DECRIPTION";
		int[] avatars = { R.drawable.img_avatar_1, R.drawable.img_avatar_2,
				R.drawable.img_avatar_3, R.drawable.img_avatar_4 };
		myData = new MyDatabase(this);
		for (int i = 0; i < 20; i++) {
			try {
				myData.addContact(new Contacts("" + i, avatars[i % 4], userName
						+ " " + i, decription + " " + i));
			} catch (SQLException e) {
				Log.d("INSERT", "ERROR");
			}
		}
	}

	/**
	 * Show List Contacts
	 */
	public void showContact() {
		FragmentTransaction fragtst = getFragmentManager().beginTransaction();
		fragtst.replace(R.id.frameLayout, mcontactsFragment);
		fragtst.commit();
	}

	/**
	 * Show detail to Edit username or description
	 * 
	 * @param position
	 */
	public void showDetail(int position) {
		FragmentTransaction fragtst = getFragmentManager().beginTransaction();
		fragtst.replace(R.id.frameLayout, new DetailFragment(this, position,
				myData));
		fragtst.commit();
	}

	/**
	 * Show dialog confirm delete
	 * 
	 * @param position
	 */
	public void showDelete(int position) {
		new DeleteDialogFragment(this, position, myData).show(
				this.getFragmentManager(), "dialog");
	}
}
