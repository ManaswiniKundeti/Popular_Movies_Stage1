package com.example.popularmovies_stage1_git.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    private String mMovieName;

    @SerializedName("poster_path")
    private String mMovieImageUri;

    @SerializedName("id")
    private String mMovieId;

    @SerializedName("overview")
    private String mSummary;

    @SerializedName("vote_average")
    private String mRating;

    @SerializedName("release_date")
    private String mReleaseDate;

    public Movie(String mMovieName, String mMovieImageUri, String mMovieId, String mSummary, String mRating, String mReleaseDate) {
        this.mMovieName = mMovieName;
        this.mMovieImageUri = mMovieImageUri;
        this.mMovieId = mMovieId;
        this.mSummary = mSummary;
        this.mRating = mRating;
        this.mReleaseDate = mReleaseDate;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmSummary() {
        return mSummary;
    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public String getmMovieName() {
        return mMovieName;
    }

    public void setmMovieName(String mMovieName) {
        this.mMovieName = mMovieName;
    }

    public String getmMovieImageUri() {
        return mMovieImageUri;
    }

    public void setmMovieImageUri(String mMovieImageUri) {
        this.mMovieImageUri = mMovieImageUri;
    }

    public String getmMovieId() {
        return mMovieId;
    }

    public void setmMovieId(String mMovieId) {
        this.mMovieId = mMovieId;
    }
}
