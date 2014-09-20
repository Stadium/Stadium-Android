package com.stadiumplayers.stadium;

import com.stadiumplayers.stadium.volley.MyVolley;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    
    private static Context mContext;
    

    @Override
    public void onCreate() {
        super.onCreate();
        
        mContext = this;
        MyVolley.init(this);
    }
    
    public static Context getContent() {
        return mContext;
    }

}
