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
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;
import com.example.team04adventure.Model.StoryListAdapter;

/**
 * MyStoriesListSwipe is the fragment in the swipe view that contains the cached stories. In this fragment, the user can 
 * choose one of those cached stories to view.
 * 
 * @author Team04Adventure
 */
public class MyStoriesListSwipe extends Fragment {

	private ListView storyListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_my_stories_list_swipe, container, false);
		storyListView = (ListView) rootView.findViewById(R.id.cachedlist);

		return rootView;
	}

	public void onResume() {
		ArrayList<Story> storylist = new ArrayList<Story>();
		StorageManager sm = new StorageManager(getActivity());

		/** Open DB connection and retrieve all of 
    	the cached stories. **/
		
		storylist = sm.getAll();


		storyListView.setAdapter(new StoryListAdapter(getActivity(), storylist));
		storyListView.setOnItemClickListener(new OnItemClickListener() {

			/** When a story is selected **/
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Story s = (Story) storyListView.getItemAtPosition(position);

				Intent intent = new Intent(getActivity(), StoryIntro.class);
//				intent.putExtra("uname", Uname);
				intent.putExtra("id", s.getId());
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}

		});
		
		super.onResume();
	}
}

