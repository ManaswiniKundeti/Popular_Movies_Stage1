package com.example.popularmovies_stage1_git;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class ImageFromUrl extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    public ImageFromUrl(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        String urlOfImages = "http://i.imgur.com/DvpvklR.png";
        Bitmap movieImage = null;

        try{
            //get image from server
            InputStream is = new URL(urlOfImages).openStream();
            movieImage = BitmapFactory.decodeStream(is);
        } catch (Exception e){
            e.printStackTrace();
        }
        return movieImage;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //set image into  image_view
        imageView.setImageBitmap(bitmap);
    }
}
