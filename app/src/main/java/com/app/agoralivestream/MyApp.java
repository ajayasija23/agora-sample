package com.app.agoralivestream;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new Webservices();
    }
}
