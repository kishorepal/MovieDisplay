package com.google.external.assignment.movie.model.moviedb;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review implements Parcelable {


    @Expose
    private String id;
    @Expose
    private String author;
    @Expose
    private String content;
    @Expose
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.author);
        dest.writeString(this.content);
        dest.writeString(this.url);
    }

    protected Review(Parcel in) {
        this.id = in.readString();
        this.author = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        public Review createFromParcel(Parcel source) {return new Review(source);}

        public Review[] newArray(int size) {return new Review[size];}
    };

    public static final class Response {

        @Expose
        public long id;

        @Expose
        public int page;

        @Expose
        @SerializedName("results")
        public List<Review> reviews;

        @Expose
        @SerializedName("total_pages")
        public int totalPages;

        @Expose
        @SerializedName("total_results")
        public int totalResults;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<Review> getReviews() {
            return reviews;
        }

        public void setReviews(List<Review> reviews) {
            this.reviews = reviews;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }
    }
}
