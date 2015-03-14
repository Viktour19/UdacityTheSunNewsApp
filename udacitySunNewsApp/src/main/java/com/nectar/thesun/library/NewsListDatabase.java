package com.nectar.thesun.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class NewsListDatabase extends SQLiteOpenHelper {

	private static final String DEBUG_TAG = "NewsListDatabase";
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "udacitythesunDB";

	public static final String TABLE_NEWS = "news";
	public static final String ID = "_id";
	public static final String COL_TITLE = "title";
	public static final String COL_URL = "url";
	public static final String COL_IMGURI = "imguri";
	public static final String COL_PAGEID = "pageid";
	public static final String COL_REPORTER = "reporter";
	public static final String COL_DATETIME = "datetime";
	public static final String COL_CATEGORY = "category";
	public static final String COL_DESC = "description";
	public static final String COL_NEWS = "news";
	 
	private static final String CREATE_TABLE_NEWS = "create table " + TABLE_NEWS
	+ " (" + ID + " integer primary key autoincrement, " + COL_TITLE 
	+ " text not null, " + COL_NEWS + " text not null, " + COL_URL + " text not null, " + COL_IMGURI + " text not null, " 
	+ COL_PAGEID + " text not null, " + COL_REPORTER + " text not null, "
	+ COL_DATETIME + " text not null, " + COL_CATEGORY + " text not null, " + COL_DESC + " text not null);";
	 
	private static final String DB_SCHEMA = CREATE_TABLE_NEWS;

	public NewsListDatabase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 db.execSQL(DB_SCHEMA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	    Log.w(DEBUG_TAG, "Upgrading database. Existing contents will be lost. ["
	            + oldVersion + "]->[" + newVersion + "]");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
	    onCreate(db);
	}

    public void addNews(News news)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_NEWS, news.news);
        values.put(COL_TITLE, news.title);
        values.put(COL_URL, news.link);
        values.put(COL_IMGURI,news.imageuri);
        values.put(COL_REPORTER, news.reporter);
        values.put(COL_DATETIME, news.datetime);
        values.put(COL_DESC,  news.description);
        values.put(COL_CATEGORY,  news.category);
        values.put(COL_PAGEID,  news.pageid);

        db.insert(TABLE_NEWS, null, values);
        db.close(); // Closing database connection
    }
	public void addNews(String news, String title, String link, String imageuri, String reporter, String datetime, String category, int pageid, String description) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(COL_NEWS, news);
		values.put(COL_TITLE, title);
		values.put(COL_URL, link);
		values.put(COL_IMGURI,imageuri);
		values.put(COL_REPORTER, reporter);
		values.put(COL_DATETIME, datetime);
		values.put(COL_DESC,  description);
		values.put(COL_CATEGORY,  category);
		values.put(COL_PAGEID,  pageid);
			
		db.insert(TABLE_NEWS, null, values);
		db.close(); // Closing database connection
	}

    public News getNewsItem(int pgid) {
        News news = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NEWS + " WHERE pageid= " + pgid;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            news = new News(cursor.getString(cursor.getColumnIndex(COL_NEWS)), cursor.getString(cursor.getColumnIndex(COL_TITLE)),
                    cursor.getString(cursor.getColumnIndex(COL_URL)), cursor.getString(cursor.getColumnIndex(COL_IMGURI)),
                    cursor.getString(cursor.getColumnIndex(COL_REPORTER)), cursor.getString(cursor.getColumnIndex(COL_DATETIME)),
                    cursor.getString(cursor.getColumnIndex(COL_CATEGORY)), Integer.valueOf(cursor.getString(cursor.getColumnIndex(COL_PAGEID))),
                    cursor.getString(cursor.getColumnIndex(COL_DESC)));


        }
        return news;
    }
	
	public ArrayList<News> getNews() {
		News news = null;
		String selectQuery = "SELECT  * FROM " + TABLE_NEWS;
        if(getUserRowCount() == 0)
            return null;
		ArrayList<News> gotten = new ArrayList<News>(getUserRowCount());

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			news = new News(cursor.getString(cursor.getColumnIndex(COL_NEWS)), cursor.getString(cursor.getColumnIndex(COL_TITLE)),
					cursor.getString(cursor.getColumnIndex(COL_URL)), cursor.getString(cursor.getColumnIndex(COL_IMGURI)),
					cursor.getString(cursor.getColumnIndex(COL_REPORTER)), cursor.getString(cursor.getColumnIndex(COL_DATETIME)),
					cursor.getString(cursor.getColumnIndex(COL_CATEGORY)), Integer.valueOf(cursor.getString(cursor.getColumnIndex(COL_PAGEID))),
					cursor.getString(cursor.getColumnIndex(COL_DESC)));
			gotten.add(news);
		}
		cursor.close();
		db.close();
		// return user
		return gotten;
	}

	/**
	 * Getting user login status return true if rows are there in table
	 * */
	public int getUserRowCount() {
        SQLiteDatabase db;
        String countQuery = "SELECT  * FROM " + TABLE_NEWS;
        try
        {
            db = this.getReadableDatabase();
        }
        catch(Exception e)
        {
            return 0;
        }

		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_NEWS, null, null);

		db.close();
	} 
}
