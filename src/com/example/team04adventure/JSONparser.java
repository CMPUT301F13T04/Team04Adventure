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

import android.os.StrictMode;

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
//	private final String users = "users/";
	private final String stories = "stories/";
//	private final String frags = "fragments/";

	// Just the generic constructor
	public JSONparser() {
		this.gson = new Gson();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	// Assumption is that the User being stored is not in the server already. 
	public void storeStory(Story aStory) throws ClientProtocolException, IllegalStateException, IOException {
		// Check if story is already registered before storing
//		if (!checkStory(aStory)) { // IF its true then the user exists.
			HttpPost httpPost = new HttpPost(WebService + stories+ aStory.getId());
			StringEntity stringentity = null;
			try {
				stringentity = new StringEntity(gson.toJson(aStory));
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

			//try {
			//	EntityUtils.consume(entity);
		//	} catch (IOException e) {
		//		e.printStackTrace();
			//}

			//httpPost.releaseConnection();
//		} else {
//			// TELL THE USER THEY ARE ALREADY IN THE SYSTEM.!>!!
//		}
	}

	//Assume user is on the server
	public Story getStory(String Storyid) {
		// Retrieve a user from the webservice, not sure why you would want to
		// do this.
		// returning null here right now to just get rid of the compiler errors.
		try {
			// This just gets using the id. This might be useless so you might
			// need to just search by name for the user.
			HttpGet getRequest = new HttpGet(WebService + stories + Storyid);
			getRequest.addHeader("Accept", "application/json");
			HttpResponse response = client.execute(getRequest);
			String status = response.getStatusLine().toString();
			System.out.println(status);
			String json = getEntityContent(response);
			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {}.getType();
			// Now we expect to get a User response
			ElasticSearchResponse<Story> esResponse = gson.fromJson(json,elasticSearchResponseType);
			Story s1 = esResponse.getSource();
			
			
			
			
		//	getRequest.releaseConnection();
			return s1;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkStory(Story aStory){
			HttpGet getRequest = new HttpGet(WebService + stories + aStory.getId());
			getRequest.addHeader("Accept", "application/json");
			HttpResponse response;
			try {
				response = client.execute(getRequest);
//			System.out.println(response.);
			String notFound = "HTTP/1.1 404 Not Found";
			String status = response.getStatusLine().toString();
			System.out.println(status);
			getRequest.releaseConnection();

			if (status.equals(notFound)){
				return false; // User is not there.
			}
			else 
				return true; // User is there. 
		
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
	}

	/**
	 * search by keywords
	 */
	 public ArrayList<Story> search(String[] keywords) throws IOException {
         String query_str = "";
         
         // create query string
         for (int i = 0; i < keywords.length; i++) {
                 query_str += keywords[i] + " OR ";
         }
         
         query_str = query_str.substring(0, query_str.length() - 4);        
         
         HttpPost searchRequest = new HttpPost(WebService + stories + "/_search?pretty=1");
         String query =         "{\"query\" : {\"query_string\" : {\"fields\" : [ \"title\"],\"query\" : \"" + query_str + "\"}}}";
         
         StringEntity stringentity = null;                
         try {
                 stringentity = new StringEntity(query);
         } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
         }
         
         searchRequest.setHeader("Accept","application/json");
         searchRequest.setEntity(stringentity);
         
         HttpResponse response = null;
         try {
                 response = client.execute(searchRequest);
         } catch (ClientProtocolException e) {
                 e.printStackTrace();
         }
         
         String status = response.getStatusLine().toString();
         System.out.println(status);

         String json = getEntityContent(response);

         Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>(){}.getType();
         ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
         
		// return 
		ArrayList<Story> abc = new ArrayList<Story>();
		for (ElasticSearchResponse<Story> r : esResponse.getHits()) {
			abc.add(r.getSource());
         }
         
         return abc;
 }

	// Not needed right now.
	public void deleteStory(Story S1) {
		HttpDelete httpDelete = new HttpDelete(WebService + stories + S1.getId());
		httpDelete.addHeader("Accept", "application/json");
		HttpResponse response;
		try {
			response = client.execute(httpDelete);

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
			EntityUtils.consume(entity);
			httpDelete.releaseConnection();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Story> getAll(){
		ArrayList<Story> list = new ArrayList<Story>();
		try {
			String[] a = new String[1];
			a[0] = "*";
			list = search(a);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return list;

	}

	public void cacheStory() {
		// Cache the story.
		// Get here from user clicking on the story and hitting download. If you
		// get here from that screen you will probably have
		// the id so take the ID as the argument. Void return type is fine.
		// This method basically just stores the story and all its fragments
		// into SQL.

		// Get it then give it to Chris' SQL code. Might have to get all the
		// frags too.
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
