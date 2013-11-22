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

package com.example.team04adventure.View;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

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
	Button randomButton;
	SearchView searchView;
	StoryListAdapter allAdapter;
	ProgressDialog mDialog;
	ArrayList<Story> stories;

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
		randomButton = (Button) rootView.findViewById(R.id.randomAllStories);

		searchView = (SearchView) rootView.findViewById(R.id.story_search);
		final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {
				// Do something
				//if (newText.length() > 0) {
					allAdapter.filter(newText);
					return true;
				//} 
				//return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// Do something
				allAdapter.filter(query);
				return true;
			}
		};
		searchView.setOnQueryTextListener(queryTextListener);

		return rootView;
	}

	/**
	 * onResume method, simply assigns sets the adapter and a listener to listen
	 * to user selections of story items.
	 */

	public void onResume() {
		super.onResume();
		//		ArrayList<Story> ostorylist = new ArrayList<Story>();


		//		JSONparser jp = new JSONparser();

		stories = new ArrayList<Story>();
		//		stories = jp.getAll();

		Integer index = Integer.valueOf(-6);
		try {
			stories = new JSONparser().execute(index).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		allAdapter = new StoryListAdapter(getActivity(), stories);
		storyListView.setAdapter(allAdapter);
		storyListView.setOnItemClickListener(new OnItemClickListener() {

			/** When a story is selected, bundle the necessary vars into an intent and begin the
			 * new activity that will bring the user to the online story's intro page.
			 * @param AdapterView, View, int, long
			 * @return void
			 */
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id ) {
				mDialog = new ProgressDialog(v.getContext());
		        mDialog.setMessage("Please wait...");
		        mDialog.show();
				Story s = (Story) storyListView.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(), OnlineStoryIntro.class);
				intent.putExtra("id", s.getId());
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}

		});

		randomButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//goToStory(id);
				Story s = (Story) stories.get(chooseRandom());
				Intent intent = new Intent(getActivity(), OnlineStoryIntro.class);
				intent.putExtra("id", s.getId());
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);

			}
		});		

	}	
	private int chooseRandom() {
		Random ran = new Random();
		return ran.nextInt(stories.size());
	}
	public void onStop(){
		super.onStop();
		mDialog.dismiss();
	}
}
