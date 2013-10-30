package com.example.team04adventure.Model;

import java.util.ArrayList;

public class Story {

	String title;
	private ArrayList<Fragment> frags;
	User author;
	long id;
	String StringID;

	String offlineAuthor;

	public Story() {

		this.frags = new ArrayList<Fragment>();

	}

	public void setTitle(String title) {

		this.title = title;

	}

	public void setAuthor(User author) {

		this.author = author;

	}

	public void setAuthorString(String author) {

		this.offlineAuthor = author;
	}

	public String getAuthorString() {

		return offlineAuthor;
	}

	public void addFragment(Fragment frag) {

		this.frags.add(frag);

	}

	public String getTitle() {

		return this.title;

	}

	public User getAuthor() {

		return this.author;

	}

	public ArrayList<Fragment> getFrags() {

		return this.frags;

	}

	public long getId() {

		return this.id;

	}

	public void setId(long id) {

		this.id = id;

	}
	public String getStringID() {
		return StringID;
	}

	public void setStringID(String stringID) {
		StringID = stringID;
	}


}
