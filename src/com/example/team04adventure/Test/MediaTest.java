package com.example.team04adventure.Test;

import junit.framework.Assert;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.Media;

public class MediaTest extends AndroidTestCase {

	RenamingDelegatingContext rdc;
	Bitmap bitmap;
	
	public MediaTest() {
		super();
	}

	protected void setUp() throws Exception {
		/* Create rdc object so that we can extract a context */
		rdc = new RenamingDelegatingContext(
				getContext(), "team04");
		/* Create a bitmap object that represents the default profile picture */
		bitmap = BitmapFactory.decodeResource(
				rdc.getResources(), R.drawable.defaultprofile);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Tests the method encodeToBase64, which encodes a bitmap into a base64 string.
	 * 
	 * @param void
	 * @return void
	 */
	public void testEncodeToBase64() {
		/* Ensure that the bitmap object is representative of the profile picture */
		String stringRep = Media.encodeToBase64(bitmap);
		Bitmap newBMP = Media.decodeBase64(stringRep);
		/* Assert that the bitmap has survived the encoding and decoding process unchanged */
		assert(newBMP.equals(bitmap));		
	}
	
	/**
	 * Tests the method decodeBase64, which decodes a string into a bitmap object.
	 * 
	 * @param void
	 * @return void
	 */
	public void testDecodeBase64() {
		/* Ensure that the bitmap object is representative of the profile picture */
		String stringRep = Media.encodeToBase64(bitmap);
		Bitmap newBMP = Media.decodeBase64(stringRep);
		/* Assert that the bitmap has survived the encoding and decoding process unchanged */
		assert(newBMP.equals(bitmap));		
	}

}
