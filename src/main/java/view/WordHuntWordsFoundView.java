import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class WordHuntWordsFoundView extends VBox {

    private ObservableList<String> wordList;
    private ListProperty<String> wordListProperty;
    private ListView<String> listView;

    public WordHuntWordsFoundView() {
        wordList = FXCollections.observableArrayList();
        wordListProperty = new SimpleListProperty<>(wordList);
        listView = new ListView<>();
        listView.itemsProperty().bind(wordListProperty);
        listView.setPrefWidth(150); // Set preferred width
        listView.setPrefHeight(300);
        //just for test
        javafx.scene.control.Button addButton = new javafx.scene.control.Button("Add Word");
        addButton.setOnAction(event -> {
            // Add a new word to the Model
            wordList.add("New Word1");
        });

        getChildren().addAll(listView, addButton);
    }

    public void update(ObservableList<String> updatedList) {
        wordList.setAll(updatedList);
    }

    public ObservableList<String> getWordList() {
        return wordList;
    }
}



