/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.team04adventure.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SQLiteHelper is meant to handle required SQLite operations that the user does
 * not have access to. It also contains the raw queries to be accessed from
 * StorageManager.
 * 
 * @author Team04Adventure
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_STORIES = "Stories";
	public static final String COLUMN_UNAME = "UName";
	public static final String COLUMN_SID = "_sid";
	public static final String COLUMN_VER = "Version";
	public static final String COLUMN_STITLE = "Title";
	public static final String COLUMN_SYN = "Synop";
	public static final String TABLE_STORY_FRAGS = "StoriesFrags";
	public static final String TABLE_FRAGS = "Fragments";
	public static final String COLUMN_FTITLE = "Title";
	public static final String COLUMN_FID = "_fid";
	public static final String COLUMN_AUT = "Author";
	public static final String TABLE_FRAGS_MEDIA = "FragsMedia";
	public static final String TABLE_FRAGS_CHOICE = "FragsChoices";
	public static final String TABLE_FRAGS_ANNOTS = "FragsAnnots";
	public static final String TABLE_ANNOTS = "Annotations";
	public static final String COLUMN_BODY = "Body";
	public static final String TABLE_CHOICE = "Choices";
	public static final String COLUMN_CID = "_cid";
	public static final String COLUMN_TEXT = "Text";
	public static final String COLUMN_CHILD_FID = "ChildID";
	public static final String TABLE_MEDIA = "Media";
	public static final String COLUMN_MID = "_mid";
	public static final String COLUMN_CONTENT = "Content";
	public static final String COLUMN_MTYPE = "Type";
	public static final String COLUMN_AID = "_aid";
	public static final String COLUMN_ILL = "Illust";

	private static final String DATABASE_NAME = "team04.db";
	private static final int DATABASE_VERSION = 15;

	// Database creation sql statement

	private static final String DATABASE_CREATE_STORY = "create table "
			+ TABLE_STORIES + "(" + COLUMN_SID + " text primary key, "
			+ COLUMN_STITLE + " text," + COLUMN_UNAME + " text, " + COLUMN_SYN
			+ " text, " + COLUMN_VER + " int);";

	private static final String DATABASE_CREATE_STORY_FRAG = "create table "
			+ TABLE_STORY_FRAGS + "(" + COLUMN_SID + " text, " + COLUMN_FID
			+ " text);";

	private static final String DATABASE_CREATE_FRAGS = "create table "
			+ TABLE_FRAGS + "(" + COLUMN_FID + " text primary key, "
			+ COLUMN_FTITLE + " text, " + COLUMN_AUT + " text, " + COLUMN_BODY
			+ " text, " + COLUMN_ILL + " text);";

	private static final String DATABASE_CREATE_FRAGS_MEDIA = "create table "
			+ TABLE_FRAGS_MEDIA + "(" + COLUMN_FID + " text, " + COLUMN_MID
			+ " integer);";

	private static final String DATABASE_CREATE_FRAGS_ANNOTS = "create table "
			+ TABLE_FRAGS_ANNOTS + "(" + COLUMN_FID + " text, " + COLUMN_AID
			+ " integer);";

	private static final String DATABASE_CREATE_FRAGS_CHOICE = "create table "
			+ TABLE_FRAGS_CHOICE + "(" + COLUMN_FID + " text, " + COLUMN_CID
			+ " integer);";

	private static final String DATABASE_CREATE_ANNOTS = "create table "
			+ TABLE_ANNOTS + "(" + COLUMN_AID
			+ " integer primary key autoincrement, " + COLUMN_BODY + " text, "
			+ COLUMN_AUT + " text, " + COLUMN_CONTENT + " text);";

	private static final String DATABASE_CREATE_MEDIA = "create table "
			+ TABLE_MEDIA + "(" + COLUMN_MID
			+ " integer primary key autoincrement, " + COLUMN_CONTENT
			+ " text, " + COLUMN_MTYPE + " text);";

	private static final String DATABASE_CREATE_CHOICE = "create table "
			+ TABLE_CHOICE + "(" + COLUMN_CID
			+ " integer primary key autoincrement, " + COLUMN_CONTENT
			+ " text, " + COLUMN_CHILD_FID + " text);";

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	/**
	 * Creates all the necessary tables to store data in.
	 * 
	 * @param database
	 *            SQLiteDatabase to create tables into.
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_STORY);
		database.execSQL(DATABASE_CREATE_STORY_FRAG);
		database.execSQL(DATABASE_CREATE_FRAGS);
		database.execSQL(DATABASE_CREATE_MEDIA);
		database.execSQL(DATABASE_CREATE_CHOICE);
		database.execSQL(DATABASE_CREATE_FRAGS_MEDIA);
		database.execSQL(DATABASE_CREATE_FRAGS_CHOICE);
		database.execSQL(DATABASE_CREATE_FRAGS_ANNOTS);
		database.execSQL(DATABASE_CREATE_ANNOTS);

	}

	/**
	 * If the database needs to be upgraded, drops all existing tables and
	 * creates the upgraded tables.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORIES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY_FRAGS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAGS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAGS_MEDIA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAGS_CHOICE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHOICE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAGS_ANNOTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANNOTS);

		onCreate(db);
	}

}
