package com.example.team04adventure;



import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		
		// Hardcoding a new story
<<<<<<< HEAD
		long id = 75;
		Story s1 = new Story();
=======
		
		User chris = new User("Chris");
		
		Story s = new Story();
		s.setTitle("Big T's Leg Day");
		s.setAuthor(chris);
		
		s.setAuthor(new User("Big T"));
>>>>>>> refs/remotes/origin/master
		
<<<<<<< HEAD
		s1.setTitle("Big T's Leg Day");
		s1.setAuthorString("Big T");
//		s1.setId(id);
		s1.setAuthor(new User("Big T"));
		
		Fragment f1 = new Fragment();
		f1.setAuthor(new User("Mike"));
		f1.setAuthorString("Mike");
		f1.setBody("Test bodyyyyyy");
		f1.setTitle("The Frag of all frags");
		//f1.setId(id);
		//f1.setChoice(null);
		
		s1.addFragment(f1);
=======
		Fragment frag = new Fragment();
		frag.setAuthor(chris);
		frag.setAuthorString("Chris");
		frag.setBody("test");
		
		s.addFragment(frag);
		
		
		
>>>>>>> refs/remotes/origin/master
		
		
		
		final ListView storyListView = (ListView) findViewById(android.R.id.list);
		ArrayList<Story> storylist = new ArrayList<Story>();
		StorageManager sm = new StorageManager(this);
		
		/** Open DB connection and retrieve all of 
		    the cached stories. **/
		
		sm.addStory(s1);
		
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
