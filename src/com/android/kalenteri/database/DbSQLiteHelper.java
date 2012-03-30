package com.android.kalenteri.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbSQLiteHelper extends SQLiteOpenHelper {

	protected static final String TABLE_USERS = "users";
	protected static final String USER_COLUMN_ID = "_id";
	protected static final String USER_COLUMN_USERNAME = "username";
	protected static final String USER_COLUMN_PASSWORD = "password"; 
	protected static final String USER_COLUMN_USERTYPE = "usertype";
	
	protected static final String TABLE_COURSES = "courses";
	protected static final String COURSES_COLUMN_ID = "_id";
	protected static final String COURSES_COLUMN_NAME = "name";
	protected static final String COURSES_COLUMN_POINTS = "points";
	
	protected static final String TABLE_TAKES = "takes";
	protected static final String TAKES_COLUMN_USER_ID = "user_id";
	protected static final String TAKES_COLUMN_COURSE_ID = "course_id";
	protected static final String TAKES_COLUMN_FINISHED = "finished";

	private static final String DATABASE_NAME = "users.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String USER_TABLE_CREATE = "create table "
			+ TABLE_USERS + "( " + USER_COLUMN_ID
			+ " integer primary key autoincrement, " + USER_COLUMN_USERNAME
			+ " text not null, " + USER_COLUMN_PASSWORD + " text not null, " 
			+ USER_COLUMN_USERTYPE + " integer not null);";
	
	private static final String COURSES_TABLE_CREATE = "create table "
			+ TABLE_COURSES + "( " + COURSES_COLUMN_ID
			+ " integer primary key autoincrement, " + COURSES_COLUMN_NAME
			+ " text not null, " + COURSES_COLUMN_POINTS + " integer not null);";
	
	private static final String TAKES_TABLE_CREATE = "create table " + TABLE_TAKES + "( " 
			+ TAKES_COLUMN_USER_ID + " integer not null, " 
			+ TAKES_COLUMN_COURSE_ID + " integer not null, " 
			+ TAKES_COLUMN_FINISHED + " integer not null, "
			+ "primary key(" + TAKES_COLUMN_USER_ID + ", " + TAKES_COLUMN_COURSE_ID +"), " 
			+ "foreign key( " + TAKES_COLUMN_USER_ID + ") references "+ TABLE_USERS+ "(" + USER_COLUMN_ID +"), " 
			+ "foreign key( " + TAKES_COLUMN_COURSE_ID + ") references "+ TABLE_COURSES + "(" + COURSES_COLUMN_ID +")"+");";

	public DbSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL(USER_TABLE_CREATE);
		database.execSQL(COURSES_TABLE_CREATE);
		database.execSQL(TAKES_TABLE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(DbSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAKES);
		onCreate(db);
	}

}
