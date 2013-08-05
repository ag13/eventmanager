package com.app.db;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.Event;

public class EventsDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME};
	
	public EventsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open(){
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public Event createEvent(String eventName){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, eventName);
		long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null, values);
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		
		cursor.moveToFirst();
		Event newEvent = cursorToEvent(cursor);
		cursor.close();
		return newEvent;
	}
	
	public List<String> getAllEvents() {
	    List<String> events = new ArrayList<String>();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Event event = cursorToEvent(cursor);
	      events.add(event.getName());
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return events;
	  }

	private Event cursorToEvent(Cursor cursor) {
		Event event = new Event();
		event.setId(cursor.getInt(0));
		event.setName(cursor.getString(1));
		return event;
	}
}
