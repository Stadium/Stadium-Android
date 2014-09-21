
package com.stadiumplayers.stadium.models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class SportGame implements Parcelable {

    private static final String GENERATED_JSON = "[ { 'title': 'Soccer Game 0', 'sport': 'Tennis', 'host': 'Mclean', 'locationText': 'Wattsville', 'latitude': 43.47297, 'longitude': -80.54011, 'timeInMillis': 1411261027, 'participants': 12, 'distance': 1.5 }, { 'title': 'Soccer Game 1', 'sport': 'Basketball', 'host': 'Joanne', 'locationText': 'Biddle', 'latitude': 43.47299, 'longitude': -80.54011, 'timeInMillis': 1411257406, 'participants': 7, 'distance': 1.2 }, { 'title': 'Tennis Game 2', 'sport': 'Football', 'host': 'Lee', 'locationText': 'Sanders', 'latitude': 43.47298, 'longitude': -80.54011, 'timeInMillis': 1411260566, 'participants': 8, 'distance': 0.7 }, { 'title': 'Basketball Game 3', 'sport': 'Football', 'host': 'June', 'locationText': 'Ogema', 'latitude': 43.47299, 'longitude': -80.54011, 'timeInMillis': 1411258570, 'participants': 7, 'distance': 0.5 }, { 'title': 'Soccer Game 4', 'sport': 'Football', 'host': 'Mabel', 'locationText': 'Joppa', 'latitude': 43.47298, 'longitude': -80.5401, 'timeInMillis': 1411260517, 'participants': 11, 'distance': 0.8 }, { 'title': 'Football Game 5', 'sport': 'Football', 'host': 'Valdez', 'locationText': 'Stouchsburg', 'latitude': 43.47299, 'longitude': -80.5401, 'timeInMillis': 1411266922, 'participants': 8, 'distance': 0.5 }, { 'title': 'Football Game 6', 'sport': 'Basketball', 'host': 'Maritza', 'locationText': 'Homeland', 'latitude': 43.47298, 'longitude': -80.54011, 'timeInMillis': 1411264064, 'participants': 10, 'distance': 1.3 }, { 'title': 'Football Game 7', 'sport': 'Soccer', 'host': 'Cecelia', 'locationText': 'Fairmount', 'latitude': 43.47297, 'longitude': -80.5401, 'timeInMillis': 1411258988, 'participants': 6, 'distance': 1.2 }, { 'title': 'Tennis Game 8', 'sport': 'Basketball', 'host': 'Lillian', 'locationText': 'Omar', 'latitude': 43.47297, 'longitude': -80.5401, 'timeInMillis': 1411265614, 'participants': 8, 'distance': 1.5 }, { 'title': 'Basketball Game 9', 'sport': 'Soccer', 'host': 'Bell', 'locationText': 'Blanco', 'latitude': 43.47297, 'longitude': -80.54011, 'timeInMillis': 1411266598, 'participants': 9, 'distance': 1.1 } ]";

    @SerializedName("title")
    private String mTitle;
    
    @SerializedName("sport")
    private String mSport;
    
    @SerializedName("host")
    private String mHost;
    
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
    
    public String getSport() {
        return mSport;
    }
    
    public String getHost() {
        return mHost;
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
        return items;
    }

    @Override
    public String toString() {
        return "SportGame [mTitle=" + mTitle + ", mLocationText=" + mLocationText + ", mLatitude="
                + mLatitude + ", mLongitude=" + mLongitude + ", mTimeInMillis=" + mTimeInMillis
                + ", mParticipants=" + mParticipants + ", mDistance=" + mDistance + "]";
    }

    protected SportGame(Parcel in) {
        mTitle = in.readString();
        mLocationText = in.readString();
        mLatitude = in.readFloat();
        mLongitude = in.readFloat();
        mTimeInMillis = in.readLong();
        mParticipants = in.readInt();
        mDistance = in.readDouble();
        mHost = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mLocationText);
        dest.writeFloat(mLatitude);
        dest.writeFloat(mLongitude);
        dest.writeLong(mTimeInMillis);
        dest.writeInt(mParticipants);
        dest.writeDouble(mDistance);
        dest.writeString(mHost);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SportGame> CREATOR = new Parcelable.Creator<SportGame>() {
        @Override
        public SportGame createFromParcel(Parcel in) {
            return new SportGame(in);
        }

        @Override
        public SportGame[] newArray(int size) {
            return new SportGame[size];
        }
    };
}
