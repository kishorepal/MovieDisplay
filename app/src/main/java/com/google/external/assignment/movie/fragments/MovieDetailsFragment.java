package com.google.external.assignment.movie.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.external.assignment.movie.R;
import com.google.external.assignment.movie.activity.MainActivity;
import com.google.external.assignment.movie.common.utilities.PicassoUtility;
import com.google.external.assignment.movie.databinding.MoveDetailsDataBindings;
import com.google.external.assignment.movie.model.moviedb.Movie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class MovieDetailsFragment extends BaseFragment {

    private MotionLayout mMotionLayout;
    private TextView mBackDropTitle, mYear, mDuration, mScore, mDescription;
    private ImageButton mFavorite;

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

        return movieDetailsDataBinding.getRoot();
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
