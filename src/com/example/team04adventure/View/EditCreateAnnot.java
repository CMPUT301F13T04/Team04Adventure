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

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.Annotation;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

public class EditCreateAnnot extends Activity {

	String	sid,
			fid;
	String 	online;
	JSONparser jp;
	StorageManager sm;
	
	String 	author,
			review,
			image;
	
	EditText reviewIn;
	
	Button saveButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_create_annot);
		
		Bundle extras = getIntent().getExtras();
		
		sid = extras.getString("sid");
		fid = extras.getString("fid");
		online = extras.getString("online");
		
		reviewIn = (EditText) findViewById(R.id.annotBody);
		saveButton = (Button) findViewById(R.id.save);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_create_annot, menu);
		return true;
	}
	
	
	public void saveAnnot(View view) {
		
		review = reviewIn.getText().toString();
		author = "Default";
		
		Annotation a = new Annotation();
		
		a.setAuthor(author);
		a.setReview(review);
		a.setImage(image);
		
		if (online.equals("online")) {
			Integer index = Integer.valueOf(-2);
			
			Story s;
			try {
				s = new JSONparser().execute(index, sid).get().get(0);
				s.getFrag(fid).addAnnotations(a);
				index = Integer.valueOf(-4);
				new JSONparser().execute(index,sid);
				index = Integer.valueOf(-1);
				new JSONparser().execute(index,sid);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			sm = new StorageManager(this);
			Story s = sm.getStory(sid);
			sm.deleteStory(s);
			
			s.getFrag(fid).addAnnotations(a);
			
			sm.addStory(s);
			
			
		}
			
		finish();
	}

}
