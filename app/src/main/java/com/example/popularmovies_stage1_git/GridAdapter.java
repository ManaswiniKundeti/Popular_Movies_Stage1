package com.example.popularmovies_stage1_git;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class GridAdapter extends BaseAdapter {

    private final Context context;
    private final JSONArray movieList;

    public GridAdapter(Context context, JSONArray movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return this.movieList.length();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        JSONObject currentJson = new JSONObject();
        try{
            currentJson = this.movieList.getJSONObject(position);
        }catch (Exception e){
            e.printStackTrace();
        }
        return currentJson;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View gridView = inflater.inflate(R.layout.grid_adapter, null);
        JSONObject SingleObject = (JSONObject) getItem(position);

        //set grid_adapter elements to variables
        ImageView movieImageView = (ImageView) gridView.findViewById(R.id.movie_image);
        TextView movieName = (TextView) gridView.findViewById(R.id.movie_name);

        //set to adapter image_view and text_view
        try{
            new ImageFromUrl(movieImageView).execute(SingleObject.getString("image_thumb_from_JSON"));
            movieName.setText(String.valueOf(position));

        } catch (Exception e){
            e.printStackTrace();
        }
        return gridView;
    }
}
