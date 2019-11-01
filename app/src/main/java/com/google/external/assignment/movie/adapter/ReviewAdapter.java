package com.google.external.assignment.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.external.assignment.movie.databinding.ReviewDataBinding;
import com.google.external.assignment.movie.databinding.RowDataBindings;
import com.google.external.assignment.movie.fragments.BaseFragment;
import com.google.external.assignment.movie.model.moviedb.Movie;
import com.google.external.assignment.movie.model.moviedb.Review;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewAdapterHolder> {

    private List<Review> mReviewList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;
    private BaseFragment mFragment;

    public ReviewAdapter(Context context, BaseFragment fragment) {
        this.mContext = context;
        this.mFragment = fragment;
    }

    @NonNull
    @Override
    public ReviewAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(mContext);
        }

        ReviewDataBinding viewBindings = ReviewDataBinding.inflate(layoutInflater, parent, false);
        return new ReviewAdapter.ReviewAdapterHolder(viewBindings);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapterHolder holder, int position) {

        Review aReviewModel = mReviewList.get(position);
        holder.bind(aReviewModel);

    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    public class ReviewAdapterHolder extends RecyclerView.ViewHolder {

        ReviewDataBinding mReviewDataBinding;

        public ReviewAdapterHolder(ReviewDataBinding reviewDataBinding) {

            super(reviewDataBinding.getRoot());
            this.mReviewDataBinding = reviewDataBinding;

        }

        public void bind(Review aReview) {
            this.mReviewDataBinding.setReviewViewModel(aReview);
        }
    }

    public void setReviewList(List<Review> reviews) {
        this.mReviewList = reviews;
        notifyDataSetChanged();
    }
}
