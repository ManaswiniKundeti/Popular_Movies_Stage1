package com.example.popularmovies_stage1_git.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit sRetrofit;

    public final static String API_KEY = "fb818f2b738d211782879135fc73eed7";


    public static Retrofit getRetorfitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/3/movie/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return sRetrofit;
    }
}
