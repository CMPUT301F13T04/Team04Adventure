/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.team04adventure.View;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team04adventure.R;
import com.example.team04adventure.Controller.OnlineStoryList;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

/**
 * StoryIntro creates the activity that the user sees when the user selects a story from the cached story list.
 * This fragment allows the user to view the story title and synopsis, and choose whether to play or delete the story.
 * 
 * @author Team04Adventure
 */
public class StoryIntro extends Activity {

	TextView 	storyTitle,
				storyAuthor,
				storySynop;
	String id;
	Story story;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_intro);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getString("id");
		StorageManager sm = new StorageManager(this);
		
		
	
		story = sm.getStory(id);
		
		
		storyTitle = (TextView) findViewById(R.id.StoryTitle);
		storyTitle.append(story.getTitle());
		storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
		storyAuthor.append("By: " + story.getAuthor());
		storySynop = (TextView) findViewById(R.id.StorySynop);
		storySynop.append(story.getSynopsis());
	}

	/**
	 * Removes the selected story from cache.
	 * @param view the current view.
	 */
	public void removeFromCache(View view){
		Intent i;
		StorageManager sm = new StorageManager(this);
		
		/** Open DB connection and deletes 
	     the note. **/
		story = sm.getStory(id);
		sm.deleteStory(story);
		
		
		i = new Intent(StoryIntro.this, OnlineStoryList.class);
		
		startActivity(i);
		
		
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
		intent.putExtra("sid", story.getId());
		intent.putExtra("flag", "offline");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);
		}
	}
	
	
	private void help() {
		String helpText = "Shows the story's title, author and synopsis. The buttons from left to right are 'Play story', " +
				"and 'Delete story'.";
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		LinearLayout lila1= new LinearLayout(this);
	    lila1.setOrientation(1);
	    
	    final TextView helpTextView = new TextView(this);
	    helpTextView.setText(helpText);
	    lila1.addView(helpTextView);
	    adb.setView(lila1);
	    adb.setTitle("Help");
	    
	    adb.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story_intro, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		View view = new View(this);
		switch (item.getItemId()) {
			case R.id.play_menu:
				playStory(view);
				return true;
			case R.id.delete_menu:
				removeFromCache(view);
				return true;
			case R.id.help:
				help();
				return true;
		}
		return true;
	}

}
