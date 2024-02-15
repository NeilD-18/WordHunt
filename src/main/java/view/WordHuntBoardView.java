package view;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import viewmodel.WordHuntBoardViewModel;

import java.util.Stack;

public class WordHuntBoardView extends GridPane {

    private Tile[][] buttons;
    private static final int GRID_SIZE = 4;
    private static final int GRID_X_OFFSET = 435;
    private static final int GRID_Y_OFFSET = 196;
    private static final int GRID_GAP = 10;
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
        tile.setOnMousePressed(event -> handleMouseClick(tile));
        tile.addEventHandler(MouseEvent.MOUSE_DRAGGED, new ButtonDragListener());
        tile.setOnMouseReleased(event -> handleMouseReleased(tile));
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
            lastClickedTile = selectedTilesStack.isEmpty() ? null : selectedTilesStack.peek();
        }
    }

    private void handleMouseReleased(Tile tile) {
        String s = "";
        while (selectedTilesStack.isEmpty() == false){
            s = selectedTilesStack.pop().getText() + s;
        }
        
        WordHuntBoardViewModel wordHuntBoardVM = new WordHuntBoardViewModel();
        wordHuntBoardVM.handleWord(s);
        this.wipeTiles();
    }

    private void handleMouseUndragged(Tile tile) {
        toggleTileState(tile);
    }

    private void wipeTiles(){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                buttons[i][j].setYellowState();
            }
        }
    }


    private void toggleTileState(Tile tile) {
        if (tile.getCurrentState().equals("yellow-state")) {
            tile.setNeutralState();
        } else {
            tile.setYellowState();
        }
    }

    private boolean isAdjacent(Tile tile1, Tile tile2) {
        boolean adjacent = WordHuntBoardViewModel.isAdjacent(tile1, tile2, selectedTilesStack);
        return adjacent;
    }

    private class ButtonDragListener implements javafx.event.EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event){
            int row = getButtonRow(event);
            int col = getButtonCol(event);
            // System.out.println("Row: " + row);
            // System.out.println("Column: " + col);
            if (row >= 0 && col >=0){
                if (selectedTilesStack.contains(buttons[row][col])){
                    if (buttons[row][col] != selectedTilesStack.peek()){
                        handleMouseClick(buttons[row][col]);
                    }
                    if (selectedTilesStack.size() > 1){
                        Tile tmp = selectedTilesStack.pop();
                        if (buttons[row][col] == selectedTilesStack.peek()){
                            handleMouseUndragged(tmp);
                        }
                        else{
                            selectedTilesStack.push(tmp);
                        }
                    }
                }
                else{
                    handleMouseClick(buttons[row][col]);
                }
            }
            System.out.println(selectedTilesStack.toString());
        }

        private int getButtonRow(MouseEvent event){
            double y = event.getSceneY() - GRID_Y_OFFSET;
            double buttonHeight = buttons[0][0].getHeight();
            double row0 = buttons[0][0].getLayoutY();
            double row1 = buttons[1][0].getLayoutY();
            double row2 = buttons[2][0].getLayoutY();
            double row3 = buttons[3][0].getLayoutY();
            // System.out.println("Y Location: " + y);
            if (y <= row3 + buttonHeight && y >= row0){
                if (y >= row0 && y <= row0 + buttonHeight){
                    return 0;
                }
                if (y >= row1 && y <= row1 + buttonHeight){
                    return 1;
                }
                if (y >= row2 && y <= row2 + buttonHeight){
                    return 2;
                }
                if (y >= row3 && y <= row3 + buttonHeight){
                    return 3;
                }
                return -1;
            }
            return -1;
        }
        
        private int getButtonCol(MouseEvent event){
            double x = event.getSceneX() - GRID_X_OFFSET;
            double buttonWidth = buttons[0][0].getWidth();
            double col0 = buttons[0][0].getLayoutX();
            double col1 = buttons[0][1].getLayoutX();
            double col2 = buttons[0][2].getLayoutX();
            double col3 = buttons[0][3].getLayoutX();
            // System.out.println("X Location: " + x);
            if (x <= col3 + buttonWidth && x >= col0){
                if (x >= col0 && x <= col0 + buttonWidth){
                    return 0;
                }
                if (x >= col1 && x <= col1 + buttonWidth){
                    return 1;
                }
                if (x >= col2 && x <= col2 + buttonWidth){
                    return 2;
                }
                if (x >= col3 && x <= col3 + buttonWidth){
                    return 3;
                }
                return -1;
            }
            return -1;
        }

    }

}
