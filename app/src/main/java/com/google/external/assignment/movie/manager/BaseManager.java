package com.google.external.assignment.movie.manager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseManager {

    protected Retrofit getRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseAPIUrl())
                .addConverterFactory(getConverterFactory())
                .build();

        return retrofit;
    }

    protected abstract  String getBaseAPIUrl();

    protected abstract  String getBaseAPIKey();

    protected abstract Map<Object, Object> getQueryParams();

    protected Converter.Factory getConverterFactory(){
        return GsonConverterFactory.create();
    }
}
