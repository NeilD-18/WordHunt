package model;

import java.util.ArrayList;

public class WordHuntScore {

    private WordHuntWords words;


    public WordHuntScore(WordHuntWords w){
        words = w;
    }

    public int getNumFoundWords(){
        return words.getNumFoundWords();
    }

    public ArrayList<String> getFoundWords(){
        return words.getFoundWords();
    }
    
    public void tearDown(){

    }

}
