package com.havistudio.myjokeactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kostas on 14/04/2016.
 */
public class JokeActivityFragment extends Fragment {

    private static final String TAG = "JokeActivityFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Log.i(TAG, "onCreateView: " + "Hello");


        return rootView;
    }


}
