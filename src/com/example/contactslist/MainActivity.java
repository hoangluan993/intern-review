package com.example.contactslist;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentTransaction fragtst = getFragmentManager().beginTransaction();
    	fragtst.replace(R.id.container_fragment,new ContactsFragment());
    	fragtst.commit();
	}
}
