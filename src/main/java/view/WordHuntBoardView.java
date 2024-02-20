package view;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import viewmodel.WordHuntBoardViewModel;
import viewmodel.WordHuntCurrentWordViewModel;

import java.util.ArrayList;
import java.util.Stack;

/**
 * WordHuntBoardView class.
 */
public class WordHuntBoardView extends GridPane {

    WordHuntBoardViewModel wordHuntBoardVM;
    WordHuntCurrentWordViewModel wordHuntCurrentWordVM; 
    public Stack<Tile> selectedTilesStack;
    private static final int GRID_X_OFFSET = 435;
    private static final int GRID_Y_OFFSET = 196;
    public WordHuntScoreView scoreView;
    public WordHuntWordsFoundView wordsFound;

    
    

    /**
     * Constructs a new WordHuntBoardView object with the specified file, score view, and words found view.
     * @param file The file to initialize the board from.
     * @param scoreview The score view associated with the game.
     * @param wordsfound The view for displaying found words.
     */
    public WordHuntBoardView(String file, WordHuntScoreView scoreview, WordHuntWordsFoundView wordsfound) {
        scoreView = scoreview;
        wordsFound = wordsfound;
        if (file != "null"){
            initializeBoard(file);
        }
        else{
            initializeBoard();
        }
    }

    /**
     * Initializes the game board.
     */
    private void initializeBoard() {
        wordHuntBoardVM = new WordHuntBoardViewModel();
        wordHuntCurrentWordVM = new WordHuntCurrentWordViewModel();
        selectedTilesStack = new Stack<>();
        setHgap(10);
        setVgap(10);
        ArrayList<ArrayList<String>> tmp = wordHuntBoardVM.initializeBoard(false, "");    
        for (int i = 0; i < tmp.size(); i++){
            for (int j = 0; j < tmp.get(i).size(); j++){
                createAndAddTile(tmp.get(i).get(j), i, j);
            }
        }
        wordHuntBoardVM.setLastClickedTile(null);
        wordHuntBoardVM.checkUsedTiles();
    }

    /**
     * Initializes the game board with the specified file.
     * @param file The file to initialize the board from.
     */
    private void initializeBoard(String file) {
        wordHuntBoardVM = new WordHuntBoardViewModel();
        wordHuntCurrentWordVM = new WordHuntCurrentWordViewModel();
        selectedTilesStack = new Stack<>();
        setHgap(10);
        setVgap(10);
        ArrayList<ArrayList<String>> tmp = wordHuntBoardVM.initializeBoard(true, file);
        for (int i = 0; i < tmp.size(); i++){
            for (int j = 0; j < tmp.get(i).size(); j++){
                createAndAddTile(tmp.get(i).get(j), i, j);
            }
        }
        wordHuntBoardVM.setLastClickedTile(null);
        wordHuntBoardVM.checkUsedTiles();
    }

    /**
     * Gets the number of possible words in the game.
     * @return The number of possible words.
     */
    public int getNumPossibleWords(){
        return wordHuntBoardVM.getNumPossibleWords();
    }

    /**
     * Creates and adds a tile to the game board.
     * @param letter The letter to be displayed on the tile.
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     */
    private void createAndAddTile(String letter, int row, int col) {
        Tile tile = new Tile(letter, row, col);
        tile.setYellowState();
        tile.setMinSize(80, 80);
        tile.setOnMousePressed(event -> handleMouseClick(tile));
        tile.addEventHandler(MouseEvent.MOUSE_DRAGGED, new ButtonDragListener());
        tile.setOnMouseReleased(event -> handleMouseReleased(tile));
        wordHuntBoardVM.addButton(tile, row, col);
        add(tile, col, row);
    }

    
    /**
     * Handles mouse click events on tiles.
     * @param tile The tile that was clicked.
     */
    private void handleMouseClick(Tile tile) {
        if (!tile.isDisabled()) { 
            if (selectedTilesStack.isEmpty()) {
                wordHuntBoardVM.toggleTileState(tile);
                wordHuntBoardVM.setLastClickedTile(tile);
                selectedTilesStack.push(tile);
            } else if (!selectedTilesStack.contains(tile) && wordHuntBoardVM.getLastClicked() != null && tile != wordHuntBoardVM.getLastClicked() && wordHuntBoardVM.isAdjacent(tile, selectedTilesStack.peek(), selectedTilesStack)) {
                wordHuntBoardVM.toggleTileState(tile);
                wordHuntBoardVM.setLastClickedTile(tile);
                selectedTilesStack.push(tile);
            } else if (wordHuntBoardVM.getLastClicked() != null && tile == selectedTilesStack.peek()) {
                selectedTilesStack.pop();
                wordHuntBoardVM.toggleTileState(tile);
                wordHuntBoardVM.setLastClickedTile(selectedTilesStack.isEmpty() ? null : selectedTilesStack.peek());
            }
        }
    }

