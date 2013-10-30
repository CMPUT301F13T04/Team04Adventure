package com.example.team04adventure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class StorageManager {

	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allStories = { SQLiteHelper.COLUMN_SID,
		      SQLiteHelper.COLUMN_STITLE,SQLiteHelper.COLUMN_UNAME 
		      };
	private String[] allFrags = { SQLiteHelper.COLUMN_FID,
			SQLiteHelper.COLUMN_FTITLE, SQLiteHelper.COLUMN_AUT
			};
	private String[] allMedia = { SQLiteHelper.COLUMN_MID,
			SQLiteHelper.COLUMN_CONTENT, SQLiteHelper.COLUMN_MTYPE
			};
	private String[] allChoice = { SQLiteHelper.COLUMN_CID,
			SQLiteHelper.COLUMN_TEXT, SQLiteHelper.COLUMN_CHILD_FID
			};
	
	/**
	 * Constructor that also creates an instance of 
	 * SQLiteHelper for easier DB access.
	 * @param context
	 */
	public StorageManager(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	
	private void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	private void close() {
		dbHelper.close();
	}
	
	/**
	 * addEntry: Adds a Story to the DB from information 
	 * contained in the object story.
	 *
	 * @param story
	 */
	public void addStory(Story story) {
		
		long lastsId = -1;
		long lastid = -1;
		
		this.open();
		
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_STITLE, story.getTitle());
		values.put(SQLiteHelper.COLUMN_UNAME, story.getAuthor().getName());
		database.insert(SQLiteHelper.TABLE_STORIES, null,
	    		values);
		
		
		String query = "SELECT _sid from Stories order by _sid DESC limit 1";
		Cursor cursor = database.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			lastsId = cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
		}
		
		values.clear();
		
		if(!story.frags.isEmpty()) {
			for (Fragment f : story.frags){
				lastid = addFrags(f);
				values.put(SQLiteHelper.COLUMN_SID, lastsId);
				values.put(SQLiteHelper.COLUMN_FID, lastid);
				database.insert(SQLiteHelper.TABLE_STORY_FRAGS, null,
						values);
			}
		}
		
		this.close();
		
	}
	
	/**
	 * addFrags: Inserts a Fragment object into the DB.
	 * @param f
	 * @return the ID of the fragment that was just added.
	 * to
	 */
	private long addFrags(Fragment f) {
		
		long lastid;
		long lastfId = -1;
		
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_FTITLE, f.getTitle());
		values.put(SQLiteHelper.COLUMN_AUT, f.getAuthor().getName());
		database.insert(SQLiteHelper.TABLE_FRAGS, null,
	    		values);
		
		
		String query = "SELECT _fid from Fragments order by _fid DESC limit 1";
		Cursor cursor = database.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			lastfId = cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
		}
		
		
		if(!f.choices.isEmpty()){
			for (Choice c : f.choices){
				lastid = addChoices(c);
				values.clear();
				values.put(SQLiteHelper.COLUMN_FID, lastfId);
				values.put(SQLiteHelper.COLUMN_CID, lastid);
				database.insert(SQLiteHelper.TABLE_FRAGS_CHOICE, null,
		    		values);
			}
		}
		
		if(!f.pictures.isEmpty()){
			for (Media m : f.pictures){
				lastid = addMedia(m);
				values.clear();
				values.put(SQLiteHelper.COLUMN_FID, lastfId);
				values.put(SQLiteHelper.COLUMN_MID, lastid);
				database.insert(SQLiteHelper.TABLE_FRAGS_MEDIA, null,
		    		values);
			
			}
		}
		
		if(!f.vids.isEmpty()){
			for (Media m : f.vids){
				lastid = addMedia(m);
				values.put(SQLiteHelper.COLUMN_FID, lastfId);
				values.put(SQLiteHelper.COLUMN_MID, lastid);
				database.insert(SQLiteHelper.TABLE_FRAGS_MEDIA, null,
	    		values);
			}
		}
		
		return lastfId;
	}


	/**
	 * addMedia: Inserts a Media object into the DB.
	 * @param m
	 * @return the ID of the Media that was just added.
	 * to
	 */
	private long addMedia(Media m) {
		
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_CONTENT, convertToBlob(m.getMedia()));
		values.put(SQLiteHelper.COLUMN_MTYPE, m.getType());
		database.insert(SQLiteHelper.TABLE_MEDIA, null,
	    		values);
		
		long lastId = -1;
		
		String query = "SELECT _mid from Media order by _mid DESC limit 1";
		Cursor c = database.rawQuery(query, null);
		if (c != null && c.moveToFirst()) {
		    lastId = c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
		}
		
		return lastId;
		
	}

	private byte[] convertToBlob(Bitmap media) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		media.compress(Bitmap.CompressFormat.PNG, 100, baos);   
		byte[] photo = baos.toByteArray(); 
		
		return photo;
	}


	/**
	 * addChoices: Inserts a Choice object into the DB.
	 * @param c
	 * @return the ID of the Choice that was just added.
	 * to
	 */
	private long addChoices(Choice c) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_CONTENT, c.getBody());
		values.put(SQLiteHelper.COLUMN_CHILD_FID, c.getChild());
		database.insert(SQLiteHelper.TABLE_CHOICE, null,
	    		values);
		
		long lastId = -1;
		
		String query = "SELECT _cid from Choices order by _cid DESC limit 1";
		Cursor cursor = database.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
		    lastId = cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
		}
		
		return lastId;
		
	}


	/**
	 * deleteEntry: Deletes a Story from the DB
	 * 
	 * @param story
	 */
	public void deleteStory(Story story) {
		
		this.open();
		
		database.delete(SQLiteHelper.TABLE_STORIES, SQLiteHelper.COLUMN_SID
				+ " = " + story.getId(), null);
	
		database.delete(SQLiteHelper.TABLE_STORY_FRAGS, SQLiteHelper.COLUMN_SID
				+ " = " + story.getId(), null);
		
		if(!story.frags.isEmpty()){
			for(Fragment f : story.frags){
				
				deleteFragment(f);
				
			}
			
		}
	
		this.close();
		
		
	}
	
	/**
	 * deleteFragment: Deletes a Fragment from the DB
	 * 
	 * @param f
	 */
	private void deleteFragment(Fragment f) {
		
		database.delete(SQLiteHelper.TABLE_FRAGS, SQLiteHelper.COLUMN_FID
				+ " = " + f.getId(), null);
		
		database.delete(SQLiteHelper.TABLE_FRAGS_MEDIA, SQLiteHelper.COLUMN_FID
				+ " = " + f.getId(), null);
		
		database.delete(SQLiteHelper.TABLE_FRAGS_CHOICE, SQLiteHelper.COLUMN_FID
				+ " = " + f.getId(), null);
		
		
		if(!f.choices.isEmpty()){
			for (Choice c : f.choices)
				deleteChoice(c);
			
		}
		
		if(!f.pictures.isEmpty()){
			for (Media m : f.pictures)
				deleteMedia(m);
			
			
		}
		
		if(!f.vids.isEmpty()){
			for (Media m : f.vids)
				deleteMedia(m);
			
		}
	
	}

	/**
	 * deleteMedia: Deletes a Picture or Video from the DB
	 * 
	 * @param m
	 */
	private void deleteMedia(Media m) {
		database.delete(SQLiteHelper.TABLE_MEDIA, SQLiteHelper.COLUMN_MID
				+ " = " + m.getID(), null);
		
	}

	/**
	 * deleteChoice: Deletes a choice from the DB
	 * 
	 * @param c
	 */
	private void deleteChoice(Choice c) {
		database.delete(SQLiteHelper.TABLE_CHOICE, SQLiteHelper.COLUMN_CID
				+ " = " + c.getID(), null);		
	}


	/**
	 * getAll: Returns every Story that the user has cached.
	 * 
	 * @return An ArrayList containing every Story.
	 */
	public ArrayList<Story> getAll() {
		
		ArrayList<Story> storyarr = new ArrayList<Story>();
		
		this.open();
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_STORIES,
				allStories, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Story story = cursorToStory(cursor);
			story = getInfo(story);
			storyarr.add(story);
			cursor.moveToNext();
		}
		
		cursor.close();
		
		this.close();
		
		return storyarr;
	}
	
	/**
	 * getInfo: Returns a single story object of the story with id
	 * sid.
	 * 
	 * @param sid
	 * 
	 * @return A Story to be displayed to the user.
	 */
	public Story getStory(long sid){
		
		String[] sId = {SQLiteHelper.COLUMN_SID};
		String swhere = "_sid = ?";
		String[] whereargs = {""+sid};
		
		this.open();
		
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_STORIES,
				sId, swhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		Story story = cursorToStory(cursor);
		story = getInfo(story);
		
		cursor.close();
		
		this.close();
		
		return story;
		
		
		
	}
	
	
	/**
	 * getInfo: Returns a single story object stored in the
	 * story object passed in..
	 * 
	 * @param story
	 * 
	 * @return A Story to be displayed to the user.
	 */
	private Story getInfo(Story story) {
		String[] fIds = {SQLiteHelper.COLUMN_FID};
		String sFwhere = "_sid = ?";
		String[] whereargs = {""+story.getId()};

		Cursor cursor = database.query(SQLiteHelper.TABLE_STORY_FRAGS,
				fIds, sFwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Fragment frag = new Fragment();
			frag = getFrag(cursor.getLong(0));
			story.addFragment(frag);
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return story;
		
	}

	/**
	 * getFrag: Returns a single Fragment object.
	 * 
	 * @param fid
	 * 
	 * @return A fragment to be stored in a story.
	 */
	public Fragment getFrag(long fid) {
		
		String fwhere = "_fid = ?";
		String[] whereargs = {""+fid};
		Fragment frag = null;
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_FRAGS,
				allFrags, fwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		frag = cursorToFragment(cursor);
		
		ArrayList<Choice> choicearr = getFragChoiceInfo(frag.getId());
		
		if (!choicearr.isEmpty()){
			for(Choice c : choicearr)
				frag.setChoice(c);
			}
		
		ArrayList<Media> picsarr = getFragMediaInfo(frag.getId(), "pic");
		
		if (!picsarr.isEmpty()){
			for(Media m : picsarr)
				frag.addPicture(m);
			}
		
		ArrayList<Media> vidsarr = getFragMediaInfo(frag.getId(), "vid");
		
		if (!vidsarr.isEmpty()){
			for(Media m : vidsarr)
				frag.addVideo(m);
			}
		
		cursor.close();
		return frag;
		
	}

	/**
	 * getFragMediaInfo: Returns an ArrayList of Videos or Pictures
	 * depending on the parameter type.
	 * 
	 * @param id, type
	 * 
	 * @return A list of videos or pictures.
	 */
	private ArrayList<Media> getFragMediaInfo(long id, String type) {
		
		String[] mIds = {SQLiteHelper.COLUMN_MID};
		String mwhere = "_fid = ?";
		String[] whereargs = {""+id};
		ArrayList<Media> medarr = new ArrayList<Media>();
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_FRAGS_MEDIA,
				mIds, mwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Media media = new Media();
			media = getMedia(cursor.getLong(0), type);
			medarr.add(media);
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return medarr;
	}

	/**
	 * getMedia: Returns a single Picture or Video object depending
	 * on the value in type.
	 * 
	 * @param mid, type
	 * 
	 * @return A picture or video.
	 */
	private Media getMedia(long mid, String type) {
		
		String fwhere = "_mid = ? AND Type = ?";
		String[] whereargs = {""+mid, type};
		Media med = null;
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_MEDIA,
				allMedia, fwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		med = cursorToMedia(cursor);
		
		cursor.close();

		return med;
	}

	/**
	 * cursorToMedia: Helper method that stores all the Media 
	 * attributes in a new Media object for addition to an
	 * ArrayList.
	 * @param cursor
	 * @return the Media in the row the cursor is currently pointing
	 * to
	 */
	private Media cursorToMedia(Cursor cursor) {
		Media med = new Media();
		med.setID(cursor.getLong(0));
		med.setContent(convertToBitmap(cursor.getBlob(1)));
		med.setType(cursor.getString(2));
		
		return med;
	}

	private Bitmap convertToBitmap(byte[] blob) {
		
		ByteArrayInputStream imageStream = new ByteArrayInputStream(blob);
	        Bitmap image = BitmapFactory.decodeStream(imageStream);
		return image;
	}


	/**
	 * getFragChoiceInfo: Returns an ArrayList of Choices that
	 * belong to the fragment with ID of fid.
	 * 
	 * @param fid
	 * 
	 * @return A list of choices.
	 */
	private ArrayList<Choice> getFragChoiceInfo(long fid) {
		
		String[] cIds = {SQLiteHelper.COLUMN_CID};
		String cwhere = "_fid = ?";
		String[] whereargs = {""+fid};
		ArrayList<Choice> choicearr = new ArrayList<Choice>();
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_FRAGS_CHOICE,
				cIds, cwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Choice choice = new Choice();
			choice = getChoice(cursor.getLong(0));
			choicearr.add(choice);
			cursor.moveToNext();
		}
		
		cursor.close();
			
		return choicearr;
	}

	/**
	 * getChoice: Returns a single Choice object with ID
	 * of cid.
	 * 
	 * @param cid
	 * 
	 * @return A Choice object.
	 */
	private Choice getChoice(long cid) {
		
		String fwhere = "_cid = ?";
		String[] whereargs = {""+cid};
		Choice choice = null;
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_CHOICE,
				allChoice, fwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		choice = cursorToChoice(cursor);
		
		cursor.close();
		
		return choice;
	}


	/**
	 * cursorToChoice: Helper method that stores all the Choice 
	 * attributes in a new Choice object for addition to an
	 * ArrayList.
	 * @param cursor
	 * @return the Choice in the row the cursor is currently pointing
	 * to
	 */
	private Choice cursorToChoice(Cursor cursor) {
		Choice choice = new Choice();
		choice.setID(cursor.getLong(0));
		choice.setBody(cursor.getString(1));
		choice.setChild(cursor.getLong(2));
		
		return choice;
	}


	/**
	 * cursorToFragment: Helper method that stores all the Fragment 
	 * attributes in a new Fragment object for addition to an
	 * ArrayList.
	 * @param cursor
	 * @return the fragment in the row the cursor is currently pointing
	 * to
	 */
	private Fragment cursorToFragment(Cursor cursor) {
		Fragment frag = new Fragment();
	    frag.setId(cursor.getLong(0));
	    frag.setTitle(cursor.getString(1));
	    frag.setAuthorString(cursor.getString(2));
	    
	    return frag;
	}


	/**
	 * cursorToEntry: Helper method that stores all the Story 
	 * attributes in a new Story object for addition to an
	 * ArrayList.
	 * @param cursor
	 * @return the story in the row the cursor is currently pointing
	 * to
	 */
	private Story cursorToStory(Cursor cursor) {
		Story story = new Story();
	    story.setId(cursor.getLong(0));
	    story.setTitle(cursor.getString(1));
	    story.setAuthorString(cursor.getString(2));
	    
	    return story;
	
	}

	
}