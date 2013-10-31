package com.example.team04adventure.View;

import com.example.team04adventure.R;
import com.example.team04adventure.R.layout;
import com.example.team04adventure.R.menu;
import com.example.team04adventure.Controller.StorageManager;
import com.example.team04adventure.Model.Choice;
import com.example.team04adventure.Model.Fragment;
import com.example.team04adventure.Model.Story;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentViewer extends Activity {
	// This is the title and body fields that are allocated at runtime
	TextView 	fragTitle,
				fragBody;
	// The list of next choices
	ArrayList<Choice> choices;
	// The adapter for choices

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_viewer);

		// Extract the supplied IDs from the intent. These are supplied
		// when a choice is selected from another fragment, or from the
		// StoryIntro.java class. They have to be supplied again before
		// you can go to another frament.
		Bundle extras = getIntent().getExtras();
		long storyID = extras.getLong("storyID");
		long fragID = extras.getLong("fragID");
		// Initialize the list of choices for that frag
		choices = new ArrayList<Choice>();
		// Get the fragment, and then assign the choices
		Fragment f = getFragment(storyID, fragID);
		choices = f.getChoices();
		// Set the title and body fields in the XML file
		fragTitle = (TextView) findViewById(R.id.FragTitle);
		fragTitle.append(f.getTitle());
		fragBody = (TextView) findViewById(R.id.FragBody);
		fragBody.append(f.getBody());
		
		// Set up the list adapter to the listview associated with the 
		// frag choices.
		
		


	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_viewer, menu);
		return true;
	}

	/*
	 * This method is used to return the right story fragment to be displayed.
	 * It takes the story ID and the frag ID as args, and returns the fragment
	 * object defined by the tuple. It should ALWAYS return a fragment, so ignore
	 * the null return at the end there...
	 */	
	private Fragment getFragment(long Sid, long Fid) {
		// Because the SQL functions return full arraylists of the story and
		// fragment objects, these two arraylists will hold them so they can
		// be searched through
		ArrayList<Story> stories = new ArrayList<Story>();
		ArrayList<Fragment> frags = new ArrayList<Fragment>();

		// Create the SQL retriever object
		StorageManager sm = new StorageManager(this);
		// Get all of the stories
		stories = sm.getAll();
		// Iterate through all of the stories to find the one that corresponds
		// to the given ID. There should ALWAYS be one that corresponds to the correct
		// ID, so the case where one is not found is never checked. If that happens
		// shit will get crazy
		for (int i = 0; i < stories.size(); i++) {
			if (stories.get(i).getId() == Sid)
				// Once the correct story is found, frags gets that stories list
				// of associated story fragments
				frags = stories.get(i).getFrags();
		}
		// Iterate through all the frags until the correct matching ID is found. Same
		// as above, it should always return a frag.
		for (int i = 0; i < frags.size(); i++) {
			if (frags.get(i).getId() == Fid)
				// Once found, the fragment object will be returned.
				return frags.get(i);
		}
		// Due to the implementation of the app, execution should never reach this point,
		// and null should never be returned. This is just here so that the compiler
		// will shutup about not having a definite return value.
		return null;

	}

}
