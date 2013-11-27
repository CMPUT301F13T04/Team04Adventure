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

package com.example.team04adventure.Model;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team04adventure.R;

/**
 * StoryListAdapter is a custom adapter for the list of stories.
 * 
 * @author Team04Adventure
 */
public class StoryListAdapter extends BaseAdapter {

	private ArrayList<Story> stories;
	ArrayList<Story> storiesClone;

	private LayoutInflater layoutInflater;

	public StoryListAdapter(Context context, ArrayList<Story> stories) {
		this.stories = stories;
		layoutInflater = LayoutInflater.from(context);
		storiesClone = new ArrayList<Story>();
		storiesClone.addAll(stories);
	}

	@Override
	public int getCount() {
		return stories.size();
	}

	@Override
	public Object getItem(int position) {
		return stories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater
					.inflate(R.layout.storylistlayout, null);
			holder = new ViewHolder();
			holder.titleView = (TextView) convertView
					.findViewById(R.id.storytitle);
			holder.authorView = (TextView) convertView
					.findViewById(R.id.storyauthor);
			holder.fragmentView = (TextView) convertView
					.findViewById(R.id.storyfragments);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.titleView.setText(stories.get(position).getTitle());
		holder.authorView.setText("By: " + stories.get(position).getAuthor());
		holder.fragmentView.setText("Fragments: "
				+ stories.get(position).getFrags().size());

		return convertView;
	}

	static class ViewHolder {

		ImageView profilePic;
		TextView titleView;
		TextView authorView;
		TextView fragmentView;

	}

	/**
     * Filters the list based on the query.
     * 
     * @param query string that is being searched.
     */
	public void filter(String query) {
		if (query != null) {
			stories.clear();
			for (Story s : storiesClone) {
				String searchString = s.getTitle().concat(" ")
						.concat(s.getAuthor());
				if (searchString.matches("(?i)(.*)" + query + "(.*)")) {
					this.stories.add(s);
				}
			}
			this.notifyDataSetChanged();
		}
	}
}
