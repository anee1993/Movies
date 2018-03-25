package com.example.anirudh.dumbapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anirudh.dumbapp.utility.DescriptionClick;
import com.example.anirudh.dumbapp.model.MovieDetails;
import com.example.anirudh.dumbapp.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by Anirudh on 3/24/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    final Context context;
    final List<MovieDetails> moviesList;
    final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w200/";
    public MoviesAdapter(Context context, List<MovieDetails> moviesList){
            this.context = context;
            this.moviesList = moviesList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.horizontal_movie_list,parent,false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        if(moviesList!=null){
                final String imagePath = IMAGE_BASE_URL+moviesList.get(position).getMovieImage();
                final String desc = moviesList.get(position).getMovieDescription();
                Glide.with(context).load(imagePath).into(holder.imageView);
                holder.movieName.setText(moviesList.get(position).getMovieName());
                holder.movieDesc.setText(desc);
                holder.movieDesc.setOnClickListener(new DescriptionClick(desc,context));
                holder.movieRating.setText(String.format(Locale.US,context.getString(R.string.rating),moviesList.get(position).getMovieRating()));
        }
    }

    @Override
    public int getItemCount() {
        return moviesList!=null?moviesList.size():0;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        final TextView movieName;
        final TextView movieDesc;
        final TextView movieRating;
        final ImageView imageView;

        public MovieViewHolder(View itemView) {
           super(itemView);
           imageView = itemView.findViewById(R.id.poster);
           movieName = itemView.findViewById(R.id.movie_name);
           movieDesc = itemView.findViewById(R.id.movie_desc);
           movieRating = itemView.findViewById(R.id.movie_rating);
       }
   }
}
