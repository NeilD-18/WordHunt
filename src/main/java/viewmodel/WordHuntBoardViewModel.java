package viewmodel;

import java.util.*;

import javafx.scene.input.MouseEvent;
import view.*;
import model.*;

public class WordHuntBoardViewModel {

    private WordHuntGame game;
    private Tile[][] buttons;
    private static final int GRID_SIZE = 4;
    private Tile lastClickedTile;

    public WordHuntBoardViewModel(){
        this.game = new WordHuntGame();
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
    }


    public ArrayList<ArrayList<String>> initializeBoard(Boolean load, String txtFile){
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
        if(load){
            game.loadBoard(txtFile);
        }
        else{
            game.generateRandomBoard();
        }
        return game.getBoard();
    }

    public Tile getLastClicked(){
        return lastClickedTile;
    }

    

    public void wipeTiles(){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                buttons[i][j].setYellowState();
            }
        }
    }

    public void toggleTileState(Tile tile) {
        if (tile.getCurrentState().equals("yellow-state")) {
            tile.setNeutralState();
        } else {
            tile.setYellowState();
        }
    }

    public Tile getButton(int row, int col){
        return buttons[row][col];
    }

    public void addButton(Tile tile, int row, int col){
        buttons[row][col] = tile;
    }

    public void setLastClickedTile(Tile tile){
        lastClickedTile = tile;
    }

    public boolean isAdjacent(Tile tile1, Tile tile2, Stack<Tile> selectedTilesStack) {
        int row1 = tile1.getRow();
        int col1 = tile1.getCol();
        int row2 = tile2.getRow();
        int col2 = tile2.getCol();

        if (!selectedTilesStack.isEmpty()) {
            Tile topOfStack = selectedTilesStack.peek();
            int topRow = topOfStack.getRow();
            int topCol = topOfStack.getCol();

            return (Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1) &&
                    (Math.abs(row1 - topRow) <= 1 && Math.abs(col1 - topCol) <= 1);
        } else {
            return Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1;
        }
    }

    public void handleWord(String word) {
        int validity = this.game.isValidWord(word.toLowerCase());
        System.out.println("Pre-executing handleWord");
        System.out.println(validity);
        if (validity == 1) {
            this.game.addFoundWord(false, word);
        } else if (validity == 2) {
            this.game.addFoundWord(true, word);
        }
        System.out.println("Received string in WordHuntBoardViewModel: " + word);
    }

}
