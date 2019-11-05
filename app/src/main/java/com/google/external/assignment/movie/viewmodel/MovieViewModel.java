package com.google.external.assignment.movie.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.external.assignment.movie.callback.MovieManagerCallBack;
import com.google.external.assignment.movie.common.Constants;
import com.google.external.assignment.movie.common.utilities.SharedPreferenceUtility;
import com.google.external.assignment.movie.manager.MovieManager;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Response;
import com.google.external.assignment.movie.model.moviedb.Review;
import com.google.external.assignment.movie.model.moviedb.Video;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.util.StringUtil;

import org.apache.commons.lang3.StringUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends MovieBaseViewModel {

    private MutableLiveData<List<Movie>> movieList = new MutableLiveData<>() ;

    private MutableLiveData<String> sortOption = new MutableLiveData<>();

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private MovieManager mMovieManager;

    private SharedPreferenceUtility mSharedPreferenceUtility;

    private Integer mCurrentPage = 1;

    private void appendMovie(List<Movie> movieList) {

       List<Movie> newMovieList =  this.movieList.getValue();
       newMovieList.addAll(movieList);

       this.movieList.postValue(newMovieList);

    }



    public MovieViewModel(@NonNull Application application) {
        super(application);

        try {
            mSharedPreferenceUtility = SharedPreferenceUtility.getInstance(application.getApplicationContext());
            mMovieManager = MovieManager.getInstance(mMovieManagerCallBack, application);

            String option = mSharedPreferenceUtility.getValue(SharedPreferenceUtility.PREF_KEY_SORT_OPTION, Constants.SORT_BY_POPULARITY);
            mCurrentPage = 1;
            sortOption.postValue(option);
            if(StringUtils.compare(option, Constants.SORT_BY_FAVOURITE) == 0) {
               SortByFavourite();
               return;
            }
            mMovieManager.getMovieInfo(option, mCurrentPage);

        } catch (Exception ex) {
            Toast.makeText(getApplication().getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
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
            mCurrentPage = 1;
            mCompositeDisposable.clear();
            mSharedPreferenceUtility.putString(SharedPreferenceUtility.PREF_KEY_SORT_OPTION, Constants.SORT_BY_POPULARITY);
            sortOption.postValue(Constants.SORT_BY_POPULARITY);
            mMovieManager.getMovieInfo(Constants.SORT_BY_POPULARITY, mCurrentPage);
        } catch (Exception ex) {
            Toast.makeText(getApplication().getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void SortByTopRated() {
        try {
            mCurrentPage = 1;
            mCompositeDisposable.clear();
            mSharedPreferenceUtility.putString(SharedPreferenceUtility.PREF_KEY_SORT_OPTION, Constants.SORT_BY_TOP_RATED);
            sortOption.postValue(Constants.SORT_BY_TOP_RATED);
            mMovieManager.getMovieInfo(Constants.SORT_BY_TOP_RATED, mCurrentPage);
        } catch (Exception ex) {
            Toast.makeText(getApplication().getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }


    public void SortByFavourite() {
        try {
            mCurrentPage = 1;
            mSharedPreferenceUtility.putString(SharedPreferenceUtility.PREF_KEY_SORT_OPTION, Constants.SORT_BY_FAVOURITE);
            sortOption.postValue(Constants.SORT_BY_FAVOURITE);

            try {
                mCompositeDisposable.add(getMovieInfoFromRoomDatabase()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((movieList)->
                        {
                            Log.i("FETCH", "Successfully inserted");
                            this.movieList.postValue(movieList);
                        }));


            } catch (Exception ex) {
                Toast.makeText(getApplication().getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            Toast.makeText(getApplication().getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }


    public void loadNextPageData() {
        try {
            mCurrentPage++;
            String option = mSharedPreferenceUtility.getValue(SharedPreferenceUtility.PREF_KEY_SORT_OPTION, Constants.SORT_BY_POPULARITY);
            mMovieManager.getMovieInfo(option, mCurrentPage);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getApplication(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    /**
     *
     *
     * @return
     * @throws Exception
     */
    public Flowable<List<Movie>> getMovieInfoFromRoomDatabase() throws Exception {

        return mMovieManager.getMovieInfoFromRoomDatabase();


    }

    public MovieManagerCallBack mMovieManagerCallBack = new MovieManagerCallBack() {
        @Override
        public void onSuccess(Response response) {
            if(response == null) {
                //FIXME Add a Message later
                return;
            }
            Log.i("MovieViewModel", "OnSuccess Callback invoked");

            Log.i("MovieViewModel", "OnSuccess Callback invoked");
            Log.i("MovieViewModel", String.format("Current Page of Response [%d]", response.getPage()));
            if(response.getPage() > 1) {
                appendMovie(response.getDetailsResult());
                return;
            }

            movieList.postValue(response.getDetailsResult());

        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
            Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            movieList.postValue(new ArrayList<Movie>());
        }

        @Override
        public void onTrailerSuccess(Video.Response response) {

        }

        @Override
        public void onTrailerError(Throwable t) {

        }

        @Override
        public void onReviewSuccess(Review.Response response) {

        }

        @Override
        public void onReviewError(Throwable t) {

        }
    };

    }

