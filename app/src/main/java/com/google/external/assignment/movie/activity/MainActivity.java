package com.google.external.assignment.movie.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.external.assignment.movie.BuildConfig;
import com.google.external.assignment.movie.R;
import com.google.external.assignment.movie.adapter.MovieAdapter;
import com.google.external.assignment.movie.callback.MovieManagerCallBack;
import com.google.external.assignment.movie.common.Constants;
import com.google.external.assignment.movie.common.utilities.SharedPreferenceUtility;
import com.google.external.assignment.movie.fragments.BaseFragment;
import com.google.external.assignment.movie.fragments.MovieDetailsFragment;
import com.google.external.assignment.movie.fragments.MovieFragment;
import com.google.external.assignment.movie.manager.MovieManager;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Response;
import com.google.external.assignment.movie.viewmodel.MovieViewModel;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    public static final String MOVIES_FRAGMENT_TAG = "MOVIE_CONTAINER";
    private RecyclerView listMovie;
    private MovieViewModel movieViewModel;
    private BottomNavigationView bottomNavigationView;
    private BaseFragment mFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().setBackgroundDrawable(null);
        init();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mFragment= (BaseFragment) getSupportFragmentManager().findFragmentByTag(MOVIES_FRAGMENT_TAG);
        if (mFragment == null) {
            mFragment = new MovieFragment();
            replaceFragment(mFragment);
        }


    }

    private void init() {

        try {
            // getSupportActionBar().setHideOnContentScrollEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);

            String option = SharedPreferenceUtility.getInstance(this).getValue(SharedPreferenceUtility.PREF_KEY_SORT_OPTION,
                    Constants.SORT_BY_POPULARITY);

            if(StringUtils.compare(option, Constants.SORT_BY_FAVOURITE) == 0) {
                bottomNavigationView.setSelectedItemId(R.id.menu_sort_favourite);
            } else if(StringUtils.compare(option, Constants.SORT_BY_TOP_RATED) == 0){
                bottomNavigationView.setSelectedItemId(R.id.menu_sort_top_rated);
            } else {
                bottomNavigationView.setSelectedItemId(R.id.menu_sort_popularity);
            }
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                   if(mFragment != null) {
                       mFragment= (BaseFragment) getSupportFragmentManager().findFragmentByTag(MOVIES_FRAGMENT_TAG);
                       mFragment.handleBottomNavigation(menuItem);
                   }
                   return true;
                }
            });



        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }

     }



    public void replaceFragment(BaseFragment fragment) {


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movies_container, fragment, MOVIES_FRAGMENT_TAG)
                .addToBackStack( MOVIES_FRAGMENT_TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount()>1){
            getSupportFragmentManager().popBackStack();
        }
  }

    public void setBottomNavVisibility(int visibility) {
        bottomNavigationView.setVisibility(visibility);
    }
}
    

