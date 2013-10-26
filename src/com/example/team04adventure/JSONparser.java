package com.example.team04adventure;
import com.google.gson.Gson;

/*
 * Given an object, this class will return the JSON representation of
 * said object in a string format. Alternatively, given a string that
 * represents a JSON representation, it will convert said JSON representation
 * into the corresponding object. It will convert users, stories, and
 * fragments back and forth between objects and their JSON reps.
 */

public class JSONparser {

	Gson gson;

	// Just the generic constructor
	public JSONparser() {
		this.gson = new Gson();
	}

	// Takes a story obect, and returns the JSON representation of it
	public String storyToJson(Story story) {
		return gson.toJson(story);
	}

	// Takes a JSON story representation, and returns a story object
	public Story jsonToStory(String story) {
		return gson.fromJson(story, Story.class);
	}

	// Takes a user object, and returns the JSON representation of it
	public String userToJson(User user) {
		return gson.toJson(user);
	}

	// Takes a JSON user representation, and returns a user object
	public User jsonToUser(String user) {
		return gson.fromJson(user, User.class);
	}

	// Takes a fragment object, and returns the JSON representation of it
	public String fragToJson(Fragment frag) {
		return gson.toJson(frag);
	}

	// Takes a JSON fragment representation, and returns a fragment object
	public Fragment jsonToFrag(String frag) {
		return gson.fromJson(frag, Fragment.class);
	}



}
