package com.stadiumplayers.stadium.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.stadiumplayers.stadium.R;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    
    public interface OnSearchButtonClickedListener {
        public void onSearchButtonClicked();
    }

    private OnSearchButtonClickedListener mListener;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        mListener = (OnSearchButtonClickedListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, null, false);
        
        TextView textLocation = (TextView) rootView.findViewById(R.id.search_text_location);
        Button button = (Button) rootView.findViewById(R.id.search_btn_find_game);
        button.setOnClickListener(this);
        
        textLocation.setText("Waterloo, ON");
        
        return rootView;
    }

    @Override
    public void onClick(View v) {
        mListener.onSearchButtonClicked();
    }

}
