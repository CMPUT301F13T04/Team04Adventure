package com.example.team04adventure.View;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
		
		UsernameText = (EditText) findViewById(R.id.username);
		Button Login = (Button) findViewById(R.id.login);
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
				startActivity(i);
				
				
				}
			}
		});
		
				
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
