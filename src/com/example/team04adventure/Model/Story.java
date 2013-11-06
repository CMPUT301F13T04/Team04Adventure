package com.example.team04adventure.Model;


import java.util.ArrayList;

/**
 * Story is meant to create and hold the ID, title, author and all associated fragments of a story.
 */
public class Story {
	
	String title;
	private ArrayList<Frag> frags;
	String id;
	String Author;
	String synopsis;
	
	public Story(){
		
		this.frags = new ArrayList<Frag>();
		
	}
	
	/**
	 * Gets the synopsis of the story.
	 * @return the synopsis of the story.
	 */
	public String getSynopsis(){
		
		return this.synopsis;
	}
	
	/**
	 * Sets the synopsis of the story as specified.
	 * @param syn the synopsis of the story.
	 */
	public void setSynopsis(String syn){
		
		this.synopsis = syn;
	}

	/**
	 * Sets the title of the story as specified.
	 * @param title the title of the story.
	 */
	public void setTitle(String title){
		
		this.title = title;
		
	}
	
	/**
	 * Sets the author of the story as specified.
	 * @param author the author of the story.
	 */
	public void setAuthor(String author){
		
		this.Author = author;
		
	}
	
	/**
	 * Adds the given fragment as one of the associated fragments of the story.
	 * @param frag the fragment to add.
	 */
	public void addFragment(Frag frag){
		
		this.frags.add(frag);
	
	}
	
	/**
	 * Gets the title of the story.
	 * @return title of the story.
	 */
	public String getTitle(){
		
		return this.title;
	
	}
	
	/**
	 * Gets the author of the story.
	 * @return author of the story.
	 */
	public String getAuthor(){
		
		return this.Author;
	
	}
	
	/**
	 * Gets the array of fragments associated with the story.
	 * @return array of fragments.
	 */
	public ArrayList<Frag> getFrags(){
		
		return this.frags;
	
	}
	
	/**
	 * Gets the ID of the story.
	 * @return the ID of the story.
	 */
	public String getId(){
		
		return this.id;
	
	}
	
	/**
	 * Sets the ID of the story.
	 * @param id the ID of the story.
	 */
	public void setId(String id){
		
		this.id = id;
	
	}
		
}


