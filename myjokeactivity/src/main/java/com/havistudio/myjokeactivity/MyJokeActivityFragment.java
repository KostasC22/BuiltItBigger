package com.havistudio.myjokeactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        View rootView = inflater.inflate(R.layout.fragment_my_joke, container, false);
        TextView djTextView = (TextView) rootView.findViewById(R.id.display_joke);
        //ProgressBar spinner = (ProgressBar) rootView.findViewById(R.id.progressBar1);

        Intent intent = getActivity().getIntent();
        String mJoke = intent.getStringExtra("joke");
        Log.i(TAG, "onCreateView: "+mJoke);
        djTextView.setText(mJoke);
        //spinner.setVisibility(View.VISIBLE);
        try{
            //new EndpointsAsyncTask(djTextView,spinner).execute(new Pair<Context, String>(this.getActivity(), "Manfred"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return rootView;
    }
}
