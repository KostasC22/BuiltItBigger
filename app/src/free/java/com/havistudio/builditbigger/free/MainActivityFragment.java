package com.havistudio.builditbigger.free;

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
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.havistudio.Joke;
import com.havistudio.builditbigger.EndpointsAsyncTask;
import com.havistudio.builditbigger.R;
import com.havistudio.myjokeactivity.MyJokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String TAG = "MainActivityFragment";
    InterstitialAd mInterstitialAd;
    private static Context mContext;
    String tempString = "";
    private ProgressBar spinner;
    private Button bShowJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        bShowJoke = (Button) rootView.findViewById(R.id.show_joke);
        final Context context = getActivity();
        mContext = context;
        spinner = (ProgressBar) rootView.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        mInterstitialAd = new InterstitialAd(context);
        String myId = getString(R.string.banner_ad_unit_id);
        Log.i(TAG, "onCreateView: " + myId);
        mInterstitialAd.setAdUnitId(myId);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                openNewActivity();
            }
        });

        requestNewInterstitial();
        final TextView tempTextJoke = (TextView) rootView.findViewById(R.id.joke_text);
        bShowJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                bShowJoke.setVisibility(View.GONE);
                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        Log.i(TAG, "------------------------- onClick: " + mInterstitialAd.isLoaded());

                        showInterstitial();
                        try {
                            tempString = new EndpointsAsyncTask(spinner).execute(new Pair<Context, String>(getActivity(), "Manfred")).get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }.start();

            }
        });


        Log.i("Tag", "onCreate: " + tempString);
        return rootView;
    }

    public void onStart() {
        super.onStart();
        spinner.setVisibility(View.GONE);
        bShowJoke.setVisibility(View.VISIBLE);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void openNewActivity() {

        Joke joke = new Joke();
        Log.i(TAG, "onClick: " + tempString);
        Intent intent = new Intent(mContext, MyJokeActivity.class);
        intent.putExtra("joke", tempString);
        mContext.startActivity(intent);

    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(mContext, "Ad did not load", Toast.LENGTH_SHORT).show();
            openNewActivity();
        }
    }
}
