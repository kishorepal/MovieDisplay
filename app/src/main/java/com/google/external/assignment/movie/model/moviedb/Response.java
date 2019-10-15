package com.google.external.assignment.movie.model.moviedb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response  {

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    @SerializedName("results")
    @Expose
    private List<Movie> detailsResult;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Movie> getDetailsResult() {
        return detailsResult;
    }

    public void setDetailsResult(List<Movie> detailsResult) {
        this.detailsResult = detailsResult;
    }




    //   {"page":1,"totalResults":10000,"total_pages":500,"results":[{"popularity":279.411,"vote_count":590,"video":false,"poster_path":"\/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg","id":429203,"adult":false,"backdrop_path":"\/6X2YjjYcs8XyZRDmJAHNDlls7L4.jpg","original_language":"en","original_title":"The Old Man & the Gun","genre_ids":[35,80,18],"title":"The Old Man & the Gun","vote_average":6.3,"overview":"The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.","release_date":"2018-09-28"},


}

