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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.AdventureApp;
import com.example.team04adventure.Model.Choice;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.Media;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

/**
 * EditFragment creates the activity screen displayed when the user chooses to
 * edit an existing fragment of the story. This fragment allows the user to
 * upload images, open the camera to take images which will be uploaded, connect
 * the selected fragment to other existing fragments, and edit the text of a
 * fragment. Parts of the openCamera function were taken from the CMPUT301
 * Camera Demo.
 * 
 * @author Team04Adventure
 */

public class EditFragment extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CREATE_CHOICE = 999;
	private static final int SELECT_PICTURE = 1;
	private static final int SELECT_PROFILE = 101;

	private static final int IMAGE_MAX = 250;

	private Button uploadButton;
	private Button cameraButton;
	private Button profileButton;
	private Button linkButton;
	private Button saveButton;

	private EditText fragTitle;
	private EditText fragBody;

	private TextView uploadTextView;
	private ImageView uploadImageView;
	private TextView illustrationTextView;
	private ImageView illustrationImageView;

	private String sid;
	private Uri imageFileUri;
	private StorageManager sm;
	private Frag fragment = new Frag();
	private Story origStory;
	private Story story = new Story();
	private ArrayList<String> idList = new ArrayList<String>();
	ProgressDialog mDialog;
	// "Add New Choice" Flag
	int nc = 0;
	
	String flag;
	String fid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment);

		Bundle extras = getIntent().getExtras();

		nc = extras.getInt("nc");
		sid = extras.getString("sid");
		flag = extras.getString("flag");
		fid = extras.getString("fid");

		sm = new StorageManager(this);
		
		if (flag.equals("online")) {
//			Integer index = Integer.valueOf(-2);
//			try {
				AdventureApp Adventure = (AdventureApp)getApplicationContext();
				story = Adventure.getCurrentStory();

//				story = new JSONparser().execute(index, sid).get().get(0);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
		} else {
			story = sm.getStory(sid);
		}
		origStory = story;
		fragment = story.getFrag(fid);
		if (fragment == null) {
			fragment = new Frag();
			fragment.setId(fid);
			fragment.setTitle(extras.getString("ftitle"));
			fragment.setBody(extras.getString("fbody"));
			fragment.setAuthor(MainActivity.username);
		}

		refreshIdList();

		uploadButton = (Button) findViewById(R.id.upload);
		cameraButton = (Button) findViewById(R.id.camera);
		profileButton = (Button) findViewById(R.id.profile);
		linkButton = (Button) findViewById(R.id.link);
		saveButton = (Button) findViewById(R.id.save);

		fragTitle = (EditText) findViewById(R.id.frag_title);
		fragBody = (EditText) findViewById(R.id.frag_body);

		uploadTextView = (TextView) findViewById(R.id.upload_image_text);
		uploadImageView = (ImageView) findViewById(R.id.upload_image_pic);
		illustrationTextView = (TextView) findViewById(R.id.upload_illustration_text);
		illustrationImageView = (ImageView) findViewById(R.id.upload_illustration_pic);

		fragTitle.setText(fragment.getTitle());
		fragBody.setText(fragment.getBody());
		
		uploadButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				uploadImage();
			}
		});

		cameraButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				openCamera();
			}
		});

		profileButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setProfile();
			}
		});

		linkButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				linkFrag();
			}
		});

		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mDialog = new ProgressDialog(arg0.getContext());
		        mDialog.setMessage("Please wait...");
		        mDialog.show();
				saveFrag();
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode,
			final Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE
				&& resultCode == RESULT_OK) {
			// Saves the bitmap result from the camera into the frag object
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), imageFileUri);
				Bitmap resizedBitmap = resizeImage(bitmap);

				uploadTextView.setText("Image you just added:");
				uploadImageView.setImageBitmap(resizedBitmap);

				Media media = new Media();
				String convertedString = Media.encodeToBase64(resizedBitmap);
				media.setContent(convertedString);
				media.setType("pic");

				fragSaveText();
				fragment.addPicture(media);
				saveToStory();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
			// Saves the bitmap from the selected image to be uploaded into the
			// frag object
			Uri selectedImageUri = data.getData();
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), selectedImageUri);
				Bitmap resizedBitmap = resizeImage(bitmap);

				uploadTextView.setText("Image you just added:");
				uploadImageView.setImageBitmap(resizedBitmap);

				Media media = new Media();
				String convertedString = Media.encodeToBase64(resizedBitmap);
				media.setContent(convertedString);
				media.setType("pic");

				fragSaveText();
				fragment.addPicture(media);
				saveToStory();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (requestCode == SELECT_PROFILE && resultCode == RESULT_OK) {
			Uri selectedImageUri = data.getData();
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), selectedImageUri);
				Bitmap resizedBitmap = resizeImage(bitmap);

				illustrationTextView.setText("Profile picture you just set:");
				illustrationImageView.setImageBitmap(resizedBitmap);

				Media media = new Media();
				String convertedString = Media.encodeToBase64(resizedBitmap);
				media.setContent(convertedString);
				media.setType("pic");

				fragSaveText();
				fragment.setIllustration(media);
				saveToStory();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (requestCode == CREATE_CHOICE && resultCode == RESULT_OK) {

			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			LinearLayout lila1 = new LinearLayout(this);
			lila1.setOrientation(1);
			final EditText choiceTitle = new EditText(this);
			choiceTitle.setHint("Enter the Title here.");
			lila1.addView(choiceTitle);
			adb.setView(lila1);

			adb.setTitle("New Choice");

			adb.setPositiveButton("Create",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {

							int listIndex = idList.indexOf(fragment.getId());
							if (listIndex == -1) {
								Choice c = new Choice();
								c.setBody(choiceTitle.getText().toString());
								c.setChild(data.getStringExtra("linkThis"));
								fragSaveText();
								fragment.setChoice(c);
								story.addFragment(fragment);
							} else {
								Choice c = new Choice();
								c.setBody(choiceTitle.getText().toString());
								c.setChild(data.getStringExtra("linkThis"));
								fragment = story.getFrags().get(listIndex);
								fragment.setChoice(c);
								story.deleteFrag(fragment.getId());
								story.addFragment(fragment, listIndex);

							}
							refreshIdList();

						}
					});

			adb.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {

							return;
						}
					});

			adb.show();

		}

	}

	/**
	 * Opens the camera application and prepares to store the captured image in
	 * a file.
	 */
	public void openCamera() {
		// Opens the camera app and stores the resulting image as a jpg file in
		// the /team04adventure in the external memory.

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String folder = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/team04adventure/pic";
		File folderF = new File(folder);
		if (!folderF.exists()) {
			folderF.mkdir();
		}

		String imageFilePath = folder + "/"
				+ String.valueOf(System.currentTimeMillis()) + ".jpg";
		File imageFile = new File(imageFilePath);
		imageFileUri = Uri.fromFile(imageFile);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	/**
	 * Opens the image picker for the user to add to the fragment.
	 */
	public void uploadImage() {
		// Starts the image picker for the user to choose a picture to add to
		// the fragment
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				SELECT_PICTURE);
	}

	/**
	 * Opens the activity that contains all existing fragments to allow the user
	 * to choose one to make a connection to.
	 */
	public void setProfile() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Profile"),
				SELECT_PROFILE);
	}

	public void linkFrag() {
		Intent intent = new Intent(getApplicationContext(), fragList.class);
		intent.putExtra("id", sid);
		intent.putExtra("link", 0);
		intent.putExtra("link", 1);
		intent.putExtra("flag", flag);
		startActivityForResult(intent, CREATE_CHOICE);
	}

	/**
	 * Saves the current text in the main TextView as the body text of the
	 * fragment.
	 */
	public void saveFrag() {
		// Saves the changes to the fragment text

		refreshIdList();
		fragSaveText();
		saveToStory();

		Toast.makeText(getApplicationContext(), "Fragment saved!",
				Toast.LENGTH_LONG).show();
		Intent intent = new Intent();
		if (flag.equals("online")) {
			intent = new Intent(this, OnlineStoryIntro.class);
		} else {
			intent = new Intent(this, MyStoryIntro.class);
		}
		intent.putExtra("id", sid);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);

	}

	public Bitmap resizeImage(Bitmap bitmap) {
		float width = bitmap.getWidth();
		float height = bitmap.getHeight();
		float scale = 1;
		if (width >= height) {
			scale = IMAGE_MAX / width;
		} else if (width < height) {
			scale = IMAGE_MAX / height;
		}
		Bitmap resizedBitmap = null;
		if (scale <= 1) {
			float newWidth = width * scale;
			float newHeight = height * scale;
			int newWidthInt = (int) newWidth;
			int newHeightInt = (int) newHeight;
			resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidthInt,
					newHeightInt, false);
		} else {
			resizedBitmap = bitmap;
		}
		return resizedBitmap;
	}

	public void fragSaveText() {
		String fragTitleString = fragTitle.getText().toString();
		String fragBodyString = fragBody.getText().toString();
		fragment.setTitle(fragTitleString);
		fragment.setBody(fragBodyString);
	}

	public void saveToStory() {
		int listIndex = idList.indexOf(fragment.getId());
		if (listIndex == -1) {
			story.addFragment(fragment);
		} else {
			Frag oldFragment = story.getFrags().get(listIndex);
			story.deleteFrag(oldFragment.getId());
			story.addFragment(fragment, listIndex);
		}
		
		refreshIdList();
		
		if (flag.equals("online")) {
			Integer index = Integer.valueOf(-1);
			new JSONparser().execute(index, story);	
		} else {
			sm.deleteStory(origStory);
			sm.addStory(story);
		}
		origStory = story;
	}

	public void refreshIdList() {
		ArrayList<Frag> a = story.getFrags();
		int b = a.size();
		idList.clear();
		for (int i = 0; i < b; i++) {
			idList.add(a.get(i).getId());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.upload_image_menu:
				uploadImage();
				return true;
			case R.id.camera_menu:
				openCamera();
				return true;
			case R.id.upload_profile_menu:
				setProfile();
				return true;
			case R.id.link_menu:
				linkFrag();
				return true;
			case R.id.save_menu:
				saveFrag();
				return true;
		}
		return true;
	}
	
	public void onStop(){
		super.onStop();
		
		if (mDialog!=null){
		mDialog.dismiss();
		}
	}
}
