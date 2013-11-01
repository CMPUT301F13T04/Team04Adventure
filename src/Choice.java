

//testing egit addon for eclipse - delete this later - anthony VERSION2 v5.mike
public class Choice {
	
	String body;
	long id;
	long childID;
	
	public Choice(){
		
		/** a childID of -1 signifies that the choice 
		 * does not yet have a child **/
		this.childID = -1;
		
	}
	
	public String getBody(){
		
		return this.body;
		
	}
	
	public void setBody(String body){
		
		this.body = body;
		
	}
	
	public void setChild(long id){
		
		this.childID = id;
		
	}
	
	public long getChild(){
	
		return this.childID;
	
	}
	
	public long getID(){
		
		return this.id;
	}
	
	public void setID(long id){
		
		this.id = id;
		
	}

}
