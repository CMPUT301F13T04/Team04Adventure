import android.graphics.Bitmap;


public class Media {
	
	long id;
	Bitmap content;
	
	
	public long getID(){
		
		return this.id;
		
	}

	public void setMedia(Bitmap content){
		
		this.content = content;
		
	}
	
	public Bitmap getMedia(){
		
		return this.content;
		
	}
	
	public void setID(long id){
		
		this.id = id;
		
	}
	
}
