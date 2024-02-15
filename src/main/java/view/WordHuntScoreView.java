package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import viewmodel.*;

public class WordHuntScoreView extends VBox {

    private WordHuntScoreViewModel WordHuntScoreVM;

    private Label totalWordsFoundLabel;
    private Label totalPossibleWordsLabel;

    public WordHuntScoreView() {
        WordHuntScoreVM = new WordHuntScoreViewModel();
        getChildren().addAll(WordHuntScoreVM.getTotalWordsFoundLabel(), WordHuntScoreVM.getPossibleWordsLabel());
    }

    public void bindTotalWordsFound(IntegerProperty totalWordsFoundProperty) {
        totalWordsFoundLabel.textProperty().bind(Bindings.createStringBinding(() ->
                "Total Words Found: " + totalWordsFoundProperty.get(), totalWordsFoundProperty));
    }

    public void bindTotalPossibleWords(IntegerProperty totalPossibleWordsProperty) {
        totalPossibleWordsLabel.textProperty().bind(Bindings.createStringBinding(() ->
                "Total Possible Words: " + totalPossibleWordsProperty.get(), totalPossibleWordsProperty));
    }

    public void incrementTotalWordsFound() {
        WordHuntScoreVM.incrementTotalWordsFound();
    }

    public void initilaizeScores(int possibleWords){
        WordHuntScoreVM.setTotalWordsFound(0);
        WordHuntScoreVM.setTotalPossibleWords(possibleWords);
    }

    public int getTotalWordsFound(){
        return WordHuntScoreVM.getTotalWordsFound();
    }

    public int getPossibleWords(){
        return WordHuntScoreVM.getPossibleWords();
    }

}
