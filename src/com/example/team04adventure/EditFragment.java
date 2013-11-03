package com.example.team04adventure;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditFragment extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	private Button uploadButton;
	private Button cameraButton;
	private Button linkButton;
	private Button saveButton;
	
	private TextView fragTitle;
	private EditText fragBody;
	
    private static final int SELECT_PICTURE = 1;
	
	long id;
	Uri imageFileUri;
	Frag fragment;
	JSONparser json;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");
		StorageManager sm = new StorageManager(this);
		
		fragment = sm.getFrag(id);
		
		uploadButton = (Button) findViewById(R.id.upload);
		cameraButton = (Button) findViewById(R.id.camera);
		linkButton = (Button) findViewById(R.id.link);
		saveButton = (Button) findViewById(R.id.save);
		
		fragTitle = (TextView) findViewById(R.id.frag_title);
		fragBody = (EditText) findViewById(R.id.frag_body);
		
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
		
		linkButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				linkFrag();				
			}
		});
		
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				saveFrag();
			}
		});
	}
	
	public void openCamera() {
		// Opens the camera app and stores the resulting image as a jpg file in the /team04adventure in the external memory.
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        
        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/team04adventure";
        File folderF = new File(folder);
        if (!folderF.exists()) {
            folderF.mkdir();
        }
        
        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        File imageFile = new File(imageFilePath);
        imageFileUri = Uri.fromFile(imageFile);
                
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
			// Saves the bitmap result from the camera into the frag object
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageFileUri);
				Media media = new Media();
				media.setContent(bitmap);
				media.setType("pic");
				fragment.addPicture(media);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
			// Saves the bitmap from the selected image to be uploaded into the frag object
			Uri selectedImageUri = data.getData();
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
				Media media = new Media();
				media.setContent(bitmap);
				media.setType("pic");
				fragment.addPicture(media);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void uploadImage() {
		// Starts the image picker for the user to choose a picture to add to the fragment
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
	}
	
	public void linkFrag() {
		
	}
	
	public void saveFrag() {
		// Saves the changes to the fragment text
		String fragBodyString = fragBody.getText().toString();
		fragment.setBody(fragBodyString);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
