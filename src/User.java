import java.util.ArrayList;


public class User {

	private String usrname, password;
	private long id;
	private ArrayList<Story> stories;
	
	public User(String usrname, String password){
		this.usrname = usrname;
		this.password = password;
		
	}
	
	public String getName(){
		return this.usrname;
		
	}
	
	public String getPass(){
		return this.password;
		
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
	
	public void setPass(String pass){
		this.password = pass;
		
	}
	
	public void setId(long id){
		this.id = id;
		
	}
	
	public void addStory(Story story){
		this.stories.add(story);
		
	}
}
