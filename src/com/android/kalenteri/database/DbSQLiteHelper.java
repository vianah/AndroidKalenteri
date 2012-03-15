package com.android.kalenteri.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbSQLiteHelper extends SQLiteOpenHelper {

	protected static final String TABLE_USERS = "users";
	protected static final String COLUMN_ID = "_id";
	protected static final String COLUMN_USERNAME = "username";
	protected static final String COLUMN_PASSWORD = "password"; 
	protected static final String COLUMN_USERTYPE = "usertype";

	private static final String DATABASE_NAME = "users.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_USERS + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_USERNAME
			+ " text not null, " + COLUMN_PASSWORD + " text not null, " 
			+ COLUMN_USERTYPE + " integer not null);";

	public DbSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(DbSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		onCreate(db);
	}

}
