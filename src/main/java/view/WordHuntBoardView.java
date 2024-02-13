package view;

import javafx.scene.layout.GridPane;

import java.util.Stack;

public class WordHuntBoardView extends GridPane {

    private Tile[][] buttons;
    private static final int GRID_SIZE = 4;
    private Tile lastClickedTile;
    private Stack<Tile> selectedTilesStack;

    public WordHuntBoardView() {
        initializeBoard();
    }

    public WordHuntBoardView(String[][] givenBoard) {
        initializeBoard(givenBoard);
    }

    private void initializeBoard() {
        setHgap(10);
        setVgap(10);
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
        selectedTilesStack = new Stack<>();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                createAndAddTile("A", i, j);
            }
        }

        lastClickedTile = null;
    }

    private void initializeBoard(String[][] givenBoard) {
        setHgap(10);
        setVgap(10);
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
        selectedTilesStack = new Stack<>();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                createAndAddTile(givenBoard[i][j], i, j);
            }
        }

        lastClickedTile = null;
    }

    private void createAndAddTile(String letter, int row, int col) {
        Tile tile = new Tile(letter, row, col);
        tile.setYellowState();
        tile.setMinSize(80, 80);
        tile.setOnMouseClicked(event -> handleMouseClick(tile));
        buttons[row][col] = tile;
        add(tile, col, row);
    }

    private void handleMouseClick(Tile tile) {
        if (selectedTilesStack.isEmpty()) {
            toggleTileState(tile);
            lastClickedTile = tile;
            selectedTilesStack.push(tile);
        } else if (!selectedTilesStack.contains(tile) && lastClickedTile != null && tile != lastClickedTile && isAdjacent(tile, selectedTilesStack.peek())) {
            toggleTileState(tile);
            lastClickedTile = tile;
            selectedTilesStack.push(tile);
        } else if (lastClickedTile != null && tile == selectedTilesStack.peek()) {
            selectedTilesStack.pop();
            toggleTileState(tile);
            lastClickedTile = selectedTilesStack.peek();
        } 
    }
    
    
    
    private void clearStackAndDeselect() {
        while (!selectedTilesStack.isEmpty()) {
            Tile tile = selectedTilesStack.pop();
            toggleTileState(tile);
        }
        lastClickedTile = null;
    }


    private void toggleTileState(Tile tile) {
        if (tile.getCurrentState().equals("yellow-state")) {
            tile.setNeutralState();
        } else {
            tile.setYellowState();
        }
    }

    private boolean isAdjacent(Tile tile1, Tile tile2) {
        int row1 = tile1.getRow();
        int col1 = tile1.getCol();
        int row2 = tile2.getRow();
        int col2 = tile2.getCol();

        if (!selectedTilesStack.isEmpty()) {
            Tile topOfStack = selectedTilesStack.peek();
            int topRow = topOfStack.getRow();
            int topCol = topOfStack.getCol();

            return (Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1) &&
                    (Math.abs(row1 - topRow) <= 1 && Math.abs(col1 - topCol) <= 1);
        } else {
            return Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1;
        }
    }
}
