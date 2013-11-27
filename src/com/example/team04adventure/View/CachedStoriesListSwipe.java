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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;
import com.example.team04adventure.Model.StoryListAdapter;

/**
 * CachedStoriesListSwipe is the fragment in the swipe view that contains the
 * cached stories. In this fragment, the user can choose one of those cached
 * stories to view.
 * 
 * @author Team04Adventure
 */
public class CachedStoriesListSwipe extends Fragment {

	private ListView storyListView;
	SearchView searchView;
	StoryListAdapter storyAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(
				R.layout.activity_cached_stories_list_swipe, container, false);
		storyListView = (ListView) rootView.findViewById(R.id.cachedlist);

		searchView = (SearchView) rootView
				.findViewById(R.id.cached_story_search);
		final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {

				storyAdapter.filter(newText);
				return true;

			}

			@Override
			public boolean onQueryTextSubmit(String query) {

				storyAdapter.filter(query);
				return true;
			}
		};
		searchView.setOnQueryTextListener(queryTextListener);

		return rootView;
	}

	public void onResume() {
		ArrayList<Story> storylist = new ArrayList<Story>();
		StorageManager sm = new StorageManager(getActivity());

		/*
		 * Open DB connection and retrieve all of the cached stories.
		 */

		storylist = sm.getAll();

		storyAdapter = new StoryListAdapter(getActivity(), storylist);
		storyListView.setAdapter(storyAdapter);
		storyListView.setOnItemClickListener(new OnItemClickListener() {

			/* When a story is selected */
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Story s = (Story) storyListView.getItemAtPosition(position);

				Intent intent = new Intent(getActivity(), StoryIntro.class);

				intent.putExtra("id", s.getId());
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}

		});

		super.onResume();
	}
}
