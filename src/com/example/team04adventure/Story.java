package com.example.team04adventure;

import java.util.ArrayList;



public class Story {
	
	String title;
	ArrayList<Fragment> frags;
	User author;
	long id;
	String offlineAuthor;

	public void setTitle(String title){
		
		this.title = title;
		
	}
	
	public void setAuthor(User author){
		
		this.author = author;
		
	}
	
	public void setAuthorString(String author){
		
		this.offlineAuthor = author;
	}
	
	public void addFragment(Fragment frag){
		
		this.frags.add(frag);
	
	}
	
	public String getTitle(){
		
		return this.title;
	
	}
	
	public User getAuthor(){
		
		return this.author;
	
	}
	
	public ArrayList<Fragment> getFrags(){
		
		return this.frags;
	
	}
	
	public long getId(){
		
		return this.id;
	
	}
	
	public void setId(long id){
		
		this.id = id;
	
	}
	
	
}

