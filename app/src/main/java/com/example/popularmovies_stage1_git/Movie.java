package com.example.popularmovies_stage1_git;

import android.support.annotation.NonNull;

class Movie {
    private final String mMovieName;
    private final String mMovieImageUri;

    Movie(String movieName, String movieImageUri) {
        this.mMovieName = movieName;
        this.mMovieImageUri = movieImageUri;
    }

    @NonNull
    String getMovieName() {
        return mMovieName;
    }

    @NonNull
    String getMovieImageUri() {
        return mMovieImageUri;
    }
}
