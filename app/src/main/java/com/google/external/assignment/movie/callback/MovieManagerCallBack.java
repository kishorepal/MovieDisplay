package com.google.external.assignment.movie.callback;

import com.google.external.assignment.movie.model.moviedb.Response;

public interface MovieManagerCallBack {

    void onSuccess(Response response);

    void onError(Throwable t);
}
