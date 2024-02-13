package view;

import javafx.scene.control.Button; 
import javafx.scene.layout.StackPane; 

public class Tile extends Button{
    
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

    public void setNeutralState() { 
        getStyleClass().clear(); 
        getStyleClass().add("button-neutral-state");
        currentState = "neutral-state";
    }

    public void setGreenState() { 
        getStyleClass().clear(); 
        getStyleClass().add("button-green-state");
        currentState = "green-state";
    }

    public void setYellowState() { 
        getStyleClass().clear(); 
        getStyleClass().add("button-yellow-state");
        currentState = "yellow-state";
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

    public String getCurrentState() { return currentState; }
}
