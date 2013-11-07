package com.example.team04adventure.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team04adventure.R;
import com.example.team04adventure.Controller.OnlineStoryList;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

/**
 * OnlineStoryIntro creates the activity that the user enters when the user selects a story from the online story list.
 * This fragment allows the user to play the story, add fragments to the story, edit the story, cache the story and delete the story.
 * 
 * @author Team04Adventure
 */
public class OnlineStoryIntro extends Activity {

	TextView 	storyTitle,
	storyAuthor, storySynop;
	String sid;
	Story story;
	String Uname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_online_story_intro);

		Bundle extras = getIntent().getExtras();
		sid = extras.getString("id");
		Uname = MainActivity.username;


		JSONparser jp = new JSONparser();


		story = jp.getStory(sid);

		storyTitle = (TextView) findViewById(R.id.StoryTitle);
		storyTitle.append(story.getTitle());
		storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
		storyAuthor.append(story.getAuthor());
		storySynop = (TextView) findViewById(R.id.StorySynop);
		storySynop.append(story.getSynopsis());
		
		
	}

	/**
	 * Enters the first fragment of the story.
	 * @param view the current view.
	 */
	public void playStory(View view){

		ArrayList<Frag> frags = story.getFrags();
		if (story.getFrags().isEmpty()) {
			Toast.makeText(getBaseContext(), "This story has no fragments", Toast.LENGTH_LONG).show();
			}
		else {
		Intent intent = new Intent(getApplicationContext(), FragmentViewer.class);
		
		intent.putExtra("fid", frags.get(0).getId());
		startActivity(intent);
		}
	}

	/**
	 * Adds a new fragment to the list of fragments that the story owns.
	 * @param view the current view.
	 */
	public void addFragment(View view){

		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		LinearLayout lila1= new LinearLayout(this);
	    lila1.setOrientation(1);
	   
	    final EditText titleinput = new EditText(this); 
	    final EditText bodyinput = new EditText(this);
	    titleinput.setHint("Title");
	    bodyinput.setHint("Fragment body text");
	    lila1.addView(titleinput);
	    lila1.addView(bodyinput);
	    adb.setView(lila1);

		adb.setTitle("New Fragment");


		adb.setNegativeButton("Create", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				
				String ftitle = titleinput.getText().toString();
				Random rg = new Random();
				int rint = rg.nextInt(100);
				String fbody = bodyinput.getText().toString();
				String fid = ftitle.replace(" ","")+rint;

				Intent intent = new Intent(OnlineStoryIntro.this, EditFragment.class);
				intent.putExtra("sid", story.getId());
				intent.putExtra("fid", fid);
				intent.putExtra("ftitle", ftitle);
				intent.putExtra("fbody", fbody);
				
				startActivity(intent);




			}  
		});  

		adb.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				return;   
			}
		});
		
		adb.show();
	
	}
	
	/**
	 * Lets the user select a fragment to edit.
	 * @param view the current view.
	 */
	public void editStory(View view){
		Intent intent = new Intent(this, fragList.class);
		intent.putExtra("id", sid);
		startActivity(intent);
		
	}

	/**
	 * Caches the story in the device memory.
	 * @param view the current view.
	 */
	public void cacheStory(View view) {

		String cache = "Story Cached.";
		String cantcache = "This Story is already cached!";
		JSONparser parser = new JSONparser();
		StorageManager sm = new StorageManager(this);

		Story s = parser.getStory(sid);
		
		System.out.println(s.getFrags().size());

		if(sm.storyExists(s.getId()))
			Toast.makeText(getBaseContext(), cantcache, Toast.LENGTH_LONG).show();	

		else{ 
			sm.addStory(s);
			Toast.makeText(getBaseContext(), cache, Toast.LENGTH_LONG).show();
		}

	

	}
	
	/**
	 * Deletes the story from the server.
	 * @param view the current view.
	 */
	public void deleteStory(View view){	
		JSONparser parser = new JSONparser();
		
		parser.deleteStory(story);
		Intent intent = new Intent(this, OnlineStoryList.class);
		startActivity(intent);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story_intro, menu);

		return true;
	}



}
