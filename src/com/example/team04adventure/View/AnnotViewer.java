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
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.Annotation;
import com.example.team04adventure.Model.AnnotationAdapter;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

public class AnnotViewer extends Activity {

	String 			fid, 
					sid;
	String 			online;
	JSONparser 		jp;
	StorageManager 	sm;
	ArrayList<Frag> fragList = new ArrayList <Frag>();
	ListView annotListView;
	ArrayList<Annotation> annotList;
	Integer index;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_annot_viewer);

		Bundle extras = getIntent().getExtras();
		fid = extras.getString("fid");
		sid = extras.getString("sid");
		online = extras.getString("online");

		annotListView = (ListView) findViewById(R.id.annotListView);
		annotList = new ArrayList<Annotation>();

	}
	
	public void onResume() {
		super.onResume();
		if (online.equals("online")) {
			try {
				index = Integer.valueOf(-2);
				jp = new JSONparser();
				Story story = new JSONparser().execute(index, sid).get().get(0);
				Frag f = story.getFrag(fid);
//				Annotation a = new Annotation();
//				a.setAuthor("DEFAULT");
//				a.setReview("THE REVIEW");
//				f.addAnnotations(a);
				annotList = f.getAnnotations();
				System.out.println("ITS OK");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("FUCKED UP 1");
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("FUCKED UP 2");
			} catch (Exception e) {
				System.out.println("GENERAL ERROR");
			}
		} else {
			sm = new StorageManager(this);
			Frag f = sm.getFrag(fid);
			annotList = f.getAnnotations();
		}

		annotListView.setAdapter(new AnnotationAdapter(this, annotList));
	}
	
	public void addAnnot(View view) {
		Intent intent = new Intent(getApplicationContext(), EditCreateAnnot.class);
		intent.putExtra("sid", sid);
		intent.putExtra("fid", fid);
		intent.putExtra("online", online);
		startActivity(intent);
	}

	private void help() {
		String helpText = "View added annotations or add a new one with the add button";
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.help:
				help();
				return true;
		}
		return true;
	}

}
