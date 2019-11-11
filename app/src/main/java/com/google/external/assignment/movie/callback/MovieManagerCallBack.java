package com.google.external.assignment.movie.callback;

import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Response;
import com.google.external.assignment.movie.model.moviedb.Review;
import com.google.external.assignment.movie.model.moviedb.Video;

public interface MovieManagerCallBack {

    void onSuccess(Response response);

    void onError(Throwable t);

    void onTrailerSuccess(Video.Response response);

    void onTrailerError(Throwable t);

    void onReviewSuccess(Review.Response response);

    void onReviewError(Throwable t);

    void onDetailsSuccess(Movie aMovie);


}
