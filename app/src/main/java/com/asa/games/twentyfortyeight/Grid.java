package com.asa.games.twentyfortyeight;

import java.util.ArrayList;
import java.util.Random;

import timber.log.Timber;

public class Grid {
    private static final String TAG = "Grid";

    private int size;
    public Tile[][] cells;

    public Grid(int size) {
        this.size = size;
        cells = new Tile[size][size];
        Timber.tag(TAG);
    }

    public Grid(Grid previous) {
        if (previous == null) {
            throw new IllegalStateException("Cannot create a grid from a null grid.");
        }
        this.size = previous.size;
        Timber.tag(TAG);
        fromState(previous.cells);
    }

    /**
     * Builds an empty grid
     */
    public void empty() {
        cells = null;
        cells = new Tile[size][size];
    }

    private void init() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells[x][y] = new Tile(x, y, 0);
            }
        }
    }

    /**
     * Builds out a grid from a previous grid
     */
    public void fromState(Tile[][] previousState) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = previousState[x][y];
                if (tile != null) {
                    cells[x][y] = tile;
                }
            }
        }
    }

    /**
     * Finds a random cell that can be filled
     */
    public Tile getRandomAvailableCell() {
        ArrayList<Tile> tiles = getAvailableCells();
        int availableTileSize = tiles.size();
        if (availableTileSize > 0) {
            Random rand = new Random();
            int randNum = rand.nextInt(availableTileSize + 1);
            return tiles.get(randNum);
        }
        return null;
    }

    /**
     * Finds all of the available cells.
     */
    public ArrayList<Tile> getAvailableCells() {
        ArrayList<Tile> availableCells = new ArrayList<Tile>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = cells[x][y];
                if (tile == null) {
                    availableCells.add(new Tile(x, y, 0));
                }
            }
        }
        return availableCells;
    }

    /**
     * Checks if there are any cells available.
     */
    public boolean areCellsAvailable() {
        return getAvailableCells().size() > 0;
    }

    /**
     * Determines if the given cell is available.
     */
    public boolean isCellAvailable(Tile tile) {
        return getTile(tile.x, tile.y) == null;
    }

    public boolean isCellOccupied(Tile tile) {
        return getTile(tile.x, tile.y) != null;
    }

    public Tile getTile(int x, int y) {
        if (withinBounds(x, y)) {
            return cells[x][y];
        }
        return null;
    }

    /**
     * Inserts the given tile into the grid
     *
     * @param tile
     */
    public void insertTile(Tile tile) {
        if (!withinBounds(tile)) {
            throw new IllegalStateException("The tile is not within range:" + tile.toString());
        }
        cells[tile.x][tile.y] = tile;
    }

    /**
     * Removes the given tile.
     *
     * @param tile
     */
    public void removeTile(Tile tile) {
        removeTile(tile.x, tile.y);
    }

    public void removeTile(int x, int y) {
        if (!withinBounds(x, y)) {
            throw new IllegalStateException("The tile is not within range. x: " + x + "; y: " + y);
        }
        cells[x][y] = null;
    }

    /**
     * Determines if the given position is within bounds of the grid
     */
    public boolean withinBounds(Tile tile) {
        return tile.x >= 0 && tile.x < size &&
                tile.y >= 0 && tile.y < size;
    }

    /**
     * Determines if the given position is within bounds of the grid
     */
    public boolean withinBounds(int x, int y) {
        return x >= 0 && x < size &&
                y >= 0 && y < size;
    }


    public void logGrid(){
        String row = "";
        for(int x = 0; x < size; x++){
            row += "[";
            for(int y = 0; y < size; y++){
                if(y > 0){
                    row+=", ";
                }
                Tile tile = cells[x][y];
                if(tile != null){
                    row += tile.value;
                }else{
                    row += "x";
                }
            }
            row += "]\n";
        }
        Timber.d("Grid:\n%s\n", row);
    }

}
