/*
 * Create database
 */
package database;

import java.util.ArrayList;

import model.Contacts;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDatabase {
	// Declare Variables
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
			Log.d("DROP", "Error");
		}

		String sql = "CREATE TABLE contacts (";
		sql += "id text primary key,";
		sql += "avatar integer,";
		sql += "username text not null,";
		sql += "description text)";

		try {
			mData.execSQL(sql);
		} catch (SQLException sqlex) {
			Log.d("CREATE", "Error");
		}
	}

	/**
	 * Insert Data to Database
	 */
	public String addContact(Contacts contact) {
		// TODO insert data to database
		String id = contact.getId();
		int avatar = contact.getAvatar();
		String username = contact.getUserName();
		String description = contact.getDecription();

		if (username == null) {
			return "Insert fail";
		}

		if (description == null) {
			description = "";
		}
		//sql insert data into table contacts
		String sql = "INSERT INTO contacts VALUES('" + id + "', " + avatar
				+ ", '" + username + "', '" + description + "');";
		mData.execSQL(sql);

		return "Success";
	}

	/**
	 * Get data from Database for ArrayList
	 */
	public ArrayList<Contacts> getContacts() {
		// TODO get data for ArrayList
		ArrayList<Contacts> contacts = new ArrayList<Contacts>();
		//sql get data from Database
		String sql = "SELECT * FROM contacts";
		Cursor cursor = mData.rawQuery(sql, null);

		cursor.moveToFirst();
		if (cursor != null) {
			do {
				String id = cursor.getString(0);
				int avatar = cursor.getInt(1);
				String username = cursor.getString(2);
				String decription = cursor.getString(3);

				contacts.add(new Contacts(id, avatar, username, decription));
			} while (cursor.moveToNext());
		}

		return contacts;
	}

	/**
	 * Delete Data from Database
	 */
	public void deleteContacts(String id) {
		// TODO delete contacts
		String sql = "DELETE FROM contacts WHERE id='" + id + "'";
		try {
			mData.execSQL(sql);
		} catch (SQLException ex) {
			Log.d("DELETE", "Error");
		}

	}

	/**
	 * Update data for Database
	 */
	public void updateContacts(String id, int avatar, String userName,
			String description) {
		// TODO update contacts
		String sql = "UPDATE contacts SET username='" + userName + "',avatar="
				+ avatar + ",description='" + description + "' WHERE id='" + id
				+ "'";
		Log.d("UPDATE", sql);
		try {
			mData.execSQL(sql);
		} catch (SQLException ex) {
			Log.d("UPDATE", "Error");
		}

	}

}
