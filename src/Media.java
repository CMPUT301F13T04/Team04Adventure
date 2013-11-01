

import android.graphics.Bitmap;

public class Media {
	
	long id;
	Bitmap content;
	String type;
	
	
	public long getID(){
		
		return this.id;
		
	}

	public void setContent(Bitmap content){
		
		this.content = content;
		
	}
	
	public Bitmap getMedia(){
		
		return this.content;
		
	}
	
	public void setID(long id){
		
		this.id = id;
		
	}
	
	public void setType(String type){
		
		this.type = type;
		
	}
	
	public String getType(){
		
		return this.type;
	}
	

	
}
