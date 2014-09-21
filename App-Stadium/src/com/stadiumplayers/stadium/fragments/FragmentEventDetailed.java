
package com.stadiumplayers.stadium.fragments;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.adapters.AdapterFriendsAttending;
import com.stadiumplayers.stadium.models.Sport;
import com.stadiumplayers.stadium.models.SportGame;

public class FragmentEventDetailed extends Fragment implements View.OnClickListener {

    private static final String KEY_SPORT_GAME = "keySportGame";
    
    private TextView mTextTitle;
    private TextView mTextDate;
    private TextView mTextLocation;
    private ListView mListFriendsAttending;
    private Button mBtnRsvp;

    public static FragmentEventDetailed newInstance(SportGame sportGame) {

        FragmentEventDetailed fragment = new FragmentEventDetailed();
        Bundle bundle = new Bundle();
        
        bundle.putParcelable(KEY_SPORT_GAME, sportGame);

        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_event_detailed, null, false);
        View headerView = inflater.inflate(R.layout.header_event_detailed, null, false);
        View footerView = inflater.inflate(R.layout.footer_event_detailed, null, false);

        mListFriendsAttending = (ListView) rootView.findViewById(R.id.event_detailed_list);
        mListFriendsAttending.addHeaderView(headerView);
        mListFriendsAttending.addFooterView(footerView);

        mTextTitle = (TextView) headerView.findViewById(R.id.event_detailed_title);
        mTextDate = (TextView) headerView.findViewById(R.id.event_detailed_date);
        mTextLocation = (TextView) headerView.findViewById(R.id.event_detailed_location);
        mBtnRsvp = (Button) footerView.findViewById(R.id.event_detailed_btn_rsvp);

        Bundle args = getArguments();

        // Set the title
        SportGame sportGame = (SportGame) getArguments().getParcelable(KEY_SPORT_GAME);
        String title = String.format("%s's %s Game", sportGame.getHost(), sportGame.getSport());
        mTextTitle.setText(title);

        // Set the date
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d");
        mTextDate.setText(sdf.format(new Date(sportGame.getTimeInMillis())));

        // Set the location text
        mTextLocation.setText(sportGame.getLocationText());

        // Set the map
        LatLng position = (LatLng) sportGame.getLocation();
        FragmentMapModule fragment = FragmentMapModule.newInstance(position);
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.event_detailed_map_container, fragment,
                        FragmentMapModule.class.getSimpleName()).commit();

        ImageView fakeImage = new ImageView(getActivity());
        fakeImage.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        ((FrameLayout) headerView.findViewById(R.id.event_detailed_map_container))
                .addView(fakeImage);

        // Bind data to the listview
        String[] names = {"Meghan", "Chris", "Kevin"};
        AdapterFriendsAttending adapter = new AdapterFriendsAttending(getActivity(), names);
        mListFriendsAttending.setAdapter(adapter);

        mBtnRsvp.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "Okay, you're in!", Toast.LENGTH_SHORT).show();
    }

}
