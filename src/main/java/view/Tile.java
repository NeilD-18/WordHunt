package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

/**
 * The Tile class represents a tile in the Word Hunt game board.
 */
public class Tile extends Button {

    private int row;
    private int col;
    private String data;
    private String currentState;
    private int startingCount; 

    private Text topText;
    private Text bottomText;

    
    /***
    /**
     * Constructs a new Tile object with the specified letter, row, and column indices.
     * @param letter The letter to be displayed on the tile.
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     * @param startingCount starting count of the tile. 
     
    public Tile(String letter, int row, int col, int startingCount) {
        super(letter + " " + String.valueOf(startingCount));
        data = letter;
        this.row = row;
        this.col = col;
        this.startingCount = startingCount;
        //add text property
        setYellowState();
    }
    */

    public Tile(String letter, int row, int col, int startingCount) {
        data = letter;
        this.row = row;
        this.col = col;
        this.startingCount = startingCount;
        

        this.topText = new Text(letter);
        this.topText.setStyle(
            "-fx-font-size: 25px;" +
            "-fx-font-weight: bold;" +
            "-fx-fill: rgba(171, 82, 54, 1);"
        );

        this.bottomText = new Text(String.valueOf(startingCount));
        this.bottomText.setStyle(
            "-fx-font-size: 10px;" +
            "-fx-fill: black;" + 
            "-fx-font-weight: bold;"
        );

        HBox bottomHBox = new HBox(bottomText);
        bottomHBox.setAlignment(Pos.BASELINE_RIGHT); 
        bottomHBox.setTranslateX(-5);
        bottomHBox.setTranslateY(5);
        
        VBox root = new VBox(topText, bottomHBox);
        root.setAlignment(Pos.CENTER);
        root.setTranslateY(8);

        this.setGraphic(root);
   
    }



    /**
     * Constructs a new Tile object with the specified letter, row, and column indices.
     * @param letter The letter to be displayed on the tile.
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     */
    public Tile(String letter, int row, int col) {
        super(letter);
        data = letter;
        this.row = row;
        this.col = col;

        setYellowState();
    }

    /**
     * Constructs a new Tile object with the specified letter.
     * @param letter The letter to be displayed on the tile.
     */
    public Tile(String letter) { 
        super(letter);
    }

    /**
     * Sets the tile state to neutral.
     */
    public void setNeutralState() {
        getStyleClass().clear();
        getStyleClass().add("button-neutral-state");
        if (topText != null) { topText.setStyle("-fx-fill: rgba(95, 87, 79, 1);"); }
        
        currentState = "neutral-state";
    }

    
    /**
     * Sets the tile state to yellow.
     */
    public void setYellowState() {
        getStyleClass().clear();
        getStyleClass().add("button-yellow-state");
        if (topText != null) { topText.setStyle("-fx-fill: rgba(171, 82, 54, 1);"); }
        currentState = "yellow-state";
    }


    /**
     * Sets the tile state to green.
     */
    public void setGreenState() {
        getStyleClass().clear();
        getStyleClass().add("button-green-state");
        if (topText != null) { topText.setStyle("-fx-fill: rgba(5, 84, 89, 1);"); }
        currentState = "green-state";
    }
   
    /**
     * Sets the tile state to blue.
     */
    public void setBlueState() {
        getStyleClass().clear();
        getStyleClass().add("button-blue-state");
        currentState = "blue-state";
    }

    /**
     * Sets the tile state to unavailable.
     */
    public void setUnavailableState() {
        getStyleClass().clear();
        getStyleClass().add("button-unavailable-state");
        if (topText != null) { topText.setStyle("-fx-fill: rgb(255, 255, 255);"); }
        currentState = "unavailable-state";
    }

    /**
     * Gets the row index of the tile.
     * @return The row index of the tile.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column index of the tile.
     * @return The column index of the tile.
     */
    public int getCol() {
        return col;
    }

    
    /**
     * Gets the data (letter) represented by the tile.
     * @return The data (letter) represented by the tile.
     */
    public String getData(){
        return data;
    }
    
    /**
     * Gets the current state of the tile.
     * @return The current state of the tile.
     */
    public String getCurrentState() {
        return currentState;
    }

    /**
     * 
     * @return The amount of words that start with this tile. 
     */
    public int getStartingLetterCount(){ 
        return this.startingCount; 
    }

    /**
     * Update the starting letter count given a new count. 
     * @param count
     */
    public void updateCount(int count) { 
        this.startingCount = count; 
        bottomText.setText(String.valueOf(count));
        if (getStartingLetterCount() == 0) { 
            bottomText.setText(null);
        }
    }
}
