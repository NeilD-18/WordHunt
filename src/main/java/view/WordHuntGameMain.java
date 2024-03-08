package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import viewmodel.*;
import java.util.*;
import javafx.stage.FileChooser;
import java.io.File;

/**
 * Main Application class, launches application
 */
public class WordHuntGameMain extends Application {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720; 
    private WordHuntMenuViewModel menuViewModel;
    private WordHuntMenu wordHuntMenu;
    public WordHuntBoardViewModel boardViewModel;
    

    /**
     * main method
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start method
     * @param Stage stage to start
     */
    @Override
    public void start(Stage primaryStage) {
        menuViewModel = new WordHuntMenuViewModel();
        wordHuntMenu = new WordHuntMenu(menuViewModel);
        menuViewModel.startNewGameRequestedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                startNewGame();
                menuViewModel.resetStartNewGameRequest();
            }
        });

        menuViewModel.loadGameRequestedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                loadGame();
                menuViewModel.resetLoadGameRequest();
            }
        });

        Scene scene = new Scene(wordHuntMenu, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("WordHunt Game");
        primaryStage.show();
    }
    /**
     * Start a new game
     */
    private void startNewGame() {
       
        WordHuntNewGame wordHuntNewGame = new WordHuntNewGame("null");
        StackPane root = new StackPane(wordHuntNewGame);
        StackPane.setAlignment(wordHuntNewGame, Pos.CENTER);
        root.setId("game-root");
        
        
        Scene newGameScene = new Scene(root, WIDTH, HEIGHT); 
        newGameScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        Stage primaryStage = (Stage) wordHuntMenu.getScene().getWindow();
        primaryStage.setScene(newGameScene);
    }

    /**
     * Load a game
     */
    private void loadGame() { 
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(new Stage());
        String fileString = "";
        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                fileString = "" + file.getAbsolutePath(); 
                // (fileString);
            }
        } else {
           //  System.out.println("No file selected.");
        } 

        WordHuntNewGame wordHuntNewGame = new WordHuntNewGame(fileString);
        StackPane root = new StackPane(wordHuntNewGame);
        StackPane.setAlignment(wordHuntNewGame, Pos.CENTER);
        root.setId("game-root");
        
        
        Scene newGameScene = new Scene(root, WIDTH, HEIGHT); 
        newGameScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        Stage primaryStage = (Stage) wordHuntMenu.getScene().getWindow();
        primaryStage.setScene(newGameScene);



    }
}