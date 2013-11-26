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
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team04adventure.R;
import com.example.team04adventure.Controller.OnlineStoryList;

/**
 * MainActivity is the initial login screen that the user sees upon opening the app. The user can enter his or her name to login, 
 * and this will bring the user to the swipe view containing all of the existing stories.
 * 
 * @author Team04Adventure
 */
public class MainActivity extends Activity {
	
	public static String username;
	private EditText UsernameText; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences prefs = getSharedPreferences("prefs",0);
		String prevUsername = prefs.getString("uname", "");
		
		UsernameText = (EditText) findViewById(R.id.username);
		Button Login = (Button) findViewById(R.id.login);
		UsernameText.setText(prevUsername);
		Login.setOnClickListener(new View.OnClickListener() 
		{
				
			public void onClick(View v)
			{
				
				String Uname = UsernameText.getText().toString();

				if ((Uname.equals("")) || Uname.equals(null)){
					
				String validun = "Please enter a valid username.";
				Toast.makeText(getBaseContext(), validun, Toast.LENGTH_LONG).show();
				}
				else {
				Intent i = new Intent(MainActivity.this, OnlineStoryList.class);
				username = Uname;
				SharedPreferences prefs = getSharedPreferences("prefs",0);
				Editor editor = prefs.edit();
				editor.putString("uname",  username);
				editor.commit();
				
				startActivity(i);				
				}
			}
		});
		
				
	}
	
	private void help() {
		String helpText = "Please enter a username and press the login button to proceed.";
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
