package view; 

import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class TestButtons extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create Model (list of words)
        ObservableList<String> wordList = FXCollections.observableArrayList();

        // Create ViewModel
        ListProperty<String> wordListProperty = new SimpleListProperty<>(wordList);

        // Create View (ListView to display words)
        ListView<String> listView = new ListView<>();
        listView.itemsProperty().bind(wordListProperty);

        // Create a button to add a new word (for demonstration purposes)
        javafx.scene.control.Button addButton = new javafx.scene.control.Button("Add Word");
        addButton.setOnAction(event -> {
            // Add a new word to the Model
            wordList.add("New Word");
        });

        WordHuntBoardView wordHuntBoard = new WordHuntBoardView(); // Assuming you have this class already
        
        HBox root = new HBox(wordHuntBoard, new VBox(listView, addButton));

        Scene scene = new Scene(root, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Word List Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}