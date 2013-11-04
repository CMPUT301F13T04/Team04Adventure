package com.example.team04adventure;


import android.graphics.Bitmap;

/**
 * Media holds the images, videos, and sounds that can be associated to any fragment.
 */
public class Media {
	
	long id;
	Bitmap content;
	String type;
	
	/**
	 * Gets the ID of the media object.
	 * @return the ID number.
	 */
	public long getID(){
		
		return this.id;
		
	}

	/**
	 * Sets the content of a Media object as the specified image.
	 * @param content the bitmap object to be stored.
	 */
	public void setContent(Bitmap content){
		
		this.content = content;
		
	}
	
	/**
	 * Gets the content in the Media object.
	 * @return the bitmap object in the Media object.
	 */
	public Bitmap getMedia(){
		
		return this.content;
		
	}
	
	/**
	 * Sets the ID of the Media object as specified.
	 * @param id the ID number to set to.
	 */
	public void setID(long id){
		
		this.id = id;
		
	}
	
	/**
	 * Sets the type of the Media object. Possible examples include <ul><li>pic</li><li>vid</li><li>sound</li></ul>
	 * @param type the type of object being stored in Media in string format.
	 */
	public void setType(String type){
		
		this.type = type;
		
	}
	
	/**
	 * Gets the type of the Media object.
	 * @return type of Media.
	 */
	public String getType(){
		
		return this.type;
	}
	


}
