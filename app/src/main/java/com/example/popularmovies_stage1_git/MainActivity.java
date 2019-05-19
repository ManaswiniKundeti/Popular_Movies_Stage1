package com.example.popularmovies_stage1_git;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    final static String POPULAR = "popular";

    final static String TOP_RATED = "top_rated";

    public List<Movie> movieList = new ArrayList<Movie>();
    GridAdapter movieAdapter;
    private ProgressBar movieProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView mGridView = findViewById(R.id.movie_grid_data);
        movieAdapter = new GridAdapter(this, movieList);
        mGridView.setAdapter(movieAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedMovie = movieAdapter.getItem(position);
                
            }
        });

        movieProgressBar = findViewById(R.id.movie_progress_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }

    public void onItemClick(MenuItem item){

        if(item.getItemId() == R.id.popular) {
            new NetworkUtils().execute(POPULAR);

        } else if (item.getItemId() == R.id.top_rated) {
//            Toast toast = Toast.makeText(getApplicationContext(), "Item 2 clicked", Toast.LENGTH_SHORT);
//            toast.show();
            new NetworkUtils().execute(TOP_RATED);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (movieList.isEmpty()) {
            new NetworkUtils().execute(POPULAR);
        }
    }

    public void toggleProgressBar(boolean isLoading) {
        movieProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    public class NetworkUtils extends AsyncTask<String, Void, String>{

        final static String BASE_URL = "http://api.themoviedb.org/3/movie";

        final static String API_KEY = "fb818f2b738d211782879135fc73eed7";


        public URL buildUrl(String string) {
            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(string)
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
            toggleProgressBar(true);
        }

        @Override
        protected String doInBackground(String... strings) {
            String myParam = strings[0];
            URL url = buildUrl(myParam);
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
            toggleProgressBar(false);
            Log.d(TAG, data);

            try{
                movieList.clear(); //clear the movie list each time

                JSONArray results = new JSONObject(data).getJSONArray("results");

                for(int iter = 0; iter < results.length(); iter++){
                    JSONObject firstMovie = results.getJSONObject(iter);
                    String name = firstMovie.getString("title");
                    String posterPath = firstMovie.getString("poster_path");
                    String movieImageUri = "https://image.tmdb.org/t/p/w500"+posterPath;
                    movieList.add(new Movie(name, movieImageUri));
                }
                movieAdapter.notifyDataSetChanged();

            } catch(JSONException e){
                e.printStackTrace();
            }
        }
    }
}
