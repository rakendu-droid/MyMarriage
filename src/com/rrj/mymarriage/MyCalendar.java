package com.rrj.mymarriage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.util.Log;

public class MyCalendar {
	
	private static final String DEBUG_TAG = "MyActivity";
	public static final String[] INSTANCE_PROJECTION = new String[] {
	    Instances.EVENT_ID,      // 0
	    Instances.BEGIN,         // 1
	    Instances.TITLE          // 2
	  };
	
	public static final String[] CALENDAR_PROJECTION = new String[] {
		CalendarContract.Calendars._ID,
		CalendarContract.Calendars.ACCOUNT_NAME,
		CalendarContract.Calendars.CALENDAR_DISPLAY_NAME
	};
	
	public static final String[] EVENT_PROJECTION = new String[] {
		CalendarContract.Events._ID,
		CalendarContract.Events.TITLE,
		CalendarContract.Events.EVENT_COLOR
	};
	  
	// The indices for the projection array above.
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_BEGIN_INDEX = 1;
	private static final int PROJECTION_TITLE_INDEX = 2;


	private long CALL_ID = 3;
	private long startMills=0;
	private long endMills=0;
	
	private long startMillis = 0;
	private long endMillis = 0;
	Calendar beginTime = Calendar.getInstance();
	Context ctx;
	String eventID;
	
	
	
	public MyCalendar(Context context)
	{
		ctx= context;
	}
	
	
	
	public long getStartMills() {
		return startMills;
	}

	public void setStartMills(long startMills) {
		this.startMills = startMills;
	}
	
	

	public long getEndMills() {
		return endMills;
	}

	public void setEndMills(long endMills) {
		this.endMills = endMills;
	}

	
	
	
	
	public void AddEvent(long startTime,long endTime, String name, String desc)
	{
//		beginTime.set(2013,12, 20, 9, 30);
//		Log.d("Time"," time si  "+beginTime.getTime().toString());
//		startMillis = beginTime.getTimeInMillis();
//		Calendar endTime1 = Calendar.getInstance();
//		endTime1.set(2013,10, 20, 11, 45);
//		endMillis = endTime1.getTimeInMillis();
//		beginTime.set(2012, 9, 14, 7, 30);
//		startMillis = beginTime.getTimeInMillis();
//		Calendar endTime1 = Calendar.getInstance();
//		endTime1.set(2013, 4, 26, 8, 45);
//		endMillis = endTime1.getTimeInMillis();
		Log.d("Tag1", "Tag   111111"+ctx);
		ContentResolver cr = ctx.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(CalendarContract.Events.DTSTART, startTime);
		values.put(CalendarContract.Events.DTEND, endTime);
		values.put(CalendarContract.Events.TITLE, name);
		values.put(CalendarContract.Events.DESCRIPTION, desc);
		values.put(CalendarContract.Events.CALENDAR_ID, 1);
		values.put(CalendarContract.Events.EVENT_TIMEZONE,"America/Chicago");
		Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

		// Retrieve ID for new event
		eventID = uri.getLastPathSegment();
		Log.d("Event", "Event  "+eventID);
	}
	
	public void DeleteEvent()
	{
		long event = Long.parseLong(eventID);
		ContentResolver cr = ctx.getContentResolver();
		ContentValues values = new ContentValues();
		Uri deleteUri = null;
		deleteUri = ContentUris.withAppendedId(Events.CONTENT_URI, 311);
		int rows = cr.delete(deleteUri, null, null);
		Log.i("DEBUG_TAG", "Rows deleted: " + rows); 
	}
	
	public void ModifyEvent()
	{
		beginTime.set(2012, 9, 14, 7, 30);
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime1 = Calendar.getInstance();
		endTime1.set(2013, 4, 26, 8, 45);
		endMillis = endTime1.getTimeInMillis();
		
		Cursor cur = null;
		ContentResolver cr = ctx.getContentResolver();

		// The ID of the recurring event whose instances you are searching
		// for in the Instances table
		String selection = Instances.EVENT_ID + " = ?";
		String[] selectionArgs = new String[] {"204"};

		// Construct the query with the desired date range.
		Uri.Builder builder = Instances.CONTENT_URI.buildUpon();
		ContentUris.appendId(builder, startMillis);
		ContentUris.appendId(builder, endMillis);

		// Submit the query
		cur =  cr.query(builder.build(), 
		    INSTANCE_PROJECTION, 
		    selection, 
		    selectionArgs, 
		    null);
		   
		while (cur.moveToNext()) {
		    String title = null;
		    long eventID = 0;
		    long beginVal = 0;    
		    
		    // Get the field values
		    eventID = cur.getLong(PROJECTION_ID_INDEX);
		    beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
		    title = cur.getString(PROJECTION_TITLE_INDEX);
		              
		    // Do something with the values. 
		    Log.i(DEBUG_TAG, "Event:  " + title); 
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(beginVal);  
		    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    Log.i(DEBUG_TAG, "Date: " + formatter.format(calendar.getTime()));  
		    Log.d("hello", "Event Id  "+eventID);
		    }

	}
	
	public void GetCalender()
	{
		 ContentResolver contentResolver = ctx.getContentResolver();
	        Log.d("MainActivity.TAG", "URI = " + CalendarContract.Calendars.CONTENT_URI);
	        final Cursor cursor = contentResolver.query(CalendarContract.Calendars.CONTENT_URI,
	        		CALENDAR_PROJECTION, null, null, null);
	        Log.d("MainActivity.TAG", "cursor = " + cursor);
	        while (cursor.moveToNext()) {
	        final String _id = cursor.getString(0);
	        final String displayName = cursor.getString(1);
//	        final Boolean selected = !cursor.getString(2).equals("0");
//	        final String accountName = cursor.getString(3);
	        Log.d("MainActivity.TAG", "Found calendar " + "Display Name"+displayName+"Id"+ _id);
	       // calendarList.append(
	        //"Calendar: Id: " + _id + " Display Name: " + displayName + " Selected: " + selected + " Name " + accountName);
	        }
	}
	
	public void GetEvent()
	{
		ContentResolver contentResolver = ctx.getContentResolver();
		
		final Cursor cursor = contentResolver.query(CalendarContract.Events.CONTENT_URI, EVENT_PROJECTION, null, null, null);
		 while (cursor.moveToNext()) {
		        final String _id = cursor.getString(0);
		        final String title = cursor.getString(1);
//		        final Boolean selected = !cursor.getString(2).equals("0");
//		        final String accountName = cursor.getString(3);
		        Log.d("MainActivity.TAG", "Found calendar " + "Display Name"+title+"Id"+ _id);
		        eventID= _id;
		        // calendarList.append(
		        //"Calendar: Id: " + _id + " Display Name: " + displayName + " Selected: " + selected + " Name " + accountName);
		       
		 }
	}
	
}
