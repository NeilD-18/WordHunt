package viewmodel;

import java.util.*;

import view.*;
import model.*;

/**
 * WordHuntBoard ViewModel
 */
public class WordHuntBoardViewModel {

    private WordHuntGame game;
    private Tile[][] buttons;
    private static final int GRID_SIZE = 4;
    private Tile lastClickedTile;
    private ArrayList<String> foundWords;
    private ArrayList<String> foundBonusWords;

    /**
     * Constructor, initializes viewmodel
     */
    public WordHuntBoardViewModel(){
        this.game = new WordHuntGame();
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
    }

    /**
     * initialize board
     * @param Boolean load, true if loading, false if not
     * @param String txt file to load
     * @return ArrayList<ArrayList<String>> as board
     */
    public ArrayList<ArrayList<String>> initializeBoard(Boolean load, String txtFile){
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
        if(load){
            game.loadBoard(txtFile);
        }
        else{
            game.generateRandomBoard();
        }
        return game.getBoard();
    }

    /**
     * @return Tile last clicked
     */
    public Tile getLastClicked(){
        return lastClickedTile;
    }

    /**
     * Reset state of all tiles
     */
    public void wipeTiles(){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                buttons[i][j].setYellowState();
            }
        }
    }

    /**
     * Toggle the tile state
     * @param Tile
     */
    public void toggleTileState(Tile tile) {
        if (tile.getCurrentState().equals("yellow-state")) {
            tile.setNeutralState();
        } else {
            tile.setYellowState();
        }
    }

    /**
     * Getter for tile
     * @return Tile
     */
    public Tile getButton(int row, int col){
        return buttons[row][col];
    }

    /**
     * Add tile
     * @param Tile to be added
     * @param int row
     * @param int col
     */
    public void addButton(Tile tile, int row, int col){
        buttons[row][col] = tile;
    }

    /**
     * Setter for last clicked tile
     * @param Tile
     */
    public void setLastClickedTile(Tile tile){
        lastClickedTile = tile;
    }

    /**
     * Check adjacency between two tiles
     * @param Tile 1
     * @param Tile 2
     * @param Stack<Tile>
     */
    public boolean isAdjacent(Tile tile1, Tile tile2, Stack<Tile> selectedTilesStack) {
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

    /**
     * Handle Word from view
     * @param Stack<Tile>
     * @param WordHuntScoreView
     * @param WordHuntWordsFoundView
     */
    public void handleWord(Stack<Tile> stack, WordHuntScoreView scoreView, WordHuntWordsFoundView wordsFound){
        foundWords = game.getFoundWords();
        foundBonusWords = game.getFoundBonusWords();
        String word = "";
        while (stack.isEmpty() == false){
            word = stack.pop().getData() + word;
        }
        word = word.toLowerCase();
        int validity = this.game.isValidWord(word);
        if (!foundWords.contains(word) && !foundBonusWords.contains(word)){
            if (validity == 1) {
                this.game.addFoundWord(false, word);
                wordsFound.wordList.add(wordsFound.createStyledText(word));
                wordsFound.animateWordAddition();
                scoreView.incrementTotalWordsFound();
            } else if (validity == 2) {
                this.game.addFoundWord(true, word);
                wordsFound.wordList.add(wordsFound.bonusStyledText(word));
                wordsFound.animateWordAddition();
            }
        }
        this.wipeTiles();
    }

    /**
     * Check win
     * @param WordHuntScoreView
     * @param WordHuntWordsFoundView
     * @return Boolean 
     */
    public Boolean checkWin(WordHuntScoreView scoreView, WordHuntWordsFoundView wordsFound){
        return scoreView.getTotalWordsFound() == scoreView.getPossibleWords();
    }

    /**
     * get number of possible words
     * @return int
     */
    public int getNumPossibleWords(){
        System.out.println(game.getPossibleWords().size());
        return game.getPossibleWords().size();
    }

    /**
     * set win state
     */
    public void setWin(){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                buttons[i][j].setGreenState();
                buttons[i][j].setDisable(true); 
            }
        }
    }

    /**
     * Save the board in a txt file
     * @param String file
     */
    public void saveGame(String file) { 
        game.saveBoard(file); 
    }
}
