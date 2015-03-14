package com.nectar.thesun.library;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class NewsListProvider extends ContentProvider{

	
	private static final String AUTHORITY = "com.nectar.thesun.data.NewsListProvider";
	public static final int ALLNEWSLIST = 1;
	public static final int NEWS_ID = 2;
	public static final int NEWSLIST = 3;
	 
	private static final String NEWSLIST_BASE_PATH = "news";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
	        + "/" + NEWSLIST_BASE_PATH);
	
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
	        + "/mt-news";
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
	        + "/mt-news";
	
	private static final UriMatcher sURIMatcher = new UriMatcher(
	        UriMatcher.NO_MATCH);
	static {
	    sURIMatcher.addURI(AUTHORITY, NEWSLIST_BASE_PATH , ALLNEWSLIST);
	    sURIMatcher.addURI(AUTHORITY, NEWSLIST_BASE_PATH + "/#", NEWS_ID);
	    sURIMatcher.addURI(AUTHORITY, NEWSLIST_BASE_PATH + "/category/#", NEWSLIST);
	}
	
	private NewsListDatabase nldb;
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		nldb = new NewsListDatabase(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
	        String[] selectionArgs, String sortOrder) {
	    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
	    queryBuilder.setTables(NewsListDatabase.TABLE_NEWS);
	 
	    int uriType = sURIMatcher.match(uri);
	    switch (uriType) {
	    case NEWS_ID:
	        queryBuilder.appendWhere(NewsListDatabase.COL_PAGEID + "="
	                + uri.getLastPathSegment());
	        
	        break;
	    case NEWSLIST:
	    	 queryBuilder.appendWhere(NewsListDatabase.COL_CATEGORY + "="
		                + uri.getLastPathSegment());
	        break;
	    default:
	        throw new IllegalArgumentException("Unknown URI");
	    }
	 
	    Cursor cursor = queryBuilder.query(nldb.getReadableDatabase(),
	            projection, selection, selectionArgs, null, null, sortOrder);
	    cursor.setNotificationUri(getContext().getContentResolver(), uri);
	    return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
