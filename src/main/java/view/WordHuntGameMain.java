package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import viewmodel.*;
import java.util.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;

import java.io.File;
import java.io.IOException;
import javafx.scene.control.Button;

/**
 * Main Application class, launches application
 */
public class WordHuntGameMain extends Application {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720; 
    private WordHuntMenuViewModel menuViewModel;
    private WordHuntMenu wordHuntMenu;
    public WordHuntBoardViewModel boardViewModel;
    public int GRID_SIZE = 6;
    public Stage primaryStage;

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
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(primaryStage);
        popupStage.setTitle("Select Grid Size");
    
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
    
        Button size4Button = new Button("4x4");
        size4Button.setOnAction(e -> {
            startGameWithGridSize(4);
            popupStage.close();
        });
    
        Button size5Button = new Button("5x5");
        size5Button.setOnAction(e -> {
            startGameWithGridSize(5);
            popupStage.close();
        });
    
        Button size6Button = new Button("6x6");
        size6Button.setOnAction(e -> {
            startGameWithGridSize(6);
            popupStage.close();
        });

        Button size7Button = new Button("7x7");
        size7Button.setOnAction(e -> {
            startGameWithGridSize(7);
            popupStage.close();
        });
    
        gridPane.add(size4Button, 0, 0);
        gridPane.add(size5Button, 1, 0);
        gridPane.add(size6Button, 2, 0);
        gridPane.add(size7Button, 3, 0);
    
        Scene scene = new Scene(gridPane, 300, 100);
        popupStage.setScene(scene);
        popupStage.showAndWait(); 
    }
    
    private void startGameWithGridSize(int gridSize) {
        WordHuntNewGame wordHuntNewGame = new WordHuntNewGame("null", gridSize);
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
                System.out.println(fileString);
            }
        } else {
            System.out.println("No file selected.");
        }

        try (Scanner scanner = new Scanner(new File(fileString))) {
            int boardSize = Integer.valueOf(scanner.nextLine());
            if (boardSize <= 7 && boardSize >= 4){
                GRID_SIZE = boardSize;
            }
            else {
                System.err.println("Error: File format does not match the expected format.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        WordHuntNewGame wordHuntNewGame = new WordHuntNewGame(fileString, GRID_SIZE);
        StackPane root = new StackPane(wordHuntNewGame);
        StackPane.setAlignment(wordHuntNewGame, Pos.CENTER);
        root.setId("game-root");
        
        
        Scene newGameScene = new Scene(root, WIDTH, HEIGHT); 
        newGameScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        Stage primaryStage = (Stage) wordHuntMenu.getScene().getWindow();
        primaryStage.setScene(newGameScene);



    }
}