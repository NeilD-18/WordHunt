package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import viewmodel.*;

/**
 * WordHuntScoreView class 
 */
public class WordHuntScoreView extends VBox {

    private WordHuntScoreViewModel WordHuntScoreVM;

    private Label totalWordsFoundLabel;
    private Label totalPossibleWordsLabel;

    /**
     * Constructor that binds view to viewmodel.
     */
    public WordHuntScoreView() {
        WordHuntScoreVM = new WordHuntScoreViewModel();
        getChildren().addAll(WordHuntScoreVM.getTotalWordsFoundLabel(), WordHuntScoreVM.getPossibleWordsLabel());
    }

    /**
     * Bind total words found to score label
     * @param IntegerProperty totalWordsFoundProperty
     */
    public void bindTotalWordsFound(IntegerProperty totalWordsFoundProperty) {
        totalWordsFoundLabel.textProperty().bind(Bindings.createStringBinding(() ->
                "Total Words Found: " + totalWordsFoundProperty.get(), totalWordsFoundProperty));
    }

      /**
     * Bind total possible found to score label
     * @param IntegerProperty totalPossibleProperty
     */
    public void bindTotalPossibleWords(IntegerProperty totalPossibleWordsProperty) {
        totalPossibleWordsLabel.textProperty().bind(Bindings.createStringBinding(() ->
                "Total Possible Words: " + totalPossibleWordsProperty.get(), totalPossibleWordsProperty));
    }

    /**
     * increment total word found count
     */
    public void incrementTotalWordsFound() {
        WordHuntScoreVM.incrementTotalWordsFound();
    }

    /**
     * Initialize scores
     * @param int possibleWords
     */
    public void initilaizeScores(int possibleWords){
        WordHuntScoreVM.setTotalWordsFound(0);
        WordHuntScoreVM.setTotalPossibleWords(possibleWords);
    }

    /**
     * get total words found
     * @return int
     */
    public int getTotalWordsFound(){
        return WordHuntScoreVM.getTotalWordsFound();
    }

     /**
     * get possible words
     * @return int
     */
    public int getPossibleWords(){
        return WordHuntScoreVM.getPossibleWords();
    }

}
