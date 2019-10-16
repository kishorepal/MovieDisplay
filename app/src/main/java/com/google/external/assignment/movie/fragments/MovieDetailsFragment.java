package com.google.external.assignment.movie.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.external.assignment.movie.R;
import com.google.external.assignment.movie.activity.MainActivity;
import com.google.external.assignment.movie.adapter.MovieAdapter;
import com.google.external.assignment.movie.adapter.ReviewAdapter;
import com.google.external.assignment.movie.adapter.VideoAdapter;
import com.google.external.assignment.movie.common.utilities.PicassoUtility;
import com.google.external.assignment.movie.databinding.MoveDetailsDataBindings;
import com.google.external.assignment.movie.databinding.ReviewDataBinding;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Review;
import com.google.external.assignment.movie.model.moviedb.Video;
import com.google.external.assignment.movie.viewmodel.MovieDetailsViewModel;
import com.google.external.assignment.movie.viewmodel.MovieViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDetailsFragment extends BaseFragment {

    private MovieDetailsViewModel mMovieDetailsViewModel;

    private MotionLayout mMotionLayout;
    private TextView mBackDropTitle, mYear, mDuration, mScore, mDescription;
    private ImageButton mFavorite;
    private RecyclerView listViewReview;

    private RecyclerView listViewTrailer;

    private Movie mMovie;

    public  MovieDetailsFragment(Movie aMovie) {
        this.mMovie = aMovie;

    }

    public MovieDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setBackgroundDrawable(null);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        if(savedInstanceState != null) {
            mMovie = savedInstanceState.getParcelable("movie");
        }
        MoveDetailsDataBindings  movieDetailsDataBinding = DataBindingUtil.inflate(inflater,R.layout.movie_details_fragment, container, false);

        movieDetailsDataBinding.setMovieModel(mMovie);
        setActionBar();

        init(movieDetailsDataBinding.getRoot());

        return movieDetailsDataBinding.getRoot();
    }

    private void init(View aView) {

        try {

            listViewReview = (RecyclerView)aView.findViewById(R.id.list_review);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

            listViewReview.setLayoutManager(mLayoutManager );
            listViewReview.setHasFixedSize(true);
            final ReviewAdapter aReviewAdapter = new ReviewAdapter(getContext(), this);
            listViewReview.setAdapter(aReviewAdapter);


            listViewTrailer = (RecyclerView)aView.findViewById(R.id.list_trailer);
            listViewTrailer.setLayoutManager(new LinearLayoutManager(getContext()));
            listViewTrailer.setHasFixedSize(false);
            final VideoAdapter aVideoAdapter =  new VideoAdapter(getContext(), this);
            listViewTrailer.setAdapter(aVideoAdapter);


            mMovieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);


            mMovieDetailsViewModel.getVideoList().observe(this, new Observer<List<Video>>() {
                @Override
                public void onChanged(List<Video> videos) {
                    aVideoAdapter.setVideoList(videos);
                }
            });
            mMovieDetailsViewModel.getTrailerList(mMovie.getId());


            mMovieDetailsViewModel.getReviewList().observe(this, new Observer<List<Review>>() {
                @Override
                public void onChanged(List<Review> reviews) {
                    aReviewAdapter.setReviewList(reviews);
                }
            });

            mMovieDetailsViewModel.getReviewList(mMovie.getId());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void setActionBar() {
        try {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Movie Details");
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("movie", mMovie);

        super.onSaveInstanceState(outState);
    }
}
