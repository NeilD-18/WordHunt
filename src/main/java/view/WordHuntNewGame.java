package view;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import viewmodel.WordHuntInGameMenuViewModel; 

/**
 * WordHuntNewGame view class
 */
public class WordHuntNewGame extends Pane {
    private WordHuntScoreView scoreLabel;
    private WordHuntWordsFoundView foundWords;
    private WordHuntBoardView gameBoard;
    private WordHuntCurrentWordView currentWord; 
    private WordHuntInGameMenuView inGameMenu; 
    private WordHuntInGameMenuViewModel inGameMenuVM;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private int GRID_SIZE = 4;
    
    /**
     * Initialize a new game given a filepath
     * @param String filePath
     */
    public WordHuntNewGame(String filePath, int gridSize) {
        GRID_SIZE = gridSize;
        initializeComponents(filePath);
        setupLayout();
    }

    /**
     * Initialize componenets given a filePath
     * @param String filePath
     */
    public void initializeComponents(String filePath) {
        scoreLabel = new WordHuntScoreView();
        foundWords = new WordHuntWordsFoundView();
        gameBoard = new WordHuntBoardView(filePath, scoreLabel, foundWords, GRID_SIZE);
        currentWord = new WordHuntCurrentWordView(gameBoard.wordHuntCurrentWordVM);
        inGameMenuVM = new WordHuntInGameMenuViewModel(gameBoard.wordHuntBoardVM); 
        inGameMenu = new WordHuntInGameMenuView(inGameMenuVM, this);

    }

    /**
     * setupLayout
     */
    public void setupLayout() {
        getChildren().clear();
        int possibleWords = gameBoard.getNumPossibleWords();
        // while (possibleWords <= 5 || gameBoard.emptyCells()){
        while (possibleWords <= 5){
            this.initializeComponents("null");
            possibleWords = gameBoard.getNumPossibleWords();
        }
        scoreLabel.setAlignment(Pos.CENTER);
        foundWords.setAlignment(Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        scoreLabel.initilaizeScores(possibleWords);

        

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scoreLabel, gameBoard);
        
        vBox.getChildren().add(currentWord); 

        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(vBox, foundWords);
        hBox.setAlignment(Pos.CENTER);
        int xOffset = 622;
        int yOffset = 404;
        int tmp = GRID_SIZE - 4;
        while (tmp > 0){
            if (tmp > 1){
                xOffset -= (tmp + 4) * 5;
                yOffset -= (tmp + 4) * 5;
            }
            xOffset += 90;
            yOffset += 90;
            tmp--;
        }

        xOffset = WIDTH - xOffset;
        yOffset = HEIGHT - yOffset;

        hBox.setTranslateX(xOffset / 2);
        hBox.setTranslateY(yOffset / 2);

        getChildren().addAll(hBox, inGameMenu);
    }
    
}

