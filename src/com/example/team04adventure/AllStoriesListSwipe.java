package com.example.team04adventure;

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

public class AllStoriesListSwipe extends Fragment {

	ListView storyListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.activity_all_stories_list_swipe, container, false);
		storyListView = (ListView) rootView.findViewById(R.id.story_list);


		return rootView;
	}

	public void onResume() {
//		ArrayList<Story> ostorylist = new ArrayList<Story>();


		JSONparser jp = new JSONparser();

		ArrayList<Story> stories = new ArrayList<Story>();
		stories.add(jp.getStory("10"));
		
//		String id = "69696";
//		
//	    /* Simple choice */
//
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

			/** When a story is selected **/
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