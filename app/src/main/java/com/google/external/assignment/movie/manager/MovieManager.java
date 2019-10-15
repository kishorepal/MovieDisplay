package com.google.external.assignment.movie.manager;

import android.util.Log;

import com.google.external.assignment.movie.api.IMovieDBApi;
import com.google.external.assignment.movie.callback.MovieManagerCallBack;
import com.google.external.assignment.movie.common.Constants;
import com.google.external.assignment.movie.model.moviedb.Response;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MovieManager extends  BaseManager {

    private MovieManagerCallBack mCallBack;
    private IMovieDBApi apiInvokeService;

    private static final String TAG = MovieManager.class.getSimpleName();

    private static final String QUERY_PARAM_API_KEY = "api_key";
    private static final String OPTION_POPULAR = "popular";
    private static final String OPTION_TOP_RATED = "top_rated";

    private MovieManager(MovieManagerCallBack callBack) {

        this.mCallBack = callBack;
        apiInvokeService =  getRetrofitClient().create(IMovieDBApi.class);
    }

    private static MovieManager mInstance;

    public static synchronized MovieManager getInstance(MovieManagerCallBack callBack) {
        if(mInstance == null) {
            mInstance = new MovieManager(callBack);
        }
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

    public void getMovieInfo(String sortOption) throws Exception {
        Call<Response> call = apiInvokeService.GetMovieList(sortOption, (Map)getQueryParams());
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.i(TAG, "API Calling successful");
                //Timber.i("API Calling successful");
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


    public void getTrailers(Integer videoId) throws Exception {
        Call<Response> call = apiInvokeService.GetTrailerList(String.valueOf(videoId), (Map)getQueryParams());
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.i(TAG, "API Calling successful");
                //Timber.i("API Calling successful");
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

    public void getReviews(Integer videoId) throws Exception {
        Call<Response> call = apiInvokeService.GetReviews(String.valueOf(videoId), (Map)getQueryParams());
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.i(TAG, "API Calling successful");
                //Timber.i("API Calling successful");
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




}
