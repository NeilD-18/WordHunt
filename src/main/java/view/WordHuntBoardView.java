

import javafx.scene.layout.GridPane;

public class WordHuntBoardView extends GridPane {
    
    private Tile[][] buttons;
    private static final int GRID_SIZE = 4;
    private String[] myArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};

    public WordHuntBoardView() { 
        initializeDefaultBoard();
    }


    public WordHuntBoardView(String[][] givenBoard) { 
        initializeBoard(givenBoard); 
    }
    

    private void initializeDefaultBoard() {
        setHgap(10);
        setVgap(10);
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
        
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int row = i;
                final int col = j;
                Tile tile = new Tile(myArray[(i * GRID_SIZE + j + 1)-1], i ,j); 
                tile.setYellowState();
                tile.setMinSize(80, 80);
                tile.setOnMousePressed(event -> handleMousePressed(row, col));
                buttons[i][j] = tile; 
                add(tile, j, i); 
            }
        }

    }

    private void initializeBoard(String[][] givenBoard) {
        setHgap(10);
        setVgap(10);
        buttons = new Tile[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int row = i;
                final int col = j;
                Tile tile = new Tile(givenBoard[i][j], i ,j); 
                tile.setYellowState();
                tile.setMinSize(80, 80);
                tile.setOnMousePressed(event -> handleMousePressed(row, col));
                buttons[i][j] = tile; 
                add(tile, j, i); 
            
        
            }
        }   
    }


    private void handleMousePressed(int row, int col) {
        Tile tile = buttons[row][col];
        if (tile.getCurrentState().equals("yellow-state")) {
            tile.setNeutralState();
        }    else {
            tile.setYellowState();
        }
    }
}
