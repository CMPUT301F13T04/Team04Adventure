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
		f.setAuthor("Anthony");
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
		testAdvancedSearch();
		testGetEntityContent();

	}

	
	public void testUpdateStory() {
		
		setUp();
		
		/* Ensure stored story equals s */
		try {
			jp.storeStory(s);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		assert(s.equals(jp.getStory(s.getTitle())));
		/* Modify s */
		s.setTitle("New Title");
		s.setAuthor("New Author");
		/* Cache new story and check equality again */
		//jp.updateStory(s);
		assert(s.equals(jp.getStory(s.getTitle())));
		
		tearDown();
		
		
	}
	
	public void testGetStory() {
		
		setUp();
		
		/* Store a story online */
		try {
			jp.storeStory(s);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/* Ensure s2 is an unit story */
		Story s2 = null;
		assert(s2 == null);
		/* Ensure s2 equals after it loads from server */
		s2 = jp.getStory(s.getTitle());
		assert(s2.equals(s));
		
		tearDown();
		
	}
	
	public void testSearchStories() {
		
	}
	
	public void testDeleteStory() {
		
		setUp();
		/* Store a story online */
		try {
			jp.storeStory(s);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/* Ensure story exists on server */
		assert(jp.getStory(s.title) != null);
		/* Delete story */
		jp.deleteStory(s);
		/* Ensure story doesn't exist on server */
		assert(jp.getStory(s.title) == null);
		
		tearDown();
		
	}
	
	public void testAdvancedSearch() {
		
	}
	
	public void testCacheStory() {
		
	}
	
	public void testGetEntityContent() {
		
	}
	

}
