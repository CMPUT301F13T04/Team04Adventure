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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.team04adventure.R;


/**
 * Frag is meant to contain the id, title, body, and related media of a single story fragment inside a single object. 
 * 
 * @author Team04Adventure
 */

public class Frag {
	
	/*
	 * I dont know who made this class so I wasn't sure if there was
	 * supposed to be a "body" variable that held the frags description.
	 * I added one because it seemed like it needed one, and I added the
	 * appropriate getters and setters too. If this was done on purpose,
	 * just delete it but let me know because frag.java uses these
	 * fields.
	 * - Mike
	 */
	
	String title;
	String body;
	String id;
	Media profile;
	private ArrayList<Media> pictures;
	private ArrayList<Media> vids;
	private ArrayList<Choice> choices;
	private ArrayList<Annotation> annotations;
	String Author;
	
	/**
	 * Initializes a Frag object.
	 */
	public Frag(){
		
		this.setPictures(new ArrayList<Media>());
		this.vids = new ArrayList<Media>();
		this.choices = new ArrayList<Choice>();
		this.profile = new Media();
		this.profile.type = "pic";
		this.annotations = new ArrayList<Annotation>();
		
//		Bitmap bm = BitmapFactory.decodeResource(null, R.drawable.ic_launcher);
//		String convertedString = Media.encodeTobase64(bm);
		
//		this.profile.setContent(convertedString);
		this.body = "";
		this.Author = "";
		this.title = "";
	
	}
	
	public ArrayList<Annotation> getAnnotations() {
		if (this.annotations == null) {
			this.annotations = new ArrayList<Annotation>();
		}
		return annotations;
	}

	public void addAnnotations(Annotation a) {
		this.annotations.add(a);
	}

	/**
	 * Sets the author of the fragment as specified.
	 * @param author the author of the fragment.
	 */
	public void setAuthor(String author){
		
		this.Author = author;
	
	}
	
	/**
	 * Sets the title of the fragment as specified.
	 * @param title the title of the fragment.
	 */
	public void setTitle(String title){
		
		this.title = title;
		
	}
	
	/**
	 * Sets the body of the fragment as specified.
	 * @param body the body of the fragment.
	 */
	public void setBody(String body){
		
		this.body = body;
		
	}
	
	/**
	 * Sets the illustration of the fragment as the given image.
	 * @param pic the picture to be the illustration.
	 */
	public void setIllustration(Media pic){
		
		this.profile = pic;
	
	}
	
	/**
	 * Adds the image as one of the fragment's set of images.
	 * @param pic the picture to be added.
	 */
	public void addPicture(Media pic){
		
		this.getPictures().add(pic);
	
	}

	/**
	 * Adds the video as one of the fragment's set of videos.
	 * @param vid the video to be added.
	 */
	public void addVideo(Media vid){
		
		this.vids.add(vid);
	
	}
	
	/**
	 * Gets the author of the fragment.
	 * @return the author name.
	 */
	public String getAuthor(){
		
		return this.Author;
	
	}
	
	/**
	 * Gets the title of the fragment.
	 * @return the fragment title.
	 */
	public String getTitle(){
		
		return this.title;
	
	}

	/**
	 * Gets the body of the fragment.
	 * @return the fragment body.
	 */
	public String getBody(){
		
		return this.body;
	
	}
	
	/**
	 * Gets the array of images associated with the fragment.
	 * @return array of images.
	 */
	public ArrayList<Media> getImages(){
		
		return this.getPictures();
	
	}
	
	/**
	 * Gets the array of videos associated with the fragment.
	 * @return array of videos.
	 */
	public ArrayList<Media> getVids(){
		
		return this.vids;
	
	}
	
	/**
	 * Gets the profile illustration of the fragment.
	 * @return the profile illustration.
	 */
	public Media getProfile(){
		
		return this.profile;
	
	}
	
	/**
	 * Gets the ID number of the fragment.
	 * @return the ID number.
	 */
	public String getId(){
		
		return this.id;
	
	}
	
	/**
	 * Adds the choice as one of the fragment choices.
	 * @param choice the choice to be added.
	 */
	public void setChoice(Choice choice){
		
		this.choices.add(choice);
		
	}
	
	/**
	 * Gets the array of choices that the user can make in the fragment.
	 * @return array of possible choices.
	 */
	public ArrayList<Choice> getChoices(){
		
		return this.choices;
		
	}
	
	/**
	 * Sets the ID of the fragment as specified.
	 * @param id ID of the fragment.
	 */
	public void setId(String id) {
		
		this.id = id;
		
	}

	/**
	 * Gets the images associated with the fragment.
	 * @return array of images.
	 */
	public ArrayList<Media> getPictures() {
		return pictures;
	}

	/**
	 * Sets the specified array of images as the fragment's images.
	 * @param pictures array of images to be set.
	 */
	public void setPictures(ArrayList<Media> pictures) {
		this.pictures = pictures;
	}
	
	
}
