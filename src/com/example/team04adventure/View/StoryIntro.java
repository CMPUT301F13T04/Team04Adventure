package com.example.team04adventure.View;

import com.example.team04adventure.R;
import com.example.team04adventure.Controller.StorageManager;
import com.example.team04adventure.Model.Story;
import com.example.team04adventure.R.id;
import com.example.team04adventure.R.layout;
import com.example.team04adventure.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


public class StoryIntro extends Activity {

	TextView 	storyTitle,
				storyAuthor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_intro);
		
		Bundle extras = getIntent().getExtras();
		long id = extras.getLong("id");
		StorageManager sm = new StorageManager(this);
		
		Story s = sm.getStory(id);
		
		storyTitle = (TextView) findViewById(R.id.StoryTitle);
		storyTitle.append(s.getTitle());
		storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
		storyAuthor.append(s.getAuthor().getName());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story_intro, menu);
		
		return true;
	}
	
	

}
