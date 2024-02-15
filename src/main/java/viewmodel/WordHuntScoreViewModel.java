package viewmodel;

import javafx.scene.control.Label;

/*
 * Score ViewModel class
 */
public class WordHuntScoreViewModel {

    private Label totalWordsFoundLabel;
    private Label totalPossibleWordsLabel;

    private int totalWordsFoundCount;
    private int totalPossibleWords;

    /**
     * Constructor to initialize score labels
     */
    public WordHuntScoreViewModel(){
        totalWordsFoundCount = 0; 
        totalWordsFoundLabel = new Label();
        totalWordsFoundLabel.setStyle("-fx-text-fill: rgba(171, 82, 54, 1);");
        totalPossibleWordsLabel = new Label();
        totalPossibleWordsLabel.setStyle("-fx-text-fill: rgba(171, 82, 54, 1);");
    }

    /*
     * @return Label for total words
     */
    public Label getTotalWordsFoundLabel(){
        return totalWordsFoundLabel;
    }

    /**
     * @return Label for possible words
     */
    public Label getPossibleWordsLabel(){
        return totalPossibleWordsLabel;
    }

    /**
     * @return int number of possible words
     */
    public int getPossibleWords(){
        return totalPossibleWords;
    }

    /**
     * @return int total words found
     */
    public int getTotalWordsFound(){
        return totalWordsFoundCount;
    }

    /**
     * increment total words found
     */
    public void incrementTotalWordsFound() {
        totalWordsFoundCount++;
        totalWordsFoundLabel.setText("Total Words Found: " + totalWordsFoundCount);
    }

    /**
     * Setter for total words found
     * @param int totalWordsFound
     */
    public void setTotalWordsFound(int totalWordsFound) {
        totalWordsFoundLabel.setText("Total Words Found: " + totalWordsFound);
    }

    /**
     * setter for total possible words
     * @param int possibleWords
     */
    public void setTotalPossibleWords(int possibleWords) {
        totalPossibleWords = possibleWords;
        totalPossibleWordsLabel.setText("Total Possible Words: " + totalPossibleWords);
    }

}
