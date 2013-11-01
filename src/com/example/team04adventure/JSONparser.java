package com.example.team04adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
 * This class deals with storage on the server. It stores stores and users,
 * is able to get stories and users and is able to search them. 
 * Based on  ESDemo from class. 
 */
// add the appropriate search classes (Elasticsearchresponse and elastic searchsearchresponse)
// Add hits class and fermiliarize yourself completely with how all these work. 
// Thats about it for classes, need to collaborate with team and see how all these methods will work together
// At this point you can outline the general code but to make it specific you need to know exactly how the app will run
// and what it will need from each method. 

public class JSONparser {

	private Gson gson;
	private HttpClient client = new DefaultHttpClient();
	private final String WebService = "http://cmput301.softwareprocess.es:8080/cmput301f13t04/";
	private final String users = "users/";
	private final String stories = "stories/";
	private final String frags = "fragments/";

	// Just the generic constructor
	public JSONparser() {
		this.gson = new Gson();
	}

	public void storeUser(User aUser) throws ClientProtocolException, IllegalStateException, IOException {		
		// Check if user is already registered before storing
		if (searchUsers(aUser.getName()).isEmpty()) { 
			//Meaning searching by their name returns nothing so they arent in the server and they need to be added.
	
			HttpPost httpPost = new HttpPost(WebService + users + aUser.getId());
			StringEntity stringentity = null;
			try {
				stringentity = new StringEntity(gson.toJson(aUser));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			httpPost.setHeader("Accept", "application/json");
			httpPost.setEntity(stringentity);
			HttpResponse response = null;

			try {
				response = client.execute(httpPost);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String status = response.getStatusLine().toString();
			System.out.println(status);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String output;
			System.err.println("Output from Server -> ");
			while ((output = br.readLine()) != null) {
				System.err.println(output);
			}

			// These dont work because i think EntityUtils is out of date. Could
			// not find a way to update it.
			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}

			httpPost.releaseConnection();
		} else {
			// TELL THE USER THEY ARE ALREADY IN THE SYSTEM.!>!!
		}
	}

	public User getUser(String UserID) {
		// Retrieve a user from the webservice, not sure why you would want to
		// do this.
		// returning null here right now to just get rid of the compiler errors.
		try {
			// This just gets using the id. This might be useless so you might
			// need to just search by name for the user.
			HttpGet getRequest = new HttpGet(WebService + users + UserID);
			getRequest.addHeader("Accept", "application/json");
			HttpResponse response = client.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);
			String json = getEntityContent(response);
			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<User>>() {}.getType();
			// Now we expect to get a User response
			ElasticSearchResponse<User> esResponse = gson.fromJson(json, elasticSearchResponseType);
			User u1 = esResponse.getSource();
			System.out.println(u1.toString());
			getRequest.releaseConnection();
			
			return u1;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * search by keywords
	 */
	public ArrayList<User> searchUsers(String str) throws ClientProtocolException, IOException {
		HttpGet searchRequest = new HttpGet(WebService + users + java.net.URLEncoder.encode(str, "UTF-8"));
		searchRequest.setHeader("Accept", "application/json");
		HttpResponse response = client.execute(searchRequest);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		ArrayList<User> tempList = new ArrayList<User>();
		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<User>>() {}.getType();
		ElasticSearchSearchResponse<User> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<User> r : esResponse.getHits()) {
			User u1 = r.getSource();
			tempList.add(u1);
		}
		searchRequest.releaseConnection();
		return tempList;
	}

	// Not needed right now.
	public void deleteUser() {
	}
	// when storing story should store all fragments first.
	// Get here when the user chooses to create a new story or update a
	// story.
	// Check if the story is already there
	// If not put it on the server
	// If it is, UPDATE** it.
	// Instead of 2 methods(Store and Update), this one does both by
	// checking if it needs to store or update.

	public void storeStory(Story aStory) throws IllegalStateException,
			IOException {
		if (searchStories(aStory.getTitle()).isEmpty()) {
			//Story is not on the server.
			HttpPost httpPost = new HttpPost(WebService + stories + aStory.getId());
			StringEntity stringentity = null;
			try {
				stringentity = new StringEntity(gson.toJson(aStory)); // Change this to story pojo, Store all story frags. 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			httpPost.setHeader("Accept", "application/json");
			httpPost.setEntity(stringentity);
			HttpResponse response = null;
			
			try {
				response = client.execute(httpPost);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String status = response.getStatusLine().toString();
			System.out.println(status);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String output;
			System.err.println("Output from Server -> ");
			while ((output = br.readLine()) != null) {
				System.err.println(output);
			}

			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			httpPost.releaseConnection();
		} else {
			updateStory(aStory);
		}

	}
	//Update stories by changing the fragments around not actually messing with the story. 
	private void updateStory(Story aStory) {
	}

	private Story getStory(String StoryID) {
		// Retrieve a story from the webservice. This method can probably be
		// used when you want to cache a story
		// when you want to cache, get it then store it to sql.
		try {
			// This just gets using the id. This might be useless so you might
			// need to just search by name for the user.
			HttpGet getRequest = new HttpGet(WebService + stories + StoryID);
			getRequest.addHeader("Accept", "application/json");
			HttpResponse response = client.execute(getRequest);
			 String status = response.getStatusLine().toString();
			 System.out.println(status);
			String json = getEntityContent(response);
			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {}.getType();
			// Now we expect to get a User response
			ElasticSearchResponse<Story> esResponse = gson.fromJson(json, elasticSearchResponseType);

			Story story1 = esResponse.getSource();

			System.out.println(story1.toString());
			getRequest.releaseConnection();
			return story1;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Im not even sure if we have to implement a search but whatever
	// Allow the user to search stories (can only search using story name.
	// return stories that match the criteria, not void.
	private ArrayList<Story> searchStories(String storytitle) throws ClientProtocolException, IOException {
		HttpGet searchRequest = new HttpGet(WebService + stories + java.net.URLEncoder.encode(storytitle, "UTF-8"));
		searchRequest.setHeader("Accept", "application/json");
		HttpResponse response = client.execute(searchRequest);
		
		String status = response.getStatusLine().toString();
	    System.out.println(status);
		
		ArrayList<Story> tempList = new ArrayList<Story>();
		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {}.getType();
		ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,elasticSearchSearchResponseType);
		 System.err.println(esResponse);
		for (ElasticSearchResponse<Story> r : esResponse.getHits()) {
			Story s1 = r.getSource();
			tempList.add(s1);
			System.err.println(s1.toString());
		}
		searchRequest.releaseConnection();
		return tempList;
	}

	public void deleteStory() {
	}

	public void storeFrag(Frag aFrag) throws IllegalStateException,
			IOException {
		// Check if its there using search, If it is, remove it and then put it
		// back ensuring it is the most updated verison
		if (searchFrag(aFrag.getTitle()).isEmpty()) {
			//If you get here you know the fragment isnt on the server.
			HttpPost httpPost = new HttpPost(WebService + frags + aFrag.getId());
			StringEntity stringentity = null;
			try {
				stringentity = new StringEntity(gson.toJson(aFrag)); //Assuming this will work at this point. 
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			httpPost.setHeader("Accept", "application/json");
			httpPost.setEntity(stringentity);
			HttpResponse response = null;

			try {
				response = client.execute(httpPost);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 String status = response.getStatusLine().toString();
			 System.out.println(status);
			 HttpEntity entity = response.getEntity();
			 BufferedReader br = new BufferedReader(new
			 InputStreamReader(entity.getContent()));
			 String output;
			 System.err.println("Output from Server -> ");
			 while ((output = br.readLine()) != null) {
			 System.err.println(output);
			 }
			
			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}
			httpPost.releaseConnection();
		} else {
			updateFragment(aFrag);
		}
	}
	private void updateFragment(Frag aFrag) {
		
	}

	public ArrayList<Frag> searchFrag(String fragtitle) throws ClientProtocolException, IOException {
		// Search via title, used in updating
		HttpGet searchRequest = new HttpGet(WebService + frags
				+ java.net.URLEncoder.encode(fragtitle, "UTF-8"));
		searchRequest.setHeader("Accept", "application/json");
		HttpResponse response = client.execute(searchRequest);

		// Dont really need to print status right now
		// String status = response.getStatusLine().toString();
		// System.out.println(status);
		//
		ArrayList<Frag> tempList = new ArrayList<Frag>();
		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Frag>>() {}.getType();
		ElasticSearchSearchResponse<Frag> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		// System.err.println(esResponse);
		for (ElasticSearchResponse<Frag> r : esResponse.getHits()) {
			Frag f1 = r.getSource();
			tempList.add(f1);
			// System.err.println(recipe);
		}
		searchRequest.releaseConnection();
		return tempList;
	}
	public Frag getFrag(String ID){
		// Get via ID. pretty simple, not sure the use though
		try {
			// This just gets using the id. This might be useless so you might
			// need to just search by name for the user.
			HttpGet getRequest = new HttpGet(WebService + frags + ID);

			getRequest.addHeader("Accept", "application/json");
			HttpResponse response = client.execute(getRequest);
			
			 String status = response.getStatusLine().toString();
			 System.out.println(status);
			String json = getEntityContent(response);
			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Frag>>() {}.getType();
			// Now we expect to get a Frag response
			ElasticSearchResponse<Frag> esResponse = gson.fromJson(json,elasticSearchResponseType);

			Frag returnFrag = esResponse.getSource();

			 System.out.println(returnFrag.toString());
			getRequest.releaseConnection(); 
			return returnFrag;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return null;
	}
	
	// Remove from the server so you can put a new updated version. Might have to do this for story too. 
	public void removeFrag(String fragID) throws IOException {
		HttpDelete httpDelete = new HttpDelete(WebService + frags + fragID);
		httpDelete.addHeader("Accept","application/json");
		HttpResponse response = client.execute(httpDelete);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}
		EntityUtils.consume(entity);
		httpDelete.releaseConnection();
	}

	public void advancedSearch() {
		// search using advanced parameters. Maybe dont even need this, maybe we
		// can just search using the title and thats it.
	}

	public void cacheStory() {
		// Cache the story.
		// Get here from user clicking on the story and hitting download. If you
		// get here from that screen you will probably have
		// the id so take the ID as the argument. Void return type is fine.
		// This method basically just stores the story and all its fragments
		// into SQL.
		
		// Get it then give it to Chris' SQL code. Might have to get all the frags too. 
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

