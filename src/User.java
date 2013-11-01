

import java.util.ArrayList;




public class User {

	private String usrname;
	private long id;
	private ArrayList<Story> stories; // Users and stories are stored on server, stories have frags associated, frags are
	 // not independently stored on the server. If a story is stored, all its fragments are stored too.
	
	
	public User(String usrname){
		
		this.usrname = usrname;
	
	}
	
	public String getName(){
		
		return this.usrname;
		
	}
	
	public ArrayList<Story> getStories(){
		
		return this.stories;
		
	}
	
	public long getId(){
		
		return this.id;
	
	}
	
	public void setName(String name){
	
		this.usrname = name;
		
	}
	
	public void setId(long id){
		
		this.id = id;
		
	}
	
	public void addStory(Story story){
		
		this.stories.add(story);
		
	}
}
