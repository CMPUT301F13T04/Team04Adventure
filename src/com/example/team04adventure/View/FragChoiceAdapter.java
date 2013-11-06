package com.example.team04adventure.View;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.team04adventure.R;
import com.example.team04adventure.Model.Choice;

public class FragChoiceAdapter extends BaseAdapter {

	
	private ArrayList<Choice> choices;
	 
    private LayoutInflater layoutInflater;
 
    public FragChoiceAdapter(Context context, ArrayList<Choice> choices) {
        this.choices = choices;
        layoutInflater = LayoutInflater.from(context);
    }
 
    public int getCount() {
    	return choices.size();
    }
 
    public Object getItem(int position) {
        return choices.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.choicelistlayout, null);
            holder = new ViewHolder();
            holder.choiceView = (TextView) convertView.findViewById(R.id.choice);
        
            
            convertView.setTag(holder);
        } else 
            holder = (ViewHolder) convertView.getTag();
        
      
        holder.choiceView.setText(choices.get(position).getBody());
       
        
 
        return convertView;
    }
 
    static class ViewHolder {
       
    	
        TextView choiceView;
      
    }
	
}
