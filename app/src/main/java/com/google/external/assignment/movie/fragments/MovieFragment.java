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

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieFragment extends BaseFragment {

    private static final String TAG = "MovieFragment";

    private MovieViewModel movieViewModel;
    private RecyclerView listMovie;


    private MovieAdapter aAdapter ;

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
            Toast.makeText(this.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void init(View viewFlater) {

        listMovie = (RecyclerView)viewFlater.findViewById(R.id.list_movie);

//        //FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
//        //layoutManager.setFlexDirection(FlexDirection.COLUMN);
//        //layoutManager.setJustifyContent(JustifyContent.FLEX_END);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), getResources().getInteger(R.integer.column_count));
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        listMovie.setLayoutManager(gridLayoutManager);

        listMovie.setHasFixedSize(true);


//        val flexboxLayoutManager = FlexboxLayoutManager(this).apply {
//            flexWrap = FlexWrap.WRAP
//            flexDirection = FlexDirection.ROW
//            alignItems = AlignItems.STRETCH
//        }

//        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
//        layoutManager.setFlexWrap(FlexWrap.WRAP);
//        layoutManager.setAlignItems(AlignItems.STRETCH);
//        layoutManager.setFlexDirection(FlexDirection.ROW);
//       // layoutManager.setJustifyContent(JustifyContent.CENTER);
//        listMovie.setLayoutManager(layoutManager);




        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        //listMovie.setLayoutManager(layoutManager);

        aAdapter = new MovieAdapter(getContext(), this);
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

        handleListScrolling(gridLayoutManager, aAdapter);

    }

    /**
     *
     * @param gridLayoutManager
     * @param aAdapter
     */

    private void handleListScrolling(final GridLayoutManager gridLayoutManager, final MovieAdapter aAdapter) {
        listMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastCompleteItemNo = gridLayoutManager.findLastCompletelyVisibleItemPosition();

                if (dy > 0 && lastCompleteItemNo >= aAdapter.getItemCount() - 1) {
                    Log.i(TAG, String.format("Current Position [%d]", lastCompleteItemNo));
                    movieViewModel.loadNextPageData();
                }
            }
        });
    }

    private void handleListScrolling(final FlexboxLayoutManager gridLayoutManager, final MovieAdapter aAdapter) {
        listMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastCompleteItemNo = gridLayoutManager.findLastCompletelyVisibleItemPosition();

                if (dy > 0 && lastCompleteItemNo >= aAdapter.getItemCount() - 1) {
                    Log.i(TAG, String.format("Current Position [%d]", lastCompleteItemNo));
                    movieViewModel.loadNextPageData();
                }
            }
        });
    }

    @Override
    public void handleBottomNavigation(MenuItem menuItem) {
        handleMenuItem(menuItem);
    }

    private void handleMenuItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_sort_popularity:
                movieViewModel.SortByPopularity();
                break;

            case R.id.menu_sort_top_rated:
                movieViewModel.SortByTopRated();
                break;

            case R.id.menu_sort_favourite:
                movieViewModel.SortByFavourite();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        handleMenuItem(item);
        return super.onContextItemSelected(item);
    }

}
