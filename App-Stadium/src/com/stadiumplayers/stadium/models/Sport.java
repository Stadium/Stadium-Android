
package com.stadiumplayers.stadium.models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.common.Utils;

public enum Sport implements Parcelable {

    SOOCER(R.string.sport_soccer, R.drawable.ic_launcher),
    FOOTBALL(R.string.sport_football, R.drawable.ic_launcher),
    TENNIS(R.string.sport_tennis, R.drawable.ic_launcher),
    BASKETBALL(R.string.sport_basketball, R.drawable.ic_launcher);

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
