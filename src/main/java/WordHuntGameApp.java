import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordHuntGameApp {

    private ArrayList<ArrayList<String>> board;
    private int COLUMNS = 4;
    private int ROWS = 4;

    

    private ArrayList<String> FOUR_LETTER_WORDS;
    private ArrayList<String> BONUS_WORDS;

    public ArrayList<ArrayList<String>> generateRandomBoard(){
        this.initializeWordLists();
        Random r = new Random();
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                char c = (char)(r.nextInt(26) + 'A');
                String letter = String.valueOf(c);
                board.get(i).set(j, letter);
            }
        }
        return board;
    }

    public ArrayList<ArrayList<String>> loadBoard(){
        this.initializeWordLists();
        return board;
    }

    private void initializeWordLists(){
        try (BufferedReader br = new BufferedReader(new FileReader("4LetterWordList.txt"))){
            String line = br.readLine();
            while (line != null){
                FOUR_LETTER_WORDS.add(line);
                line = br.readLine();
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("BonusWordListFinal.txt"))){
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
        int vCheck = prevRow - nextRow;
        int hCheck = prevCol - nextCol;

        if (vCheck > -2 && vCheck < 2 && hCheck > -2 && hCheck < 2){
            if (vCheck == hCheck){
                return vCheck == 0;
            }
            return true;
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

    public void notifyListeners(){
        
    }

}
