package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestButtonsViewModel {

    private final ObservableList<String> wordList;
    private final ListProperty<String> wordListProperty;

    public TestButtonsViewModel() {
        wordList = FXCollections.observableArrayList();
        wordListProperty = new SimpleListProperty<>(wordList);
    }

    public ListProperty<String> wordListProperty() {
        return wordListProperty;
    }

    public void addWord(String word) {
        wordList.add(word);
    }
}
