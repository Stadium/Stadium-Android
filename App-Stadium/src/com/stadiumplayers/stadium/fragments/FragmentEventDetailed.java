
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

public class FragmentEventDetailed extends Fragment implements View.OnClickListener {

    private static final String KEY_EVENT_HOST_NAME = "keyEventHostName";
    private static final String KEY_SPORT = "keySport";
    private static final String KEY_DATE = "keyDate";
    private static final String KEY_LOCATION_TEXT = "keyLocationText";
    private static final String KEY_LOCATION = "keyLocation";
    private static final String KEY_FRIENDS_ATTENDING = "keyFriendsAttending";

    private TextView mTextTitle;
    private TextView mTextDate;
    private TextView mTextLocation;
    private ListView mListFriendsAttending;
    private Button mBtnRsvp;

    public static FragmentEventDetailed newInstance(String eventHostName, Sport sport,
            long epochTime, String languifiedLocation, LatLng location, String[] friendsAttending) {

        FragmentEventDetailed fragment = new FragmentEventDetailed();
        Bundle bundle = new Bundle();

        bundle.putString(KEY_EVENT_HOST_NAME, eventHostName);
        bundle.putParcelable(KEY_SPORT, sport);
        bundle.putLong(KEY_DATE, epochTime);
        bundle.putString(KEY_LOCATION_TEXT, languifiedLocation);
        bundle.putParcelable(KEY_LOCATION, location);
        bundle.putStringArray(KEY_FRIENDS_ATTENDING, friendsAttending);

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
        Sport sport = (Sport) args.getParcelable(KEY_SPORT);
        String title = String.format("%s's %s Game", args.getString(KEY_EVENT_HOST_NAME),
                sport.getName());
        mTextTitle.setText(title);

        // Set the date
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d");
        mTextDate.setText(sdf.format(new Date(args.getLong(KEY_DATE))));

        // Set the location text
        mTextLocation.setText(args.getString(KEY_LOCATION_TEXT));

        // Set the map
        LatLng position = (LatLng) args.getParcelable(KEY_LOCATION);
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
        String[] names = args.getStringArray(KEY_FRIENDS_ATTENDING);
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
