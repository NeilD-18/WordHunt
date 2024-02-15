package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class WordHuntGame {

    private ArrayList<ArrayList<String>> board;
    private int COLUMNS = 4;
    private int ROWS = 4;
    private WordHuntScore score;
    private WordHuntWords words;

    public WordHuntGame(){
        words = new WordHuntWords(this);
        score = new WordHuntScore(words);
        words.initializeWordLists();
        board = new ArrayList<ArrayList<String>>();
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
        words.findWords();
    }

    public void tearDown(){
        board = new ArrayList<ArrayList<String>>();
        words.tearDown();
        score.tearDown();
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
        words.findWords();
        // System.out.println(POSSIBLE_4_LETTER_WORDS);
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

    public ArrayList<String> getFoundWords(){
        return words.getFoundWords();
    }

    public int isValidWord (String word){
        return words.isValidWord(word);
    }

    public void addFoundWord(Boolean bonus, String word){
        words.addFoundWord(bonus, word);
    }
    
    public ArrayList<String> getPossibleWords(){
        return words.getPossibleWords();
    }
}
