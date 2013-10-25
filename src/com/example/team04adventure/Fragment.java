import java.util.ArrayList;


public class Fragment {
	
	String title;
	User author;
	long id;
	Media profile;
	ArrayList<Media> pictures;
	ArrayList<Media> vids;
	ArrayList<Choice> choices;
	
	public Fragment(User author){
		
		this.pictures = null;
		this.vids = null;
		this.choices = null;
		this.author = author;
	
	}

	public void setAuthor(User author){
		
		this.author = author;
	
	}
	
	public void setTitle(String title){
		
		this.title = title;
		
	}
	
	public void setIllustration(Media pic){
		
		this.profile = pic;
	
	}
	
	public void addPicture(Media pic){
		
		this.pictures.add(pic);
	
	}

	public void addVideo(Media vid){
		
		this.vids.add(vid);
	
	}
	
	public User getAuthor(){
		
		return this.author;
	
	}
	
	public String getTitle(){
		
		return this.title;
	
	}
	
	public ArrayList<Media> getImages(){
		
		return this.pictures;
	
	}
	
	public ArrayList<Media> getVids(){
		
		return this.vids;
	
	}
	
	public Media getProfile(){
		
		return this.profile;
	
	}
	
	public long getId(){
		
		return this.id;
	
	}
	
	public void setChoice(Choice choice){
		
		this.choices.add(choice);
		
	}
	
	public ArrayList<Choice> getChoices(){
		
		return this.choices;
		
	}
	
}
