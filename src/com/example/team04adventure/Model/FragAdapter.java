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

/**
 * FragAdapter is an adapter to get the list of fragments.
 * @author Team04Adventure
 */
public class FragAdapter extends BaseAdapter {

	
	private ArrayList<Frag> frags;
	private Context context;
    private LayoutInflater layoutInflater;
 
    public FragAdapter(Context context, ArrayList<Frag> frag) {
        this.frags = frag;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
 
    public int getCount() {
    	return frags.size();
    }
 
    public Object getItem(int position) {
        return frags.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fraglistlayout, null);
            holder = new ViewHolder();
            holder.profile = (ImageView) convertView.findViewById(R.id.image);
            holder.fragView = (TextView) convertView.findViewById(R.id.frag);
            holder.authorView = (TextView) convertView.findViewById(R.id.author);
        

            convertView.setTag(holder);
        } else 
            holder = (ViewHolder) convertView.getTag();
        
        if (frags.get(position).getProfile().getMedia() == null) {
        	Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultprofile);
        	Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, false);
            holder.profile.setImageBitmap(scaledBitmap);
        } else {
        	String encodedString = frags.get(position).getProfile().getMedia();
        	Bitmap bm = Media.decodeBase64(encodedString);
        	holder.profile.setImageBitmap(bm);
        }
        holder.fragView.setText(frags.get(position).getTitle());
        holder.authorView.setText("By: "+frags.get(position).getAuthor());
        
        
 
        return convertView;
    }
 
    static class ViewHolder {
       
    	ImageView profile;
        TextView fragView;
        TextView authorView;
      
    }
	
}
