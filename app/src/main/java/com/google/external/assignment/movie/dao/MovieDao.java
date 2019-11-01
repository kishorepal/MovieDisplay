package com.google.external.assignment.movie.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.external.assignment.movie.model.moviedb.Movie;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Movie aMovie);

    @Query("Select * from MOVIE WHERE id = :movieID LIMIT 1")
    Flowable<Movie> getMovie(Long movieID);

    @Query("SELECT * FROM MOVIE where favourite = 1 order by id ASC")
    Flowable<List<Movie>> getMovieList();
}
