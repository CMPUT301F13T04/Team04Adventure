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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.Choice;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.FragChoiceAdapter;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.Media;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

/**
 * FragmentViewer creates the activity that lets the user view the contents of the fragment.
 * @author Team04Adventure
 */
public class FragmentViewer extends Activity {
	// This is the title and body fields that are allocated at runtime
	TextView 	fragTitle,
	fragAuthor,
	fragBody;
	ImageView profilePic;
	// The list of next choices
	ArrayList<Choice> choices;
	// The adapter for choices
	ArrayList<Media> fragImages;
	LinearLayout imageLayout;
	String flag;
	String storyID;
	String fragID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_viewer);

		// Extract the supplied IDs from the intent. These are supplied
		// when a choice is selected from another fragment, or from the
		// StoryIntro.java class. They have to be supplied again before
		// you can go to another fragment.
		Bundle extras = getIntent().getExtras();
		fragID = extras.getString("fid");
		storyID = extras.getString("sid");
		flag = extras.getString("flag");
		JSONparser jp = new JSONparser(); 
		final ListView choiceListView = (ListView) findViewById(R.id.ChoiceList);
		
		// Initialize the list of choices for that frag
		choices = new ArrayList<Choice>();
		// Get the fragment, and then assign the choice
		Frag f = new Frag();
		StorageManager sm = new StorageManager(this);
		if (flag.equals("online")){
			Story s = jp.getStory(storyID);
			ArrayList<Frag> frags = s.getFrags();
			for(Frag fr: frags){
				if (fr.getId().equals(fragID)){
					f = fr;
					break;
				}
			}
		} else {
			f = sm.getFrag(fragID);
		}

		// Populate the choice list with the fragments choices
		choices = f.getChoices();

		if (choices.size() > 0) {
			// Create a list of possible child IDs
			String childIds[] = new String[choices.size()];
			// Populate the list with possible IDs
			for (int i = 0; i < choices.size(); i++) {
				childIds[i] = choices.get(i).getChild();
			}
			Random r = new Random();
			int ranId = r.nextInt(choices.size());
			// Create the random choice
			Choice ranChoice = new Choice();
			ranChoice.setID(-1);
			ranChoice.setChild(choices.get(ranId).getChild());
			ranChoice.setBody("Random Choice?");

			// Append the random Choice to the end of the list
			choices.add(ranChoice);
		}
		// Set the title and body fields in the XML file
		fragTitle = (TextView) findViewById(R.id.FragTitle);
		fragTitle.append(f.getTitle());
		fragAuthor = (TextView) findViewById(R.id.FragAuthor);
		fragAuthor.append("By: " + f.getAuthor());
		fragBody = (TextView) findViewById(R.id.FragBody);
		fragBody.append(f.getBody());
		profilePic = (ImageView) findViewById(R.id.profile_pic);
		
		if (f.getProfile().getMedia() != null) {
			Media fragProfile = f.getProfile();
			String cString = fragProfile.getMedia();
			Bitmap bm = Media.decodeBase64(cString);
			profilePic.setImageBitmap(bm);
		}

		fragImages = f.getImages();
		imageLayout = (LinearLayout) findViewById(R.id.image_layout);
		for (int i=0; i < fragImages.size(); i++) {
			Media mediaImage = fragImages.get(i);
			String convertedString = mediaImage.getMedia();
			if (convertedString != null) {
				ImageView image = new ImageView(FragmentViewer.this);
				Bitmap bitmap = Media.decodeBase64(convertedString);
				image.setImageBitmap(bitmap);
				imageLayout.setGravity(Gravity.CENTER);
				imageLayout.addView(image);
			}
		}

		choiceListView.setAdapter(new FragChoiceAdapter(this, choices));

		choiceListView.setOnItemClickListener(new OnItemClickListener() {

			/** When a story is selected **/
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Choice c = (Choice) choiceListView.getItemAtPosition(position);
				/** Need to call another instance of this class here? Just the fragmentID of the child will be brought in.*/
				Intent intent = new Intent(getApplicationContext(), FragmentViewer.class);
				if(flag.equals("online")){
					intent.putExtra("flag", "online");
					intent.putExtra("sid", storyID); 
				}else{
					intent.putExtra("flag", "offline");
				}
				intent.putExtra("fid", c.getChild());
				startActivity(intent);
				/* This line terminates the last fragment so that you
				 * can't "cheat" and go back to the previous fragment.
				 * Do we want this feature?
				 */
				finish();
			}


		});


	}
	
	public void showAnnots(View view) {
		Intent intent = new Intent(getApplicationContext(), AnnotViewer.class);
		intent.putExtra("sid", storyID);
		intent.putExtra("fid", fragID);
		intent.putExtra("online", flag);
		startActivity(intent);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_viewer, menu);
		return true;
	}


}
