
package com.stadiumplayers.stadium;

import android.content.Context;
import android.content.SharedPreferences;

public enum AppData {

    INSTANCE;

    private static final String SHARED_PREF_LOGIN = "loginstate";
    private static final String KEY_LOGGED_IN = "keyLoggedIn";
    private static final String KEY_USER_ID = "keyUserId";
    private static final String KEY_FIRST_NAME = "keyFirstName";
    private static final String KEY_LAST_NAME = "keyLastName";

    private long mUserId;
    private String mFirstName;
    private String mLastName;
    private boolean mLoggedIn = false;

    public static void init(long userId, String firstName, String lastName,
            boolean loggedIn) {
        
        getSharedPref().edit()
                .putLong(KEY_USER_ID, userId)
                .putString(KEY_FIRST_NAME, firstName)
                .putString(KEY_LAST_NAME, lastName)
                .putBoolean(KEY_LOGGED_IN, loggedIn)
                .commit();
    }
    
    private static SharedPreferences getSharedPref() {
        return App.getContext().getSharedPreferences(SHARED_PREF_LOGIN, Context.MODE_PRIVATE);
    }

    public static Long getUserId() {
        return getSharedPref().getLong(KEY_USER_ID, 0);
    }

    public static String getFirstName() {
        return getSharedPref().getString(KEY_FIRST_NAME, "null");
    }

    public static String getLastName() {
        return getSharedPref().getString(KEY_LAST_NAME, "null");
    }

    public static boolean isLoggedIn() {
        return getSharedPref().getBoolean(KEY_LOGGED_IN, false);
    }

}
