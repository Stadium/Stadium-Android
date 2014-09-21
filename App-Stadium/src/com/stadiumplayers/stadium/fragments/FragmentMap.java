
package com.stadiumplayers.stadium.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.common.Constants;

public class FragmentMap extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, null, false);

        Fragment fragment = FragmentMapModule.newInstance(Constants.POSITION_E5_WATERLOO);
        getChildFragmentManager().beginTransaction()
                .add(R.id.map_map_container, fragment, FragmentMapModule.class.getSimpleName())
                .commit();

        return rootView;
    }

}
