package com.example.team04adventure;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team04adventure.R;

public class FragAdapter extends BaseAdapter {

	
	private ArrayList<Frag> frags;
	 
    private LayoutInflater layoutInflater;
 
    public FragAdapter(Context context, ArrayList<Frag> frag) {
        this.frags = frag;
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
        
        holder.profile.setImageResource(R.drawable.ic_launcher);
        //holder.profile.setImageBitmap(frags.get(position).getProfile().getMedia());
        holder.fragView.setText(frags.get(position).getTitle());
        holder.authorView.setText("By, "+frags.get(position).getAuthor());
        
        
 
        return convertView;
    }
 
    static class ViewHolder {
       
    	ImageView profile;
        TextView fragView;
        TextView authorView;
      
    }
	
}
