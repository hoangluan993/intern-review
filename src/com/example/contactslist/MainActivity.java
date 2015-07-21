package com.example.contactslist;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	public static FragmentManager sfragmentmng;
	private ImageView mimgBack;
	public static boolean scheckBack = false;
	public static Fragment sfragment;
	private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
	private long mBackPressed;

	/**
	 * Show ContactsFragment
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sfragmentmng = getFragmentManager();
		sfragment = new ContactsFragment();
		showContactFragment();
		mimgBack = (ImageView) findViewById(R.id.imgback);
		mimgBack.setOnClickListener(this);
	}
	/**
	 * Set event for action back
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgback:
			if (scheckBack) {
				showContactFragment();
				scheckBack = !scheckBack;
			} else {
				onBackPressed();
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
		if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) 
	    { 
	        super.onBackPressed(); 
	        return;
	    }
	    else { Toast.makeText(getBaseContext(), "Double Click to exit.", Toast.LENGTH_SHORT).show(); }

	    mBackPressed = System.currentTimeMillis();
	};
}
