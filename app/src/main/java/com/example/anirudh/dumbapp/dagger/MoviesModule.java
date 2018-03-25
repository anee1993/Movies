package com.example.anirudh.dumbapp.dagger;

import android.content.Context;

import com.example.anirudh.dumbapp.manager.MoviesApi;
import com.example.anirudh.dumbapp.manager.MoviesApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Anirudh on 3/24/2018.
 */

@Module
public class MoviesModule {
    Context context;
    public MoviesModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    MoviesApi provideMoviesApi(){
        return new MoviesApiImpl(context);
    }
}
