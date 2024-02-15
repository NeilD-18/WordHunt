package view;


import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class WordHuntNewGame extends Pane {
    private WordHuntScoreView scoreLabel;
    private WordHuntWordsFoundView foundWords;
    private WordHuntBoardView gameBoard;

    public WordHuntNewGame() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        scoreLabel = new WordHuntScoreView();
        foundWords = new WordHuntWordsFoundView();

        String[][] randomGrid = generateRandomGrid(4, 4);
        gameBoard = new WordHuntBoardView();
    }

    private void setupLayout() {
        scoreLabel.setAlignment(Pos.CENTER);
        foundWords.setAlignment(Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        scoreLabel.setTotalWordsFound(0);
        scoreLabel.setTotalPossibleWords(100);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scoreLabel, gameBoard);

        HBox hBox = new HBox(50);
        hBox.setTranslateX(435);
        hBox.setTranslateY(150);
        hBox.getChildren().addAll(vBox, foundWords);

        getChildren().add(hBox);
    }

    /**
    private String[][] generateRandomGrid(int rows, int columns) {
        String[][] randomGrid = new String[rows][columns];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int randomIndex = (int) (Math.random() * alphabet.length());
                randomGrid[i][j] = String.valueOf(alphabet.charAt(randomIndex));
            }
        }

        return randomGrid;
    }
    */

    private String[][] generateRandomGrid(int rows, int columns) {
        String[][] fixedGrid = {
                {"B", "A", "N", "K"},
                {"B", "E", "E", "R"},
                {"A", "B", "A", "N"},
                {"A", "N", "O", "D"}
        };
    
        return fixedGrid;
    }
    
}

