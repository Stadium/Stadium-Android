
package com.stadiumplayers.stadium.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stadiumplayers.stadium.R;

public class FragmentMapModuleMulti extends SupportMapFragment {
    
    private static final LatLng POSITION_ICE_FIELD_WATERLOO = new LatLng(43.4737573d, -80.55233717d);
    private static final LatLng POSITION_E5_WATERLOO = new LatLng(43.4729791d, -80.5401027);
    private static final LatLng POSITION_V1_GREEN = new LatLng(43.47100106, -80.5478096);

    private static final String KEY_MAP_LAT_LNG = "keyMapLatLng";
    private static final float ZOOM_LEVEL = 16f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        UiSettings settings = getMap().getUiSettings();
        settings.setAllGesturesEnabled(true);
        settings.setMyLocationButtonEnabled(true);
                
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                LatLngBounds bounds = builder.include(POSITION_E5_WATERLOO).include(POSITION_ICE_FIELD_WATERLOO)
                        .include(POSITION_V1_GREEN).build();

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 150);
                
                getMap().addMarker((new MarkerOptions()).position(POSITION_E5_WATERLOO).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_soccer)));
                getMap().addMarker((new MarkerOptions()).position(POSITION_ICE_FIELD_WATERLOO).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_football)));
                getMap().addMarker((new MarkerOptions()).position(POSITION_V1_GREEN).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_basketball)));
                
                getMap().setMyLocationEnabled(true);
                getMap().animateCamera(cameraUpdate);
            }
            
        }, 1000);

        return rootView;
    }
}
