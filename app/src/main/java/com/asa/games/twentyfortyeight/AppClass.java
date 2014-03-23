package com.asa.games.twentyfortyeight;

import android.app.Application;

public class AppClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GameManager.getInstance(getApplicationContext());
    }
}
