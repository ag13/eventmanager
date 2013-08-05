package com.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "events.db";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "event";
	public static final String COLUMN_ID = "eventid";
	public static final String COLUMN_NAME = "name";
	
	private static final String DATABASE_CREATE = "create table " 
		+ TABLE_NAME + " ( " + COLUMN_ID + " integer primary key autoincrement,"
		+ COLUMN_NAME + " text not null);";
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(database);
	}

}
