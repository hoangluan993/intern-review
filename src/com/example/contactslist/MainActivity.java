package com.example.contactslist;

import model.Contacts;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import database.MyDatabase;
import detail.DeleteDialogFragment;
import detail.DetailFragment;

public class MainActivity extends Activity implements OnClickListener {

	// Declare Variables
	private boolean mIsFragmentDetail = false;
	private ImageView mButtonBack, mLoading;
	private Fragment mContactsFragment;
	private final int TIME_INTERVAL = 2000;
	private long mBackPressed;
	private int mRotation = 0;
	private LinearLayout mIsLoadding;
	private MyDatabase mDatabase;
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	/**
	 * Locate the element in activity_main.xml
	 * Set Data for Database
	 * Init ContactsFragment. Show Interface List contacts
	 * Set event click button back to change fragment and exit application
	 */

	private void init() {
		// Locate the ImageView in activity_main.xml
		mIsLoadding = (LinearLayout) findViewById(R.id.llLoading);
		mButtonBack = (ImageView) findViewById(R.id.imgBack);
		mHandler = new Handler();
		// run Thread set rotate icon loading more
		new Thread(run).start();
		// Set data for Database
		setDatabase();
		mContactsFragment = new ContactsFragment(this, mDatabase);
		showContact();
		mButtonBack.setOnClickListener(this);
	}

	/**
	 * Set event for action back
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBack:
			//is at Detail
			if (mIsFragmentDetail) {
				//show list Contacts 
				showContact();
				mIsFragmentDetail = false;
			} else {
				//is at List Contacts
				setDoubleClick();
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Show icon Loading more rotate
	 */
	public void showLoading() {
		mIsLoadding.setVisibility(LinearLayout.VISIBLE);
	}

	/**
	 * Hide icon Loading more rotate
	 */
	public void hideLoading() {
		mIsLoadding.setVisibility(LinearLayout.INVISIBLE);
	}

	/**
	 * Thread run set rotation for icon
	 */
	Runnable run = new Runnable() {
		@Override
		public void run() {
			mHandler.postDelayed(run, 10);
			// Locate the ImageView in activity_main.xml
			mLoading = (ImageView) findViewById(R.id.imgLoading);
			//Set Image rotate
			mLoading.setRotation(mRotation);
			mRotation += 4;
			if (mRotation >= 360) {
				mRotation = 0;
			} else {
				mLoading.setRotation(mRotation);
			}
		}

	};

	/**
	 * Function set Double Click to exit for exit application
	 */
	@Override
	public void onBackPressed() {
		if (mIsFragmentDetail) {
		    showContact();
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
		//Create Array avatar
		int[] avatars = { R.drawable.img_avatar_1, R.drawable.img_avatar_2,
				R.drawable.img_avatar_3, R.drawable.img_avatar_4 };
		mDatabase = new MyDatabase(this);
		for (int i = 0; i < 100; i++) {
			try {
				//Add data to Database
				mDatabase.addContact(new Contacts("" + i, avatars[i % 4], userName
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
		mIsFragmentDetail = false;
		FragmentTransaction fragtst = getFragmentManager().beginTransaction();
		fragtst.replace(R.id.frameLayout, mContactsFragment);
		fragtst.commit();
	}

	/**
	 * Show detail contacts to Edit username or description
	 * 
	 * @param position positon in list
	 */
	public void showDetail(int position) {
		mIsFragmentDetail = true;
		FragmentTransaction fragtst = getFragmentManager().beginTransaction();
		fragtst.replace(R.id.frameLayout, new DetailFragment(this, position,
				mDatabase));
		fragtst.commit();
	}

	/**
	 * Show dialog confirm delete
	 * 
	 * @param position positon in list
	 */
	public void showDelete(int position) {
		new DeleteDialogFragment(this, position, mDatabase).show(
				this.getFragmentManager(), "dialog");
	}

}
