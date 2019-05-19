package com.example.popularmovies_stage1_git.retrofit;

import com.example.popularmovies_stage1_git.model.Movie;
import com.example.popularmovies_stage1_git.model.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("{filter}")
    Call<MovieResult> getMovies(@Path("filter") String filter, @Query("api_key") String apiKey);

    @GET("{movieId}")
    Call<Movie> getMovie(@Path("movieId") String movieId, @Query("api_key") String apiKey);
}
