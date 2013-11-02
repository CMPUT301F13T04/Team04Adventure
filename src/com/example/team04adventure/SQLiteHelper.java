package com.example.team04adventure;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	  public static final String TABLE_STORIES = "Stories";
	  public static final String COLUMN_UNAME = "UName";
	  public static final String COLUMN_SID = "_sid";
	  public static final String COLUMN_STITLE = "Title";
	  public static final String TABLE_STORY_FRAGS = "StoriesFrags";
	  public static final String TABLE_FRAGS = "Fragments";
	  public static final String COLUMN_FTITLE = "Title";
	  public static final String COLUMN_FID = "_fid";
	  public static final String COLUMN_AUT = "Author";
	  public static final String TABLE_FRAGS_MEDIA = "FragsMedia";
	  public static final String TABLE_FRAGS_CHOICE = "FragsChoices";
	  public static final String COLUMN_BODY = "Body";
	  public static final String TABLE_CHOICE = "Choices";
	  public static final String COLUMN_CID = "_cid";
	  public static final String COLUMN_TEXT = "Text";
	  public static final String COLUMN_CHILD_FID = "ChildID";
	  public static final String TABLE_MEDIA = "Media";
	  public static final String COLUMN_MID = "_mid";
	  public static final String COLUMN_CONTENT = "Content";
	  public static final String COLUMN_MTYPE = "Type";
	  
	  private static final String DATABASE_NAME = "team04.db";
	  private static final int DATABASE_VERSION = 2;

	  // Database creation sql statement
	  
	  private static final String DATABASE_CREATE_STORY = "create table "
		  + TABLE_STORIES + "(" + COLUMN_SID
		  + " text primary key, " + COLUMN_STITLE
		  + " text not null," + COLUMN_UNAME +" text not null);";
	  
	  private static final String DATABASE_CREATE_STORY_FRAG = "create table "
		  + TABLE_STORY_FRAGS + "(" + COLUMN_SID
		  + " integer primary key, " + COLUMN_FID + " integer);";
	  
		 
	  private static final String DATABASE_CREATE_FRAGS = "create table "
		  + TABLE_FRAGS + "(" + COLUMN_FID
		  + " text primary key, " + COLUMN_FTITLE
		  + " text not null, " + COLUMN_AUT + " text not null, "+
		   COLUMN_BODY + " text not null);";
	  
	  
	  private static final String DATABASE_CREATE_FRAGS_MEDIA = "create table "
		  + TABLE_FRAGS_MEDIA + "(" + COLUMN_FID
		  + " integer primary key, " + COLUMN_MID + " integer);";
	  
	  
	  private static final String DATABASE_CREATE_FRAGS_CHOICE = "create table "
		  + TABLE_FRAGS_CHOICE + "(" + COLUMN_FID 
		  + " integer primary key, " + COLUMN_CID + " integer);";
	  
	  private static final String DATABASE_CREATE_MEDIA = "create table "
		  + TABLE_MEDIA + "(" + COLUMN_MID 
		  + " integer primary key autoincrement, " + COLUMN_CONTENT
		  + " blob not null, " + COLUMN_MTYPE + " text not null);";
	  
	  
	  private static final String DATABASE_CREATE_CHOICE = "create table "
		  + TABLE_CHOICE + "(" + COLUMN_CID
		  + " integer primary key autoincrement, " + COLUMN_CONTENT 
		  + " text not null, " + COLUMN_CHILD_FID + " integer);";
	

	  public SQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE_STORY);
	    database.execSQL(DATABASE_CREATE_STORY_FRAG);
	    database.execSQL(DATABASE_CREATE_FRAGS);
	    database.execSQL(DATABASE_CREATE_MEDIA);
	    database.execSQL(DATABASE_CREATE_CHOICE);
	    database.execSQL(DATABASE_CREATE_FRAGS_MEDIA);
	    database.execSQL(DATABASE_CREATE_FRAGS_CHOICE);
	    
	  }
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(SQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORIES);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY_FRAGS);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAGS);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAGS_MEDIA);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAGS_CHOICE);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIA);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHOICE);
	    
	    onCreate(db);
	  }
	
}
