package com.example.team04adventure.View;

import com.example.team04adventure.R;
import com.example.team04adventure.R.layout;
import com.example.team04adventure.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FragmentViewer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_viewer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_viewer, menu);
		return true;
	}

}
