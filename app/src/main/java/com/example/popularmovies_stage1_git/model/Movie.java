package com.example.popularmovies_stage1_git.model;

import android.support.annotation.NonNull;

public class Movie {
    private final String mMovieName;
    private final String mMovieImageUri;
    private final String mMovieId;


    public Movie(@NonNull String movieName, @NonNull String movieImageUri, @NonNull String movieId) {
        this.mMovieName = movieName;
        this.mMovieImageUri = movieImageUri;
        this.mMovieId = movieId;
    }


    @NonNull
    public String getMovieName() {
        return mMovieName;
    }

    @NonNull
    public String getMovieImageUri() {
        return mMovieImageUri;
    }


    @NonNull
    public String getMovieId() { return mMovieId; }
}
