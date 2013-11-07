package com.example.team04adventure.View;



import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.example.team04adventure.Model.Media;
import com.example.team04adventure.Model.StorageManager;

/**
 * FragmentViewer creates the activity that lets the user view the contents of the fragment.
 * @author Team04Adventure
 */
public class FragmentViewer extends Activity {
	// This is the title and body fields that are allocated at runtime
	TextView 	fragTitle,
				fragAuthor,
				fragBody;
	// The list of next choices
	ArrayList<Choice> choices;
	// The adapter for choices
	ArrayList<Media> fragImages;
	LinearLayout imageLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_viewer);

		// Extract the supplied IDs from the intent. These are supplied
		// when a choice is selected from another fragment, or from the
		// StoryIntro.java class. They have to be supplied again before
		// you can go to another fragment.
		Bundle extras = getIntent().getExtras();
		String fragID = extras.getString("fid");
		final ListView choiceListView = (ListView) findViewById(R.id.ChoiceList);
		
		// Initialize the list of choices for that frag
		choices = new ArrayList<Choice>();
		// Get the fragment, and then assign the choices
		StorageManager sm = new StorageManager(this);
		
		Frag f = sm.getFrag(fragID);

		choices = f.getChoices();
		// Set the title and body fields in the XML file
		fragTitle = (TextView) findViewById(R.id.FragTitle);
		fragTitle.append(f.getTitle());
		fragAuthor = (TextView) findViewById(R.id.FragAuthor);
		fragAuthor.append(f.getAuthor());
		fragBody = (TextView) findViewById(R.id.FragBody);
		fragBody.append(f.getBody());
		
		fragImages = f.getImages();
		imageLayout = (LinearLayout) findViewById(R.id.image_layout);
		for (int i=0; i < fragImages.size(); i++) {
			ImageView image = new ImageView(FragmentViewer.this);
			Media mediaImage = fragImages.get(i);
			Bitmap bitmap = mediaImage.getMedia();
			image.setImageBitmap(bitmap);
			imageLayout.addView(image);
		}
		
		choiceListView.setAdapter(new FragChoiceAdapter(this, choices));
		
		choiceListView.setOnItemClickListener(new OnItemClickListener() {
        
			/** When a story is selected **/
			@Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Choice c = (Choice) choiceListView.getItemAtPosition(position);
               /** Need to call another instance of this class here? Just the fragmentID of the child will be brought in.*/
				Intent intent = new Intent(getApplicationContext(), FragmentViewer.class);
        		intent.putExtra("fid", c.getChild());
        		startActivity(intent);
             }

			
        });
	

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_viewer, menu);
		return true;
	}


}
