package com.asa.games.twentyfortyeight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Aaron on 3/22/14.
 */
public class FragmentMain extends Fragment {
    public static final String TAG = "FragmentMain";

    public static FragmentMain newInstance(){
        FragmentMain frag = new FragmentMain();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, v);
        return v;
    }
}
