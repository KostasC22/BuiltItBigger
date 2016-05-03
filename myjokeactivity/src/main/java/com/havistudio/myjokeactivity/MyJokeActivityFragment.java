package com.havistudio.myjokeactivity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyJokeActivityFragment extends Fragment {

    private static final String TAG = "MyJokeActivityFragment";

    public MyJokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_my_joke, container, false);
        String temp = getActivity().getIntent().getStringExtra("joke");
        Log.i(TAG, "onCreateView: "+temp);
        return inflater.inflate(R.layout.fragment_my_joke, container, false);
    }
}
