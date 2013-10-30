package com.example.team04adventure.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
// This is the wrong versino and its causing problems, dont know how to get the right version.

/*
 * This class deals with storage on the server. It stores stores and users,
 * is able to get stories and users and is able to search them. 
 * Based on  ESDemo from class. 
 */

// Add a couple pojos for user and story for searching purposes, maybe you only need story.
// Thats about it for classes, need to collaborate with team and see how all these methods will work together
public class JSONparser {

	private Gson gson;
	private HttpClient client = new DefaultHttpClient();
	private final String WebService = "http://cmput301.softwareprocess.es:8080/cmput301f13t04/";
	private final String users = "users/";
	private final String stories = "stories/";

	// Just the generic constructor
	public JSONparser() {
		this.gson = new Gson();
	}

//	public void storeUser(User aUser) {
//		// Check if the user is there already by using Get first.
//		// Check using the user ID. User is stored under their id. 
//		if (getUser(aUser.getusrid()).getName() != null){
//			// User is already on the server, dont do anything. 
//			// Show the user that they're logged in successfully.
//		}
//		else {
//		HttpPost httpPost = new HttpPost(WebService + users + aUser.getId());
//		StringEntity stringentity = null;
//		// Server only stores the names.
//		try {
//			stringentity = new StringEntity(gson.toJson(aUser));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		httpPost.setHeader("Accept", "application/json");
//		httpPost.setEntity(stringentity);
//		HttpResponse response = null;
//		try {
//			response = client.execute(httpPost);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		String status = response.getStatusLine().toString();
//		System.out.println(status);
//		HttpEntity entity = response.getEntity();
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				entity.getContent()));
//		String output;
//		System.err.println("Output from Server -> ");
//		while ((output = br.readLine()) != null) {
//			System.err.println(output);
//		}
//
//		try {
//			EntityUtils.consume(entity);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		httpPost.releaseConnection();
//		}
//		// Basically when the user hits login you need to check if that account
//		// is already there, if it isnt you need to generate
//		// an id for them and store them. Otherwise they're already there and
//		// you just access stories using their id.
//		// Check if user is already registered before storing
//	}

//	public void storeStory(Story aStory) throws IllegalStateException,
//			IOException {
//
//		// Get here when the user chooses to create a new story or update a
//		// story.
//		// Check if the story is already there
//		// If not put it on the server
//		// If it is, UPDATE** it.
//		// Instead of 2 methods(Store and Update), this one does both by
//		// checking if it needs to store or update.
//		// Change the following code:
//		HttpPost httpPost = new HttpPost(WebService + "stories/"
//				+ aStory.getId());
//		StringEntity stringentity = null;
//		try {
//			stringentity = new StringEntity(gson.toJson(aStory));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		httpPost.setHeader("Accept", "application/json");
//		httpPost.setEntity(stringentity);
//		HttpResponse response = null;
//
//		try {
//			response = client.execute(httpPost);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		String status = response.getStatusLine().toString();
//		System.out.println(status);
//		HttpEntity entity = response.getEntity();
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				entity.getContent()));
//		String output;
//		System.err.println("Output from Server -> ");
//		while ((output = br.readLine()) != null) {
//			System.err.println(output);
//		}
//
//		try {
//			EntityUtils.consume(entity);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		httpPost.releaseConnection();
//
//	}

	public void updateStory(Story aStory) {

	}

	// Retrieve user. Used to check whether or not the user is on the server.
	public User getUser(String UserID) {
		try {
			HttpGet getRequest = new HttpGet(WebService + users + UserID);
			getRequest.addHeader("Accept", "application/json");

			HttpResponse response = client.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			if (json.equals(null)){
				User a1 = new User();
				return a1;
		}
			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<User>>() {
			}.getType();
			// Now we expect to get a Recipe response
			ElasticSearchResponse<User> esResponse = gson.fromJson(json,
					elasticSearchResponseType);
			// We get the recipe from it!
			User U1 = esResponse.getSource();
			return U1;
		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		User a2 = new User();
		return a2;

	}

	public Story getStory(String StoryID) {
		// Retrieve a story from the webservice. This method can probably be
		// used when you want to cache a story
		// when you want to cache, get it then store it to sql.
		return null;
	}

	public void searchStories() {
		// Im not even sure if we have to implement a search but whatever
		// Allow the user to search stories (can only search using story name.
		// return stories that match the criteria, not void.
	}

	public void advancedSearch() {
		// search using advanced parameters. Maybe dont even need this, maybe we
		// can just search using the title and thats it.
	}

	public void searchUsers() {
		// Same as search stories, can only search by name.
		// Use this class to find whether or not when a user logs in that the
		// user has already logged in before.
		// If the user exists then basically just login as him and show his
		// stories and all stories
		// If the user doesnt exist create the user and give the user a blank
		// page with the user's stories and then
		// another page with all the other stories.
	}

	public void cacheStory() {
		// Cache the story.
		// Get here from user clicking on the story and hitting download. If you
		// get here from that screen you will probably have
		// the id so take the ID as the argument. Void return type is fine.
		// This method basically just stores the story and all its fragments
		// into SQL.
	}

	// You just know hes going to make us do this. You know.
	public void deleteUser() {
	}

	public void deleteStory() {
	}

	public String getEntityContent(HttpResponse response) throws IOException {

		// basic class, its just repeated code thats in every insert so its
		// here. This is straight out of the example.
		// Might change it later a bit

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:" + json);
		return json;
	}

}
