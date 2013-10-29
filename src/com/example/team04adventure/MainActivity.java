package com.example.team04adventure;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText UsernameText; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		UsernameText = (EditText) findViewById(R.id.username);
		Button Login = (Button) findViewById(R.id.login);
		Button OfflineMode = (Button) findViewById(R.id.offline);
		Button SignUp = (Button) findViewById(R.id.signup);
		
		Login.setOnClickListener(new View.OnClickListener()
		{
				
			public void onClick(View v)
			{
				
//FIX/ADD CONDITIONS AND CHECK IF LOGIN IS VALID HERE~~~
				String Uname = UsernameText.getText().toString();
				if (Uname != "valid username"){
					
				String message = "Please enter a valid username.";
				Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
				}
				else {
				//Intent i = new Intent(MainActivity.this, OnlineStoryList.class);
				//startActivity(i);
				}
			}
		});
		
		OfflineMode.setOnClickListener(new View.OnClickListener()
		{
				
			public void onClick(View v)
			{
				Intent i = new Intent(MainActivity.this, OfflineStoryList.class);
				startActivity(i);
			}
		});
		
		SignUp.setOnClickListener(new View.OnClickListener()
		{
				
			public void onClick(View v)
			{
				Intent i = new Intent(MainActivity.this, SignUp.class);
				startActivity(i);
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
