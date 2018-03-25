package com.example.anirudh.dumbapp.manager;

/**
 * Created by Anirudh on 3/24/2018.
 */

public interface MoviesApi {
    void getTopRatedMovies(MoviesApiImpl.NotifySuccess notifySuccess);
    void getCurrentlyPlayingMovies();
    void getUpcomingMovies();
}
