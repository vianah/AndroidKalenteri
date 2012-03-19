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
	private String[] allUserColumns = {DbSQLiteHelper.USER_COLUMN_ID, DbSQLiteHelper.USER_COLUMN_USERNAME, 
			DbSQLiteHelper.USER_COLUMN_PASSWORD, DbSQLiteHelper.USER_COLUMN_USERTYPE};
	private String[] allCourseColumns = {DbSQLiteHelper.COURSES_COLUMN_ID, DbSQLiteHelper.COURSES_COLUMN_NAME, 
			DbSQLiteHelper.COURSES_COLUMN_POINTS};

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
		values.put(DbSQLiteHelper.USER_COLUMN_USERNAME, username);
		values.put(DbSQLiteHelper.USER_COLUMN_PASSWORD, password);
		values.put(DbSQLiteHelper.USER_COLUMN_USERTYPE, userType);
		long insertId = database.insert(DbSQLiteHelper.TABLE_USERS, null,
				values);
		// To show how to query
		Cursor cursor = database.query(DbSQLiteHelper.TABLE_USERS,
				allUserColumns, DbSQLiteHelper.USER_COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		return cursorToUser(cursor);
	}
	
	public boolean createCourse(String cName, int cPoints) {
		ContentValues values = new ContentValues();
		values.put(DbSQLiteHelper.COURSES_COLUMN_NAME, cName);
		values.put(DbSQLiteHelper.COURSES_COLUMN_POINTS, cPoints);
		long insertId = database.insert(DbSQLiteHelper.TABLE_COURSES, null,
				values);
		return (insertId != -1);
	}
	
	public boolean userToCourse(int userId, int courseId ) {
		ContentValues values = new ContentValues();
		values.put(DbSQLiteHelper.TAKES_COLUMN_USER_ID, userId);
		values.put(DbSQLiteHelper.TAKES_COLUMN_COURSE_ID, courseId);
		values.put(DbSQLiteHelper.TAKES_COLUMN_FINISHED, 0);
		long insertId = database.insert(DbSQLiteHelper.TABLE_TAKES, null,
				values);
		return (insertId != -1);
	}
	
	
	private User cursorToUser(Cursor cursor) {
		User user = new User((int)cursor.getLong(0), 
				cursor.getString(1), (int) cursor.getLong(3));
		return user;
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		Cursor cursor = database.query(DbSQLiteHelper.TABLE_USERS,
				allUserColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			User user = cursorToUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		return users;
	}
}
