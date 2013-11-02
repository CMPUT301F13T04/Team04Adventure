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

public class fragList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frag_list);
		
	  Bundle extras = getIntent().getExtras();
		id = extras.getString("id");
		
		final ListView fragListView = (ListView) findViewById(android.R.id.list);
		ArrayList<Frag> fraglist = new ArrayList<Frag>();
		
		JSONparser jp = new JSONparser();
		
		Story story = jp.getStory(id);
		
		fraglist = story.getFrags();
		
		fragListView.setAdapter(new StoryListAdapter(this, fraglist));
		fragListView.setOnItemClickListener(new OnItemClickListener() {
        
			/** When a story is selected **/
			@Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Frag f = (Frag) fragListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), EditFragment.class);
        		intent.putExtra("id", story.getId());
        		startActivity(intent);
             }

        });
	
	}
