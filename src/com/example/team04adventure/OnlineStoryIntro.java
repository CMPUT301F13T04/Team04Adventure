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
	long id;
	Story story;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_story_intro);

Bundle extras = getIntent().getExtras();
id = extras.getLong("id");


JSONparser jp = new JSONparser();


Story s = jp.getStory(""+id);


story = s;


storyTitle = (TextView) findViewById(R.id.StoryTitle);
storyTitle.append(s.getTitle());
storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
storyAuthor.append(s.getAuthorString());
}


public void playStory(View view){

ArrayList<Frag> frags = story.getFrags();
Intent intent = new Intent(getApplicationContext(), FragmentViewer.class);
System.out.println(frags.get(0).getTitle());
System.out.println(frags.get(0).getId());
intent.putExtra("fid", frags.get(0).getId());
startActivity(intent);

}

public void cacheStory(View view) {
		
		JSONparser parser = new JSONparser();
		StorageManager sm = new StorageManager(this);
		
		Story s = parser.getStory("" +id);
		sm.addStory(s);
		
		if (s != null) {
			String message = "Story Cached.";
			Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
		}
	}


@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.story_intro, menu);

return true;
}

	
	
}
