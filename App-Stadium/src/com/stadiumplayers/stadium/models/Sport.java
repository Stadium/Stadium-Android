
package com.stadiumplayers.stadium.models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.common.Utils;

public enum Sport implements Parcelable {

    SOOCER(R.string.sport_soccer, R.drawable.pin_soccer),
    FOOTBALL(R.string.sport_football, R.drawable.pin_football),
    TENNIS(R.string.sport_tennis, R.drawable.pin_tennis),
    BASKETBALL(R.string.sport_basketball, R.drawable.pin_basketball);

    private String mName;
    private int mIconId;
    private Drawable mIcon;

    private Sport(int stringId, int iconId) {
        mName = Utils.getString(stringId);
        mIconId = iconId;
    }
    
    public String getName() {
        return mName;
    }
    
    public int getIconId() {
        return mIconId;
    }
    
    public Drawable getIcon() {
        if (mIcon == null) {
            mIcon = Utils.getDrawable(mIconId);
        }
        
        return mIcon;
    }

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ordinal());
    }
    
    public static final Creator<Sport> CREATOR = new Creator<Sport>() {
        @Override
        public Sport createFromParcel(final Parcel source) {
            return Sport.values()[source.readInt()];
        }

        @Override
        public Sport[] newArray(final int size) {
            return new Sport[size];
        }
    };
}
