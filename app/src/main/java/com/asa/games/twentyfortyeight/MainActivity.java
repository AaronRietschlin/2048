package com.asa.games.twentyfortyeight;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

public class MainActivity extends FragmentActivity implements GestureDetector.OnGestureListener {

    @InjectView(R.id.grid_container_0_1)
    FrameLayout mGridContainer01;
    @InjectView(R.id.grid_container_0_2)
    FrameLayout mGridContainer02;
    @InjectView(R.id.grid_container_0_3)
    FrameLayout mGridContainer03;
    @InjectView(R.id.grid_container_0_4)
    FrameLayout mGridContainer04;
    @InjectView(R.id.grid_container_1_1)
    FrameLayout mGridContainer11;
    @InjectView(R.id.grid_container_1_2)
    FrameLayout mGridContainer12;
    @InjectView(R.id.grid_container_1_3)
    FrameLayout mGridContainer13;
    @InjectView(R.id.grid_container_1_4)
    FrameLayout mGridContainer14;
    @InjectView(R.id.grid_container_2_1)
    FrameLayout mGridContainer21;
    @InjectView(R.id.grid_container_2_2)
    FrameLayout mGridContainer22;
    @InjectView(R.id.grid_container_2_3)
    FrameLayout mGridContainer23;
    @InjectView(R.id.grid_container_2_4)
    FrameLayout mGridContainer24;
    @InjectView(R.id.grid_container_3_1)
    FrameLayout mGridContainer31;
    @InjectView(R.id.grid_container_3_2)
    FrameLayout mGridContainer32;
    @InjectView(R.id.grid_container_3_3)
    FrameLayout mGridContainer33;
    @InjectView(R.id.grid_container_3_4)
    FrameLayout mGridContainer34;

    private FrameLayout[][] mGrid;
    private GameManager mGameManager;

    private GestureDetectorCompat mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        ButterKnife.inject(this);

        mGestureDetector = new GestureDetectorCompat(this, this);

        setupGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupGame() {
        mGameManager = GameManager.getInstance(this);
        setupGridViews();
        mGameManager.setViews(mGrid);
        mGameManager.setup();
    }

    private void setupGridViews() {
        mGrid = null;
        mGrid = new FrameLayout[mGameManager.size][mGameManager.size];
        mGrid[0][0] = mGridContainer01;
        mGrid[0][1] = mGridContainer02;
        mGrid[0][2] = mGridContainer03;
        mGrid[0][3] = mGridContainer04;
        mGrid[1][0] = mGridContainer11;
        mGrid[1][1] = mGridContainer12;
        mGrid[1][2] = mGridContainer13;
        mGrid[1][3] = mGridContainer14;
        mGrid[2][0] = mGridContainer21;
        mGrid[2][1] = mGridContainer22;
        mGrid[2][2] = mGridContainer23;
        mGrid[2][3] = mGridContainer24;
        mGrid[3][0] = mGridContainer31;
        mGrid[3][1] = mGridContainer32;
        mGrid[3][2] = mGridContainer33;
        mGrid[3][3] = mGridContainer34;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Timber.d("");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Timber.d("");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Timber.d("");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Timber.d("");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Timber.d("");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Timber.d("");
        return false;
    }
}
