package com.stadiumplayers.stadium;

import java.text.SimpleDateFormat;

import android.app.Application;
import android.content.Context;

import com.stadiumplayers.stadium.common.Utils;
import com.stadiumplayers.stadium.volley.MyVolley;

public class App extends Application {

    private static App mApp = null;
    private static SimpleDateFormat mDateFormat;
    private static SimpleDateFormat mDateFormatVerbose;
    private static SimpleDateFormat mTimeFormat;

    @Override
    public void onCreate() {
        super.onCreate();
        
        mApp = this;
        MyVolley.init(this);
        mDateFormat = new SimpleDateFormat(Utils.detectDateFormat(false));
        mDateFormatVerbose = new SimpleDateFormat(Utils.detectDateFormat(true));
        mTimeFormat = new SimpleDateFormat(Utils.detectTimeFormat());
    }
    
    public static Context getContent() {
        return mApp;
    }
    
    public static Context getAppContext() {
        return mApp;
    }
    
    public static SimpleDateFormat getDateFormat() {
        return mDateFormat;
    }

    public static SimpleDateFormat getDateFormatVerbose() {
        return mDateFormatVerbose;
    }

    public static SimpleDateFormat getTimeFormat() {
        return mTimeFormat;
    }

}
