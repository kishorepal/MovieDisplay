package com.google.external.assignment.movie.model.moviedb;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "MOVIE")
public class Movie extends BaseObservable implements Parcelable {


        @Expose
        @PrimaryKey
        @ColumnInfo
        private Long id;

        @SerializedName("popularity")
        @Expose
        @ColumnInfo
        private Float popularity;

        @SerializedName("vote_count")
        @Expose
        @ColumnInfo
        private Integer voteCount;

        @SerializedName("poster_path")
        @Expose
        @ColumnInfo
        private String posterPath;

        @SerializedName("original_title")
        @Expose
        @ColumnInfo
        private String originalTitle;


        @SerializedName("title")
        @Expose
        @ColumnInfo
        private String title;

        @SerializedName("overview")
        @Expose
        @ColumnInfo
        private String overview;

        @SerializedName("release_date")
        @Expose
        @ColumnInfo
        private String releaseDate;

        @SerializedName("vote_average")
        @Expose
        @ColumnInfo
        private double voteAverage;



        @SerializedName("adult")
        @Expose
        @ColumnInfo
        private boolean adult;


        @SerializedName("runtime")
        @Expose
        @ColumnInfo
        private int runTime;


        /**
         * Favourite Option from UI
         */
        @ColumnInfo
        @Bindable
        private boolean favourite;


        public  Movie () {

        }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }


    public boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }


    public Movie copyMovie() {
        Movie newMovie = new Movie();
        newMovie.setId(this.getId());
        newMovie.setFavourite(this.getFavourite());
        newMovie.setAdult(this.isAdult());
        newMovie.setOriginalTitle(this.getOriginalTitle());
        newMovie.setOverview(this.getOverview());
        newMovie.setPopularity(this.getPopularity());
        newMovie.setPosterPath(this.getPosterPath());
        newMovie.setReleaseDate(this.getReleaseDate());
        newMovie.setRunTime(this.getRunTime());
        newMovie.setTitle(this.getTitle());
        newMovie.setVoteAverage(this.getVoteAverage());
        newMovie.setVoteCount(this.getVoteCount());
        return newMovie;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeFloat(popularity);
        dest.writeInt(voteCount);
        dest.writeString(posterPath);
        dest.writeString(originalTitle);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeDouble(voteAverage);
        dest.writeByte((byte)(adult ?1:0));
        dest.writeInt(runTime);
        dest.writeByte((byte)(favourite?1:0));


    }

    private Movie(Parcel in){
        id = in.readLong();
        popularity = in.readFloat();
        voteCount = in.readInt();
        posterPath = in.readString();
        originalTitle = in.readString();
        title = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readDouble();
        adult = in.readByte() != 0;
        runTime = in.readInt();
        favourite = in.readByte() != 0;

    }



    @Override

    public int describeContents() {

        return 0;

    }





    @Ignore
    public final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override

        public Movie createFromParcel(Parcel parcel) {

            return new Movie(parcel);

        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[0];
        }
    };

    //   {"page":1,"totalResults":10000,"total_pages":500,"results":[{"popularity":279.411,"vote_count":590,"video":false,"poster_path":"\/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg","id":429203,"adult":false,"backdrop_path":"\/6X2YjjYcs8XyZRDmJAHNDlls7L4.jpg","original_language":"en","original_title":"The Old Man & the Gun","genre_ids":[35,80,18],"title":"The Old Man & the Gun","vote_average":6.3,"overview":"The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.","release_date":"2018-09-28"},

}
