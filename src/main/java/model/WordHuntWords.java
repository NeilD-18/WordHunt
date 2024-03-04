package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.util.Pair;

/**
 * WordHuntWords model
 */
public class WordHuntWords{

    private int COLUMNS = 4;
    private int ROWS = 4;
    private WordHuntGame game;
    private ArrayList<String> POSSIBLE_4_LETTER_WORDS;
    private ArrayList<ArrayList<Pair<Integer, Integer>>> POSSIBLE_4_LETTER_WORDS_TILES;
    private ArrayList<String> FOUND_4_LETTER_WORDS;
    private ArrayList<String> FOUND_BONUS_WORDS;
    private ArrayList<String> FOUR_LETTER_WORDS;
    private ArrayList<String> BONUS_WORDS;
    private HashMap<String, String> POSSIBLE_EMOJI_WORDS;

    
    /**
     * Constructs a new WordHuntWords object with the specified WordHuntGame object.
     * @param g The WordHuntGame object to associate with this WordHuntWords object.
     */
    public WordHuntWords(WordHuntGame g){
        game = g;
        POSSIBLE_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_BONUS_WORDS = new ArrayList<String>();
        POSSIBLE_4_LETTER_WORDS_TILES = new ArrayList<ArrayList<Pair<Integer, Integer>>>();
        POSSIBLE_EMOJI_WORDS = new HashMap<String, String>();
        initializeEmojiWords();
    }


    /**
     * Finds possible words on the game board.
     */
    public void findWords(){
        ArrayList<ArrayList<String>> tmpBoard = game.getBoard();
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                ArrayList<Pair<Integer, Integer>> visited = new ArrayList<Pair<Integer, Integer>>();
                this.findWordsHelper(i, j, "", 0, tmpBoard, visited);
            }
        }
        System.out.println(POSSIBLE_4_LETTER_WORDS);
    }



    /**
     * Initializing a dictionary with the emojis unicode
     */
    private void initializeEmojiWords() {
        // Add 4-letter words along with their corresponding emoji Unicode
        POSSIBLE_EMOJI_WORDS.put("able", "\uD83D\uDE00");
        POSSIBLE_EMOJI_WORDS.put("acid", "\uD83D\uDE21");
        POSSIBLE_EMOJI_WORDS.put("adam", "\uD83D\uDE42");
        POSSIBLE_EMOJI_WORDS.put("anti", "\uD83D\uDE08");
        POSSIBLE_EMOJI_WORDS.put("arch", "\uD83C\uDFDB");
        POSSIBLE_EMOJI_WORDS.put("arms", "\uD83D\uDC89");
        POSSIBLE_EMOJI_WORDS.put("atom", "\u269B");
        POSSIBLE_EMOJI_WORDS.put("away", "\uD83D\uDE4B");
        POSSIBLE_EMOJI_WORDS.put("babe", "\uD83D\uDC69\u200D\uD83D\uDCBB");
        POSSIBLE_EMOJI_WORDS.put("back", "\uD83D\uDD19");
        // Add more words and their emojis as needed
    }

    /**
     * Recursively finds possible words on the game board.
     * @param row The current row index.
     * @param col The current column index.
     * @param word The current word being formed.
     * @param length The length of the current word.
     * @param grid The game board.
     * @param visited The list of visited cells.
     */
    private void findWordsHelper(int row, int col, String word, int length, ArrayList<ArrayList<String>> grid, ArrayList<Pair<Integer, Integer>> visited){
        word = word + grid.get(row).get(col);
        length++;
        visited.add(new Pair<> (row, col));
        if (length == 4){
            word = word.toLowerCase();
            if (this.isValidWord(word) == 1){
                this.addPossibleWord(word, visited);
            }
        }
        else {
            for (int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    if (game.isValidMove(row, col, row + i, col+ j)){
                        Pair<Integer, Integer> p = new Pair<> (row + i, col + j);
                        if (!visited.contains(p)){
                            ArrayList<Pair<Integer, Integer>> newVisited = new ArrayList<Pair<Integer, Integer>>();
                            for (int k = 0; k < visited.size(); k++){
                                newVisited.add(visited.get(k));
                            }
                            this.findWordsHelper(row + i, col + j, word, length, grid, newVisited);
                        }
                    }
                }
            }
        }
    }


    /**
     * Add possible word
     */
    private void addPossibleWord(String word, ArrayList<Pair<Integer, Integer>> visited){
        if (!POSSIBLE_4_LETTER_WORDS.contains(word)){
            POSSIBLE_4_LETTER_WORDS.add(word);
            POSSIBLE_4_LETTER_WORDS_TILES.add(visited);
            for (int i = 0; i < visited.size(); i++){
                Pair<Integer, Integer> p = visited.get(i);
                game.incrementLetterUse(p.getKey(), p.getValue());
            }
        }
    }

    /**
     * Initializes word lists from text files.
     */
    public void initializeWordLists(){

        FOUR_LETTER_WORDS = new ArrayList<String>();
        BONUS_WORDS = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/WordLists/4LetterWordList.txt"))){
            String line = br.readLine();
            while (line != null){
                FOUR_LETTER_WORDS.add(line);
                line = br.readLine();
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/WordLists/BonusWordListFinal.txt"))){
            String line = br.readLine();
            while (line != null){
                BONUS_WORDS.add(line);
                line = br.readLine();
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public ArrayList<Pair<Integer, Integer>> getTilesForWord(String word){
        int index = POSSIBLE_4_LETTER_WORDS.indexOf(word);
        return POSSIBLE_4_LETTER_WORDS_TILES.get(index);
    }


    /**
     * Gets the list of possible words found on the game board.
     * @return The list of possible words.
     */
    public ArrayList<String> getPossibleWords(){
        return POSSIBLE_4_LETTER_WORDS;
    }

    /**
     * returning the emoji dictionary
     * @return the dictionary with emoji unicode
     */

    public ArrayList<String> getEmojis(){
        return POSSIBLE_EMOJI_WORDS;
    }

    /**
     * Checks if a word is valid.
     * @param word The word to check.
     * @return 1 if the word is a valid 4-letter word, 2 if it's a bonus word, and 0 if it's invalid.
     */
    public int isValidWord (String word){
        if (FOUR_LETTER_WORDS.contains(word)){
            return 1;
        }
        if (BONUS_WORDS.contains(word)){
            return 2;
        }
        if ()
        return 0;
    }


    /**
     * Adds a found word to the appropriate list of found words.
     * @param bonus True if the word is a bonus word, false otherwise.
     * @param word The found word.
     */
    public void addFoundWord(Boolean bonus, String word){
        if (bonus){
            FOUND_BONUS_WORDS.add(word);
        }
        else{
            FOUND_4_LETTER_WORDS.add(word);
        }
    }


    /**
     * Gets the number of found 4-letter words.
     * @return The number of found 4-letter words.
     */
    public int getNumFoundWords(){
        return FOUND_4_LETTER_WORDS.size();
    }


    /**
     * Gets the list of found 4-letter words.
     * @return The list of found 4-letter words.
     */
    public ArrayList<String> getFoundWords(){
        return FOUND_4_LETTER_WORDS;
    }

    /**
     * Gets the list of found bonus words.
     * @return The list of found bonus words.
     */
    public ArrayList<String> getFoundBonusWords(){
        return FOUND_BONUS_WORDS;
    }


    /**
     * Clears the word lists.
     */
    public void tearDown(){
        POSSIBLE_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_BONUS_WORDS = new ArrayList<String>();
    }

}