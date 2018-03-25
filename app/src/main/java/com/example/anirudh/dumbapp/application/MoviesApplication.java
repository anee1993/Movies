package com.example.anirudh.dumbapp.application;

import android.app.Application;

import com.example.anirudh.dumbapp.dagger.DaggerMoviesComponent;
import com.example.anirudh.dumbapp.dagger.MoviesComponent;
import com.example.anirudh.dumbapp.dagger.MoviesModule;
import com.example.anirudh.dumbapp.service.ApiInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anirudh on 3/24/2018.
 */

public class MoviesApplication extends Application {

    private MoviesComponent moviesComponent;
    private ApiInterface apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MoviesComponent getApplicationComponent(){
        if(moviesComponent==null){
            moviesComponent = DaggerMoviesComponent
                    .builder()
                    .moviesModule(new MoviesModule(this))
                    .build();
        }
        return moviesComponent;
    }

    public ApiInterface getApiInterface(){
        if(apiInterface==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/movie/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            apiInterface = retrofit.create(ApiInterface.class);
        }
        return apiInterface;
    }
}
