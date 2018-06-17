package com.wfis.wfis_shop.rest;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {

    private static Retrofit retrofit;
    private static RestInterface restInterface;

    private Rest() {

    }

    public static void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .client(new OkHttpClient().newBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restInterface = retrofit.create(RestInterface.class);
    }

    public static RestInterface getRestInterface() {
        return restInterface;
    }
}
