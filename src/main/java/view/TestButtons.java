package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import viewmodel.TestButtonsViewModel;

public class TestButtons extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create ViewModel
        TestButtonsViewModel viewModel = new TestButtonsViewModel();

        // Create View (ListView to display words)
        ListView<String> listView = new ListView<>();
        listView.itemsProperty().bind(viewModel.wordListProperty());

        // Create a button to add a new word (for demonstration purposes)
        javafx.scene.control.Button addButton = new javafx.scene.control.Button("Add Word");
        addButton.setOnAction(event -> {
            // Add a new word through the ViewModel
            viewModel.addWord("New Word");
        });

        WordHuntBoardView wordHuntBoard = new WordHuntBoardView(); 

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
