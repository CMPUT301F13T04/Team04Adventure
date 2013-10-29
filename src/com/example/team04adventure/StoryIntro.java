package com.example.team04adventure;

import java.util.ArrayList;

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story_intro, menu);
		
		Bundle extras = getIntent().getExtras();
		long id = extras.getLong("id");
		
		Story s = getStory(id);
		
		storyTitle = (TextView) findViewById(R.id.StoryTitle);
		storyTitle.append(s.getTitle());
		storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
		storyAuthor.append(s.getUser().getName());

		
		return true;
	}
	
	private Story getStory(long id) {
		
		ArrayList<Story> stories = new ArrayList<Story>();
		
		StorageManager sm = new StorageManager(this);
		
		stories = sm.getAll();
		
		for (int i = 0; i < stories.size(); i++) {
			if (stories.get(i).getId().equals(id))
				return stories.get(i);
		}
		
		return null;
	}
	

}
