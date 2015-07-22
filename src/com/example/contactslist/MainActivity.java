package com.example.contactslist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	public static FragmentManager sfragmentmng;
	private RelativeLayout mrlBack;
	public static boolean scheckBack = false;
	public static Fragment sfragment;
	private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
	private long mBackPressed;
	public static ArrayList<Integer> sAvatars;
//	public static ArrayList<Contacts> suserName;
	public static MyDatabase data;

	/**
	 * Show ContactsFragment
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setDatabase();
		sfragmentmng = getFragmentManager();
		sfragment = new ContactsFragment();
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
	private void setDatabase(){
		String userName = "Hoang Luan";
		String decription = "DECRIPTION";
		sAvatars = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			sAvatars.add(R.drawable.img_avatar_1);
		}

		data = new MyDatabase(this);
		for(int i=0;i<20;i++){
			data.addContact(new Contacts(""+i, userName, decription));
		}
	}
}
