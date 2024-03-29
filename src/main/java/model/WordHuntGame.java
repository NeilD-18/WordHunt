package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.sql.RowSetEvent;

import javafx.util.Pair;


/**
 * WordHuntGame model class. 
 *
 */
public class WordHuntGame {

    private ArrayList<ArrayList<String>> board;
    private ArrayList<ArrayList<Integer>> usedLetters;
    private ArrayList<ArrayList<Integer>> startingCountOfLetters; 

    private int COLUMNS;
    private int ROWS;
    private WordHuntScore score;
    private WordHuntWords words;

    /**
     * Default constructor
     */
    public WordHuntGame(int grid){
        words = new WordHuntWords(this, grid);
        score = new WordHuntScore(words);
        words.initializeWordLists();
        COLUMNS = grid;
        ROWS = grid;
        // System.out.println(grid);
        board = new ArrayList<ArrayList<String>>();
        usedLetters = new ArrayList<ArrayList<Integer>>();
        startingCountOfLetters = new ArrayList<ArrayList<Integer>>();
    }


    /**
     *
     * Generates a random game board filled with letters and finds words on the board.
     */
    public void generateRandomBoard(){
        Random r = new Random();
        for (int i = 0; i < ROWS; i++){
            ArrayList<String> temp = new ArrayList<>();
            ArrayList<Integer> tempInt = new ArrayList<>();
            ArrayList<Integer> tempInt1 = new ArrayList<>();
            for (int j = 0; j < COLUMNS; j++){
                char c = (char)(r.nextInt(26) + 'A');
                while (c == 'X' || c == 'Z' || c == 'V' || c == 'Q'){
                    c = (char)(r.nextInt(26) + 'A');
                }
                String letter = String.valueOf(c);
                temp.add(letter);
                tempInt.add(0);
                tempInt1.add(0);
            }
            board.add(temp);
            usedLetters.add(tempInt);
            startingCountOfLetters.add(tempInt1); 
        }
        words.findWords();
    }

    /**
     * Clears the game board and tears down associated objects.
     */
    public void tearDown(){
        board = new ArrayList<ArrayList<String>>();
        words.tearDown();
        score.tearDown();
    }


    /**
     * Increments the uses of a tile, given row and column index.
     * @param row Row of tile location.
     * @param col Col of tile location.
     */
    public void incrementLetterUse(int row, int col){
        int tmp = usedLetters.get(row).get(col);
        usedLetters.get(row).set(col, tmp + 1);
    }

     /**
     * Increments the starting word uses of a tile, given row and column index.
     * @param row Row of tile location.
     * @param col Col of tile location.
     */
    public void incrementStartingCountForLetter(int row, int col) { 
        int tmp = startingCountOfLetters.get(row).get(col);
        startingCountOfLetters.get(row).set(col, tmp + 1);
    }

    /**
     * Decrease the starting count given a row and col
     * @param row
     * @param col
     */
    public void decrementStartingCountForLetter(int row, int col) { 
        int tmp = startingCountOfLetters.get(row).get(col);
        startingCountOfLetters.get(row).set(col, tmp - 1);
    }

    /**
     * Decrements the uses of a tile, given a word.
     * @param Word to decrement tiles of
     */
    public void decrementLetterUse(String word){
        ArrayList<Pair<Integer, Integer>> tiles = words.getTilesForWord(word);
        for (int i = 0; i < word.length(); i++){
            Pair<Integer, Integer> p = tiles.get(i);
            int r = p.getKey();
            int c = p.getValue();
            int tmp = usedLetters.get(r).get(c);
            usedLetters.get(r).set(c, tmp - 1);
            if (i == 0) { 
                //System.out.println(String.valueOf(startingCountOfLetters.get(r).get(c)) + " before ");
                decrementStartingCountForLetter(r, c);
                //System.out.println(String.valueOf(startingCountOfLetters.get(r).get(c)) + " after ");
            }
            
        }
    }

    /**
     * Gets the uses of a tile, given row and column index.
     * @param row Row of tile location.
     * @param col Col of tile location.
     * @return int number of times tile is used in basic words
     */
    public int getLetterUse(int row, int col){
        return usedLetters.get(row).get(col);
    }

    /**
     * Get the count of words that can be made starting from the tile
     * @param row
     * @param col
     * @return number of words. 
     */
    public int getStartingCountLetterUse(int row, int col) { 
        return startingCountOfLetters.get(row).get(col);
    }
    


