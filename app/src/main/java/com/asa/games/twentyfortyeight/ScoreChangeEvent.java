package com.asa.games.twentyfortyeight;

/**
 * Created by Aaron on 3/25/14.
 */
public class ScoreChangeEvent {

    private int score;

    public ScoreChangeEvent(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
