import java.io.File;


public class Image {
	
	long id;
	File file;
	
	public Image(File file){
		this.file = file;
	}
	
	public long getId(){
		
		return this.id;
	}
	

}
