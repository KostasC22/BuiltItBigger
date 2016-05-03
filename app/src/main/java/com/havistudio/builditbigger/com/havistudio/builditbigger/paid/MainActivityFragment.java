package com.havistudio.builditbigger.com.havistudio.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private static Context mContext;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button tempButton = (Button) rootView.findViewById(R.id.show_joke);
        final Context context = getActivity();
        mContext = context;

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
        TextView tempTextJoke = (TextView) rootView.findViewById(R.id.joke_text);
        String tempString = "";
        try {
            tempString = new EndpointsAsyncTask().execute(new Pair<Context, String>(this.getActivity(), "Manfred")).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("Tag", "onCreate: "+tempString);
        return rootView;
    }

    private void openNewActivity(){

        Joke joke = new Joke();
        Log.i(TAG, "onClick: " + joke.getJoke());
        Intent intent = new Intent(mContext,MyJokeActivity
                .class);
        intent.putExtra("joke",joke.getJoke());
        mContext.startActivity(intent);

    }

}
