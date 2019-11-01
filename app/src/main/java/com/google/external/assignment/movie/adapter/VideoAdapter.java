package com.google.external.assignment.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.external.assignment.movie.databinding.TrailerDataBindings;
import com.google.external.assignment.movie.fragments.BaseFragment;
import com.google.external.assignment.movie.model.moviedb.Video;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    private List<Video> mVideoList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;
    private BaseFragment mFragment;


    public VideoAdapter(Context mContext, BaseFragment mFragment) {
        this.mContext = mContext;
        this.mFragment = mFragment;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(mContext);
        }

        TrailerDataBindings viewBindings = TrailerDataBindings.inflate(layoutInflater, parent, false);
        return new VideoAdapter.VideoHolder(viewBindings);



    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        Video aVideoModel = mVideoList.get(position);

        EventListener aListener = new EventListener();

        holder.bind(aVideoModel, aListener);



    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }



    public void setVideoList(List<Video> aVideoList) {
        this.mVideoList = aVideoList;
        notifyDataSetChanged();
    }

    public class VideoHolder extends  RecyclerView.ViewHolder{

        private TrailerDataBindings mTrailerDataBindings;


        public VideoHolder(TrailerDataBindings mTrailerDataBindings) {
            super(mTrailerDataBindings.getRoot());
            this.mTrailerDataBindings = mTrailerDataBindings;

        }

        public void bind(Video aVideoModel, EventListener aListener) {

            mTrailerDataBindings.setVideoModel(aVideoModel);
            mTrailerDataBindings.setEventHandler(aListener);
        }
    }


    public class EventListener {

        public void playVideo(View aView, Video video) {
            if (!StringUtils.equals(video.getSite(), Video.SITE_YOUTUBE) ) {
                Toast.makeText(mContext, "Unsupported Video Format", Toast.LENGTH_LONG).show();
            } else {
                mFragment.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + video.getKey())));
            }

        }
    }
}
