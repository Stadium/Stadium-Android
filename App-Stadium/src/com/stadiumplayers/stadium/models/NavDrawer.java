
package com.stadiumplayers.stadium.models;

import android.graphics.drawable.Drawable;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.common.Utils;

public enum NavDrawer {

    PROFILE(R.string.nav_drawer_profile, R.drawable.ic_launcher),
    SEARCH_GAMES(R.string.nav_drawer_search_games, R.drawable.ic_launcher), 
    NEAR_ME(R.string.nav_drawer_near_me, R.drawable.ic_launcher),
    UPCOMING(R.string.nav_drawer_upcoming, R.drawable.ic_launcher),
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
