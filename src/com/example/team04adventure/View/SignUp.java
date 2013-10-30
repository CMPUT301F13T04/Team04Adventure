package com.example.team04adventure.View;

import com.example.team04adventure.R;
import com.example.team04adventure.R.id;
import com.example.team04adventure.R.layout;
import com.example.team04adventure.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends Activity {

	private EditText regUsernameText; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		regUsernameText = (EditText) findViewById(R.id.username);
		Button Register = (Button) findViewById(R.id.register);
		
		Register.setOnClickListener(new View.OnClickListener()
		{
				
			public void onClick(View v)
			{
//ADD CHECK TO MAKE SURE USERNAME DOES NOT ALREADY EXIST
//ADD STUFF HERE TO PUT THE USERNAME IN THE DB - from regUsernameText
				Intent i = new Intent(SignUp.this, MainActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

}
