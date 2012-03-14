package com.android.kalenteri.database;



import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserCourseDatabase {
	private SQLiteDatabase database;
	private DbSQLiteHelper dbHelper;
	private String[] allColumns = {DbSQLiteHelper.COLUMN_USERNAME, 
			DbSQLiteHelper.COLUMN_PASSWORD, DbSQLiteHelper.COLUMN_USERTYPE};

	public UserCourseDatabase(Context context) {
		// TODO Auto-generated constructor stub
		dbHelper = new DbSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public User createUser(String username, String password, int userType) {
		ContentValues values = new ContentValues();
		values.put(DbSQLiteHelper.COLUMN_USERNAME, username);
		values.put(DbSQLiteHelper.COLUMN_PASSWORD, password);
		values.put(DbSQLiteHelper.COLUMN_USERTYPE, userType);
		long insertId = database.insert(DbSQLiteHelper.TABLE_USERS, null,
				values);
		// To show how to query
		Cursor cursor = database.query(DbSQLiteHelper.TABLE_USERS,
				allColumns, DbSQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		return cursorToUser(cursor);
	}
	
	private User cursorToUser(Cursor cursor) {
		User user = new User(cursor.getString(1), cursor.getInt(3));
		return user;
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		Cursor cursor = database.query(DbSQLiteHelper.TABLE_USERS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			User user = cursorToUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		return users;
	}
}
