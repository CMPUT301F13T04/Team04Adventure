package com.example.team04adventure.Model;

/**
 * Class that models a choice associated with a fragment item. It contains
 * text(body) that differentiates it from other choices, an ID for storage and
 * linking purposes, and a childID that links to the fragment it represents.
 * 
 * @author Team04Adventure
 */
public class Choice {
	
	String body;
	long id;
	String childID;
	
	public Choice(){
		
		/** a childID of -1 signifies that the choice 
		 * does not yet have a child **/
		this.childID = "";
		
	}
	
	/*
	 * Simple getters and setters.
	 * 
	 */
	
	/**
	 * Gets the body of the choice.
	 * @return body of the choice.
	 */
	public String getBody(){
		
		return this.body;
		
	}
	
	/**
	 * Sets the body of the choice as specified.
	 * @param body body of the choice.
	 */
	public void setBody(String body){
		
		this.body = body;
		
	}
	
	/**
	 * Sets the child of the choice as the specified ID.
	 * @param id ID of the choice.
	 */
	public void setChild(String id){
		
		this.childID = id;
		
	}
	
	/**
	 * Gets the child of the choice.
	 * @return ID of the child of the choice.
	 */
	public String getChild(){
	
		return this.childID;
	
	}
	
	/**
	 * Gets the ID of the choice.
	 * @return ID of the choice.
	 */
	public long getID(){
		
		return this.id;
	}
	
	/**
	 * Sets the ID of the choice.
	 * @param id ID of the choice.
	 */
	public void setID(long id){
		
		this.id = id;
		
	}

}
