package com.example.team04adventure.Model;



public class compressedStory {

	String title;
	int numfrags;
	String id;
	String author;
	

	public compressedStory(Story aStory){
		
		this.title = aStory.getTitle();
		this.author = aStory.getAuthor();
		this.id = aStory.getId();
		this.numfrags = aStory.getFrags().size();
	}

	public Story toStory(){
		Story tempStory = new Story();
		tempStory.setTitle(this.title);
		tempStory.setAuthor(this.author);
		tempStory.setId(this.id);
		for (int b = 0; b<this.numfrags;b++){
			Frag tempFrag = new Frag();
			tempStory.getFrags().add(tempFrag);
		}
		return tempStory;
		
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getNumfrags() {
		return numfrags;
	}


	public void setNumfrags(int numfrags) {
		this.numfrags = numfrags;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
}
