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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.AdventureApp;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.FragAdapter;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

/**
 * fragList creates the list of fragments that exist in a story.
 * @author Team04Adventure
 */
public class fragList extends Activity {

	String id;
	String flag;
	Story story;
	int link = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frag_list);

		Bundle extras = getIntent().getExtras();
		id = extras.getString("id");
		link = extras.getInt("link");
		flag = extras.getString("flag");

		final ListView fragListView = (ListView) findViewById(android.R.id.list);
		ArrayList<Frag> fraglist = new ArrayList<Frag>();



		if (flag.equals("online")) {
				AdventureApp Adventure = (AdventureApp)getApplicationContext();
				story = Adventure.getCurrentStory();
		} else {
			StorageManager sm = new StorageManager(this);
			story = sm.getStory(id);
		}

		fraglist = story.getFrags();

		fragListView.setAdapter(new FragAdapter(this, fraglist));
		fragListView.setOnItemClickListener(new OnItemClickListener() {

			/* When a story is selected */
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Frag f = (Frag) fragListView.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(), EditFragment.class);
				if (link == 0) {
					intent.putExtra("sid", story.getId());
					intent.putExtra("fid", f.getId());
					intent.putExtra("ftitle", f.getTitle());
					intent.putExtra("fbody", f.getBody());
					intent.putExtra("flag", flag);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
				else {
					intent.putExtra("linkThis", f.getId());
					setResult(Activity.RESULT_OK, intent);
					finish();
				}
			}

		});

	}
}