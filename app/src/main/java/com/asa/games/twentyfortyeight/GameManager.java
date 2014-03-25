package com.asa.games.twentyfortyeight;

import android.content.Context;
import android.util.SparseArray;
import android.widget.FrameLayout;

import timber.log.Timber;

/**
 * Created by Aaron on 3/22/14.
 */
public class GameManager {

    public static final int DEFAULT_SIZE = 4;

    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;

    private static final int START_TILE_COUNT = 2;

    public int size;
    public int score;
    private Context context;
    public Grid grid;
    public FrameLayout[][] gridViews;

    private boolean won;
    private boolean over;
    private boolean keepPlaying;

    private static final SparseArray<int[]> DIRECTION_MAP = new SparseArray<int[]>();

    static {
        DIRECTION_MAP.put(DIRECTION_LEFT, new int[]{0, -1});
        DIRECTION_MAP.put(DIRECTION_RIGHT, new int[]{0, 1});
        DIRECTION_MAP.put(DIRECTION_UP, new int[]{-1, 0});
        DIRECTION_MAP.put(DIRECTION_DOWN, new int[]{1, 0});
    }

    // We are mapping the directions to an int array where the 0 position is the x and the 1 position is the y
    private static final int VECTOR_X_POS = 0;
    private static final int VECTOR_Y_POS = 1;

    private static GameManager sInstance;

    private GameManager(Context context, int size) {
        this.size = size;
        this.context = context;
    }

    public static GameManager getInstance(Context context, int size) {
        if (sInstance == null) {
            sInstance = new GameManager(context, size);
        }
        return sInstance;
    }

    public static GameManager getInstance(Context context) {
        return getInstance(context, DEFAULT_SIZE);
    }

    public void setViews(FrameLayout[][] layouts) {
        gridViews = layouts;
    }

    public void setup() {
        // TODO - implement saving/restoring the state.
        grid = new Grid(size);
        addStartTiles();
    }

    private void addStartTiles() {
        for (int i = 0; i < START_TILE_COUNT; i++) {
            addRandomTile();
        }
        if (BuildConfig.DEBUG) {
            grid.logGrid();
        }
    }

    public void insertTileToView(Tile tile) {
        if (gridViews == null) {
            throw new IllegalStateException("To insert a tile, the layout array must not be null.");
        }
        FrameLayout layout = gridViews[tile.x][tile.y];
        if (layout == null) {
            throw new IllegalStateException("The position for tile (" + tile.toString() + ") is null.");
        }
        if (layout.getChildCount() != 0) {
            layout.removeAllViews();
        }
        layout.addView(tile.createTileView(context));
    }

    private void addRandomTile() {
        if (grid.areCellsAvailable()) {
            int value = Math.random() < 0.5 ? Tile.DEFAULT_VALUE_2 : Tile.DEFAULT_VALUE_4;
            Tile tile = grid.getRandomAvailableCell();
            tile.value = value;
            grid.insertTile(tile);
            insertTileToView(tile);
        }
    }

    public void prepareTiles() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = grid.cells[x][y];
                if (tile != null) {
                    tile.mergedFrom = null;
                    tile.savePosition();
                }
            }
        }
    }

    public void moveTile(Tile tileToMove, Tile cellToMoveTo) {
        grid.cells[tileToMove.x][tileToMove.y] = null;
        grid.cells[cellToMoveTo.x][cellToMoveTo.y] = tileToMove;
        tileToMove.updatePosition(cellToMoveTo);
    }

    public void moveTile(Tile tileToMove, int x, int y) {
        moveTile(tileToMove, new Tile(x, y, 0));
    }

    public void move(int direction) {
        if (isGameTerminated()) {
            return;
        }
        int[] vector = getVector(direction);
        prepareTiles();
        boolean moved = false;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = grid.getTile(x, y);

                if (tile != null) {
                    Tile[] positions = findFarthestPosition(tile, vector);
                    Tile next = positions[1];

                    if (next != null && grid.withinBounds(next) && next.value == tile.value && next.mergedFrom == null) {
                        Tile mergedTile = new Tile(next, tile.value * 2);
                        grid.insertTile(mergedTile);
                        grid.removeTile(tile);
                        tile.updatePosition(next);
                        score += mergedTile.value;
                        // TODO if the high score, end
                        if (mergedTile.value == 2048) {
                            won = true;
                        }
                    } else {
                        moveTile(tile, positions[0]);
                    }
                    if (!positionsEqual(x, y, tile.x, tile.y)) {
                        moved = true;
                    }
                }
            }
        }
        if (moved) {
            addRandomTile();
            if (!movesAvailable()) {
                over = true;
            }
            reAddViews();
            grid.logGrid();
        } else {
            Timber.d("Did not move.");
        }
    }

    private void reAddViews() {
        if (BuildConfig.DEBUG) {
            // First, remove all views
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    gridViews[x][y].removeAllViews();
                }
            }
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    Tile tile = grid.getTile(x, y);
                    if (tile != null) {
                        gridViews[x][y].addView(tile.createTileView(context));
                    }
                }
            }
        }
    }

    private int[] getVector(int direction) {
        return DIRECTION_MAP.get(direction);
    }

    public void keepPlaying() {
        keepPlaying = true;
        // TODO actuator stuff?
    }

    public boolean isGameTerminated() {
        return over || (won && !keepPlaying);
    }

    /**
     * Determines if there are any moves available on the grid.
     */
    public boolean movesAvailable() {
        return grid.areCellsAvailable() || tileMatchesAvailable();
    }

    /**
     * Determines if there are tiles that can be merged together.
     */
    public boolean tileMatchesAvailable() {
        Tile tile = null;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                tile = grid.getTile(x, y);
                if (tile != null) {
                    // now check all cells around it.
                    // 4 is the no. of possible directions.
                    for (int i = 0; i < 4; i++) {
                        int[] vector = getVector(i);
                        Tile cell = grid.getTile(x + vector[VECTOR_X_POS], y + vector[VECTOR_Y_POS]);
                        if (cell != null && cell.value == tile.value) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Finds the farthest position that the given tile can travel in the given direction.
     *
     * @param tile   The tile that is lookng to move
     * @param vector The direction to travel in.
     * @return An array of tiles with position 0 being the farthest tile and position 1 being the next tile.
     */
    public Tile[] findFarthestPosition(Tile tile, int[] vector) {
        Tile previous = null;
        boolean withinBounds = grid.withinBounds(tile);
        boolean available = grid.isCellAvailable(tile);
        do {
            previous = tile;
            tile = grid.getTile(tile.x + vector[VECTOR_X_POS], tile.y + vector[VECTOR_Y_POS]);
            if (tile == null) {
                tile = new Tile(previous.x + vector[VECTOR_X_POS], previous.y + vector[VECTOR_Y_POS], previous.value);
            }
        } while (grid.withinBounds(tile) && grid.isCellAvailable(tile));
        return new Tile[]{previous, tile};
    }

    private boolean positionsEqual(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    private boolean positionsEqual(Tile tile1, Tile tile2) {
        return positionsEqual(tile1.x, tile1.y, tile2.x, tile2.y);
    }
}
