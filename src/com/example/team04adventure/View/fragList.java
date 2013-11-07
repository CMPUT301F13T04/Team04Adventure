package com.example.team04adventure.View;



import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.FragAdapter;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.Story;


/**
 * fragList creates the list of fragments that exist in a story.
 * @author Team04Adventure
 */
public class fragList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frag_list);
		
	  Bundle extras = getIntent().getExtras();
		String id = extras.getString("id");
		
		final ListView fragListView = (ListView) findViewById(android.R.id.list);
		ArrayList<Frag> fraglist = new ArrayList<Frag>();
		
		JSONparser jp = new JSONparser();
		
		final Story story = jp.getStory(id);
		
		fraglist = story.getFrags();
		
		fragListView.setAdapter(new FragAdapter(this, fraglist));
		fragListView.setOnItemClickListener(new OnItemClickListener() {
        
			/** When a story is selected **/
			@Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Frag f = (Frag) fragListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), EditFragment.class);
                intent.putExtra("sid", story.getId());
				intent.putExtra("fid", f.getId());
				intent.putExtra("ftitle", f.getTitle());
				intent.putExtra("fbody", f.getBody());
        		startActivity(intent);
             }

        });
	
	}
}
