package com.example.contactslist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	public static FragmentManager sfragmentmng;
	private RelativeLayout mrlBack;
	public static boolean scheckBack = false;
	public static Fragment sfragment;
	private static final int TIME_INTERVAL = 2000;
	private long mBackPressed;
	private static ArrayList<Integer> sAvatars;
	public static MyDatabase sdata;

	/**
	 * Show ContactsFragment
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setDatabase();
		sfragmentmng = getFragmentManager();
		sfragment = new ContactsFragment(getAvatars(), getData());
		showContactFragment();
		mrlBack = (RelativeLayout) findViewById(R.id.rlback);
		mrlBack.setOnClickListener(this);
	}

	/**
	 * Set event for action back
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rlback:
			if (scheckBack) {
				showContactFragment();
				scheckBack = false;
			} else {
				setDoubleClick();
			}
			break;

		default:
			break;
		}
	}

	public static void showContactFragment() {
		FragmentTransaction fragtst = sfragmentmng.beginTransaction();
		fragtst.replace(R.id.container_fragment, sfragment);
		fragtst.commit();
	}

	/**
	 * Function set Double Click to exit
	 */
	@Override
	public void onBackPressed() {
		if (scheckBack) {
			showContactFragment();
			scheckBack = false;
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
		sAvatars = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			sAvatars.add(R.drawable.img_avatar_1);
			sAvatars.add(R.drawable.img_avatar_2);
			sAvatars.add(R.drawable.img_avatar_3);
			sAvatars.add(R.drawable.img_avatar_4);
			sAvatars.add(R.drawable.img_avatar_5);
		}
		sdata = new MyDatabase(this);
		for (int i = 0; i < 20; i++) {
			sdata.addContact(new Contacts("" + i, userName + " " + i,
					decription + " " + i));
		}
	}

	public static ArrayList<Integer> getAvatars() {
		return sAvatars;
	}

	public static MyDatabase getData() {
		return sdata;
	}
}
