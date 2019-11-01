package com.google.external.assignment.movie.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.external.assignment.movie.model.moviedb.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "movie_database";

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE MOVIE");
           // database.execSQL("ALTER TABLE MOVIE  COLUMN comment BIGINT NOT NULL;\nADD COLUMN newAttribute INTEGER DEFAULT 0 NOT NULL")
        }
    };


    public abstract MovieDao movieDao();

    private static volatile MovieRoomDatabase INSTANCE;

    public static MovieRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDatabase.class, DATABASE_NAME)

                            //.addMigrations(MIGRATION_1_2)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
