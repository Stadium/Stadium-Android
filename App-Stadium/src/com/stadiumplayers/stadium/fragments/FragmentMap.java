
package com.stadiumplayers.stadium.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.stadiumplayers.stadium.R;

public class FragmentMap extends Fragment {

    private FrameLayout mFrameLayout;
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, null, false);

        LatLng position = new LatLng(43.4729791d, -80.5401027d);
        Fragment fragment = FragmentMapModule.newInstance(position);
        getChildFragmentManager().beginTransaction()
                .add(R.id.map_map_container, fragment, FragmentMapModule.class.getSimpleName())
                .commit();

        return rootView;
    }

}
