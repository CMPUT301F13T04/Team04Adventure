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
package com.example.team04adventure.Controller;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;
import com.example.team04adventure.View.AllStoriesListSwipe;
import com.example.team04adventure.View.CachedStoriesListSwipe;
import com.example.team04adventure.View.MainActivity;
import com.example.team04adventure.View.MyStoriesListSwipe;

public class OnlineStoryList extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	String Uname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_online_story_list);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}
	
	/**
	 * Shows the help information for this fragment.
	 */
	private void help() {
		String helpText = "All stories are displayed here. Press a story to read it. Online stories contains all" +
				"the stories on the server. My stories contains all the stories written by you." +
				"Cached stories contains all the stories downloaded on your phone that aren't written by you. " +
				"The 'I'm Feeling Lucky!' button chooses a random story for you from the existing online stories." +
				"The 'Add Story' button lets you create a new story, and new stories can only be published by creating a" +
				"new story from here. The 'Sync' button locally mirrors the cached stories with the online stories so that" +
				"the cached stories are updated.";
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
		getMenuInflater().inflate(R.menu.online_story_list, menu);
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
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			//Frag fragment = new DummySectionFragment();
			//Bundle args = new Bundle();
			//args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			//fragment.setArguments(args);
			
			switch (position){
			case 0:
				return new AllStoriesListSwipe();
			case 1:
				return new MyStoriesListSwipe();
			case 2:
				return new CachedStoriesListSwipe();
			}
			
			
			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater
					.inflate(R.layout.fragment_online_story_list_dummy,
							container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	/**
	 * Starts a dialog box which allows the user to create a new story.
	 * 
	 * @param view the current view.
	 */
	public void addStory(View view){
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		LinearLayout lila1= new LinearLayout(this);
	    lila1.setOrientation(1);
	    final EditText titleinput = new EditText(this); 
	    final EditText bodyinput = new EditText(this);
	    titleinput.setHint("Enter the Title here.");
	    bodyinput.setHint("Enter a Synopsis here.");
	    lila1.addView(titleinput);
	    lila1.addView(bodyinput);
	    adb.setView(lila1);
	

		adb.setTitle("New Story");

		adb.setNegativeButton("Create", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				Story story = new Story();
				story.setTitle(titleinput.getText().toString());
				Random rg = new Random();
				int rint = rg.nextInt(100);
				story.setSynopsis(bodyinput.getText().toString());
				story.setId(story.getTitle().replace(" ", "")+rint);
				story.setAuthor(MainActivity.username);
				story.setVersion(1);

				StorageManager sm = new StorageManager(getBaseContext());

				sm.addStory(story);

				Intent intent = new Intent(OnlineStoryList.this, OnlineStoryList.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

				startActivity(intent);				
			}  
		});  

		adb.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				return;   
			}
		});
		
		adb.show();


	}
	
	/**
	 * Updates the cached stories with the stories of their online versions.
	 * 
	 * @param view the current view.
	 */
	public void sync(View view){
		StorageManager sm = new StorageManager(this);
		ArrayList<Story> offlines = sm.getAll();
		
		if(offlines.isEmpty()){
			Toast.makeText(getBaseContext(), "You have no stories to sync..", Toast.LENGTH_LONG).show();	
			
		}
		else{
			
		
		Integer tempIndex = Integer.valueOf(-5);
		ArrayList<Story> onlines = null;
		try {
			onlines = new JSONparser().execute(tempIndex).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Story s : onlines){
			for(Story ss : offlines){
				if(s.getId().equals(ss.getId())){
					if(s.getVersion()>ss.getVersion()){
						sm.deleteStory(ss);
						sm.addStory(s);				
					}
				}					
			}
		}
		
		Intent intent = new Intent(OnlineStoryList.this, OnlineStoryList.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		}	
	}
}
