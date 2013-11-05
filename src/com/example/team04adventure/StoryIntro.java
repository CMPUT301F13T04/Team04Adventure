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
	String id;
	Story story;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_intro);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getString("id");
		StorageManager sm = new StorageManager(this);
		
		System.out.println("ID: "+ id);
		
	
		story = sm.getStory(id);
		
		
		storyTitle = (TextView) findViewById(R.id.StoryTitle);
		storyTitle.append(story.getTitle());
		storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
		storyAuthor.append(story.getAuthor());
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
		               "com.example.team04adventure.OnlineStoryList");
		startActivity(i);
		
		
	}
	
	public void playStory(View view){
		
		ArrayList<Frag> frags = story.getFrags();
		if (story.getFrags().isEmpty()) {
			System.out.println("empty");
		}
		Intent intent = new Intent(getApplicationContext(), FragmentViewer.class);
//		System.out.println(frags.get(0).getTitle());
//		System.out.println(frags.get(0).getId());
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
