
package com.stadiumplayers.stadium;

public enum AppData {

    INSTANCE;

    private long mUserId;
    private String mFirstName;
    private String mLastName;
    private boolean mLoggedIn = false;

    public static void init(long userId, String firstName, String lastName, boolean loggedIn) {
        INSTANCE.mUserId = userId;
        INSTANCE.mFirstName = firstName;
        INSTANCE.mLastName = lastName;
        INSTANCE.mLoggedIn = loggedIn;
    }
    
    public static Long getUserId() {
        return INSTANCE.mUserId;
    }

    public static String getFirstName() {
        return INSTANCE.mFirstName;
    }
    
    public static String getLastName() {
        return INSTANCE.mLastName;
    }
    
    public static boolean isLoggedIn() {
        return INSTANCE.mLoggedIn;
    }

}
