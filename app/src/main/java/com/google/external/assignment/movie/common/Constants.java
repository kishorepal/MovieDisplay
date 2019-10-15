package com.google.external.assignment.movie.common;

import com.google.external.assignment.movie.BuildConfig;

public class Constants {

    public static final String SORT_BY_POPULARITY = "popular";
    public static final String SORT_BY_TOP_RATED = "top_rated";

    // FIXME Change the API Key
    public static final String API_KEY_MOVIE_DB = BuildConfig.MOVIEDB_API_KEY;//"da61481c696b839e4db3c86449141754";
    public static final String BASE_URL_MOVIE_DB = "https://api.themoviedb.org/3/";
    public static final String BASE_URL_PICASSO = "http://image.tmdb.org/t/p/w780%s";
}
