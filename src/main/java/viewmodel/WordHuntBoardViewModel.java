package viewmodel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import view.*;
import model.*;

/**
 * WordHuntBoard ViewModel
 */
public class WordHuntBoardViewModel {

    private WordHuntGame game;
    private Tile[][] buttons;
    private int GRID_SIZE;
    private Tile lastClickedTile;
    private ArrayList<String> foundWords;
    private ArrayList<String> foundBonusWords;
    private WordHuntWords words; 

    /**
     * Constructor, initializes viewmodel
     */
    public WordHuntBoardViewModel(int grid){
        GRID_SIZE = grid;
        this.game = new WordHuntGame(grid);
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
        words = new WordHuntWords(game);
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
                if (buttons[i][j].isDisabled() == false){
                    buttons[i][j].setYellowState();
                }
            }
        }
    }

    /**
     * Toggle the tile state
     * @param Tile
     */
    public void toggleTileState(Tile tile) {
        if (tile.getCurrentState().equals("yellow-state") && !tile.isDisabled()) {
            tile.setNeutralState();
        } else {
            if (!tile.isDisabled()) {
                tile.setYellowState();
            }
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
        for (int j = 0; j < GRID_SIZE; j++){
            for (int k = 0; k < GRID_SIZE; k++){
                // System.out.print("Letter use for " + j + ", " + k + ": ");
                // System.out.println(game.getLetterUse(j, k));
            }
        }
        foundWords = game.getFoundWords();
        foundBonusWords = game.getFoundBonusWords();
        String word = "";
        Stack<Tile> temp = new Stack<Tile>();
        while (stack.isEmpty() == false){
            Tile tile = stack.pop();
            word = tile.getData() + word;
            temp.add(tile);
        }
        word = word.toLowerCase();
        int validity = this.game.isValidWord(word);

        if (!foundWords.contains(word) && !foundBonusWords.contains(word)){
            if (validity == 1) {
                game.decrementLetterUse(word);
                this.game.addFoundWord(false, word);
                wordsFound.wordList.add(wordsFound.createStyledText(word));
                wordsFound.animateWordAddition();
                scoreView.incrementTotalWordsFound();
            } else if (validity == 2) {
                this.game.addFoundWord(true, word);
                wordsFound.wordList.add(wordsFound.bonusStyledText(word));
                wordsFound.animateWordAddition();
                // bonus word point increment should go here "scoreView.incrementTotalWordsFound()"<-------
            }
            else if (validity == 3){
                String unicode = this.game.getUnicode(word);

                
                if (getFourLetterWords().contains(word)){ // need to find some way to call POSSIBLE_FOUR_LETTER_WORDS and make that check
                    this.game.addFoundWord(false, word); 
                    wordsFound.wordList.add(wordsFound.emojiPopUp(word, unicode));
                    wordsFound.animateWordAddition();
                    scoreView.incrementTotalWordsFound();
    
                }
                if (getBonusWords().contains(word)){  // need to find some way to call BONUS_WORDS and make that check for word
                    this.game.addFoundWord(true,word);
                    wordsFound.wordList.add(wordsFound.emojiPopUp(word, unicode));
                    wordsFound.animateWordAddition();

                }
                this.game.addFoundWord(false, word);
            }
        }
        this.checkUsedTiles();
        this.wipeTiles();
    }

    /**
     * initialize four letter word
     * @return
     */
    public ArrayList<String> getFourLetterWords() {
        ArrayList<String> fourLetterWords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/WordLists/4LetterWordList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                fourLetterWords.add(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return fourLetterWords;
    }

    /**
     * Initializes bonus word list.
     */
    public ArrayList<String> getBonusWords() {
        ArrayList<String> bonusWords = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/WordLists/BonusWordListFinal.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                bonusWords.add(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return bonusWords;
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
     * Checks the board for used up tiles, disables used up tiles
     */
    public void checkUsedTiles(){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                int tmp = game.getLetterUse(i, j);
                if (tmp == 0){
                    buttons[i][j].setUnavailableState();
                    buttons[i][j].setDisable(true);
                }
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
