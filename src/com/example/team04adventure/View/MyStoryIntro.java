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
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
 * MyStoryIntro creates the activity that the user enters when the user selects
 * a story from the online story list. This fragment allows the user to play the
 * story, add fragments to the story, edit the story, cache the story and delete
 * the story.
 * 
 * @author Team04Adventure
 */

public class MyStoryIntro extends Activity {

	TextView storyTitle, storyAuthor, storySynop;
	String sid;
	Story story;
	String Uname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_story_intro);

		Bundle extras = getIntent().getExtras();
		sid = extras.getString("id");
		Uname = MainActivity.username;

		StorageManager sm = new StorageManager(this);

		story = sm.getStory(sid);

		storyTitle = (TextView) findViewById(R.id.StoryTitle);
		storyTitle.append(story.getTitle());
		storyAuthor = (TextView) findViewById(R.id.StoryAuthor);
		storyAuthor.append(story.getAuthor());
		storySynop = (TextView) findViewById(R.id.StorySynop);
		storySynop.append(story.getSynopsis());

	}

	/**
	 * Enters the first fragment of the story.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void playStory(View view) {

		ArrayList<Frag> frags = story.getFrags();
		if (story.getFrags().isEmpty()) {
			Toast.makeText(getBaseContext(), "This story has no fragments",
					Toast.LENGTH_LONG).show();
		} else {
			Intent intent = new Intent(getApplicationContext(),
					FragmentViewer.class);

			intent.putExtra("fid", frags.get(0).getId());
			intent.putExtra("sid", story.getId());
			intent.putExtra("flag", "my");
			startActivity(intent);
		}
	}

	/**
	 * Adds a new fragment to the list of fragments that the story owns.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void addFragment(View view) {

		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		LinearLayout lila1 = new LinearLayout(this);
		lila1.setOrientation(1);

		final EditText titleinput = new EditText(this);
		final EditText bodyinput = new EditText(this);
		titleinput.setHint("Title");
		bodyinput.setHint("Fragment body text");
		lila1.addView(titleinput);
		lila1.addView(bodyinput);
		adb.setView(lila1);

		adb.setTitle("New Fragment");

		adb.setPositiveButton("Create", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				String ftitle = titleinput.getText().toString();
				Random rg = new Random();
				int rint = rg.nextInt(100);
				String fbody = bodyinput.getText().toString();
				String fid = ftitle.replace(" ", "") + rint;

				Intent intent = new Intent(MyStoryIntro.this,
						EditFragment.class);
				intent.putExtra("sid", story.getId());
				intent.putExtra("fid", fid);
				intent.putExtra("ftitle", ftitle);
				intent.putExtra("fbody", fbody);
				intent.putExtra("flag", "my");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				startActivity(intent);
			}
		});

		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				return;
			}
		});

		adb.show();
	}

	/**
	 * Lets the user select a fragment to edit.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void editStory(View view) {
		Intent intent = new Intent(this, fragList.class);
		intent.putExtra("id", sid);
		intent.putExtra("link", 0);
		intent.putExtra("flag", "my");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);

	}

	/**
	 * Uploads the story onto the server.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void publishStory(View view) {

		JSONparser parser = new JSONparser();
		StorageManager sm = new StorageManager(this);

		String publish = "This Story has been published.";
		String republish = "This Story has already been published. You can access it online.";

		Story s = sm.getStory(sid);

		if (parser.checkStory(s)) {

			Toast.makeText(getBaseContext(), republish, Toast.LENGTH_LONG)
					.show();
		} else {
			parser.addStory(s);
			Toast.makeText(getBaseContext(), publish, Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * Deletes the story from the server.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void deleteStory(View view) {

		StorageManager sm = new StorageManager(this);

		sm.deleteStory(story);

		Intent intent = new Intent(this, OnlineStoryList.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();

	}

	/**
	 * Shows the help information for this fragment.
	 */
	private void help() {
		String helpText = "Shows the story's title, author and synopsis. The buttons from left to right are 'Play story', "
				+ "'Add new fragment', 'Edit a fragment', 'Publish story', and 'Delete story'.";
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		LinearLayout lila1 = new LinearLayout(this);
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
		getMenuInflater().inflate(R.menu.my_story_intro, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		View view = new View(this);
		switch (item.getItemId()) {
		case R.id.play_menu:
			playStory(view);
			return true;
		case R.id.add_menu:
			addFragment(view);
			return true;
		case R.id.edit_menu:
			editStory(view);
			return true;
		case R.id.upload_menu:
			publishStory(view);
			return true;
		case R.id.delete_menu:
			deleteStory(view);
			return true;
		case R.id.help:
			help();
			return true;
		}
		return true;
	}

}
