package com.example.team04adventure;


import java.util.ArrayList;


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
	String Author;
	
	public Frag(){
		
		this.setPictures(new ArrayList<Media>());
		this.vids = new ArrayList<Media>();
		this.choices = new ArrayList<Choice>();
	
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
	
	public void addPicture(Media pic){
		
		this.getPictures().add(pic);
	
	}

	public void addVideo(Media vid){
		
		this.vids.add(vid);
	
	}
	
	public String getAuthor(){
		
		return this.Author;
	
	}
	
	public String getTitle(){
		
		return this.title;
	
	}
	// NEW
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
