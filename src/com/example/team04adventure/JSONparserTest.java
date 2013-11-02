package com.example.team04adventure;

public class JSONparserTest implements TestCase{

	Story s;
	
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
		
		
	}

	@Override
	public void tearDown() {
		
		JSONparser jpars = new JSONparser();
		jpars.deleteStory(s);
		
		
	}

	@Override
	public void run() {
		
		setUp();
		
		testStoreUser();
		testGetUser();
		testSearchUsers();
		testDeleteUser();
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
		testCacheStory();
		testGetEntityContent();
		
		tearDown();
		
	}
	
	public void testStoreUser() {
		
	}
	
	public void testGetUser() {
		
	}
	
	public void testSearchUsers() {
		
	}
	
	public void testDeleteUser() {
		
	}
	
	public void testUpdateStory() {
		
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
