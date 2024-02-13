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

public class WordHuntNewGame extends Pane{
    private static final int WIDTH = 1280; 
    private static final int HEIGHT = 720;
    private String[][] testList = {
        {"N", "E", "I", "L"},
        {"I", "S", "S", "O"},
        {"C", "O", "O", "L"},
        {"L", "O", "O", "L"}
    };
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
        gameBoard = new WordHuntBoardView(testList);

        
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



}

