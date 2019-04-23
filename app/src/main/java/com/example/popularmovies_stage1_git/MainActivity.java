package com.example.popularmovies_stage1_git;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView movieGridDisplay;
    private Context context;
    //private TextView mNameTextView;
    private JSONArray jsonArrayData;
//    private Button click;
//    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        movieGridDisplay = (GridView) findViewById(R.id.movie_grid_data);
        //mNameTextView = (TextView) findViewById(R.id.movie_name);

        new NetworkUtils().execute();
        //new MNameFromUrl(mNameTextView).execute();

        createGridView();
//        urldata = (TextView) findViewById(R.id.tv_url);
//        click = (Button) findViewById(R.id.button);
//        image = (ImageView) findViewById(R.id.movie_image);
//
//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new NetworkUtils().execute();
//            }
//        });
    }

//    private ArrayAdapter createGridAdapter(List<String> list){
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        return adapter;
//    }

    private void createGridView(){
        GridAdapter movieAdapter = new GridAdapter(this, jsonArrayData);
        movieGridDisplay.setAdapter(movieAdapter);
        //movieAdapter.getView(0,movieGridDisplay,);
    }

//    private void loadImage(){
//        Picasso.get()
//                .load("http://i.imgur.com/DvpvklR.png")
//                .into(image);
//    }

    public class NetworkUtils extends AsyncTask<Void, Void, String>{

        final static String BASE_URL = "http://api.themoviedb.org/3/movie/popular";

        final static String API_KEY = "fb818f2b738d211782879135fc73eed7";

        public URL buildUrl() {
            Uri builtUri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter("api_key",API_KEY)
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
        protected String doInBackground(Void... voids) {
            URL url = buildUrl();
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

            try{
                JSONArray results = new JSONObject(data).getJSONArray("results");
                JSONObject firstMovie;
                String names[] = new String[results.length()];
                StringBuilder stringBuilder = new StringBuilder();

                for(int iter = 0; iter < results.length(); iter++){
                    firstMovie = results.getJSONObject(iter);
                    names[iter] = firstMovie.getString("title");
                    stringBuilder.append(names[iter]+"\n");
                    //mNameTextView.setText(stringBuilder);
                    jsonArrayData = new JSONArray(stringBuilder.toString());
                }
//                -------for grid view-------
//                String[] nameArray = stringBuilder.toString().split("\n");
//                List<String> list = new ArrayList<>(Arrays.asList(nameArray));
//
//                jsonArrayData = new JSONArray(list);
////                ---ArrayAdapter<String> adapter = createGridAdapter(list);
////                --movieGridDisplay.setAdapter(adapter);
//                createGridView(jsonArrayData);
//              -------------------------------

                //urldata.setText(buildUrl().toString());
                //movieGridDisplay.setText(nameArray[2]);
                //movieGridDisplay.setText(stringBuilder.toString());
                //loadImage();

            } catch(JSONException e){
                e.printStackTrace();
            }
        }
    }
}
