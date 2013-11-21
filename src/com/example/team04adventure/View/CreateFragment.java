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

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.team04adventure.R;

/**
 * CreateFragment creates the activity that is called when the user chooses to create a new fragment.
 * @author Team04Adventure
 */
public class CreateFragment extends Activity {

	private EditText title, body;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_fragment);
		
		title = (EditText)findViewById(R.id.ftitle);
		body = (EditText)findViewById(R.id.fbody);
		title.setMovementMethod(new ScrollingMovementMethod());
		body.setMovementMethod(new ScrollingMovementMethod());
		
		
	}

	/**
	 * Links the fragment to another fragment.
	 * @param view current view.
	 */
	private void link(View view){
		
		
	}
	
	/**
	 * Saves the changes to the fragment.
	 * @param view current view.
	 */
	private void saveFragment(View view){
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}

}
