package view;

import javafx.geometry.Pos;
import javafx.scene.layout.*;

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
        gameBoard = new WordHuntBoardView("testBoard.txt", scoreLabel, foundWords);
    }

    private void setupLayout() {
        scoreLabel.setAlignment(Pos.CENTER);
        foundWords.setAlignment(Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        int possibleWords = gameBoard.getNumPossibleWords();
        scoreLabel.initilaizeScores(possibleWords);

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

