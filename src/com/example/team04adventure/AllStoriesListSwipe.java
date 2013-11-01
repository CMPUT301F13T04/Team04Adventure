package com.example.team04adventure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AllStoriesListSwipe extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_all_stories_list_swipe, container, false);
         
        return rootView;
    }
	

}
