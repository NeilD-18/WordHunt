import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

public class WordHuntBoard extends GridPane {
    private static final int GRID_SIZE = 4;
    private Tile[][] tiles;

    public WordHuntBoard() {
        initializeBoard();
    }

    public WordHuntBoard(String[][] letters) {
        initializeBoardWithLetters(letters);
    }

    private void initializeBoard() {
        tiles = new Tile[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int row = i;
                final int col = j;
                tiles[i][j] = new Tile("" + (i * GRID_SIZE + j + 1));
                tiles[i][j].setMinSize(80, 80);
                tiles[i][j].setOnMousePressed(event -> handleMousePressed(row, col));
                tiles[i][j].setOnMouseDragged(event -> handleMouseDragged(row, col));
                tiles[i][j].setOnMouseReleased(event -> handleMouseReleased(row, col));
            }
        }

        // Add buttons to the grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                this.add(tiles[i][j], j, i);
            }
        }
    }

    private void initializeBoardWithLetters(String[][] letters) {
        tiles = new Tile[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int row = i;
                final int col = j;
                tiles[i][j] = new Tile(letters[i][j]);
                tiles[i][j].setMinSize(80, 80);
                tiles[i][j].setOnMousePressed(event -> handleMousePressed(row, col));
                tiles[i][j].setOnMouseDragged(event -> handleMouseDragged(row, col));
                tiles[i][j].setOnMouseReleased(event -> handleMouseReleased(row, col));
            }
        }

        // Add buttons to the grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                this.add(tiles[i][j], j, i);
            }
        }
    }

    private void handleMousePressed(int row, int col) {
        tiles[row][col].setGreenState();
    }

    private void handleMouseDragged(int row, int col) {
        tiles[row][col].setGreenState();
    }

    private void handleMouseReleased(int row, int col) {
        int startRow = -1;
        int startCol = -1;
        for (Tile[] buttonRow : tiles) {
            for (Tile button : buttonRow) {
                button.setYellowState();
            }
        }
    }


    private class ButtonDragListener implements javafx.event.EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            int row = getButtonRow(event);
            int col = getButtonColumn(event);
            handleMouseDragged(row, col);
        }

        private int getButtonRow(MouseEvent event) {
            double y = event.getY();
            double buttonHeight = tiles[0][0].getHeight();
            return (int) (y / buttonHeight);
        }

        private int getButtonColumn(MouseEvent event) {
            double x = event.getX();
            double buttonWidth = tiles[0][0].getWidth();
            return (int) (x / buttonWidth);
        }
   

    }
}