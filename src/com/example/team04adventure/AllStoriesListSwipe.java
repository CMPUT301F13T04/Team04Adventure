package com.example.team04adventure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class AllStoriesListSwipe extends Fragment {

	ListView storyListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.activity_all_stories_list_swipe, container, false);
		storyListView = (ListView) rootView.findViewById(R.id.cachedlist);


		return rootView;
	}

	public void onResume() {
//		ArrayList<Story> ostorylist = new ArrayList<Story>();


		JSONparser jp = new JSONparser();

		ArrayList<Story> stories = jp.getAll();

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
	}

	public void addStory(View view){

		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
		final EditText input = new EditText(getActivity()); 
		adb.setView(input);

		adb.setTitle("Story Title");

		adb.setPositiveButton("Create", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				Story story = new Story();
				story.setTitle(input.getText().toString());
				Random rg = new Random();
				int rint = rg.nextInt(100);

				story.setId(story.getTitle()+rint);
				story.setAuthor(MainActivity.username);
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
}	