package com.example.popularmovies_stage1_git.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies_stage1_git.R;
import com.example.popularmovies_stage1_git.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private final Context context;
    private final List<Movie> movieList;

    public GridAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Movie getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.movie_item, parent, false);
        }

        TextView movieTextView = convertView.findViewById(R.id.movie_text_view);
        ImageView movieImageView = convertView.findViewById(R.id.movie_image_view);

        Movie movie = movieList.get(position);
        movieTextView.setText(movie.getMovieName());
        Picasso.get()
                .load(Uri.parse(movie.getMovieImageUri()))
                .into(movieImageView);

        return convertView;
    }
}
