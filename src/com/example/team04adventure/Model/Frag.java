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


/**
 * Frag is meant to contain the id, title, body, and related media of a single story fragment inside a single object. 
 * 
 * @author Team04Adventure
 */

public class Frag {
	
	String title;
	String body;
	String id;
	Media profile;
	private ArrayList<Media> pictures;
	private ArrayList<Media> vids;
	private ArrayList<Choice> choices;
	private ArrayList<Annotation> annotations;
	String Author;
	
	public Frag(){
		
		this.setPictures(new ArrayList<Media>());
		this.vids = new ArrayList<Media>();
		this.choices = new ArrayList<Choice>();
		this.profile = new Media();
		this.profile.type = "pic";
		this.annotations = new ArrayList<Annotation>();

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

	public void setAuthor(String author){
		this.Author = author;
	}
	
	public void setTitle(String title){	
		this.title = title;
	}

	public void setBody(String body){		
		this.body = body;
	}
	
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
	
	public String getAuthor(){	
		return this.Author;
	}
	
	public String getTitle(){	
		return this.title;
	}

	public String getBody(){		
		return this.body;
	}

	public ArrayList<Media> getImages(){		
		return this.getPictures();
	}

	public ArrayList<Media> getVids(){		
		return this.vids;
	}
	
	public Media getProfile(){	
		return this.profile;
	}
	
	public String getId(){	
		return this.id;
	}

	public void setChoice(Choice choice){		
		this.choices.add(choice);	
	}
	
	public ArrayList<Choice> getChoices(){
		return this.choices;
	}
	
	public void setId(String id) {	
		this.id = id;	
	}

	public ArrayList<Media> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Media> pictures) {
		this.pictures = pictures;
	}
}
