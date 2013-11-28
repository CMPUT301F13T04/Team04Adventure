package com.example.team04adventure.Test;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.Story;

/**
 * @author Team04Adventure The JUnit test case for the JSONparser class.
 * 
 */
public class JsonParserTest extends TestCase {
	// Hardcoded story and fragment objects
	Frag frag;
	Story story;
	JSONparser jp;

	public JsonParserTest(String name) {
		super(name);
	}

	/**
	 * @author Team04Adventure Create the default story and fragment. Also
	 *         create the new JSONparser object.
	 * @param void
	 * @return void
	 */
	protected void setUp() throws Exception {
		Frag f = new Frag();
		f.setTitle("This Fragment Should Be Unique");
		f.setAuthor("Fragment Author");
		f.setBody("This fragment sucks");
		f.setId("uniqueID1");
		/* Simple story */
		Story s = new Story();
		s.setTitle("This Should Be A Unique Story");
		s.setSynopsis("This is a test story. It will be boring as hell.");
		s.setAuthor("Story's Author");
		s.setId("uniqueID2");
		s.addFragment(f);
		this.frag = f;
		this.story = s;
		this.jp = new JSONparser();

		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests the storeStory() method. Ensures that the parser actually stores a
	 * story into the server.
	 * 
	 * @param void
	 * @return void
	 */
	public void testStoreStory() {
		/* Assert that story does not already exist in the server */
		//Assert.assertFalse(jp.checkStory(story));
		Integer index = Integer.valueOf(-3);
		Assert.assertNotNull(jp.execute(index, story));
		/* Add story to the server */
		try {
			index = Integer.valueOf(-6);
			jp.execute(index, story);
			//jp.storeStory(story);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Store the Story to Server");
		}
		/* Assert that the story story now does exist in the server */
		Assert.assertNotNull(jp.execute(-3, story));
		/* Clean up and delete the story */
		jp.execute(-4, story);
	}

	/**
	 * Tests the getStory() method. Ensures that the parser actually gets a
	 * story from the server. It also ensures that the story is the correct one
	 * and not an arbitrary story.
	 * 
	 * @param void
	 * @return void
	 */
	public void testGetStory() {
		Story s;
		/* Assert that story does not already exist in the server */
		Assert.assertFalse(jp.checkStory(story));
		/* Add story to the server */
		try {
			jp.storeStory(story);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Store the Story to Server");
		}
		/* Assert that the story story now does exist in the server */
		Assert.assertTrue(jp.checkStory(story));
		/* Get the recently inserted story from the server */
		s = jp.getStory(story.getId());
		/* Ensure that s actually got a story */
		Assert.assertTrue(s != null);
		/* Ensure that the stories are equivalent */
		Assert.assertTrue(s.equals(story));
		/* Clean up and delete the story */
		jp.deleteStory(story);
	}

	/**
	 * Tests the checkStory() method. Ensures that the parser actually checks
	 * the server for a story. It also ensures that the story is the correct one
	 * and not an arbitrary story.
	 * 
	 * @param void
	 * @return void
	 */
	public void testCheckStory() {
		Story s;
		/* Assert that story does not already exist in the server */
		Assert.assertFalse(jp.checkStory(story));
		/* Add story to the server */
		try {
			jp.storeStory(story);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Store the Story to Server");
		}
		/* Assert that the story story now does exist in the server */
		Assert.assertTrue(jp.checkStory(story));
		/* Get the recently inserted story from the server */
		s = jp.getStory(story.getId());
		/* Ensure that s actually got a story */
		Assert.assertTrue(s != null);
		/* Ensure that the stories are equivalent */
		Assert.assertTrue(s.equals(story));
		/* Clean up and delete the story */
		jp.deleteStory(story);

	}

	/**
	 * Tests the search() method. Ensures that the parser actually checks the
	 * server for a story that matches the search criteria and returns all
	 * stories that satisfy it. It also ensures that the story is the correct
	 * one and not an arbitrary story.
	 * 
	 * @param void
	 * @return void
	 */
	public void testSearch() {
		Story s;
		ArrayList<Story> storyArr = new ArrayList<Story>();
		String[] badQuery = { "Leg", "Day" };
		String[] goodQuery = { "unique", "story" };
		/* Assert that story does not already exist in the server */
		Assert.assertFalse(jp.checkStory(story));
		/* Add story to the server */
		try {
			jp.storeStory(story);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Store the Story to Server");
		}
		/* Assert that the story story now does exist in the server */
		Assert.assertTrue(jp.checkStory(story));
		/* Attempt to query with bad parameters */
		try {
			storyArr = jp.search(badQuery);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Query the Server");
		}
		/* Ensure the query returned nothing */
		Assert.assertEquals(0, storyArr.size());
		/* Attempt to query with good parameters */
		try {
			storyArr = jp.search(goodQuery);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Query the Server");
		}
		/* Ensure the query returned nothing */
		Assert.assertFalse(storyArr.size() == 0);
		/* Get the returned story */
		s = storyArr.get(0);
		/* Ensure that s actually got a story */
		Assert.assertTrue(s != null);
		/* Ensure that the stories are equivalent */
		Assert.assertTrue(s.equals(story));
		/* Clean up and delete the story */
		jp.deleteStory(story);
	}

	/**
	 * Tests the delete() method. Ensures that the parser actually deletes the
	 * right story from the server.
	 * 
	 * @param void
	 * @return void
	 */
	public void testDeleteStory() {
		/* Assert that story does not already exist in the server */
		Assert.assertFalse(jp.checkStory(story));
		/* Add story to the server */
		try {
			jp.storeStory(story);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Store the Story to Server");
		}
		/* Assert that the story story now does exist in the server */
		Assert.assertTrue(jp.checkStory(story));
		/* Delete the story */
		jp.deleteStory(story);
		/* Assert that story does not exist in the server */
		Assert.assertFalse(jp.checkStory(story));
	}

	/**
	 * This method tests the 'getAll()' method. It ensures that the method
	 * returns all of the stories stored in the server.
	 * 
	 * @param void
	 * @return void
	 */
	public void testGetAll() {
		Story s;
		ArrayList<Story> storyArr = new ArrayList<Story>();
		/* Assert that story does not already exist in the server */
		Assert.assertFalse(jp.checkStory(story));
		/* Add story to the server */
		try {
			jp.storeStory(story);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Store the Story to Server");
		}
		/* Assert that the story story now does exist in the server */
		Assert.assertTrue(jp.checkStory(story));
		/* Attempt to query with bad parameters */
		try {
			storyArr = jp.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Cannot Query the Server");
		}
		/* Ensure the query did not return nothing */
		Assert.assertFalse(storyArr.size() == 0);
		/* Get the returned story */
		s = storyArr.get(0);
		/* Ensure that s actually got a story */
		Assert.assertTrue(s != null);
		/* Ensure that the stories are equivalent */
		Assert.assertTrue(s.equals(story));
		/* Clean up and delete the story */
		jp.deleteStory(story);
	}

	/**
	 * This method is useless. Thought I'd keep the test case in case it gets
	 * implemented later.
	 * 
	 * @param void
	 * @return void
	 */
	public void testCacheStory() {
		fail("This method doesn't do anything...");
	}

	/**
	 * This method is internal to the JSONparser class. Should probably be
	 * private but just in case, I kept the test. Looking at you Hassaan.
	 * 
	 * @param void
	 * @return void
	 */
	public void testGetEntityContent() {
		fail("Method should be private. No test implemented.");
	}

}
