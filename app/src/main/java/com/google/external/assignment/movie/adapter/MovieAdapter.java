package com.google.external.assignment.movie.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.external.assignment.movie.R;
import com.google.external.assignment.movie.activity.MainActivity;
import com.google.external.assignment.movie.common.utilities.PicassoUtility;
import com.google.external.assignment.movie.databinding.RowDataBindings;
import com.google.external.assignment.movie.fragments.BaseFragment;
import com.google.external.assignment.movie.fragments.MovieDetailsFragment;
import com.google.external.assignment.movie.model.moviedb.Movie;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterHolder> {

    private static final String TAG = "MovieAdapter";


    private List<Movie> mItems = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;
    private BaseFragment mFragment;

    public MovieAdapter(Context context, BaseFragment fragment) {
        this.mContext = context;
        this.mFragment = fragment;
       //this.mItems = items;
    }

    @NonNull
    @Override
    public MovieAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(mContext);
        }

        RowDataBindings viewBindings = RowDataBindings.inflate(layoutInflater, viewGroup, false);
        return new MovieAdapterHolder(viewBindings );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterHolder movieAdapterHolder, int position) {

        Movie aMoviewModel = mItems.get(position);


        OnClickListener onClickListener = new OnClickListener();
        movieAdapterHolder.bind(aMoviewModel, onClickListener);
    }




    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setMovies(List<Movie> movies) {
        this.mItems = movies;
        notifyDataSetChanged();
    }

    public class MovieAdapterHolder extends RecyclerView.ViewHolder {

        RowDataBindings mRowDataBindings;

        //TextView txtTitle;
        ImageView imageView;

        public MovieAdapterHolder(RowDataBindings rowDataBindings) {
            super(rowDataBindings.getRoot());
            mRowDataBindings = rowDataBindings;
        }

        public void bind(Movie aMovie, OnClickListener listener) {
            this.mRowDataBindings.setMovieModel(aMovie);
            this.mRowDataBindings.setListener(listener);
        }

        public RowDataBindings getRowDataBindings() {
            return this.mRowDataBindings;
        }
    }

    public class OnClickListener {

        public void onClickImageView(View view, Movie aMovie) {

            try{
                Log.i(TAG, "Click on ImageButton");
                Toast.makeText(mContext, String.format("Moview ID[%s] ", aMovie.getTitle()), Toast.LENGTH_LONG).show();
                MovieDetailsFragment movieDeatils =  new MovieDetailsFragment(aMovie);
                ((MainActivity)mFragment.getActivity()).replaceFragment(movieDeatils);
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage());
                ex.printStackTrace();

                Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_LONG).show();
            }

        }



        public void onClickFavouriteButton(View view, Movie aMovie) {

            try{
                Log.i(TAG, "Click on Favourite Button");
                view.setSelected(true);
//                Toast.makeText(mContext, String.format("Moview ID[%s] ", aMovie.getTitle()), Toast.LENGTH_LONG).show();
//                MovieDetailsFragment movieDeatils =  new MovieDetailsFragment(aMovie);
//                ((MainActivity)mFragment.getActivity()).replaceFragment(movieDeatils);
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage());
                ex.printStackTrace();

                Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }

}
