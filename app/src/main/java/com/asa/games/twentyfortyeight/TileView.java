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
            setBackground();
        }
    }

    private void setBackground() {
        switch (mTile.value) {
            case 2:
                setBackgroundColor(getResources().getColor(R.color.tile_color_2));
                break;
            case 4:
                setBackgroundColor(getResources().getColor(R.color.tile_color_4));
                break;
            case 8:
                setBackgroundColor(getResources().getColor(R.color.tile_color_8));
                break;
            case 16:
                setBackgroundColor(getResources().getColor(R.color.tile_color_16));
                break;
            case 32:
                setBackgroundColor(getResources().getColor(R.color.tile_color_32));
                break;
            case 64:
                setBackgroundColor(getResources().getColor(R.color.tile_color_64));
                break;
            case 128:
                setBackgroundColor(getResources().getColor(R.color.tile_color_128));
                break;
            case 256:
                setBackgroundColor(getResources().getColor(R.color.tile_color_256));
                break;
            case 512:
                setBackgroundColor(getResources().getColor(R.color.tile_color_512));
                break;
            case 1024:
                setBackgroundColor(getResources().getColor(R.color.tile_color_1024));
                break;
            case 2048:
                setBackgroundColor(getResources().getColor(R.color.tile_color_2048));
                break;
        }
    }
}
