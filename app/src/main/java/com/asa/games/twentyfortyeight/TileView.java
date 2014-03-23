package com.asa.games.twentyfortyeight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TileView extends TextView {

    private Tile mTile;

    public TileView(Context context) {
        super(context);
        init(context);
    }

    public TileView(Context context, Tile tile) {
        super(context);
        init(context);
        mTile = tile;
        setTileValue();
    }

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        int height = context.getResources().getDimensionPixelSize(R.dimen.grid_container_cell_height);
        int width = context.getResources().getDimensionPixelSize(R.dimen.grid_container_cell_width);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        setLayoutParams(params);
        setGravity(Gravity.CENTER);
    }

    public void setTile(Tile tile) {
        mTile = tile;
    }

    public Tile getTile() {
        return mTile;
    }

    public void setTileValue() {
        if (mTile != null) {
            setText(String.valueOf(mTile.value));
        }
    }
}
