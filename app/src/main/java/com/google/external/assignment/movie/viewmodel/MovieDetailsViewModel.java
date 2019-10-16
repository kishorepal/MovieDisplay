package com.google.external.assignment.movie.viewmodel;

import android.app.Application;
import android.util.Log;

import com.google.external.assignment.movie.callback.MovieManagerCallBack;
import com.google.external.assignment.movie.common.Constants;
import com.google.external.assignment.movie.common.utilities.SharedPreferenceUtility;
import com.google.external.assignment.movie.manager.MovieManager;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Response;
import com.google.external.assignment.movie.model.moviedb.Review;
import com.google.external.assignment.movie.model.moviedb.Video;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailsViewModel extends AndroidViewModel {

    private static final String TAG = "MovieDetailsViewModel";

    private MutableLiveData<List<Video>> videoList = new MutableLiveData<>() ;

    private MutableLiveData<List<Review>> reviewList = new MutableLiveData<>();

    private MovieManager mMovieManager;


    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        try {
            mMovieManager = MovieManager.getInstance(mMovieManagerCallBack);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void getTrailerList(Long videoId) throws Exception{
        mMovieManager.getTrailers(videoId);
    }

    public void getReviewList(Long videoId) throws Exception{
        mMovieManager.getReviews(videoId);
    }

    public MutableLiveData<List<Video>> getVideoList() {
        return videoList;
    }

    public MutableLiveData<List<Review>> getReviewList() {
        return reviewList;
    }

    public MovieManagerCallBack mMovieManagerCallBack = new MovieManagerCallBack() {
        @Override
        public void onSuccess(Response response) {

        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onTrailerSuccess(Video.Response response) {
            Log.i(TAG, "onTrailerSuccess Callback invoked");
            videoList.postValue(response.getVideos());
        }

        @Override
        public void onTrailerError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onReviewSuccess(Review.Response response) {
            Log.i(TAG, "onTrailerSuccess Callback invoked");
            reviewList.postValue(response.getReviews());
        }

        @Override
        public void onReviewError(Throwable t) {
            t.printStackTrace();
        }
    };
}
