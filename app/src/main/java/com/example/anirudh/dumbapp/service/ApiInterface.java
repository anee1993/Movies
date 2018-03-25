package com.example.anirudh.dumbapp.service;

import com.example.anirudh.dumbapp.model.Page;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anirudh on 3/24/2018.
 */

public interface ApiInterface {
    @GET("top_rated/")
    Observable<Page> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("now_playing/")
    Observable<Page> getCurrentlyPlayingMovies(@Query("api_key") String apiKey);

    @GET("upcoming/")
    Observable<Page> getUpcomingMovies(@Query("api_key") String apiKey);
 }
