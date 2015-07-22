package com.example.contactslist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDatabase {
	private SQLiteDatabase mData;

	public MyDatabase(Context context) {
		mData = context.openOrCreateDatabase("MyContacts.db",
				Activity.MODE_PRIVATE, null);

		String sql = "create table contacts (";
		sql += "id text primary key,";
		sql += "username text not null,";
		sql += "dicription text)";

		try {
			mData.execSQL(sql);
		} catch (SQLException sqlex) {
			// Table already exists

			Cursor cursor = mData.rawQuery("select * from contacts", null);

			for (String name : cursor.getColumnNames()) {
				Log.d("colname", name);
			}
		}
	}

	public String addContact(Contacts contact) {
		// TODO insert data to database

		String id = contact.getId();
		String username = contact.getUserName();
		String dicription = contact.getDecription();

		if (username == null) {
			return "Insert fail";
		}

		if (dicription == null) {
			dicription = "";
		}

		String sql = "insert into contacts values('" + id + "','" + username
				+ "', '" + dicription + "');";
		try {
			mData.execSQL(sql);
		} catch (SQLException ex) {
			Log.d("add " + id, "fail");
			Cursor cursor = mData.rawQuery("select * from contacts", null);

			for (Contacts ct : getContacts()){
				Log.d("contact", ct.getId());
			}
		}

		return "Success";
	}

	public ArrayList<Contacts> getContacts() {
		// TODO get all data from database

		ArrayList<Contacts> contacts = new ArrayList<Contacts>();

		String sql = "select * from contacts";
		Cursor cursor = mData.rawQuery(sql, null);

		cursor.moveToFirst();
		if (cursor != null) {
			do {
				String id = cursor.getString(0);
				String username = cursor.getString(1);
				String decription = cursor.getString(2);

				contacts.add(new Contacts(id, username, decription));
			} while (cursor.moveToNext());
		}

		return contacts;
	}

	public Contacts getContact(int id) {
		// TODO get a contact from database by ID

		return null;
	}
}
