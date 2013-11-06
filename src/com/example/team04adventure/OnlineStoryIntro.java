package com.example.team04adventure;

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
import android.widget.TextView;
import android.widget.Toast;

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

	public void addFragment(View view){

		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		final EditText input = new EditText(this); 
		adb.setView(input);

		adb.setTitle("Fragment Title");


		adb.setNegativeButton("Create", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				Frag frag = new Frag();
				frag.setTitle(input.getText().toString());
				Random rg = new Random();
				int rint = rg.nextInt(100);

				frag.setId(frag.getTitle().replace(" ","")+rint);
				frag.setAuthor(Uname);
				story.addFragment(frag);

				JSONparser jp = new JSONparser();

				try {
					jp.storeStory(story);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}




			}  
		});  

		adb.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				return;   
			}
		});
		
		adb.show();
	
	}
	
	
	public void editStory(View view){
		Intent intent = new Intent(this, fragList.class);
		intent.putExtra("id", sid);
		startActivity(intent);
		
	}


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
