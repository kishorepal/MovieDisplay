package com.google.external.assignment.movie.viewmodel;

import android.app.Application;
import android.util.Log;

import com.google.external.assignment.movie.callback.MovieManagerCallBack;
import com.google.external.assignment.movie.common.Constants;
import com.google.external.assignment.movie.common.utilities.SharedPreferenceUtility;
import com.google.external.assignment.movie.manager.MovieManager;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Response;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class MovieViewModel extends AndroidViewModel {

    private MutableLiveData<List<Movie>> movieList = new MutableLiveData<>() ;

    private MutableLiveData<String> sortOption = new MutableLiveData<>();

    private MovieManager mMovieManager;

    private SharedPreferenceUtility mSharedPreferenceUtility;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        try {
            mSharedPreferenceUtility = SharedPreferenceUtility.getInstance(application.getApplicationContext());
            mMovieManager = MovieManager.getInstance(new MovieManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    Log.i("MovieViewModel", "OnSuccess Callback invoked");
                    movieList.postValue(response.getDetailsResult());
                }

                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                }
            });

            String option = mSharedPreferenceUtility.getValue(SharedPreferenceUtility.PREF_KEY_SORT_OPTION, Constants.SORT_BY_POPULARITY);

            sortOption.postValue(option);
            mMovieManager.getMovieInfo(option);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public MutableLiveData<List<Movie>> getMovieList() {
        return movieList;
    }


    public MutableLiveData<String> getSortOption() {
        return this.sortOption;
    }



    public void SortByPopularity() {
        try {
            mSharedPreferenceUtility.putString(SharedPreferenceUtility.PREF_KEY_SORT_OPTION, Constants.SORT_BY_POPULARITY);
            sortOption.postValue(Constants.SORT_BY_POPULARITY);
            mMovieManager.getMovieInfo(Constants.SORT_BY_POPULARITY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void SortByTopRated() {
        try {
            mSharedPreferenceUtility.putString(SharedPreferenceUtility.PREF_KEY_SORT_OPTION, Constants.SORT_BY_TOP_RATED);
            sortOption.postValue(Constants.SORT_BY_TOP_RATED);
            mMovieManager.getMovieInfo(Constants.SORT_BY_TOP_RATED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    }