    /**
     * Handles mouse released events on tiles.
     * @param tile The tile that was released.
     */
    private void handleMouseReleased(Tile tile) {
        wordHuntBoardVM.handleWord(selectedTilesStack, scoreView, wordsFound);
        if (checkWin()){
            wordHuntBoardVM.setWin();
        }
        wordHuntCurrentWordVM.updateCurrentWord("");
    }

    /**
     * Handle the mouse undrag.
     * @param tile the tile that was undragged
     */
    private void handleMouseUndragged(Tile tile) {
        wordHuntBoardVM.toggleTileState(tile);
    }

    /**
     * Checks if the game has been won.
     * @return True if the game is won, false otherwise.
     */
    public Boolean checkWin(){
        return wordHuntBoardVM.checkWin(scoreView, wordsFound);
    }
    
    /**
     * A class implementing the button drag listener for tile dragging functionality.
     */
    private class ButtonDragListener implements javafx.event.EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event){
            int row = getButtonRow(event);
            int col = getButtonCol(event);
            // System.out.println("Row: " + row);
            // System.out.println("Column: " + col);
            if (row >= 0 && col >=0){
                if (selectedTilesStack.contains(wordHuntBoardVM.getButton(row, col))){
                    if (wordHuntBoardVM.getButton(row, col) != selectedTilesStack.peek()){
                        handleMouseClick(wordHuntBoardVM.getButton(row, col));
                    }
                    if (selectedTilesStack.size() > 1){
                        Tile tmp = selectedTilesStack.pop();
                        if (wordHuntBoardVM.getButton(row, col) == selectedTilesStack.peek()){
                            handleMouseUndragged(tmp);
                        }
                        else{
                            selectedTilesStack.push(tmp);
                        }
                    }
                }
                else{
                    handleMouseClick(wordHuntBoardVM.getButton(row, col));
                }
            }
           
            String word = "";
            // System.out.println(selectedTilesStack.toString());
            for (int i = 0; i< selectedTilesStack.size(); i++){
                word += selectedTilesStack.get(i).getData();
            }
            wordHuntCurrentWordVM.updateCurrentWord(word);
        }

        /**
         * Get button row
         * @param MouseEvent event
         */
        private int getButtonRow(MouseEvent event){
            double y = event.getSceneY() - GRID_Y_OFFSET;
            double buttonHeight = wordHuntBoardVM.getButton(0, 0).getHeight();
            double row0 = wordHuntBoardVM.getButton(0, 0).getLayoutY();
            double row1 = wordHuntBoardVM.getButton(1, 0).getLayoutY();
            double row2 = wordHuntBoardVM.getButton(2, 0).getLayoutY();
            double row3 = wordHuntBoardVM.getButton(3, 0).getLayoutY();
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
        
        /**
         * Get button col
         * @param MouseEvent event
         */
        private int getButtonCol(MouseEvent event){
            double x = event.getSceneX() - GRID_X_OFFSET;
            double buttonWidth = wordHuntBoardVM.getButton(0, 0).getWidth();
            double col0 = wordHuntBoardVM.getButton(0, 0).getLayoutX();
            double col1 = wordHuntBoardVM.getButton(0, 1).getLayoutX();
            double col2 = wordHuntBoardVM.getButton(0, 2).getLayoutX();
            double col3 = wordHuntBoardVM.getButton(0, 3).getLayoutX();
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
