import java.io.File;


public class Video {
	
	long id;
	File file;
	
	public Video(File file){
		
		this.file = file;
	}
	
	public long getId(){
		
		return this.id;
	}

}
