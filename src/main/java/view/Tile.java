package view;

import javafx.scene.control.Button;

public class Tile extends Button {

    private int row;
    private int col;
    private String data;
    private String currentState;

    public Tile(String letter, int row, int col) {
        super(letter);
        data = letter;
        this.row = row;
        this.col = col;
        setYellowState();
    }

    public Tile(String letter) { 
        super(letter);
    }

    public void setNeutralState() {
        getStyleClass().clear();
        getStyleClass().add("button-neutral-state");
        currentState = "neutral-state";
    }

    public void setYellowState() {
        getStyleClass().clear();
        getStyleClass().add("button-yellow-state");
        currentState = "yellow-state";
    }

    public void setGreenState() {
        getStyleClass().clear();
        getStyleClass().add("button-green-state");
        currentState = "green-state";
    }
    public void setBlueState() {
        getStyleClass().clear();
        getStyleClass().add("button-blue-state");
        currentState = "blue-state";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getData(){
        return data;
    }
    
    public String getCurrentState() {
        return currentState;
    }
}
