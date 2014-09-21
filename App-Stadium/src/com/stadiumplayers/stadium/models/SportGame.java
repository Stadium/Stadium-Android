
package com.stadiumplayers.stadium.models;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class SportGame {
    
    private static final String GENERATED_JSON = "[ { 'title': 'Soccer Game 0', 'locationText': 'Malott', 'latitude': 16.985459, 'longitude': 112.087594, 'timeInMillis': 1411266164, 'participants': 11, 'distance': 1 }, { 'title': 'Tennis Game 1', 'locationText': 'Berwind', 'latitude': 23.828462, 'longitude': 52.663709, 'timeInMillis': 1411265605, 'participants': 9, 'distance': 0.5 }, { 'title': 'Soccer Game 2', 'locationText': 'Osage', 'latitude': -82.850078, 'longitude': 12.558101, 'timeInMillis': 1411269115, 'participants': 11, 'distance': 1.2 }, { 'title': 'Soccer Game 3', 'locationText': 'Joes', 'latitude': 16.049577, 'longitude': -90.305988, 'timeInMillis': 1411271002, 'participants': 6, 'distance': 1.5 }, { 'title': 'Football Game 4', 'locationText': 'Cazadero', 'latitude': 10.931172, 'longitude': 103.11952, 'timeInMillis': 1411267997, 'participants': 8, 'distance': 1.2 }, { 'title': 'Tennis Game 5', 'locationText': 'Neahkahnie', 'latitude': 65.057355, 'longitude': 30.242079, 'timeInMillis': 1411266987, 'participants': 9, 'distance': 1.1 }, { 'title': 'Football Game 6', 'locationText': 'Day', 'latitude': -13.285096, 'longitude': -136.829665, 'timeInMillis': 1411274167, 'participants': 11, 'distance': 0.9 }, { 'title': 'Basketball Game 7', 'locationText': 'Fingerville', 'latitude': 16.201841, 'longitude': 43.105604, 'timeInMillis': 1411265588, 'participants': 12, 'distance': 0.8 }, { 'title': 'Soccer Game 8', 'locationText': 'Shindler', 'latitude': 21.450712, 'longitude': -89.111618, 'timeInMillis': 1411271542, 'participants': 7, 'distance': 0.6 }, { 'title': 'Tennis Game 9', 'locationText': 'Grandview', 'latitude': -31.238959, 'longitude': -70.814426, 'timeInMillis': 1411266256, 'participants': 10, 'distance': 0.8 }, { 'title': 'Football Game 10', 'locationText': 'Independence', 'latitude': -7.78358, 'longitude': -35.920727, 'timeInMillis': 1411269602, 'participants': 12, 'distance': 1 }, { 'title': 'Tennis Game 11', 'locationText': 'Lindcove', 'latitude': -44.625393, 'longitude': 178.524664, 'timeInMillis': 1411270590, 'participants': 12, 'distance': 0.9 } ]";

    public class LatLngTypeAdapter extends TypeAdapter<LatLng> {

        @Override
        public LatLng read(JsonReader reader) throws IOException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void write(JsonWriter writer, LatLng value) throws IOException {
            // TODO Auto-generated method stub
            
        }
    }
    
    @SerializedName("title")
    private String mTitle;
    
    @SerializedName("locationText")
    private String mLocationText;
    
    @SerializedName("latitude")
    private float mLatitude;
    
    @SerializedName("longitude")
    private float mLongitude;
    
    @SerializedName("timeInMillis")
    private long mTimeInMillis;
    
    @SerializedName("participants")
    private int mParticipants;
    
    @SerializedName("distance")
    private double mDistance;

    public String getTitle() {
        return mTitle;
    }

    public String getLocationText() {
        return mLocationText;
    }

    public LatLng getLocation() {
        return new LatLng(mLatitude, mLongitude);
    }

    public long getTimeInMillis() {
        return mTimeInMillis;
    }

    public int getParticipants() {
        return mParticipants;
    }

    public double getDistance() {
        return mDistance;
    }
    
    public static List<SportGame> getGenerated() {
        Type listType = new TypeToken<ArrayList<SportGame>>() {
            
        }.getType();
        List<SportGame> items = new Gson().fromJson(GENERATED_JSON, listType);
        Log.e("ryan", items.toString());
        return items;
    }

    @Override
    public String toString() {
        return "SportGame [mTitle=" + mTitle + ", mLocationText=" + mLocationText + ", mLatitude="
                + mLatitude + ", mLongitude=" + mLongitude + ", mTimeInMillis=" + mTimeInMillis
                + ", mParticipants=" + mParticipants + ", mDistance=" + mDistance + "]";
    }

}
