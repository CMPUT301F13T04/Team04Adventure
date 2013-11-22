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


import java.util.ArrayList;

import com.example.team04adventure.View.MainActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * StorageManager is meant to write data into the SQLite database. This data is meant for access in offline mode in the case
 * that the user chooses offline mode or to view stories that the user has cached in the device memory.
 * 
 * @author Team04Adventure
 */
public class StorageManager implements Storage {

	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allStories = { SQLiteHelper.COLUMN_SID,
		      SQLiteHelper.COLUMN_STITLE,SQLiteHelper.COLUMN_UNAME,
		      SQLiteHelper.COLUMN_SYN
		      };
	private String[] allFrags = { SQLiteHelper.COLUMN_FID,
			SQLiteHelper.COLUMN_FTITLE, SQLiteHelper.COLUMN_AUT,
			SQLiteHelper.COLUMN_BODY, SQLiteHelper.COLUMN_ILL
			};
	private String[] allMedia = { SQLiteHelper.COLUMN_MID,
			SQLiteHelper.COLUMN_CONTENT, SQLiteHelper.COLUMN_MTYPE
			};
	private String[] allChoice = { SQLiteHelper.COLUMN_CID,
			SQLiteHelper.COLUMN_CONTENT, SQLiteHelper.COLUMN_CHILD_FID
			};
	
	private String[] allAnnots = { SQLiteHelper.COLUMN_AID,
			SQLiteHelper.COLUMN_BODY, SQLiteHelper.COLUMN_AUT,
			SQLiteHelper.COLUMN_CONTENT
			};
	
	/**
	 * Constructor that also creates an instance of 
	 * SQLiteHelper for easier DB access.
	 * @param context current context.
	 */
	public StorageManager(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	/**
	 * Opens the database.
	 * @throws SQLException
	 */
	private void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	/**
	 * Closes the database.
	 */
	private void close() {
		dbHelper.close();
	}
	
	/**
	 * Checks if the story exists.
	 * @param id ID of the story to check.
	 * @return boolean of if it exists.
	 */
	public boolean checkStory(String id){
		
		String[] sId = {SQLiteHelper.COLUMN_SID};
		String swhere = "_sid = ?";
		String[] whereargs = {""+id};
		
		this.open();
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_STORIES,
				sId, swhere, whereargs, null, null, null);
		

		
		if(cursor.moveToFirst() == false){
			cursor.close();
			this.close();
			return false;
		}	
		
		cursor.close();
		this.close();
		return true;
		
	}
	
	/**
	 * Adds a Story to the DB from information 
	 * contained in the object story.
	 *
	 * @param story story to be added.
	 */
	public void addStory(Story story) {
		
		this.open();
		
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_SID, story.getId());
		values.put(SQLiteHelper.COLUMN_STITLE, story.getTitle());
		values.put(SQLiteHelper.COLUMN_UNAME, story.getAuthor());
		values.put(SQLiteHelper.COLUMN_SYN, story.getSynopsis());
		database.insert(SQLiteHelper.TABLE_STORIES, null,
	    		values);
		
		values.clear();
		
		if(!story.getFrags().isEmpty()) {
			for (Frag f : story.getFrags()){
				addFrags(f);
				values.put(SQLiteHelper.COLUMN_SID, story.getId());
				values.put(SQLiteHelper.COLUMN_FID, f.getId());
				database.insert(SQLiteHelper.TABLE_STORY_FRAGS, null,
						values);
			}
		}
		
