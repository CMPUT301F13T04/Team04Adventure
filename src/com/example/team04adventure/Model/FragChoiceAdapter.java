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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.team04adventure.R;

/**
 * FragChoiceAdapter is an adapter to get the fragment choice list. 
 * @author Team04Adventure
 */
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
