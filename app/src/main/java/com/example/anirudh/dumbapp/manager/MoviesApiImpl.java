package com.example.anirudh.dumbapp.manager;

import android.content.Context;
import android.util.Log;

import com.example.anirudh.dumbapp.application.MoviesApplication;
import com.example.anirudh.dumbapp.model.MovieDetails;
import com.example.anirudh.dumbapp.model.Page;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Anirudh on 3/24/2018.
 */

public class MoviesApiImpl implements MoviesApi {
    private static final String API_KEY = "9a76aa06ad5d8300293b97802cbf1ebb";
    final CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<MovieDetails> moviesList;
    NotifySuccess notifySuccess;
    private Context context;
    int count = 0;

    public MoviesApiImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getTopRatedMovies(NotifySuccess notifySuccess) {
        this.notifySuccess = notifySuccess;
        compositeDisposable.add(((MoviesApplication) context).getApiInterface().getTopRatedMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(this::handleResponse, this::handleError));
    }

    @Override
    public void getCurrentlyPlayingMovies() {
        compositeDisposable.add(((MoviesApplication) context).getApiInterface().getCurrentlyPlayingMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(this::handleResponse, this::handleError));
    }

    @Override
    public void getUpcomingMovies() {
        compositeDisposable.add(((MoviesApplication) context).getApiInterface().getUpcomingMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(Page page) {
        if (page != null) {
            moviesList = page.getResults();
        }
        count+=1;
        notifySuccess.notifySuccessResponse(moviesList,count);
    }

    private void handleError(Throwable error) {
        Log.d("Failed", error.getMessage());
    }

    public interface NotifySuccess {
        void notifySuccessResponse(List<MovieDetails> movieDetails,int count);
    }
}
