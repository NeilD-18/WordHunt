package model;

import java.util.ArrayList;

/**
 * WordHuntScore model
 */
public class WordHuntScore {

    private WordHuntWords words;


    /**
     * Constructs a new WordHuntScore object with the specified WordHuntWords object.
     * @param w The WordHuntWords object to associate with this score manager.
     */
    public WordHuntScore(WordHuntWords w){
        words = w;
    }
    
    /**
     * Gets the number of found words.
     * @return The number of found words.
     */ 
    public int getNumFoundWords(){
        return words.getNumFoundWords();
    }


    /**
     * Gets the list of found words.
     * @return The list of found words.
     */
    public ArrayList<String> getFoundWords(){
        return words.getFoundWords();
    }
    
    /**
     * Tears down the WordHuntWords object associated with this score manager.
     */
    public void tearDown(){
        words.tearDown();
    }

}
