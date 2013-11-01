package com.example.team04adventure;


import java.util.ArrayList;





public class Story {
	
	String title;
	private ArrayList<Frag> frags;
	User author;
	String id;
	String offlineAuthor;
	
	public Story(){
		
		this.frags = new ArrayList<Frag>();
		
	}

	public void setTitle(String title){
		
		this.title = title;
		
	}
	
	public void setAuthor(User author){
		
		this.author = author;
		
	}
	
	public void setAuthorString(String author){
		
		this.offlineAuthor = author;
	}
	
	public String getAuthorString(){
		
		return offlineAuthor;
	}
	
	public void addFragment(Frag frag){
		
		this.frags.add(frag);
	
	}
	
	public String getTitle(){
		
		return this.title;
	
	}
	
	public User getAuthor(){
		
		return this.author;
	
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


