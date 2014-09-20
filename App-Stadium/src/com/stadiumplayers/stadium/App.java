package com.stadiumplayers.stadium;

import java.text.SimpleDateFormat;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.stadiumplayers.stadium.common.Utils;
import com.stadiumplayers.stadium.volley.MyVolley;

public class App extends Application {

    private static App sApp;
    private static SimpleDateFormat sDateFormat;
    private static SimpleDateFormat sDateFormatVerbose;
    private static SimpleDateFormat sTimeFormat;

    @Override
    public void onCreate() {
        super.onCreate();
        
        sApp = this;
        MyVolley.init(this);
        sDateFormat = new SimpleDateFormat(Utils.detectDateFormat(false));
        sDateFormatVerbose = new SimpleDateFormat(Utils.detectDateFormat(true));
        sTimeFormat = new SimpleDateFormat(Utils.detectTimeFormat());
    }
    
    public static Context getContext() {
        return sApp;
    }
    
    public static SimpleDateFormat getDateFormat() {
        return sDateFormat;
    }

    public static SimpleDateFormat getDateFormatVerbose() {
        return sDateFormatVerbose;
    }

    public static SimpleDateFormat getTimeFormat() {
        return sTimeFormat;
    }

}
