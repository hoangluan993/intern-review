package com.example.contactslist;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

public class MainActivity extends Activity {
	public static FragmentManager sfragment;
	/**
	 * Show ContactsFragment
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sfragment = getFragmentManager();
		FragmentTransaction fragtst = sfragment.beginTransaction();
		fragtst.replace(R.id.container_fragment, new ContactsFragment());
		fragtst.commit();
	}
}
