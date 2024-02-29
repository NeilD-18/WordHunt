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
    public int GRID_SIZE;
    public WordHuntScoreView scoreView;
    public WordHuntWordsFoundView wordsFound;

    
    

    /**
     * Constructs a new WordHuntBoardView object with the specified file, score view, and words found view.
     * @param file The file to initialize the board from.
     * @param scoreview The score view associated with the game.
     * @param wordsfound The view for displaying found words.
     */
    public WordHuntBoardView(String file, WordHuntScoreView scoreview, WordHuntWordsFoundView wordsfound, int gridSize) {
        scoreView = scoreview;
        wordsFound = wordsfound;
        GRID_SIZE = gridSize;
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
        wordHuntBoardVM = new WordHuntBoardViewModel(GRID_SIZE);
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
        wordHuntBoardVM = new WordHuntBoardViewModel(GRID_SIZE);
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
        tile.addEventHandler(MouseEvent.MOUSE_DRAGGED, new ButtonDragListener(this.getGridSize()));
        tile.setOnMouseReleased(event -> handleMouseReleased(tile));
        wordHuntBoardVM.addButton(tile, row, col);
        add(tile, col, row);
    }

    public int getGridSize(){
        return GRID_SIZE;
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

        public int GRID_SIZE;

        public ButtonDragListener(int gridSize){
            GRID_SIZE = gridSize;
        }

        public int getGridSize(){
            return GRID_SIZE;
        }

        @Override
        public void handle(MouseEvent event){
            int row = getButtonRow(event);
            int col = getButtonCol(event);
            System.out.println("Row: " + row);
            System.out.println("Column: " + col);
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
            for (int i = 0; i < selectedTilesStack.size(); i++){
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
            double top = wordHuntBoardVM.getButton(0, 0).getLayoutY();
            double bottom = wordHuntBoardVM.getButton(this.getGridSize() - 1, 0).getLayoutY();
            // System.out.println(top);
            // System.out.println(bottom);
            // System.out.println("Y Location: " + y);
            if (y >= top && y <= bottom + buttonHeight){
                // System.out.println("true");
                for (int i = 0; i < this.getGridSize(); i++){
                    double buttonY = wordHuntBoardVM.getButton(i, 0).getLayoutY();
                    if (y >= buttonY && y <= buttonY + buttonHeight){
                        return i;
                    }
                }
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
            double left = wordHuntBoardVM.getButton(0, 0).getLayoutX();
            double right = wordHuntBoardVM.getButton(0, this.getGridSize() - 1).getLayoutX();
            if (x >= left && x <= right + buttonWidth){
                // System.out.println("true");
                for (int i = 0; i < this.getGridSize(); i++){
                    double buttonX = wordHuntBoardVM.getButton(0, i).getLayoutX();
                    if (x >= buttonX && x <= buttonX + buttonWidth){
                        return i;
                    }
                }
            }
            return -1;
        }

    }

}
