package view;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import viewmodel.WordHuntInGameMenuViewModel; 

public class WordHuntNewGame extends Pane {
    private WordHuntScoreView scoreLabel;
    private WordHuntWordsFoundView foundWords;
    private WordHuntBoardView gameBoard;
    private WordHuntCurrentWordView currentWord; 
    private WordHuntInGameMenuView inGameMenu; 
    private WordHuntInGameMenuViewModel inGameMenuVM; 
    
    public WordHuntNewGame(String filePath) {
        initializeComponents(filePath);
        setupLayout();
    }

    public void initializeComponents(String filePath) {
        scoreLabel = new WordHuntScoreView();
        foundWords = new WordHuntWordsFoundView();
        gameBoard = new WordHuntBoardView(filePath, scoreLabel, foundWords);
        currentWord = new WordHuntCurrentWordView(gameBoard.wordHuntCurrentWordVM);
        inGameMenuVM = new WordHuntInGameMenuViewModel(gameBoard.wordHuntBoardVM); 
        inGameMenu = new WordHuntInGameMenuView(inGameMenuVM, this);

    }

    public void setupLayout() {
        getChildren().clear();
        scoreLabel.setAlignment(Pos.CENTER);
        foundWords.setAlignment(Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        int possibleWords = gameBoard.getNumPossibleWords();
        scoreLabel.initilaizeScores(possibleWords);

        

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scoreLabel, gameBoard);
        
        vBox.getChildren().add(currentWord); 

        HBox hBox = new HBox(50);
        hBox.setTranslateX(435);
        hBox.setTranslateY(150);
        hBox.getChildren().addAll(vBox, foundWords);

        getChildren().addAll(hBox, inGameMenu);
    }
    
}

