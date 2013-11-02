package com.example.team04adventure;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.team04adventure.R;

public class FragAdapter extends BaseAdapter {

	
	private ArrayList<Frag> frags;
	 
    private LayoutInflater layoutInflater;
 
    public FragChoiceAdapter(Context context, ArrayList<Frag> frag) {
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
            holder.fragView = (TextView) convertView.findViewById(R.id.frag);
        
            
            convertView.setTag(holder);
        } else 
            holder = (ViewHolder) convertView.getTag();
        
      
        holder.fragView.setText(frags.get(position).getTitle());
       
        
 
        return convertView;
    }
 
    static class ViewHolder {
       
    	
        TextView fragView;
      
    }
	
}
