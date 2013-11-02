package com.example.team04adventure;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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

	
	private void link(View view){
		
		
	}
	
	private void saveFragment(View view){
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}

}
