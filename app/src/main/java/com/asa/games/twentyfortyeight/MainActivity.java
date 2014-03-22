package com.asa.games.twentyfortyeight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            Fragment frag = FragmentMain.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fragment_container, frag, FragmentMain.TAG)
                    .commit();
        } else {
            // Try to restore the fragmet
            Fragment frag = mFragmentManager.findFragmentByTag(FragmentMain.TAG);
            if (frag == null) {
                // not present so add it.
                frag = FragmentMain.newInstance();
                mFragmentManager.beginTransaction().add(R.id.fragment_container, frag, FragmentMain.TAG)
                        .commit();
            } else {
                if (!frag.isAdded()) {
                    mFragmentManager.beginTransaction().attach(frag).commit();
                }
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
