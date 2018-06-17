package com.wfis.wfis_shop;

import android.app.Application;

import com.wfis.wfis_shop.rest.Rest;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Rest.init();
    }
}
