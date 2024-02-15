package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.util.Pair;

public class WordHuntWords{

    private int COLUMNS = 4;
    private int ROWS = 4;
    private WordHuntGame game;
    private ArrayList<String> POSSIBLE_4_LETTER_WORDS;
    private ArrayList<String> FOUND_4_LETTER_WORDS;
    private ArrayList<String> FOUND_BONUS_WORDS;
    private ArrayList<String> FOUR_LETTER_WORDS;
    private ArrayList<String> BONUS_WORDS;

    public WordHuntWords(WordHuntGame g){
        game = g;
        POSSIBLE_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_BONUS_WORDS = new ArrayList<String>();
    }

    public void findWords(){
        ArrayList<ArrayList<String>> tmpBoard = game.getBoard();
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                ArrayList<Pair<Integer, Integer>> visited = new ArrayList<Pair<Integer, Integer>>();
                this.findWordsHelper(i, j, "", 0, tmpBoard, visited);
            }
        }
        // System.out.println(POSSIBLE_4_LETTER_WORDS);
    }

    private void findWordsHelper(int row, int col, String word, int length, ArrayList<ArrayList<String>> grid, ArrayList<Pair<Integer, Integer>> visited){
        word = word + grid.get(row).get(col);
        length++;
        visited.add(new Pair<> (row, col));
        if (length == 4){
            word = word.toLowerCase();
            if (this.isValidWord(word) == 1){
                this.addPossibleWord(word);
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

    private void addPossibleWord(String word){
        if (!POSSIBLE_4_LETTER_WORDS.contains(word)){
            POSSIBLE_4_LETTER_WORDS.add(word);
        }
    }

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

    public ArrayList<String> getPossibleWords(){
        return POSSIBLE_4_LETTER_WORDS;
    }

    public int isValidWord (String word){
        if (FOUR_LETTER_WORDS.contains(word)){
            return 1;
        }
        if (BONUS_WORDS.contains(word)){
            return 2;
        }
        return 0;
    }

    public void addFoundWord(Boolean bonus, String word){
        if (bonus){
            FOUND_BONUS_WORDS.add(word);
        }
        else{
            FOUND_4_LETTER_WORDS.add(word);
        }
    }

    public int getNumFoundWords(){
        return FOUND_4_LETTER_WORDS.size();
    }

    public ArrayList<String> getFoundWords(){
        return FOUND_4_LETTER_WORDS;
    }

    public ArrayList<String> getFoundBonusWords(){
        return FOUND_BONUS_WORDS;
    }

    public void tearDown(){
        POSSIBLE_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_BONUS_WORDS = new ArrayList<String>();
    }

}