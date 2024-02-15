package viewmodel; 

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty; 

/**
 * WordHuntCurrentWord ViewModel class
 */
public class WordHuntCurrentWordViewModel {
    
    private final StringProperty currentWord = new SimpleStringProperty("");

    /**
     * StringProperty for the current word
     */
    public StringProperty currentWordProperty() { 
        return currentWord;
    }

    /**
     * @return String currentWord
     */
    public String getCurrentWord() { 
        return currentWord.get(); 
    }

    /**
     * update the current word
     * @param String newWord
     */
    public void updateCurrentWord(String newWord) { 
        currentWord.set(newWord);
    }
}