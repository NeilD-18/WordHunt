package viewmodel; 

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty; 

public class WordHuntCurrentWordViewModel {
    
    private final StringProperty currentWord = new SimpleStringProperty("");

    public StringProperty currentWordProperty() { 
        return currentWord;
    }

    public String getCurrentWord() { 
        return currentWord.get(); 
    }

    public void updateCurrentWord(String newWord) { 
        currentWord.set(newWord);
        System.out.println(currentWord); 
    }
}