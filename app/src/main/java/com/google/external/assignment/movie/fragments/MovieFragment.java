package com.google.external.assignment.movie.fragments;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.external.assignment.movie.R;
import com.google.external.assignment.movie.activity.MainActivity;
import com.google.external.assignment.movie.adapter.MovieAdapter;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.viewmodel.MovieViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieFragment extends BaseFragment {

    private static final String TAG = "MovieFragment";

    private MovieViewModel movieViewModel;
    private RecyclerView listMovie;

    public MovieFragment() {
        //this.movieViewModel = movieViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getActivity().getWindow().setBackgroundDrawable(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewFlater =  inflater.inflate(R.layout.fragment_movie_list, container, false);
        init(viewFlater);



        return viewFlater;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
       // inflater.inflate(R.menu.main_menu, menu);

    }


    private void init(View viewFlater) {

        listMovie = (RecyclerView)viewFlater.findViewById(R.id.list_movie);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        listMovie.setLayoutManager(gridLayoutManager);
        listMovie.setHasFixedSize(true);
        final MovieAdapter aAdapter = new MovieAdapter(getContext(), this);
        listMovie.setAdapter(aAdapter);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                aAdapter.setMovies(movies);
            }
        });

        movieViewModel.getSortOption().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String option) {
                ((MainActivity)getActivity()).getSupportActionBar().setTitle(option);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_sort_popularity:
                movieViewModel.SortByPopularity();
                break;

            case R.id.menu_sort_top_rated:
                movieViewModel.SortByTopRated();
                break;

        }
        return super.onContextItemSelected(item);
    }

}