		this.close();
		
	}
	
	/**
	 * Inserts a F object into the DB.
	 * @param f fragment to be added.
	 * @return the ID of the fragment that was just added.
	 */
	private void addFrags(Frag f) {
		
		long lastid;
		
		
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_FID, f.getId());
		values.put(SQLiteHelper.COLUMN_FTITLE, f.getTitle());
		values.put(SQLiteHelper.COLUMN_AUT, f.getAuthor());
		values.put(SQLiteHelper.COLUMN_BODY, f.getBody());
		values.put(SQLiteHelper.COLUMN_ILL, f.getProfile().getMedia());
		
		database.insert(SQLiteHelper.TABLE_FRAGS, null,
	    		values);
		
		if(!f.getAnnotations().isEmpty()){
			for (Annotation a : f.getAnnotations()){
				lastid = addAnnots(a);
				values.clear();
				values.put(SQLiteHelper.COLUMN_FID, f.getId());
				values.put(SQLiteHelper.COLUMN_AID, lastid);
				database.insert(SQLiteHelper.TABLE_FRAGS_ANNOTS, null,
		    		values);
			}
		}
		
		
		if(!f.getChoices().isEmpty()){
			for (Choice c : f.getChoices()){
				lastid = addChoices(c);
				values.clear();
				values.put(SQLiteHelper.COLUMN_FID, f.getId());
				values.put(SQLiteHelper.COLUMN_CID, lastid);
				database.insert(SQLiteHelper.TABLE_FRAGS_CHOICE, null,
		    		values);
			}
		}
		
		if(!f.getPictures().isEmpty()){
			for (Media m : f.getPictures()){
				lastid = addMedia(m);
				values.clear();
				values.put(SQLiteHelper.COLUMN_FID, f.getId());
				values.put(SQLiteHelper.COLUMN_MID, lastid);
				database.insert(SQLiteHelper.TABLE_FRAGS_MEDIA, null,
		    		values);
			
			}
		}
		
		if(!f.getVids().isEmpty()){
			for (Media m : f.getVids()){
				lastid = addMedia(m);
				values.put(SQLiteHelper.COLUMN_FID, f.getId());
				values.put(SQLiteHelper.COLUMN_MID, lastid);
				database.insert(SQLiteHelper.TABLE_FRAGS_MEDIA, null,
	    		values);
			}
		}
		
		
	}


	/**
	 * Inserts a Media object into the DB.
	 * @param m media object to be added.
	 * @return the ID of the Media that was just added.
	 */
	private long addMedia(Media m) {
		
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_CONTENT, m.getMedia());
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


	/**
	 * Inserts a Choice object into the DB.
	 * @param c choice to add.
	 * @return the ID of the Choice that was just added.
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
	 * Inserts a Choice object into the DB.
	 * @param c choice to add.
	 * @return the ID of the Choice that was just added.
	 */
	private long addAnnots(Annotation a) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_BODY, a.getReview());
		values.put(SQLiteHelper.COLUMN_AUT, a.getAuthor());
		values.put(SQLiteHelper.COLUMN_CONTENT, a.getImage());
		database.insert(SQLiteHelper.TABLE_ANNOTS, null,
	    		values);
		
		long lastId = -1;
		
		String query = "SELECT _aid from Annotations order by _aid DESC limit 1";
		Cursor cursor = database.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
		    lastId = cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
		}
		
		return lastId;
		
	}


	/**
	 * Deletes a Story from the DB
	 * @param story story to be deleted.
	 */
	public void deleteStory(Story story) {
		
		this.open();
		
		String[] storyargs = {story.getId()};
		
		database.delete(SQLiteHelper.TABLE_STORIES, SQLiteHelper.COLUMN_SID
				+ " = ?" , storyargs);
	
		database.delete(SQLiteHelper.TABLE_STORY_FRAGS, SQLiteHelper.COLUMN_SID
				+ " = ?", storyargs);
		
	
		
		if(!story.getFrags().isEmpty()){
			for(Frag f : story.getFrags()){
				
				deleteFragment(f);
			}
			
		}
	
		this.close();
		
		
	}
	
	/**
	 * Deletes a Fragment from the DB
	 * @param f fragment to be deleted.
	 */
	private void deleteFragment(Frag f) {
		
		String[] fragargs = {f.getId()};
		
		database.delete(SQLiteHelper.TABLE_FRAGS, SQLiteHelper.COLUMN_FID
				+ " = ?", fragargs);
		
		database.delete(SQLiteHelper.TABLE_FRAGS_MEDIA, SQLiteHelper.COLUMN_FID
				+ " = ?", fragargs);
		
		database.delete(SQLiteHelper.TABLE_FRAGS_CHOICE, SQLiteHelper.COLUMN_FID
				+ " = ?", fragargs);
		
		database.delete(SQLiteHelper.TABLE_FRAGS_ANNOTS, SQLiteHelper.COLUMN_FID
				+ " = ?", fragargs);
			
		if(!f.getChoices().isEmpty()){
			for (Choice c : f.getChoices())
				deleteChoice(c);
			
		}
		
		if(!f.getPictures().isEmpty()){
			for (Media m : f.getPictures())
				deleteMedia(m);
			
			
		}
		
		if(!f.getVids().isEmpty()){
			for (Media m : f.getVids())
				deleteMedia(m);
			
		}
		
		if(!f.getAnnotations().isEmpty()){
			for (Annotation a : f.getAnnotations())
				deleteAnnotation(a);
			
		}
	
	}

	/**
	 * Deletes a Picture or Video from the DB
	 * @param m media to be deleted.
	 */
	private void deleteMedia(Media m) {
		
		String[] medargs = {""+m.getID()};
		
		database.delete(SQLiteHelper.TABLE_MEDIA, SQLiteHelper.COLUMN_MID
				+ " = ?", medargs);
		
	}

	/**
	 * Deletes a choice from the DB
	 * @param c choice to be deleted.
	 */
	private void deleteChoice(Choice c) {
		
		String[] choiargs = {""+c.getID()};
		
		database.delete(SQLiteHelper.TABLE_CHOICE, SQLiteHelper.COLUMN_CID
				+ " = ?", choiargs);		
	}
	
	/**
	 * Deletes a choice from the DB
	 * @param c choice to be deleted.
	 */
	private void deleteAnnotation(Annotation a) {
		
		String[] annoargs = {""+a.getId()};
		
		database.delete(SQLiteHelper.TABLE_ANNOTS, SQLiteHelper.COLUMN_AID
				+ " = ?", annoargs);		
	}


	/**
	 * Returns every Story that the user has cached.
	 * @return an ArrayList containing every Story.
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
	 * Returns a list of stories that belong to
	 * the author parameter 'usr'.
	 * @param The author who's stories you want.
	 * @return an arraylist of stories.
	 */
	public ArrayList<Story> getMyStories(){
		
		String[] sId = {SQLiteHelper.COLUMN_SID, SQLiteHelper.COLUMN_STITLE,
				SQLiteHelper.COLUMN_UNAME,SQLiteHelper.COLUMN_SYN };
		String swhere = "UName = ?";
		String[] whereargs = {MainActivity.username};
		
		ArrayList<Story> storyarr = new ArrayList<Story>();
		
		this.open();
		
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_STORIES,
				sId, swhere, whereargs, null, null, null);
		
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
	 * Returns a single story object of the story with id
	 * sid.
	 * @param sid ID of the story to get.
	 * @return a Story to be displayed to the user.
	 */
	public Story getStory(String sid){
		
		String[] sId = {SQLiteHelper.COLUMN_SID, SQLiteHelper.COLUMN_STITLE,
				SQLiteHelper.COLUMN_UNAME,SQLiteHelper.COLUMN_SYN };
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
	 * Returns a single story object stored in the
	 * story object passed in.
	 * @param story story to get info from.
	 * @return a Story to be displayed to the user.
	 */
	private Story getInfo(Story story) {
		String[] fIds = {SQLiteHelper.COLUMN_FID};
		String sFwhere = "_sid = ?";
		String[] whereargs = {story.getId()};

		Cursor cursor = database.query(SQLiteHelper.TABLE_STORY_FRAGS,
				fIds, sFwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Frag frag = new Frag();
			frag = getFrag(cursor.getString(0));
			story.addFragment(frag);
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return story;
		
	}

	/**
	 * Returns a single Fragment object.
	 * @param fid ID of the fragment to get.
	 * @return a fragment to be stored in a story.
	 */
	public Frag getFrag(String fid) {
		
		String fwhere = "_fid = ?";
		String[] whereargs = {fid};
		Frag frag = null;
		int flag = 0;


		if (database == null) {

			this.open();
			flag = 1;
		}	
		Cursor cursor = database.query(SQLiteHelper.TABLE_FRAGS,
				allFrags, fwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		frag = cursorToFragment(cursor);
		
		ArrayList<Choice> choicearr = getFragChoiceInfo(frag.getId());
		
		
		if (!choicearr.isEmpty()){
			for(Choice c : choicearr)
				frag.setChoice(c);
			}
		
		ArrayList<Annotation> annotarr = getFragAnnotInfo(frag.getId());
		
		
		if (!annotarr.isEmpty()){
			for(Annotation a : annotarr)
				frag.addAnnotations(a);
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
		
		if(flag == 1)
			this.close();
		
		return frag;
		
	}

	private ArrayList<Annotation> getFragAnnotInfo(String fid) {
		
		String[] aIds = {SQLiteHelper.COLUMN_AID};
		String awhere = "_fid = ?";
		String[] whereargs = {""+fid};
		ArrayList<Annotation> annotarr = new ArrayList<Annotation>();
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_FRAGS_ANNOTS,
				aIds, awhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Annotation annot = new Annotation();
			annot = getAnnotation(cursor.getLong(0));
			annotarr.add(annot);
			cursor.moveToNext();
		}
		
		cursor.close();
			
		return annotarr;
	}

	/**
	 * Returns an ArrayList of Videos or Pictures
	 * depending on the parameter type.
	 * @param fid ID of the media object to get info from.
	 * @param type type of media object.
	 * @return a list of videos or pictures.
	 */
	private ArrayList<Media> getFragMediaInfo(String fid, String type) {
		
		String[] mIds = {SQLiteHelper.COLUMN_MID};
		String mwhere = "_fid = ?";
		String[] whereargs = {""+fid};
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
	 * Returns a single Picture or Video object depending
	 * on the value in type.
	 * @param mid
	 * @param type type of the media object.
	 * @return a picture or video.
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
	 * Helper method that stores all the Media 
	 * attributes in a new Media object for addition to an
	 * ArrayList.
	 * @param cursor
	 * @return the Media in the row the cursor is currently pointing to.
	 */
	private Media cursorToMedia(Cursor cursor) {
		Media med = new Media();
		if (cursor != null && cursor.moveToFirst()) {
			med.setID(cursor.getLong(0));
			String convertedString = cursor.getString(1);
			med.setContent(convertedString);
			med.setType(cursor.getString(2));
		}

		return med;
	}



	/**
	 * Returns an ArrayList of Choices that
	 * belong to the fragment with ID of fid.
	 * @param fid ID of the fragment to get info from.
	 * @return a list of choices.
	 */
	private ArrayList<Choice> getFragChoiceInfo(String fid) {
		
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
	 * Returns a single Choice object with ID
	 * of cid.
	 * @param cid ID of the choice.
	 * @return a Choice object.
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
	 * Returns a single Annotation object with ID
	 * of aid.
	 * @param aid ID of the choice.
	 * @return a Annotation object.
	 */
	private Annotation getAnnotation(long aid) {
		
		String fwhere = "_aid = ?";
		String[] whereargs = {""+aid};
		Annotation annot = null;
		
		Cursor cursor = database.query(SQLiteHelper.TABLE_ANNOTS,
				allAnnots, fwhere, whereargs, null, null, null);
		
		cursor.moveToFirst();
		annot = cursorToAnnotation(cursor);
		
		cursor.close();
		
		return annot;
	}


	private Annotation cursorToAnnotation(Cursor cursor) {
		Annotation annot = new Annotation();
		annot.setId(cursor.getLong(0));
		annot.setReview(cursor.getString(1));
		annot.setAuthor(cursor.getString(2));
		annot.setImage(cursor.getString(3));
		
		return annot;
	}

	/**
	 * Helper method that stores all the Choice 
	 * attributes in a new Choice object for addition to an
	 * ArrayList.
	 * @param cursor
	 * @return the Choice in the row the cursor is currently pointing to.
	 */
	private Choice cursorToChoice(Cursor cursor) {
		Choice choice = new Choice();
		choice.setID(cursor.getLong(0));
		choice.setBody(cursor.getString(1));
		choice.setChild(cursor.getString(2));
		
		return choice;
	}


	/**
	 * Helper method that stores all the Fragment 
	 * attributes in a new Fragment object for addition to an
	 * ArrayList.
	 * @param cursor
	 * @return the fragment in the row the cursor is currently pointing to.
	 */
	private Frag cursorToFragment(Cursor cursor) {
		Frag frag = new Frag();
	    frag.setId(cursor.getString(0));
	    frag.setTitle(cursor.getString(1));
	    frag.setAuthor(cursor.getString(2));
	    frag.setBody(cursor.getString(3));
	   
	    Media m = new Media();
	    m.setContent(cursor.getString(4));
	    frag.setIllustration(m);
	    
	    return frag;
	}


	/**
	 * Helper method that stores all the Story 
	 * attributes in a new Story object for addition to an
	 * ArrayList.
	 * @param cursor
	 * @return the story in the row the cursor is currently pointing to.
	 */
	private Story cursorToStory(Cursor cursor) {
		Story story = new Story();
	    story.setId(cursor.getString(0));
	    story.setTitle(cursor.getString(1));
	    story.setAuthor(cursor.getString(2));
	    story.setSynopsis(cursor.getString(3));
	   
	    return story;
	
	}

	
}
