package com.google.external.assignment.movie.manager;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.external.assignment.movie.api.IMovieDBApi;
import com.google.external.assignment.movie.callback.MovieManagerCallBack;
import com.google.external.assignment.movie.common.Constants;
import com.google.external.assignment.movie.dao.MovieDao;
import com.google.external.assignment.movie.dao.MovieRoomDatabase;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Response;
import com.google.external.assignment.movie.model.moviedb.Review;
import com.google.external.assignment.movie.model.moviedb.Video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MovieManager extends  BaseManager {

    private MovieManagerCallBack mCallBack;
    private IMovieDBApi apiInvokeService; // For Network API invoke

    private MovieRoomDatabase mMovieRoomDatabase; //For Local Database operation



    private static final String TAG = MovieManager.class.getSimpleName();

    private static final String QUERY_PARAM_API_KEY = "api_key";
    private static final String QUERY_PARAM_PAGE = "page";
    private static final String OPTION_POPULAR = "popular";
    private static final String OPTION_TOP_RATED = "top_rated";

    private MovieManager(MovieManagerCallBack callBack, Application application) {

        this.mCallBack = callBack;
        apiInvokeService =  getRetrofitClient().create(IMovieDBApi.class);

        mMovieRoomDatabase = MovieRoomDatabase.getDatabase(application.getApplicationContext());

    }

    private static MovieManager mInstance;

    public static synchronized MovieManager getInstance(MovieManagerCallBack callBack, @NonNull  Application application) {
       // if(mInstance == null) {
            mInstance = new MovieManager(callBack, application);
        //}

      // mCallBack = callBack;
        return mInstance;
    }

    @Override
    protected String getBaseAPIUrl() {
        return Constants.BASE_URL_MOVIE_DB;
    }

    @Override
    protected String getBaseAPIKey() {
        return Constants.API_KEY_MOVIE_DB;
    }

    @Override
    protected Map<Object, Object> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(QUERY_PARAM_API_KEY, getBaseAPIKey());

        return (Map)queryParams;
    }

    public void getMovieInfo(String sortOption, @NonNull Integer pageNo) throws Exception {
        Map quaeryParams = (Map)getQueryParams();
        quaeryParams.put(QUERY_PARAM_PAGE, pageNo);
        Call<Response> call = apiInvokeService.GetMovieList(sortOption, quaeryParams);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.i(TAG, "API Calling successful");
                Response aResponse = response.body();
                mCallBack.onSuccess(aResponse);
                Log.i(TAG, response.message());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e(TAG, "The API Calling failed");

                mCallBack.onError(t);
                t.printStackTrace();
            }
        });



    }


    public void getTrailers(Long videoId) throws Exception {
        Call<Video.Response> call = apiInvokeService.GetTrailerList(String.valueOf(videoId), (Map)getQueryParams());
        call.enqueue(new Callback<Video.Response>() {
            @Override
            public void onResponse(Call<Video.Response> call, retrofit2.Response<Video.Response> response) {
                Log.i(TAG, "API Calling successful");
                //Timber.i("API Calling successful");
                Video.Response aResponse = response.body();
                mCallBack.onTrailerSuccess(aResponse);
                Log.i(TAG, response.message());
            }

            @Override
            public void onFailure(Call<Video.Response> call, Throwable t) {
                Log.e(TAG, "The API Calling failed");
                mCallBack.onTrailerError(t);
                t.printStackTrace();
            }
        });



    }

    public void getReviews(Long videoId) throws Exception {
        Call<Review.Response> call = apiInvokeService.GetReviews(String.valueOf(videoId), (Map)getQueryParams());
        call.enqueue(new Callback<Review.Response>() {
            @Override
            public void onResponse(Call<Review.Response> call, retrofit2.Response<Review.Response> response) {
                Log.i(TAG, "API Calling successful");
                //Timber.i("API Calling successful");
                Review.Response aResponse = response.body();
                mCallBack.onReviewSuccess(aResponse);
                Log.i(TAG, response.message());
            }

            @Override
            public void onFailure(Call<Review.Response> call, Throwable t) {
                Log.e(TAG, "The API Calling failed");
                mCallBack.onReviewError(t);
                t.printStackTrace();
            }
        });



    }

    public void getMovieDetails(Long movieId) throws Exception {
        Call<Movie> call = apiInvokeService.GetMovieDetails(movieId, (Map)getQueryParams());
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, retrofit2.Response<Movie> response) {
                Log.i(TAG, "Movie Details API Calling successful");
                mCallBack.onDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "The API Calling failed");
                mCallBack.onReviewError(t);
                t.printStackTrace();
            }
        });
    }



    /**
     *
     *
     * @return
     * @throws Exception
     */
    public Flowable<Movie> getMovieInfoFromRoomDatabase(Long movieID) throws Exception {

        return mMovieRoomDatabase.movieDao().getMovie(movieID);


    }

    /**
     *
     *
     * @return
     * @throws Exception
     */
    public Flowable<List<Movie>> getMovieInfoFromRoomDatabase() throws Exception {

        return mMovieRoomDatabase.movieDao().getMovieList();
    }


    public Completable insertMovieToRoomDB(Movie aMovie) throws Exception {
        return mMovieRoomDatabase.movieDao().insert(aMovie);
    }



    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }







}
