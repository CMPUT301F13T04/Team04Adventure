package com.example.team04adventure;


public class Media {
	
	long id;
	byte[] content;
	String type;
	
	
	public long getID(){
		
		return this.id;
		
	}

	public void setContent(byte[] content){
		
		this.content = content;
		
	}
	
	public byte[] getMedia(){
		
		return this.content;
		
	}
	
	public void setID(long id){
		
		this.id = id;
		
	}
	
	public void setType(String type){
		
		this.type = type;
		
	}
	
	public String getType(){
		
		return this.type;
	}
	
}
