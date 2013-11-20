package com.example.team04adventure.View;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.Annotation;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

public class AnnotViewer extends Activity {

	String 	fid, 
	sid;
	String 	online;
	JSONparser jp;
	StorageManager sm;
	ArrayList <Frag> fragList = new ArrayList <Frag>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_annot_viewer);

		Bundle extras = getIntent().getExtras();
		
		fid = extras.getString("fid");
		sid = extras.getString("sid");
		online = extras.getString("online");

		final ListView annotListView = (ListView) findViewById(android.R.id.list);
		ArrayList<Annotation> annotList = new ArrayList<Annotation>();

		if (online.equals("online")) {
			Integer index = Integer.valueOf(-2);
			jp = new JSONparser();
			try {
				final Story story = new JSONparser().execute(index, sid).get().get(0);
				Frag f = story.getFrag(fid);
				annotList = f.getAnnotations();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			sm = new StorageManager(this);
			
			Frag f = sm.getFrag(fid);
			annotList = f.getAnnotations();
			
		}
		//******
//		Annotation a = new Annotation();
//		a.setAuthor(MainActivity.username);
//		a.setReview("THIS IS THE SAMPLE REVIEW");
//		annotList.add(a);
		//****** WE NEED THIS LINE -->
		//annotListView.setAdapter(new AnnotationAdapter(this, annotList));

	}
	
	public void addAnnot(View view) {
		Intent intent = new Intent(getApplicationContext(), EditCreateAnnot.class);
		intent.putExtra("sid", sid);
		intent.putExtra("fid", fid);
		intent.putExtra("online", online);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.annot_viewer, menu);
		return true;
	}

}
