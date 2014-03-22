package com.asa.games.twentyfortyeight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Aaron on 3/22/14.
 */
public class FragmentMain extends Fragment {
    public static final String TAG = "FragmentMain";
    @InjectView(R.id.grid_container_1_1)
    FrameLayout mGridContainer01;
    @InjectView(R.id.grid_container_1_2)
    FrameLayout mGridContainer02;
    @InjectView(R.id.grid_container_1_3)
    FrameLayout mGridContainer03;
    @InjectView(R.id.grid_container_1_4)
    FrameLayout mGridContainer04;
    @InjectView(R.id.grid_container_2_1)
    FrameLayout mGridContainer11;
    @InjectView(R.id.grid_container_2_2)
    FrameLayout mGridContainer12;
    @InjectView(R.id.grid_container_2_3)
    FrameLayout mGridContainer13;
    @InjectView(R.id.grid_container_2_4)
    FrameLayout mGridContainer14;
    @InjectView(R.id.grid_container_3_1)
    FrameLayout mGridContainer21;
    @InjectView(R.id.grid_container_3_2)
    FrameLayout mGridContainer22;
    @InjectView(R.id.grid_container_3_3)
    FrameLayout mGridContainer23;
    @InjectView(R.id.grid_container_3_4)
    FrameLayout mGridContainer24;
    @InjectView(R.id.grid_container_5_1)
    FrameLayout mGridContainer31;
    @InjectView(R.id.grid_container_5_2)
    FrameLayout mGridContainer32;
    @InjectView(R.id.grid_container_5_3)
    FrameLayout mGridContainer33;
    @InjectView(R.id.grid_container_5_4)
    FrameLayout mGridContainer34;

    private FrameLayout[][] mGrid;
    private GameManager mGameManager;

    public static FragmentMain newInstance() {
        FragmentMain frag = new FragmentMain();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameManager = GameManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, v);
        setupGridViews();
        return v;
    }

    private void setupGridViews() {
        mGrid = null;
        mGrid = new FrameLayout[mGameManager.size][mGameManager.size];
        mGrid[0][0] = mGridContainer01;
        mGrid[0][0] = mGridContainer02;
        mGrid[0][0] = mGridContainer03;
        mGrid[0][0] = mGridContainer04;
        mGrid[0][0] = mGridContainer11;
        mGrid[0][0] = mGridContainer12;
        mGrid[0][0] = mGridContainer13;
        mGrid[0][0] = mGridContainer14;
        mGrid[0][0] = mGridContainer21;
        mGrid[0][0] = mGridContainer22;
        mGrid[0][0] = mGridContainer23;
        mGrid[0][0] = mGridContainer24;
        mGrid[0][0] = mGridContainer31;
        mGrid[0][0] = mGridContainer32;
        mGrid[0][0] = mGridContainer33;
        mGrid[0][0] = mGridContainer34;
    }
}
