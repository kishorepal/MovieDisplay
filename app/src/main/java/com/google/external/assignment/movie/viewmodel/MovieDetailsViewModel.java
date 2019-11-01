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
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.apache.commons.lang3.mutable.Mutable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.ObservableSource;

public class MovieDetailsViewModel extends AndroidViewModel {

    private static final String TAG = "MovieDetailsViewModel";


    private ObservableField<Movie> movie = new ObservableField<>();

    private MutableLiveData<List<Video>> videoList = new MutableLiveData<>() ;

    private MutableLiveData<List<Review>> reviewList = new MutableLiveData<>();

    private ObservableField<Boolean> favorite = new ObservableField<>();



    private MovieManager mMovieManager;


    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        try {
            mMovieManager = MovieManager.getInstance(mMovieManagerCallBack, application);
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

    public ObservableField<Boolean> getFavorite() {
        return favorite;
    }

    public void setFavorite(ObservableField<Boolean> favorite) {
        this.favorite = favorite;
    }

    public ObservableField<Movie> getMovie() {
        return movie;
    }

    public void updateMovie(Movie aMovie) {
            //  movie.postValue(aMovie);
    }



    public MutableLiveData<List<Video>> getVideoList() {
        return videoList;
    }

    public MutableLiveData<List<Review>> getReviewList() {
        return reviewList;
    }




    public Completable insertMovieToRoomDB(Movie aMovie) throws Exception {
        return mMovieManager.insertMovieToRoomDB(aMovie);
    }

    /**
     *
     *
     * @return
     * @throws Exception
     */
    public Flowable<Movie> getMovieInfoFromRoomDatabase(Long mopvieID) throws Exception {

        return mMovieManager.getMovieInfoFromRoomDatabase(mopvieID);


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
