package view;

import javafx.scene.layout.GridPane;

public class WordHuntBoardView extends GridPane {

    private Tile[][] buttons;
    private static final int GRID_SIZE = 4;
    private Tile lastClickedTile; // Initialize lastClickedTile

    public WordHuntBoardView() {
        initializeDefaultBoard();
    }

    public WordHuntBoardView(String[][] givenBoard) {
        initializeBoard(givenBoard);
    }

    private void initializeDefaultBoard() {
        setHgap(10);
        setVgap(10);
        buttons = new Tile[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int row = i;
                final int col = j;
                Tile tile = new Tile("A", i, j);
                tile.setYellowState();
                tile.setMinSize(80, 80);
                tile.setOnMousePressed(event -> handleMousePressed(row, col));
                buttons[i][j] = tile;
                add(tile, j, i);
            }
        }

        lastClickedTile = buttons[0][0]; // Initialize lastClickedTile to a default value
    }

    private void initializeBoard(String[][] givenBoard) {
        setHgap(10);
        setVgap(10);
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int row = i;
                final int col = j;
                Tile tile = new Tile(givenBoard[i][j], i, j);
                tile.setYellowState();
                tile.setMinSize(80, 80);
                tile.setOnMousePressed(event -> handleMousePressed(row, col));
                buttons[i][j] = tile;
                add(tile, j, i);
            }
        }

        lastClickedTile = buttons[0][0]; // Initialize lastClickedTile to a default value
    }

    private void handleMousePressed(int row, int col) {
        Tile tile = buttons[row][col];

        // Check if the clicked tile is adjacent
        if (isAdjacent(tile, lastClickedTile)) {
            if (tile.getCurrentState().equals("yellow-state")) {
                tile.setNeutralState();
            } else {
                tile.setYellowState();
            }
            lastClickedTile = tile;
        }
    }

    private boolean isAdjacent(Tile tile1, Tile tile2) {
        int row1 = tile1.getRow();
        int col1 = tile1.getCol();
        int row2 = tile2.getRow();
        int col2 = tile2.getCol();

        // Check if the tiles are adjacent (in any direction)
        return Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1;
    }
}
