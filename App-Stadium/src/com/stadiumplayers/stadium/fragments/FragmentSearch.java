package com.stadiumplayers.stadium.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.stadiumplayers.stadium.R;

public class FragmentSearch extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, null, false);
        
        TextView textLocation = (TextView) rootView.findViewById(R.id.search_text_location);
        EditText editRadius = (EditText) rootView.findViewById(R.id.search_edit_radius);
        
        textLocation.setText("Waterloo, ON");
        
        return rootView;
    }

}
