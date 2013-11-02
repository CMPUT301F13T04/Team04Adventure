package com.example.team04adventure;

public class JSONparserTest implements TestCase{

	Story s;
	JSONparser jp;
	
	@Override
	public void setUp() {
		
		/*
		 * The below creates a new story object.
		 */
		
		/* Simple id */
		long l = 50;
		/* Simple choice */
		Choice c = new Choice();
		c.setBody("My choice");
		c.setID(l);
		/* Simple fragment */
		Frag f = new Frag();
		f.setAuthorString("Anthony");
		f.setBody("This fragment sucks");
		f.setChoice(c);
		/* Simple story */
		s = new Story();
		s.setAuthor("Mike");
		s.setTitle("Mike's Book");
		s.addFragment(f);
		
		/* Set up a new JSONparser */
		jp = new JSONparser();
		
		
	}

	@Override
	public void tearDown() {
		
		jp.deleteStory(s);
		
		
	}

	@Override
	public void run() {

		testCacheStory();
		testUpdateStory();
		testGetStory();
		testSearchStories();
		testDeleteStory();
		testStoreFrag();
		testUpdateFragment();
		testSearchFrag();
		testGetFrag();
		testRemoveFrag();
		testAdvancedSearch();
		testGetEntityContent();

	}

	
	public void testUpdateStory() {
		
		setUp();
		
		/* Ensure stored story equals s */
		jp.cacheStory(s);
		assert(s.equals(jp.getStory(s.getTitle())));
		/* Modify s */
		s.setTitle("New Title");
		s.setAuthor("New Author");
		/* Cache new story and check equality again */
		jp.updateStory(s);
		assert(s.equals(jp.getStory(s.getTitle())));
		
		tearDown();
		
		
	}
	
	public void testGetStory() {
		
	}
	
	public void testSearchStories() {
		
	}
	
	public void testDeleteStory() {
		
	}
	
	public void testStoreFrag() {
		
	}
	
	public void testUpdateFragment() {
		
	}
	
	public void testSearchFrag() {
		
	}
	
	public void testGetFrag() {
		
	}
	
	public void testRemoveFrag() {
		
	}
	
	public void testAdvancedSearch() {
		
	}
	
	public void testCacheStory() {
		
	}
	
	public void testGetEntityContent() {
		
	}
	
	
	
	

}
