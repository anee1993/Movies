package com.example.anirudh.dumbapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anirudh on 3/24/2018.
 */

public class MovieDetails {
    @SerializedName("poster_path")
    private String movieImage;
    @SerializedName("original_title")
    private String movieName;
    @SerializedName("overview")
    private String movieDescription;
    @SerializedName("vote_average")
    private String movieRating;

    public String getMovieName(){
        return movieName;
    }
    public String getMovieDescription(){
        return movieDescription;
    }
    public String getMovieRating(){
        return movieRating;
    }
    public String getMovieImage(){
        return movieImage;
    }
}
