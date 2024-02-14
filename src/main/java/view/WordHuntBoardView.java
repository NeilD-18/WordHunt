package view;

import javafx.scene.layout.GridPane;
import viewmodel.WordHuntBoardViewModel;


public class WordHuntBoardView extends GridPane {

    private Tile[][] buttons;
    private static final int GRID_SIZE = 4;
    private Tile lastClickedTile;
    private WordHuntBoardViewModel viewModel;

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
        viewModel = new WordHuntBoardViewModel();

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
        viewModel = new WordHuntBoardViewModel();

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
        if (viewModel.getSelectedTilesStack().isEmpty()) {
            toggleTileState(tile);
            lastClickedTile = tile;
            viewModel.addToSelectedTilesStack(tile);
        } else if (!viewModel.getSelectedTilesStack().contains(tile) && lastClickedTile != null && tile != lastClickedTile && viewModel.isAdjacent(tile)) {
            toggleTileState(tile);
            lastClickedTile = tile;
            viewModel.addToSelectedTilesStack(tile);
        } else if (lastClickedTile != null && tile == viewModel.getSelectedTilesStack().peek()) {
            viewModel.removeFromSelectedTilesStack(tile);
            toggleTileState(tile);
            lastClickedTile = viewModel.getSelectedTilesStack().isEmpty() ? null : viewModel.getSelectedTilesStack().peek();
        }
    }

    private void toggleTileState(Tile tile) {
        if (tile.getCurrentState().equals("yellow-state")) {
            tile.setNeutralState();
        } else {
            tile.setYellowState();
        }
    }
}
