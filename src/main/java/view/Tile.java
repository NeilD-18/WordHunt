package view;

import javafx.scene.control.Button;

/**
 * The Tile class represents a tile in the Word Hunt game board.
 */
public class Tile extends Button {

    private int row;
    private int col;
    private String data;
    private int totalStarts;
    private String currentState;
    private int startingCount; 

    
    
    /**
     * Constructs a new Tile object with the specified letter, row, and column indices.
     * @param letter The letter to be displayed on the tile.
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     * @param startingCount starting count of the tile. 
     */
    public Tile(String letter, int row, int col, int startingCount) {
        super(letter + " " + String.valueOf(startingCount));
        data = letter;
        this.row = row;
        this.col = col;
        this.startingCount = startingCount;
        //add text property
        setYellowState();
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
        currentState = "neutral-state";
    }

    
    /**
     * Sets the tile state to yellow.
     */
    public void setYellowState() {
        getStyleClass().clear();
        getStyleClass().add("button-yellow-state");
        currentState = "yellow-state";
    }

    /**
     * Sets the tile state to green.
     */
    public void setGreenState() {
        getStyleClass().clear();
        getStyleClass().add("button-green-state");
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
        currentState = "blue-state";
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

    public void updateCount(int count) { 

    }
}
