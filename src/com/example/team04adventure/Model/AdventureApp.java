package com.example.team04adventure.Model;

import android.app.Application;
import android.content.res.Configuration;

/**
 * AdventureApp is a singleton that extends the Android Application class.
 * 
 * @author Team04Adventure
 */
public class AdventureApp extends Application {
	private static AdventureApp singleton;
	private Story currentStory;

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
	}

	public AdventureApp getInstance() {
		return singleton;
	}

	@Override
	public void onConfigurationChanged(Configuration c) {
		super.onConfigurationChanged(c);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	public Story getCurrentStory() {
		return currentStory;
	}

	public void setCurrentStory(Story currentStory) {
		this.currentStory = currentStory;
	}

}
