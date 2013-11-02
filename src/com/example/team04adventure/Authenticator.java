package com.example.team04adventure;


public class Authenticator {

	// This method will return:
	//	-1 if there is no internet connection
	//	0 if the username is incorrect
	// 	1 if the username is valid
	public int authenticate(String user) {
		
		if (!checkConnection()) {
			return -1;
		}
		
		JSONparser j = new JSONparser();
		User u;
		
		return 1;
		
		
		
		
	}
	
	// This method is supposed to check to make sure the phone has an internet
	// connection. It returns true if it does, it returns false if it doesnt.
	// Right now it doesnt do shit, but it will later.
	public boolean checkConnection() {
		return true;
	}
	
}	
