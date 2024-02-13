package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import viewmodel.*;


public class WordHuntGameMain extends Application {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720; 
    private WordHuntMenuViewModel menuViewModel;
    private WordHuntMenu wordHuntMenu;
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        menuViewModel = new WordHuntMenuViewModel();
        wordHuntMenu = new WordHuntMenu(menuViewModel);
        

        // Listen for changes in the view model
        menuViewModel.startNewGameRequestedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                startNewGame();
                // Reset the request flag
                menuViewModel.resetStartNewGameRequest();
            }
        });

        Scene scene = new Scene(wordHuntMenu, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm()); // Set scene size as desired
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("WordHunt Game");
        primaryStage.show();
    }

    private void startNewGame() {

        WordHuntNewGame wordHuntNewGame = new WordHuntNewGame();
        StackPane root = new StackPane(wordHuntNewGame);
        StackPane.setAlignment(wordHuntNewGame, Pos.CENTER);
        root.setId("game-root");
        
        
        Scene newGameScene = new Scene(root, WIDTH, HEIGHT); 
        newGameScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm()); // Adjust size as needed
        Stage primaryStage = (Stage) wordHuntMenu.getScene().getWindow();
        primaryStage.setScene(newGameScene);
    }
}