/*
 * Create database
 */
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

	/**
	 * Create Database
	 */
	public MyDatabase(Context context) {
		// TODO Create Database
		mData = context.openOrCreateDatabase("MyContacts.db",
				Activity.MODE_PRIVATE, null);

		String drop = "DROP TABLE contacts";
		try {
			mData.execSQL(drop);
		} catch (SQLException ex) {

		}

		String sql = "CREATE TABLE contacts (";
		sql += "id text primary key,";
		sql += "username text not null,";
		sql += "dicription text)";

		try {
			mData.execSQL(sql);
		} catch (SQLException sqlex) {
			Log.d("CREATE", "Error");
		}
	}

	/**
	 * Insert Database
	 */
	public String addContact(Contacts contact) {
		// TODO insert data to database

		String id = contact.getId();
		String username = contact.getUserName();
		String dicription = contact.getDecription();

		String sql = "INSERT INTO contacts VALUES('" + id + "','" + username
				+ "', '" + dicription + "');";
		try {
			mData.execSQL(sql);
		} catch (SQLException ex) {
			Log.d("INSERT", "Error");
		}

		return "Success";
	}

	/**
	 * Get data from Database for ArrayList
	 */
	public ArrayList<Contacts> getContacts() {
		// TODO get data for ArrayList
		ArrayList<Contacts> contacts = new ArrayList<Contacts>();

		String sql = "SELECT * FROM contacts";
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

	/**
	 * Delete Data from Database
	 */
	public void deleteContacts(String contacts) {
		// TODO delete contacts
		String sql = "DELETE FROM contacts WHERE username='" + contacts + "'";
		try {
			mData.execSQL(sql);
		} catch (SQLException ex) {
			Log.d("DELETE", "Error");
		}

	}

	/**
	 * Update data for Database
	 */
	public void updateContacts(String id, String userName, String description) {
		// TODO update contacts
		String sql = "UPDATE contacts SET username='" + userName
				+ "',dicription='" + description + "' WHERE id='" + id + "'";
		try {
			mData.execSQL(sql);
		} catch (SQLException ex) {
			Log.d("UPDATE", "Error");
		}

	}

}