    /**
     * Gets the current game board.
     * @return The game board as a 2D ArrayList of strings.
     */
    public ArrayList<ArrayList<String>> getBoard(){
        return board;
    }

    /**
     * Gets the current game board's used tiles.
     * @return The game board's used tiles as a 2D ArrayList of ints.
     */
    public ArrayList<ArrayList<Integer>> getUsedTiles(){
        return usedLetters;
    }


    /**
     * Loads a game board from a file.
     * @param filePath The path to the file containing the game board.
     */
    public void loadBoard(String filePath){
        // System.out.println(filePath);
        this.tearDown();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine();
            board = new ArrayList<>();
            if (ROWS <= 7 && ROWS >= 4){
                for (int i = 0; i < ROWS; i++) {
                    if (scanner.hasNextLine()) {
                        String[] rowElements = scanner.nextLine().split("\\s+");
                        int size = 0;
                        for (String x : rowElements) {
                            size++;
                        }
                        // System.out.println("Size: " + size);
                        // System.out.println("Rows: " + ROWS);
                        if (size == ROWS){
                            ArrayList<String> row = new ArrayList<>();
                            ArrayList<Integer> tempInt = new ArrayList<>();
                            ArrayList<Integer> tempInt1 = new ArrayList<>();
                            for (String element : rowElements) {
                                row.add(element);
                                tempInt.add(0);
                                tempInt1.add(0);
                            }
                            board.add(row);
                            usedLetters.add(tempInt);
                            startingCountOfLetters.add(tempInt1);
                        }
                        else{
                            // System.out.println("Else clause");
                            System.err.println("Error: File format does not match the expected format.");
                            break;
                        }
                    }
                    else{
                        System.err.println("Error: File format does not match the expected format.");
                        break;
                    }
                }
            words.findWords();
            }
            else {
                System.err.println("Error: File format does not match the expected format.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(POSSIBLE_4_LETTER_WORDS);
    }

    /**
     * Saves the current game board to a file.
     * @param filePath The path to save the game board.
     */
    public void saveBoard(String filePath) {
        // System.out.println(filePath);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(ROWS);
            for (ArrayList<String> row : board) {
                for (String letter : row) {
                    writer.print(letter + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks if a move is valid on the game board.
     * @param prevRow The previous row index.
     * @param prevCol The previous column index.
     * @param nextRow The next row index.
     * @param nextCol The next column index.
     * @return True if the move is valid, false otherwise.
     */
    public boolean isValidMove (int prevRow, int prevCol, int nextRow, int nextCol){
        if (nextRow >= 0 && nextRow <= (ROWS - 1) && nextCol >= 0 && nextCol <= (COLUMNS - 1)){
            int vCheck = prevRow - nextRow;
            int hCheck = prevCol - nextCol;

            if (vCheck >= -1 && vCheck <= 1 && hCheck >= -1 && hCheck <= 1){
                if (vCheck == hCheck){
                    return vCheck != 0;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Gets the list of found words.
     * @return The list of found words.
     */
    public ArrayList<String> getFoundWords(){
        return words.getFoundWords();
    }

    /**
     * Gets the list of found bonus words.
     * @return The list of found bonus words.
     */
    public ArrayList<String> getFoundBonusWords(){
        return words.getFoundBonusWords();
    }

    /**
     * Checks if a word is valid.
     * @param word The word to check.
     * @return The validity of the word.
     */
    public int isValidWord (String word){
        return words.isValidWord(word);
    }

    public String getEmoji(String word){
        return words.getEmoji(word); 
    }

    /**
     * Adds a found word to the lists of found words.
     * @param bonus True if the word is a bonus word, false otherwise.
     * @param word The found word.
     */
    public void addFoundWord(Boolean bonus, String word){
        words.addFoundWord(bonus, word);
    }

    /**
     * Gets the list of possible words.
     * @return The list of possible words.
     */
    public ArrayList<String> getPossibleWords(){
        return words.getPossibleWords();
    }

    /**
     * Returns whether or not a tile has a starting value, i.e can a word be made starting from this tile
     * @param row
     * @param col
     * @return
     */
    public Boolean getStartingValueForTile(int row, int col) { 
        return words.getStartingValueForTile(row,col); 
    }
}