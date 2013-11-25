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


import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Media holds the images, videos, and sounds that can be associated to any fragment.
 * 
 * @author Team04Adventure
 */
public class Media {
	
	private static final int IMAGE_MAX = 250;
	
	long id;
	String content;
	String type;
	
	/**
	 * Gets the ID of the media object.
	 * @return the ID number.
	 */
	public long getID(){
		
		return this.id;
		
	}

	/**
	 * Sets the content of a Media object as the specified image.
	 * @param content the bitmap object to be stored.
	 */
	public void setContent(String content){
		
		this.content = content;
		
	}
	
	/**
	 * Gets the content in the Media object.
	 * @return the bitmap object in the Media object.
	 */
	public String getMedia(){
		
		return this.content;
		
	}
	
	/**
	 * Sets the ID of the Media object as specified.
	 * @param id the ID number to set to.
	 */
	public void setID(long id){
		
		this.id = id;
		
	}
	
	/**
	 * Sets the type of the Media object. Possible examples include <ul><li>pic</li><li>vid</li><li>sound</li></ul>
	 * @param type the type of object being stored in Media in string format.
	 */
	public void setType(String type){
		
		this.type = type;
		
	}
	
	/**
	 * Gets the type of the Media object.
	 * @return type of Media.
	 */
	public String getType(){
		
		return this.type;
	}
	
	/**
	 * Encodes bitmap into base64 string.
	 * @param bm bitmap to be encoded.
	 * @return encoded string.
	 */
	public static String encodeToBase64(Bitmap bm) {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	    byte[] bArray = baos.toByteArray();
	    String imageEncoded = Base64.encodeToString(bArray,Base64.DEFAULT);

	    return imageEncoded;
	}
	
	/**
	 * Decodes string into bitmap
	 * @param bArray encoded string.
	 * @return decoded bitmap.
	 */
	public static Bitmap decodeBase64(String bArray) {
	    byte[] decodedByte = Base64.decode(bArray, 0);
	    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
	}
	
	public static Bitmap resizeImage(Bitmap bitmap) {
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


}
