package com.google.external.assignment.movie.fragments;



import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.external.assignment.movie.common.utilities.PicassoUtility;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {



    @BindingAdapter(value={"loadPosterImage", "placeholder"}, requireAll = false)
    public  static void loadPosterImage(ImageView view, String imageUrl, Integer placeholder) {

        Log.i("LoadPOster", String.format("PlaceHolder[%d]", placeholder));
        PicassoUtility.getInstance().loadImage(imageUrl, view);
    }


    public void handleBottomNavigation(MenuItem menuItem){

    }


}
