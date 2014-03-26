package com.asa.games.twentyfortyeight;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import info.metadude.android.typedpreferences.IntPreference;

public class PreferenceUtils {

    public static final String KEY_HIGH_SCORE = "high_score";

    private static SharedPreferences sharedPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static int getHighScore(Context context) {
        return new IntPreference(sharedPrefs(context), KEY_HIGH_SCORE).get();
    }

    public static void setHighScore(Context context, int score) {
        new IntPreference(sharedPrefs(context), KEY_HIGH_SCORE).set(score);
    }

}
