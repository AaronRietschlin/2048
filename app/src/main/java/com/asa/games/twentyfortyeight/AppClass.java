package com.asa.games.twentyfortyeight;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Aaron on 3/24/14.
 */
public class AppClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GameManager.getInstance(getApplicationContext());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
