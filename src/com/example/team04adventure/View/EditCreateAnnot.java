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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.AdventureApp;
import com.example.team04adventure.Model.Annotation;
import com.example.team04adventure.Model.Frag;
import com.example.team04adventure.Model.JSONparser;
import com.example.team04adventure.Model.Media;
import com.example.team04adventure.Model.StorageManager;
import com.example.team04adventure.Model.Story;

/**
 * EditCreateAnnot opens the activity that allows the user to create new
 * annotations for a fragment.
 * 
 * @author Team04Adventure
 */
public class EditCreateAnnot extends Activity {

	private static final int SELECT_PICTURE = 1;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	String sid, fid;
	String online;
	JSONparser jp;
	StorageManager sm;

	String author, review, image;

	EditText reviewIn;
	ProgressDialog mDialog;
	Button saveButton;
	Annotation a;

	private Uri imageFileUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_create_annot);
		Bundle extras = getIntent().getExtras();

		sid = extras.getString("sid");
		fid = extras.getString("fid");
		online = extras.getString("online");
		reviewIn = (EditText) findViewById(R.id.annotBody);
		a = new Annotation();

	}

	/**
	 * Saves the entered information as a new annotation.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void saveAnnot(View view) {
		mDialog = new ProgressDialog(view.getContext());
		mDialog.setMessage("Please wait...");
		mDialog.show();
		review = reviewIn.getText().toString();
		author = MainActivity.username;

		a.setAuthor(author);
		a.setReview(review);

		if (online.equals("online")) {
			Story s;
			System.out.println("BEGINS ONLINE");
			AdventureApp Adventure = (AdventureApp) getApplicationContext();
			s = Adventure.getCurrentStory();

			Frag f = s.getFrag(fid);
			int index2 = s.getFrags().indexOf(f);
			f.addAnnotations(a);
			s.deleteFrag(fid);
			s.addFragment(f, index2);

			Integer index = Integer.valueOf(-1);
			new JSONparser().execute(index, s);
			System.out.println("IT SAVED ONLINE");

		} else {

			System.out.println("SAVED OFFLINE");
			sm = new StorageManager(this);
			Story s = sm.getStory(sid);
			sm.deleteStory(s);
			s.getFrag(fid).addAnnotations(a);
			sm.addStory(s);

		}

		finish();
	}

	/**
	 * Opens the file picker to select an image.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void uploadImage(View view) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				SELECT_PICTURE);
	}

	/**
	 * Opens the camera application and prepares a file for the image to be
	 * stored into.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void uploadCamera(View view) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String folder = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/team04adventure";
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

	protected void onActivityResult(int requestCode, int resultCode,
			final Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE
				&& resultCode == RESULT_OK) {
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), imageFileUri);
				Bitmap resizedBitmap = Media.resizeImage(bitmap);
				String convertedString = Media.encodeToBase64(resizedBitmap);
				a.setImage(convertedString);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
			Uri selectedImageUri = data.getData();
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), selectedImageUri);
				Bitmap resizedBitmap = Media.resizeImage(bitmap);
				String convertedString = Media.encodeToBase64(resizedBitmap);
				a.setImage(convertedString);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onStop() {
		super.onStop();

		if (mDialog != null) {
			mDialog.dismiss();
		}
	}

	/**
	 * Shows the help information for this fragment.
	 */
	private void help() {
		String helpText = "Enter a review, or attach images from memory or from the camera. Press the save button "
				+ "to save the annotation";
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		LinearLayout lila1 = new LinearLayout(this);
		lila1.setOrientation(1);

		final TextView helpTextView = new TextView(this);
		helpTextView.setText(helpText);
		lila1.addView(helpTextView);
		adb.setView(lila1);
		adb.setTitle("Help");

		adb.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_create_annot, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.help:
			help();
			return true;
		}
		return true;
	}

}
