package com.example.team04adventure.View;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.Story;
import com.example.team04adventure.Model.StoryListAdapter;

/**
 * This class implements the first half of the "home screen". It is responsible for displaying
 * all of the stories in a listview. It represents the left half of the swipe view.
 * 
 * @author Team04Adventure
 */

public class AllStoriesListSwipe extends Fragment {

	// ListView representing the list of stories
	ListView storyListView;
	
	/**
	 * onCreate method, simply assigns the ListView variables to the necessary variables
	 * and returns it to the caller
	 * @param LayoutInflator, ViewGroup, Bundle
	 * @return View
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_all_stories_list_swipe, container, false);
		storyListView = (ListView) rootView.findViewById(R.id.story_list);

		return rootView;
	}

	/**
	 * onResume method, simply assigns sets the adapter and a listener to listen
	 * to user selections of story items.
	 * @param void
	 * @return void
	 */
	
	public void onResume() {
//		ArrayList<Story> ostorylist = new ArrayList<Story>();


		JSONparser jp = new JSONparser();

		ArrayList<Story> stories = new ArrayList<Story>();
		stories = jp.getAll();
		
		//stories.add(jp.getStory("10"));
		
		/*
		 * Code used for testing. Hard-coded story that can be added into the list.
		 * Side-steps the need to use a JSONparser.
		 */
		
//		String id = "69696";
//	    /* Simple fragment */
//	    Frag f = new Frag();
//	    f.setTitle("title");
//	    f.setAuthor("Frag authored by " + id);
//	    f.setBody("This fragment body belongs to frag " + id);
//	    f.setId(id);
//	    /* Simple story */
//	    Story s = new Story();
//	    s.setAuthor("Story authored by " + id);
//	    s.setTitle("Title " + id);
//	    s.setId(id);
//	    s.addFragment(f);
//	    ArrayList<Story> stories = new ArrayList<Story>();
//	    stories.add(s);

		storyListView.setAdapter(new StoryListAdapter(getActivity(), stories));
		storyListView.setOnItemClickListener(new OnItemClickListener() {

			/** When a story is selected, bundle the necessary vars into an intent and begin the
			 * new activity that will bring the user to the online story's intro page.
			 * @param AdapterView, View, int, long
			 * @return void
			 */
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Story s = (Story) storyListView.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(), OnlineStoryIntro.class);
				intent.putExtra("id", s.getId());
				startActivity(intent);
			}

		});
		
		super.onResume();
	}


}	
