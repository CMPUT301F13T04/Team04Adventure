package com.example.team04adventure;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OnlineStoryIntro extends Activity {

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


JSONparser jp = new JSONparser();


Story s = jp.getStory(id);


story = s;


storyTitle = (TextView) findViewById(R.id.StoryTitle);
storyTitle.append(s.getTitle());
storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
storyAuthor.append(s.getAuthorString());
}


public void playStory(View view){

ArrayList<Frag> frags = story.getFrags();
Intent intent = new Intent(getApplicationContext(), FragmentViewer.class);
intent.putExtra("fid", frags.get(0).getId());
startActivity(intent);

}

public void cacheStory(View view) {
		
		String cache = "Story Cached.";
		String cantcache = "This Story is already cached!";
		JSONparser parser = new JSONparser();
		StorageManager sm = new StorageManager(this);
		
		Story s = parser.getStory(id);
		
		if(sm.storyExists(s.getId())
			Toast.makeText(getBaseContext(), cantcache, Toast.LENGTH_LONG).show();	
				
		else{ 
			sm.addStory(s);
			Toast.makeText(getBaseContext(), cache, Toast.LENGTH_LONG).show();
		}
		
		
	}


@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.story_intro, menu);

return true;
}

	
	
}
