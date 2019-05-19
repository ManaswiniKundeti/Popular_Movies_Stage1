package com.example.popularmovies_stage1_git.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies_stage1_git.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView mDetailImage;
    private TextView mDetailTextOne;
    private TextView mDetailTextTwo;
    private TextView mDetailTextThree;
    private TextView mDetailTextFour;

    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mDetailImage = findViewById(R.id.movieDetailImage);
        mDetailTextOne = findViewById(R.id.movieDetailText_one);
        mDetailTextTwo = findViewById(R.id.movieDetailText_two);
        mDetailTextThree = findViewById(R.id.movieDetailText_three);
        mDetailTextFour = findViewById(R.id.movieDetailText_four);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            String movieId = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            new NetworkForMovieDetail().execute(movieId);
        }

    }


    public class NetworkForMovieDetail extends AsyncTask<String, Void, String> {

        final static String BASE_URL = "http://api.themoviedb.org/3/movie";

        final static String API_KEY = "fb818f2b738d211782879135fc73eed7";


        public URL buildUrl(String movieId) {
            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(movieId)
                    .appendQueryParameter("api_key",API_KEY)
                    .build();
            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String movieId = strings[0];
            URL url = buildUrl(movieId);
            StringBuilder sb = new StringBuilder();
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while(null != line){
                    line = bufferedReader.readLine();
                    sb.append(line);
                }
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            Log.d(TAG, data);

            try {

                JSONObject movieData = new JSONObject(data);

                String posterPath = movieData.getString("poster_path");
                String movieImageUri = "https://image.tmdb.org/t/p/w500" + posterPath;
                String name = movieData.getString("title");
                String summary = movieData.getString("overview");
                String userRating = movieData.getString("vote_average"); //7.8
                String releaseDate = movieData.getString("release_date");
                Picasso.get()
                        .load(Uri.parse(movieImageUri))
                        .into(mDetailImage);
                mDetailTextOne.setText(name);
                mDetailTextTwo.setText(summary);
                mDetailTextThree.setText(userRating);
                mDetailTextFour.setText(releaseDate);

            } catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

}
