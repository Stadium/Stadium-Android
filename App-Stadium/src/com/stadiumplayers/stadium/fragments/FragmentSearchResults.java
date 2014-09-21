package com.stadiumplayers.stadium.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.adapters.AdapterSearchResult;
import com.stadiumplayers.stadium.models.SportGame;

public class FragmentSearchResults extends Fragment {
    
    private List<SportGame> sportGames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_results_list, null, false);

        sportGames = SportGame.getGenerated();

        TextView title = (TextView) rootView.findViewById(R.id.results_list_title);
        title.setText(String.format("Showing %s results ", sportGames.size()));
        
        ListView list = (ListView) rootView.findViewById(R.id.result_list_list);
        AdapterSearchResult adapter = new AdapterSearchResult(getActivity(), sportGames);
        list.setAdapter(adapter);
        
        return rootView;
    }

}
