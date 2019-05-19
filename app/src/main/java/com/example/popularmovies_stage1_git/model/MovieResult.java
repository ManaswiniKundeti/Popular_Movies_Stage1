package com.example.popularmovies_stage1_git.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult {

    @SerializedName("results")
    private List<Movie> movieList;

    public MovieResult(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
