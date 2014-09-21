package com.stadiumplayers.stadium.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.adapters.AdapterSearchResult;
import com.stadiumplayers.stadium.models.SportGame;

public class FragmentSearchResults extends Fragment implements OnItemClickListener {
    
    public interface OnSearchResultClickedListener {
        public void onSearchResultClicked(SportGame sportGame);
    }
    
    private OnSearchResultClickedListener mListener;

    private List<SportGame> sportGames;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnSearchResultClickedListener) activity;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_results_list, null, false);

        sportGames = SportGame.getGenerated();

        TextView title = (TextView) rootView.findViewById(R.id.results_list_title);
        title.setText(String.format("Showing %s results ", sportGames.size()));
        
        ListView list = (ListView) rootView.findViewById(R.id.result_list_list);
        AdapterSearchResult adapter = new AdapterSearchResult(getActivity(), sportGames);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        
        return rootView;
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_result_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
        case R.id.action_result_filter:
            Toast.makeText(getActivity(), "Testing!", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.onSearchResultClicked(sportGames.get(position));
    }
    
}
