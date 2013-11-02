package com.example.team04adventure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.team04adventure.*;

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
		Bundle extras = getIntent().getExtras();
		Uname = extras.getString("uname");

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
		
		final ListView storyListView = (ListView) findViewById(R.id.cachedlist);
		ArrayList<Story> storylist = new ArrayList<Story>();
		StorageManager sm = new StorageManager(this);
		
		/** Open DB connection and retrieve all of 
		    the cached stories. **/
		
		storylist = sm.getAll();
		
		
		storyListView.setAdapter(new StoryListAdapter(this, storylist));
		storyListView.setOnItemClickListener(new OnItemClickListener() {
        
			/** When a story is selected **/
			@Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Story s = (Story) storyListView.getItemAtPosition(position);
                
				Intent intent = new Intent(getApplicationContext(), StoryIntro.class);
				intent.putExtra("uname", Uname);
				intent.putExtra("id", s.getId());
        		startActivity(intent);
             }

        });
		
		
		final ListView ostoryListView = (ListView) findViewById(R.id.cachedlist);
		ArrayList<Story> ostorylist = new ArrayList<Story>();
		
		
		JSONparser jp = new JSONparser();
		
		ArrayList<Story> stories = jp.getAll();

		storyListView.setAdapter(new StoryListAdapter(this, stories));
		storyListView.setOnItemClickListener(new OnItemClickListener() {
        
			/** When a story is selected **/
			@Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Story s = (Story) ostoryListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), OnlineStoryIntro.class);
        		intent.putExtra("uname", Uname);
                intent.putExtra("id", s.getId());
        		startActivity(intent);
             }

        });
	
	
		
		
	}

	
	public void addStory(View view){
		
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		final EditText input = new EditText(this); 
		 adb.setView(input);
		
		adb.setTitle("Story Title");
		
		adb.setPositiveButton("Create", new DialogInterface.OnClickListener() {  
		    public void onClick(DialogInterface dialog, int whichButton) {  
		        Story story = new Story();
		       story.setTitle(input.getText().toString());
		       Random rg = new Random();
		       int rint = rg.nextInt(100);
		       
		       story.setId(story.getTitle()+rint);
		       story.setAuthor(Uname);
		       JSONparser jp = new JSONparser();
		       
		       try {
				jp.storeStory(story);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		       }  
		     });  
		
		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

	        public void onClick(DialogInterface dialog, int which) {
	           
	            return;   
	        }
	    });
	
	
	}
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.online_story_list, menu);
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
			}
			
			
			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
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

}
