package com.example.team04adventure.Model;

import java.util.ArrayList;

public class User {

	private String usrname;
	private long id;
	private String usrid;
	private ArrayList<Story> stories; // Users and stories are stored on server,
										// stories have frags associated, frags
										// are

	// not independently stored on the server. If a story is stored, all its
	// fragments are stored too.

	public User(){
		this.usrname=null;
		this.usrid=null;
		this.stories= new ArrayList<Story>();
	}
	public User(String usrname) {

		this.usrname = usrname;
		this.usrid=null;
		this.stories= new ArrayList<Story>();

	}

	public String getName() {

		return this.usrname;

	}

	public ArrayList<Story> getStories() {

		return this.stories;

	}

	public long getId() {

		return this.id;

	}

	public void setName(String name) {

		this.usrname = name;

	}

	public void setId(long id) {

		this.id = id;

	}
	public String getusrid() {
		return usrid;
	}

	public void setusrid(String stringID) {
		usrid = stringID;
	}


	public void addStory(Story story) {

		this.stories.add(story);

	}
}
