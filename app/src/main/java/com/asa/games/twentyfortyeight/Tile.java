package com.asa.games.twentyfortyeight;

public class Tile {
    private static final String TAG = "Tile";

    public static final int DEFAULT_VALUE_2 = 2;
    public static final int DEFAULT_VALUE_4 = 4;

    public int x;
    public int y;
    public int value = DEFAULT_VALUE_2;

    private Tile previousPosition;
    // Tracks tiles that are merged together
    private Tile mergedFrom;

    public Tile() {
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void savePosition() {
        previousPosition = new Tile(x, y);
    }

    public void updatePosition(Tile tile) {
        x = tile.x;
        y = tile.y;
    }

    public void updateX(int x) {
        this.x = x;
    }

    public void updateY(int y) {
        this.y = y;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }

    public int getValue() {
        return this.value;
    }

}
