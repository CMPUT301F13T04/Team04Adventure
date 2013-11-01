package com.example.team04adventure;




import java.util.ArrayList;

import com.example.team04adventure.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class StoryIntro extends Activity {

	TextView 	storyTitle,
				storyAuthor;
	long id;
	Story story;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_intro);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");
		StorageManager sm = new StorageManager(this);
		
		System.out.println("ID: "+ id);
		
	
		Story s = sm.getStory(id);
		
		
		
		
		storyTitle = (TextView) findViewById(R.id.StoryTitle);
		storyTitle.append(s.getTitle());
		storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
		storyAuthor.append(s.getAuthor().getName());
	}

	public void removeFromCache(View view){
		Intent i;
		StorageManager sm = new StorageManager(this);
		
		/** Open DB connection and deletes 
	     the note. **/
		story = sm.getStory(id);
		sm.deleteStory(story);
		
		
		i = new Intent();
		i.setClassName("com.example.team04adventure",
		               "com.example.team04adventure.OfflineStoryList");
		startActivity(i);
		
		
	}
	
	public void playStory(View view){
		
		ArrayList<Fragment> frags = story.getFrags();
		Intent intent = new Intent(getApplicationContext(), FragmentViewer.class);
		intent.putExtra("fid", frags.get(0).getId());
		startActivity(intent);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story_intro, menu);
		
		return true;
	}
	
	

}
