package com.example.team04adventure.View;

import java.util.ArrayList;

import com.example.team04adventure.R;
import com.example.team04adventure.Controller.StorageManager;
import com.example.team04adventure.Controller.StoryListAdapter;
import com.example.team04adventure.Model.Story;
import com.example.team04adventure.R.layout;
import com.example.team04adventure.R.menu;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class OfflineStoryList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline_story_list);
		
		final ListView storyListView = (ListView) findViewById(android.R.id.list);
		ArrayList<Story> storylist = new ArrayList<Story>();
		StorageManager sm = new StorageManager(this);
		
		/** Open DB connection and retrieve all of 
		    the cached stories. **/
		
		
		storylist = sm.getAll();
		
		
		storyListView.setAdapter(new StoryListAdapter(this, storylist));
		storyListView.setOnItemClickListener(new OnItemClickListener() {
        
			/** When a story is selected **/
			@Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Story s = (Story) storyListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), StoryIntro.class);
        		intent.putExtra("id", s.getId());
        		startActivity(intent);
             }

        });
	
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.offline_story_list, menu);
		return true;
	}

}
