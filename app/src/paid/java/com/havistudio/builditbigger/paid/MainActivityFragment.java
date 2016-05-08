package com.havistudio.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.havistudio.builditbigger.EndpointsAsyncTask;
import com.havistudio.builditbigger.R;
import com.havistudio.myjokeactivity.MyJokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String TAG = "MainActivityFragment";
    private static Context mContext;
    String joke = "";
    private ProgressBar spinner;
    private Button bShowJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        bShowJoke = (Button) rootView.findViewById(R.id.show_joke);
        spinner = (ProgressBar) rootView.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        final Context context = getActivity();
        mContext = context;

        bShowJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                bShowJoke.setVisibility(View.GONE);
                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {

                        try {
                            joke = new EndpointsAsyncTask(spinner).execute(new Pair<Context, String>(getActivity(), "Manfred")).get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
                openNewActivity();
            }
        });
        TextView tempTextJoke = (TextView) rootView.findViewById(R.id.joke_text);
        String tempString = "";
        try {
            joke = new EndpointsAsyncTask(spinner).execute(new Pair<Context, String>(this.getActivity(), "Manfred")).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Tag", "onCreate: " + tempString);
        return rootView;
    }

    public void onStart() {
        super.onStart();
        spinner.setVisibility(View.GONE);
        bShowJoke.setVisibility(View.VISIBLE);
    }

    private void openNewActivity() {

        Intent intent = new Intent(mContext, MyJokeActivity.class);
        intent.putExtra("joke", joke);
        mContext.startActivity(intent);

    }

}
