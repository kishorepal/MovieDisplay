package com.google.external.assignment.movie.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.external.assignment.movie.R;
import com.google.external.assignment.movie.activity.MainActivity;
import com.google.external.assignment.movie.adapter.ReviewAdapter;
import com.google.external.assignment.movie.adapter.VideoAdapter;
import com.google.external.assignment.movie.databinding.MoveDetailsDataBindings;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Review;
import com.google.external.assignment.movie.model.moviedb.Video;
import com.google.external.assignment.movie.viewmodel.MovieDetailsViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsFragment extends BaseFragment {

    private static final String TAG = "MovieDetailsFragment";


    private MovieDetailsViewModel mMovieDetailsViewModel;

    private RecyclerView listViewReview;

    private RecyclerView listViewTrailer;

    private Movie mMovie;

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

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


        mMovieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);



        MoveDetailsDataBindings  movieDetailsDataBinding = DataBindingUtil.inflate(inflater,R.layout.movie_details_fragment, container, false);

        mMovieDetailsViewModel.getMovie().set(mMovie);


        //movieDetailsDataBinding.setMovieModel(mMovie);

        movieDetailsDataBinding.setMovieViewModel(mMovieDetailsViewModel);

        movieDetailsDataBinding.setListener(new OnClickListener());
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


            mCompositeDisposable.add(mMovieDetailsViewModel.getMovieInfoFromRoomDatabase(mMovie.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((movie)->{
                            Log.i(TAG, "Movie Details from Local RoomDatabase has been faced....");
                           mMovieDetailsViewModel.getMovie().set(movie);
                           }));




            mMovieDetailsViewModel.getReviewList(mMovie.getId());

        } catch (Exception ex) {
            Toast.makeText(this.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }



    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setBottomNavVisibility(View.GONE);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState == null) {
            return;
        }
        Movie movie = savedInstanceState.getParcelable("movie");

        Log.i(TAG, "Restoring....");
    }

    private void setActionBar() {
        try {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.text_movie_details));
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        mCompositeDisposable.clear();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("movie", mMovie);
        this.setRetainInstance(true);

        super.onSaveInstanceState(outState);
    }


    public class OnClickListener {

        public void onClickFavouriteButton(final View view, Movie aMovie) {

            try {

                Log.i(TAG, "Click on Favourite Button");
                Movie newMovie = aMovie.copyMovie();
                newMovie.setFavourite(!aMovie.getFavourite());
                mCompositeDisposable.add(mMovieDetailsViewModel.insertMovieToRoomDB(newMovie)
                                         .subscribeOn(Schedulers.io())
                                         .observeOn(AndroidSchedulers.mainThread())
                                          . subscribe(()->{
                                              mMovieDetailsViewModel.getMovie().set(newMovie);
                                          }));

            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage());
                ex.printStackTrace();

                Toast.makeText(MovieDetailsFragment.this.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }




}
