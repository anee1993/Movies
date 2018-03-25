package com.example.anirudh.dumbapp.dagger;

import com.example.anirudh.dumbapp.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Anirudh on 3/24/2018.
 */
@Singleton
@Component(modules = MoviesModule.class)
public interface MoviesComponent {
    void inject(MainActivity mainActivity);
}
