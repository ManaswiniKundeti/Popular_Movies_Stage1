package com.example.popularmovies_stage1_git.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmovies_stage1_git.R;
import com.example.popularmovies_stage1_git.model.Movie;
import com.example.popularmovies_stage1_git.retrofit.MovieService;
import com.example.popularmovies_stage1_git.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView mDetailImage;
    private TextView mNameTextView;
    private TextView mSummaryTextView;
    private TextView mRatingTextView;
    private TextView mReleaseDateTextView;

    private MovieService mMovieService;

    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setTitle(getString(R.string.activity_details_title));

        mDetailImage = findViewById(R.id.movie_detail_image);
        mNameTextView = findViewById(R.id.movie_detail_name);
        mSummaryTextView = findViewById(R.id.movie_detail_summary);
        mRatingTextView = findViewById(R.id.movie_detail_rating);
        mReleaseDateTextView = findViewById(R.id.movie_detail_release_date);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            String movieId = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            getMovie(movieId);
        }

    }

    private void getMovie(final String movieId) {
        if (mMovieService == null) {
            mMovieService = RetrofitInstance.getRetorfitInstance().create(MovieService.class);
        }

        Call<Movie> call = mMovieService.getMovie(movieId, RetrofitInstance.API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Movie movie = response.body();

                    Picasso.get()
                            .load(Uri.parse("https://image.tmdb.org/t/p/w500" + movie.getmMovieImageUri()))
                            .into(mDetailImage);
                    mNameTextView.setText(movie.getmMovieName());
                    mSummaryTextView.setText(movie.getmSummary());
                    mRatingTextView.setText(movie.getmRating());
                    mReleaseDateTextView.setText(movie.getmReleaseDate());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "Error fetching movie", t);
                Toast.makeText(MovieDetailActivity.this,
                        "Error fetching movie",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
