package com.example.team04adventure.Test;

import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.Story;

import junit.framework.TestCase;

/**
 * @author Team04Adventure
 * The JUnit test case for the JSONparser class.
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
	 * @author Team04Adventure
	 * Create the default story and fragment. Also create the new 
	 * JSONparser object.
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
	 * Tests the storeStory() method. Ensures that the parser actually
	 * stores a story into the server.
	 * @param void
	 * @return void
	 */
	public void testStoreStory() {
		/* Assert that story does not already exist in the server */
		assertFalse(jp.checkStory(story));
		/* Add story to the server */
		try {
			jp.storeStory(story);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/* Assert that the story story now does exist in the server */
		assertTrue(jp.checkStory(story));
		/* Clean up and delete the story */
		jp.deleteStory(story);
		
	}

	public void testGetStory() {
		fail("Not yet implemented");
	}

	public void testCheckStory() {
		fail("Not yet implemented");
	}

	public void testSearch() {
		fail("Not yet implemented");
	}

	public void testDeleteStory() {
		fail("Not yet implemented");
	}

	public void testGetAll() {
		fail("Not yet implemented");
	}

	public void testCacheStory() {
		fail("Not yet implemented");
	}

	public void testGetEntityContent() {
		fail("Not yet implemented");
	}

}
