package com.asa.games.twentyfortyeight;

import android.content.Context;

public class Tile {
    private static final String TAG = "Tile";

    public static final int DEFAULT_VALUE_2 = 2;
    public static final int DEFAULT_VALUE_4 = 4;

    public int x;
    public int y;
    public int value = DEFAULT_VALUE_2;

    public Tile previousPosition;
    // Tracks tiles that are merged together
    public Tile mergedFrom;

    public Tile() {
    }

    public Tile(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void savePosition() {
        previousPosition = new Tile(x, y, value);
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

    @Override
    public String toString() {
        return "Tile position x=" + x + "; y=" + y;
    }

    public TileView createTileView(Context context) {
        // TODO - perhaps define the style.
        return new TileView(context, this);
    }
}
