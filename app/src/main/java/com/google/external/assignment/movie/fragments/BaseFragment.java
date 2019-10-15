package com.google.external.assignment.movie.fragments;



import android.widget.ImageView;

import com.google.external.assignment.movie.common.utilities.PicassoUtility;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {



    @BindingAdapter("loadPosterImage")
    public  static void loadPosterImage(ImageView view, String imageUrl) {
        PicassoUtility.getInstance().loadImage(imageUrl, view);
    }

}
