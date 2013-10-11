import java.util.ArrayList;


public class Fragment {
	
	String title;
	User author;
	long id;
	Image profile;
	ArrayList<Image> pictures;
	ArrayList<Video> vids;
	
	public Fragment(){

	}

	public void setAuthor(User author){
		
		this.author = author;
	}
	
	public void setTitle(String title){
		
		this.title = title;
		
	}
	
	public void setIllustration(Image pic){
		
		this.profile = pic;
	}
	
	public void addPicture(Image pic){
		
		this.pictures.add(pic);
	}

	public void addVideo(Video vid){
		
		this.vids.add(vid);
	}
	
	public User getAuthor(){
		return this.author;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public ArrayList<Image> getImages(){
		return this.pictures;
	}
	
	public ArrayList<Video> getVids(){
		
		return this.vids;
	}
	
	public Image getProfile(){
		
		return this.profile;
	}
	
	public long getId(){
		
		return this.id;
	}
	
}
