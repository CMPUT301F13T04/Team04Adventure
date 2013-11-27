package com.example.team04adventure.Test;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;
import com.example.team04adventure.Model.compressedStory;

/**
 * @author Team04Adventure The JUnit test class for the compressed story class.
 */
public class CompressedStoryTest extends AndroidTestCase {
	/* Sample hard-coded story and fragment */
	Story story;
	Frag frag;
	/* StorageManager and context used to store and delete functions */
	StorageManager sm;
	Context mc;

	// ActivityInstrumentationTestCase2 aitc;

	public CompressedStoryTest() {
		super();
	}

	/**
	 * @author Team04Adventure Create the default story and fragment.
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

		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests the 'toStory()' method that converts a compressed story into a full story based
	 * on the information given from the compressed story.
	 * 
	 * @param void
	 * @return void
	 */
	public void testToStory() {
		/* Create a new compressed story */
		compressedStory cs = new compressedStory(story);
		/* Ensure that all attributes remain constant in the conversion process */
		assert(cs.getTitle().equals(story.getTitle()));
		assert(cs.getAuthor().equals(story.getTitle()));
		assert(cs.getId().equals(story.getId()));
		/* Test that the returned story is the same as the original story */
		assert(cs.toStory().equals(story));
	}



}
