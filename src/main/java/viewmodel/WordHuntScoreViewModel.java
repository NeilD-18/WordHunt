package viewmodel;

import javafx.scene.control.Label;

public class WordHuntScoreViewModel {

    private Label totalWordsFoundLabel;
    private Label totalPossibleWordsLabel;

    private int totalWordsFoundCount;
    private int totalPossibleWords;

    public WordHuntScoreViewModel(){
        totalWordsFoundCount = 0; 
        totalWordsFoundLabel = new Label();
        totalWordsFoundLabel.setStyle("-fx-text-fill: rgba(171, 82, 54, 1);");
        totalPossibleWordsLabel = new Label();
        totalPossibleWordsLabel.setStyle("-fx-text-fill: rgba(171, 82, 54, 1);");
    }

    public Label getTotalWordsFoundLabel(){
        return totalWordsFoundLabel;
    }

    public Label getPossibleWordsLabel(){
        return totalPossibleWordsLabel;
    }

    public int getPossibleWords(){
        return totalPossibleWords;
    }

    public int getTotalWordsFound(){
        return totalWordsFoundCount;
    }

    public void incrementTotalWordsFound() {
        totalWordsFoundCount++;
        totalWordsFoundLabel.setText("Total Words Found: " + totalWordsFoundCount);
    }

    public void setTotalWordsFound(int totalWordsFound) {
        totalWordsFoundLabel.setText("Total Words Found: " + totalWordsFound);
    }

    public void setTotalPossibleWords(int possibleWords) {
        totalPossibleWords = possibleWords;
        totalPossibleWordsLabel.setText("Total Possible Words: " + totalPossibleWords);
    }

}
