package com.google.external.assignment.movie.common.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceUtility {

    private static SharedPreferenceUtility mInstance;
    private Context mContext;

    //public static final String PREF_NAME_MOVIE = "MovieDetailsFragment";

    public  static final String PREF_KEY_API_URL = "API_URL";
    public static final String PREF_KEY_API_KEY = "API_KEY";
    public static final String PREF_KEY_SORT_OPTION = "SORT_OPTION";
    private SharedPreferences mPreference;




    private SharedPreferenceUtility(Context context) {
        mContext = context;
        mPreference = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public static synchronized SharedPreferenceUtility getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new SharedPreferenceUtility(context);
        }

        return mInstance;
    }

    public void putString(String key, String value) {
        mPreference.edit().putString(key, value).apply();
    }

    public String getValue(String key, String defaultValue){
        return mPreference.getString(key, defaultValue);
    }










}
