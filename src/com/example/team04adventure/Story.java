import java.util.ArrayList;


public class Story {
	
	String title;
	ArrayList<Fragment> frags;
	User author;
	long id;
	
	public Story(User author){
		
		this.author = author;
	
	}

	public void setTitle(String title){
		
		this.title = title;
		
	}
	
	public void setAuthor(User author){
		
		this.author = author;
		
	}
	
	public void addFragment(Fragment frag){
		
		this.frags.add(frag);
	
	}
	
	public String getTitle(){
		
		return this.title;
	
	}
	
	public User getUser(){
		
		return this.author;
	
	}
	
	public ArrayList<Fragment> getFrags(){
		
		return this.frags;
	
	}
	
	public long getId(){
		
		return this.id;
	
	}
}

