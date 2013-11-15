package com.example.team04adventure.Model;

import java.util.ArrayList;

public interface Storage {
	
	public void addStory(Story s);
	
	public void deleteStory(Story s);
	
	public ArrayList<Story> getAll();
	
	public Story getStory(String s);

}
