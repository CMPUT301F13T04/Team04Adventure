package com.example.team04adventure.Model;

/**
 * @author Team04Adventure
 * Class that models a choice associated with a fragment item. It contains
 * text(body) that differentiates it from other choices, an ID for storage and
 * linking purposes, and a childID that links to the fragment it represents.
 *
 */
public class Choice {
	
	String body;
	long id;
	long childID;
	
	public Choice(){
		
		/** a childID of -1 signifies that the choice 
		 * does not yet have a child **/
		this.childID = -1;
		
	}
	
	/*
	 * Simple getters and setters.
	 * 
	 */
	public String getBody(){
		
		return this.body;
		
	}
	
	public void setBody(String body){
		
		this.body = body;
		
	}
	
	public void setChild(long id){
		
		this.childID = id;
		
	}
	
	public long getChild(){
	
		return this.childID;
	
	}
	
	public long getID(){
		
		return this.id;
	}
	
	public void setID(long id){
		
		this.id = id;
		
	}

}
