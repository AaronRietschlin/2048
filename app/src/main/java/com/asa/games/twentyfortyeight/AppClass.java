package com.asa.games.twentyfortyeight;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

public class AppClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Crashlytics.start(this);
        GameManager.getInstance(getApplicationContext());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
