
package com.stadiumplayers.stadium.models;

import android.graphics.drawable.Drawable;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.common.Utils;

public enum NavDrawer {

    PROFILE(R.string.nav_drawer_profile, R.drawable.ic_launcher),
    FIND_EVENT(R.string.nav_drawer_find_event, R.drawable.ic_launcher), 
    CREATE_EVENT(R.string.nav_drawer_create_event, R.drawable.ic_launcher);

    private String mTitle;
    private int mIconId;
    private Drawable mIcon;
    
    private NavDrawer(int stringId, int iconId) {
        mTitle = Utils.getString(stringId);
        mIconId = iconId;
    }

    public String getTitle() {
        return mTitle;
    }
    
    public Drawable getIcon() {
        if (mIcon == null) {
            mIcon = Utils.getDrawable(mIconId);
        }
        
        return mIcon;
    }
}
