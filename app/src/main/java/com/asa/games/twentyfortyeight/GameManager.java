package com.asa.games.twentyfortyeight;

import android.util.SparseArray;

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
    public Grid grid;
    public int score;

    private boolean won;
    private boolean over;
    private boolean keepPlaying;

    private static final SparseArray<int[]> DIRECTION_MAP = new SparseArray<int[]>();

    static {
        DIRECTION_MAP.put(DIRECTION_UP, new int[]{0, -1});
        DIRECTION_MAP.put(DIRECTION_DOWN, new int[]{0, 1});
        DIRECTION_MAP.put(DIRECTION_LEFT, new int[]{-1, 0});
        DIRECTION_MAP.put(DIRECTION_RIGHT, new int[]{1, 0});
    }

    // We are mapping the directions to an int array where the 0 position is the x and the 1 position is the y
    private static final int VECTOR_X_POS = 0;
    private static final int VECTOR_Y_POS = 1;

    private static GameManager sInstance;

    private GameManager(int size) {
        size = size;
    }

    public static GameManager getInstance(int size) {
        if (sInstance == null) {
            sInstance = new GameManager(size);
        }
        return sInstance;
    }

    public static GameManager getInstance() {
        return getInstance(DEFAULT_SIZE);
    }

    private void setup() {
        // TODO - implement saving/restoring the state.
        grid = new Grid(size);
        addStartTiles();
    }

    private void addStartTiles() {
        for (int i = 0; i < START_TILE_COUNT; i++) {
            addRandomTile();
        }
    }

    private void addRandomTile() {
        if (grid.areCellsAvailable()) {
            int value = Math.random() < 0.5 ? Tile.DEFAULT_VALUE_2 : Tile.DEFAULT_VALUE_4;
            Tile tile = grid.getRandomAvailableCell();
            tile.value = value;
            grid.insertTile(tile);
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
        // TODO implement
        if (isGameTerminated()) {
            return;
        }

        int[] vector = getVector(direction);

        prepareTiles();
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
        while (grid.withinBounds(tile) && grid.isCellAvailable(tile)) {
            previous = tile;
            tile = new Tile(tile.x + vector[VECTOR_X_POS], tile.y + vector[VECTOR_Y_POS], tile.value);
        }
        return new Tile[]{previous, tile};
    }
}
