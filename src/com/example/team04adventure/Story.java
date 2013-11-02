package com.example.team04adventure;


import java.util.ArrayList;


public class Story {
	
	String title;
	private ArrayList<Frag> frags;
	String id;
	String Author;
	
	public Story(){
		
		this.frags = new ArrayList<Frag>();
		
	}

	public void setTitle(String title){
		
		this.title = title;
		
	}
	
	public void setAuthor(String author){
		
		this.Author = author;
		
	}
	

	public void addFragment(Frag frag){
		
		this.frags.add(frag);
	
	}
	
	public String getTitle(){
		
		return this.title;
	
	}
	
	public String getAuthor(){
		
		return this.Author;
	
	}
	
	public ArrayList<Frag> getFrags(){
		
		return this.frags;
	
	}
	
	public String getId(){
		
		return this.id;
	
	}
	
	public void setId(String id){
		
		this.id = id;
	
	}
	
	
}


