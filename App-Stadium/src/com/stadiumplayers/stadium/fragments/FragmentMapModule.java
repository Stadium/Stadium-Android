
package com.stadiumplayers.stadium.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentMapModule extends SupportMapFragment {

    private static final String KEY_MAP_LAT_LNG = "keyMapLatLng";
    private static final float ZOOM_LEVEL = 16f;

    public static FragmentMapModule newInstance(LatLng position) {
        FragmentMapModule fragment = new FragmentMapModule();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_MAP_LAT_LNG, position);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        UiSettings settings = getMap().getUiSettings();
        settings.setAllGesturesEnabled(true);
        settings.setMyLocationButtonEnabled(true);

        LatLng position = (LatLng) getArguments().getParcelable(KEY_MAP_LAT_LNG);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, ZOOM_LEVEL);

        getMap().setMyLocationEnabled(true);
        getMap().animateCamera(cameraUpdate);
        getMap().addMarker(
                (new MarkerOptions()).position(position).icon(
                        BitmapDescriptorFactory.defaultMarker()));

        return rootView;
    }
}
