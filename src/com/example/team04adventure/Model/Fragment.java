package com.example.team04adventure.Model;

import java.util.ArrayList;


public class Fragment {
	
	String title;
	User author;
	long id;
	String fragid;
	Media profile;
	private ArrayList<Media> pictures;
	private ArrayList<Media> vids;
	private ArrayList<Choice> choices;
	String offlineAuthor;
	
	public Fragment(){
		
		this.setPictures(new ArrayList<Media>());
		this.vids = new ArrayList<Media>();
		this.choices = new ArrayList<Choice>();
	
	}

	public void setAuthor(User author){
		
		this.author = author;
	
	}
	
	public void setTitle(String title){
		
		this.title = title;
		
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
	
	public User getAuthor(){
		
		return this.author;
	
	}
	
	public String getTitle(){
		
		return this.title;
	
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
	
	public long getId(){
		
		return this.id;
	
	}
	public String getfragid(){
		return fragid;
	}
	
	public void setChoice(Choice choice){
		
		this.choices.add(choice);
		
	}
	
	public ArrayList<Choice> getChoices(){
		
		return this.choices;
		
	}
	
	public void setId(long id) {
		
		this.id = id;
		this.fragid = String.valueOf(id);
	}
	public void setId(String id) {
		this.fragid = id;
		this.id = Long.valueOf(fragid);
	}
	

	public void setAuthorString(String author){
		
		this.offlineAuthor = author;
	}

	public ArrayList<Media> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Media> pictures) {
		this.pictures = pictures;
	}
	
	
}
