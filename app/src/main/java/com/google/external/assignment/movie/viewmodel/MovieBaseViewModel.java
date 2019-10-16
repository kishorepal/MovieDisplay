package com.google.external.assignment.movie.viewmodel;

import android.app.Application;
import android.util.Log;

import com.google.external.assignment.movie.callback.MovieManagerCallBack;
import com.google.external.assignment.movie.model.moviedb.Response;
import com.google.external.assignment.movie.model.moviedb.Review;
import com.google.external.assignment.movie.model.moviedb.Video;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class MovieBaseViewModel extends AndroidViewModel {

    public MovieBaseViewModel(@NonNull Application application) {
        super(application);
    }



}
