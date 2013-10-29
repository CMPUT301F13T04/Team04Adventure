package com.example.team04adventure;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class storyListAdapter extends BaseAdapter {
 
		private ArrayList<Story> stories;
	 
	    private LayoutInflater layoutInflater;
	 
	    public storyListAdapter(Context context, ArrayList<Story> stories) {
	        this.stories = stories;
	        layoutInflater = LayoutInflater.from(context);
	    }
	 
	    @Override
	    public int getCount() {
	    	return stories.size();
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return stories.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ViewHolder holder;
	        if (convertView == null) {
	            convertView = layoutInflater.inflate(R.layout.storylistlayout, null);
	            holder = new ViewHolder();
	            holder.titleView = (TextView) convertView.findViewById(R.id.storytitle);
	            holder.authorView = (TextView) convertView.findViewById(R.id.storyauthor);
	            holder.fragmentView = (TextView) convertView.findViewById(R.id.storyfragments);
	            
	            convertView.setTag(holder);
	        } else 
	            holder = (ViewHolder) convertView.getTag();
	        
	        holder.titleView.setText(stories.get(position).getTitle());
	        holder.authorView.setText("By, " + stories.get(position).getAuthor().getName());
	        holder.fragmentView.setText("Fragments: " + stories.get(position).frags.size());
	        
	 
	        return convertView;
	    }
	 
	    static class ViewHolder {
	        TextView titleView;
	        TextView authorView;
	        TextView fragmentView;
	      
	    }
	 }