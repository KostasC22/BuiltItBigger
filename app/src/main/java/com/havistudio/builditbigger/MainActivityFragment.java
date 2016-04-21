package com.havistudio.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.havistudio.Joke;
import com.havistudio.myjokeactivity.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String TAG = "MainActivityFragment";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button tempButton = (Button) rootView.findViewById(R.id.show_joke);
        final Context context = getActivity();
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joke joke = new Joke();
                Log.i(TAG, "onClick: " + joke.getJoke());
                Toast.makeText(context, joke.getJoke(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context,JokeActivity.class);
                intent.putExtra("joke",joke.getJoke());
                context.startActivity(intent);
            }
        });

        return rootView;
    }
}
