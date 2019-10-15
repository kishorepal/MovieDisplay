package com.google.external.assignment.movie.common.utilities;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.external.assignment.movie.common.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

public class PicassoUtility {

    private static PicassoUtility mInstance;


    private PicassoUtility() {

    }

    public static PicassoUtility getInstance() {
        if(mInstance == null) {
            mInstance = new PicassoUtility();
        }

        return mInstance;
    }

    public void loadImage(String imageUrl, ImageView imageButton) {
        Picasso.get().load(String.format(Constants.BASE_URL_PICASSO,imageUrl )).into(imageButton);
    }


}
