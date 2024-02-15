package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javafx.util.Pair;

public class WordHuntGame {

    private ArrayList<ArrayList<String>> board;
    private int COLUMNS = 4;
    private int ROWS = 4;
    private ArrayList<String> FOUR_LETTER_WORDS;
    private ArrayList<String> BONUS_WORDS;
    private ArrayList<String> POSSIBLE_4_LETTER_WORDS;
    private ArrayList<String> FOUND_4_LETTER_WORDS;
    private ArrayList<String> FOUND_BONUS_WORDS;

    public WordHuntGame(){
        this.initializeWordLists();
        board = new ArrayList<ArrayList<String>>();
        POSSIBLE_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_BONUS_WORDS = new ArrayList<String>();
    }

    public void generateRandomBoard(){
        Random r = new Random();
        for (int i = 0; i < ROWS; i++){
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < COLUMNS; j++){
                char c = (char)(r.nextInt(26) + 'A');
                while (c == 'X' || c == 'Z' || c == 'V' || c == 'Q'){
                    c = (char)(r.nextInt(26) + 'A');
                }
                String letter = String.valueOf(c);
                temp.add(letter);
            }
            board.add(temp);
        }
        this.findWords();
    }

    public void tearDown(){
        board = new ArrayList<ArrayList<String>>();
        POSSIBLE_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_4_LETTER_WORDS = new ArrayList<String>();
        FOUND_BONUS_WORDS = new ArrayList<String>();
    }

    private void findWords(){
        ArrayList<ArrayList<String>> tmpBoard = this.getBoard();
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                ArrayList<Pair<Integer, Integer>> visited = new ArrayList<Pair<Integer, Integer>>();
                this.findWordsHelper(i, j, "", 0, tmpBoard, visited);
            }
        }
        System.out.println(POSSIBLE_4_LETTER_WORDS);
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
                    if (this.isValidMove(row, col, row + i, col+ j)){
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

    public ArrayList<String> getPossibleWords(){
        return POSSIBLE_4_LETTER_WORDS;
    }

    public ArrayList<ArrayList<String>> getBoard(){
        return board;
    }

    public void loadBoard(String filePath){
        this.tearDown();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            board = new ArrayList<>();
            for (int i = 0; i < ROWS; i++) {
                if (scanner.hasNextLine()) {
                    String[] rowElements = scanner.nextLine().split("\\s+");
                    int size = 0;
                    for (String x : rowElements) {
                        size++;
                    }
                    if (size == 4){
                        ArrayList<String> row = new ArrayList<>();
                        for (String element : rowElements) {
                            row.add(element);
                        }
                        board.add(row);
                    }
                    else{
                        System.err.println("Error: File format does not match the expected format.");
                        break;
                    }
                } 
                else{
                    System.err.println("Error: File format does not match the expected format.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.findWords();
        System.out.println(POSSIBLE_4_LETTER_WORDS);
    }

    public void saveBoard(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
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

    private void initializeWordLists(){
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

    public boolean isValidMove (int prevRow, int prevCol, int nextRow, int nextCol){
        if (nextRow >= 0 && nextRow <= 3 && nextCol >= 0 && nextCol <= 3){
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
}
