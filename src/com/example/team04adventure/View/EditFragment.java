package com.example.team04adventure.View;

import com.example.team04adventure.R;
import com.example.team04adventure.R.id;
import com.example.team04adventure.R.layout;
import com.example.team04adventure.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class EditFragment extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	private Button uploadButton;
	private Button cameraButton;
	private Button linkButton;
	private Button saveButton;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment);
		
		uploadButton = (Button) findViewById(R.id.upload);
		cameraButton = (Button) findViewById(R.id.camera);
		linkButton = (Button) findViewById(R.id.link);
		saveButton = (Button) findViewById(R.id.save);
		
		uploadButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
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
				// TODO Auto-generated method stub
				
			}
		});
		
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void openCamera() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	private void handleSmallCameraPhoto(Intent intent) {
	    Bundle extras = intent.getExtras();
	    Bitmap mImageBitmap = (Bitmap) extras.get("data");
	    // mImageBitmap is the image from the camera
	    
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		handleSmallCameraPhoto(data);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
