package com.example.anirudh.dumbapp.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import com.example.anirudh.dumbapp.adapter.MoviesAdapter;
import com.example.anirudh.dumbapp.manager.MoviesApi;
import com.example.anirudh.dumbapp.manager.MoviesApiImpl;
import com.example.anirudh.dumbapp.application.MoviesApplication;
import com.example.anirudh.dumbapp.R;
import com.example.anirudh.dumbapp.model.MovieDetails;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MoviesApi moviesApi;

    private RecyclerView topRatedView;
    private RecyclerView nowPlayingView;
    private RecyclerView upComingView;
    private ProgressBar progressBar;
    MoviesAdapter adapter;
    private final MoviesApiImpl.NotifySuccess successResponse = new MoviesApiImpl.NotifySuccess() {
        @Override
        public void notifySuccessResponse(List<MovieDetails> movieDetails, int count) {
            switch (count){
                case 1:
                    adapter = new MoviesAdapter(MainActivity.this, movieDetails);
                    topRatedView.setAdapter(adapter);
                    fetchCurrentlyPlayingMoviesList();
                    break;

                case 2:
                    adapter = new MoviesAdapter(MainActivity.this, movieDetails);
                    nowPlayingView.setAdapter(adapter);
                    fetchUpcomingMoviesList();
                    break;

                case 3:
                    progressBar.setVisibility(View.GONE);
                    adapter = new MoviesAdapter(MainActivity.this, movieDetails);
                    upComingView.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MoviesApplication)getApplication()).getApplicationComponent().inject(this);
        setContentView(R.layout.activity_main);
        topRatedView = findViewById(R.id.top_rated);
        nowPlayingView = findViewById(R.id.in_theatres);
        upComingView = findViewById(R.id.upcoming);
        progressBar = findViewById(R.id.progress);
        topRatedView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        nowPlayingView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        upComingView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        fetchTopRatedMoviesList();
    }

    private void fetchUpcomingMoviesList() {
        moviesApi.getUpcomingMovies();
    }

    public void fetchTopRatedMoviesList(){
        moviesApi.getTopRatedMovies(successResponse);
    }

    public void fetchCurrentlyPlayingMoviesList() {
        moviesApi.getCurrentlyPlayingMovies();
    }
}
