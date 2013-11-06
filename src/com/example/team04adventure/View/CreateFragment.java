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
