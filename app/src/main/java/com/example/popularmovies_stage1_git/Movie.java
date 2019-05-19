package com.example.popularmovies_stage1_git;

import android.support.annotation.NonNull;

class Movie {
    private final String mMovieName;
    private final String mMovieImageUri;
    private final String mMovieId;


    Movie(String movieName, String movieImageUri, String movieId) {
        this.mMovieName = movieName;
        this.mMovieImageUri = movieImageUri;
        this.mMovieId = movieId;
    }


    @NonNull
    String getMovieName() {
        return mMovieName;
    }

    @NonNull
    String getMovieImageUri() {
        return mMovieImageUri;
    }


    @NonNull
    public String getMovieId() { return mMovieId; }
}
