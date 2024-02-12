import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WordsFound {
    private ObservableList<String> wordList;

    public WordsFound() {
        wordList = FXCollections.observableArrayList();
    }

    public ObservableList<String> getWordList() {
        return wordList;
    }

    public void addWord(String word) {
        wordList.add(word);
    }
}