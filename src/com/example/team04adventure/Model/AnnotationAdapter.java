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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.FragAdapter.ViewHolder;

/**
 * StoryListAdapter is an adapter to get the story list.
 * @author Team04Adventure
 */
public class AnnotationAdapter extends BaseAdapter {
 
		private ArrayList<Annotation> annots;
		private Context context;
	    private LayoutInflater layoutInflater;
	 
	    public AnnotationAdapter(Context context, ArrayList<Annotation> annots) {
	        this.annots = annots;
	        layoutInflater = LayoutInflater.from(context);
	        this.context = context;
	    }
	 
	    @Override
	    public int getCount() {
	    	return annots.size();
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return annots.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ViewHolder holder;
	        if (convertView == null) {
	            convertView = layoutInflater.inflate(R.layout.annotlistlayout, null);
	            holder = new ViewHolder();
	            holder.image = (ImageView) convertView.findViewById(R.id.image);
	            holder.authorView = (TextView) convertView.findViewById(R.id.author);
	            holder.reviewView = (TextView) convertView.findViewById(R.id.review);
	        

	            convertView.setTag(holder);
	        } else 
	            holder = (ViewHolder) convertView.getTag();
	        
	        if (annots.get(position).getImage() == null) {
	        	Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
	        	Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
	            holder.image.setImageBitmap(scaledBitmap);
	        } else {
	        	String encodedString = annots.get(position).getImage();
	        	Bitmap bm = Media.decodeBase64(encodedString);
	        	holder.image.setImageBitmap(bm);
	        }
	        holder.authorView.setText(annots.get(position).getAuthor());
	        holder.reviewView.setText(annots.get(position).getReview());
	        
	        
	 
	        return convertView;
	    }
	 
	    static class ViewHolder {
	       
	    	ImageView image;
	        TextView authorView;
	        TextView reviewView;
	      
	    }
	 
}