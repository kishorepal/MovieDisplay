package com.google.external.assignment.movie.model.moviedb;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Parcelable {

        @SerializedName("popularity")
        @Expose
        private Float popularity;

        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;

        @SerializedName("poster_path")
        @Expose
        private String posterPath;

        @SerializedName("original_title")
        @Expose
        private String originalTitle;


        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("overview")
        @Expose
        private String overview;

        @SerializedName("release_date")
        @Expose
        private String releaseDate;

        @SerializedName("vote_average")
        @Expose
        private double voteAverage;



        @SerializedName("adult")
        @Expose
        private boolean adult;


        @SerializedName("runtime")
        @Expose
        private int runTime;




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


    @Override
    public void writeToParcel(Parcel dest, int flags) {
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


    }

    private Movie(Parcel in){
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
    }



    @Override

    public int describeContents() {

        return 0;

    }





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
