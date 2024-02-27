package view;

import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

/**
 * The Tile class represents a tile in the Word Hunt game board.
 */
public class Tile extends Button {

    private int row;
    private int col;
    private String data;
    private String currentState;

    private int leftNumber;
    private int rightNumber;
    
    /**
     * Constructs a new Tile object with the specified letter, row indices, column indices, left number and right number.
     * @param letter The letter to be displayed on the tile.
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     * @param leftNumber The left number of the tile.
     * @param rightNumber The left number of the tile.
     */
    public Tile(String letter, int row, int col, int leftNumber, int rightNumber) {
        super(String.valueOf(leftNumber) + " " + letter + " " + String.valueOf(rightNumber));
        data = letter;
        this.row = row;
        this.col = col;
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;        

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
     * Sets the left and right number of the tile.
     * @param leftNumber The left number of the tile.
     * @param rightNumber The right number of the tile.
     */
    public void setNumbers(int leftNumber, int rightNumber) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
        setText(String.valueOf(leftNumber) + " " + data + " " + String.valueOf(rightNumber));   
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

    /**
     * Gets the left number of the tile.
     * @return The left number of the tile.
     */
    public int getLeftNumber() {
        return leftNumber;
    }

    /**
     * Gets the right number of the tile.
     * @return The right number of the tile.
     */
    public int getRightNumber() {
        return rightNumber;
    }

}
