package com.wfis.wfis_shop.rest;

import com.wfis.wfis_shop.models.News;
import com.wfis.wfis_shop.models.ResNearby;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface RestInterface {

    @GET("bins/uewtm")
    Call<List<News>> getNews();

    @GET
    Call<ResNearby> kina(@Url String url, @Query("location") String location, @Query("keyword") String keyword, @Query("rankby") String rankby, @Query("key") String key);

}
