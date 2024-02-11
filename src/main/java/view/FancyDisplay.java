// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.layout.GridPane;
// import javafx.stage.Stage;

// public class FancyDisplay extends Application {

//     @Override
//     public void start(Stage primaryStage) {
//         GridPane gridPane = new GridPane();

//         for (int row = 0; row < 4; row++) {
//             for (int col = 0; col < 4; col++) {
//                 Button button = new Button("Button " + ((row * 4) + col + 1));
//                 GridPane.setRowIndex(button, row);
//                 GridPane.setColumnIndex(button, col);
//                 gridPane.getChildren().add(button);
//             }
//         }

//         Scene scene = new Scene(gridPane, 300, 300);
//         primaryStage.setTitle("Button Grid Example");
//         primaryStage.setScene(scene);

//         primaryStage.show();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FancyDisplay extends Application {
    private static final int WIDTH = 1280; 
    private static final int HEIGHT = 720; 
   
    private static final int GRID_SIZE = 4;
    private Tile[][] tiles;
    private Label scoreLabel;
    private ArrayList<String> guessedWords;
    private int startRow = -1;
    private int startCol = -1;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Word Game");

        initializeComponents();
        setupLayout(primaryStage);

        primaryStage.show();
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
    }

    private void initializeComponents() {
        tiles = new Tile[GRID_SIZE][GRID_SIZE];
        scoreLabel = new Label("Words Found: 0");
        scoreLabel.setStyle("-fx-font-size: 16;");

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int row = i;
                final int col = j;
                tiles[i][j] = new Tile("" + (i * GRID_SIZE + j + 1));
                tiles[i][j].setMinSize(80, 80);
                tiles[i][j].setOnMousePressed(event -> handleMousePressed(row, col));
                tiles[i][j].addEventHandler(MouseEvent.MOUSE_DRAGGED, new ButtonDragListener());
                tiles[i][j].setOnMouseReleased(event -> handleMouseReleased(row, col));
            }
        }
    }

    private void setupLayout(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        // Add buttons to the grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridPane.add(tiles[i][j], j, i);
            }
        }

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setId("game-root");
        vBox.getChildren().addAll(scoreLabel, gridPane, createGuessedWordsBank());

        Scene scene = new Scene(vBox, 400, 400);
        scene.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());

        primaryStage.setScene(scene);
    }

    private FlowPane createGuessedWordsBank() {
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(10);

        guessedWords = new ArrayList<>();

        // Add initial guessed words (you can add more dynamically)
        guessedWords.add("Word1");
        guessedWords.add("Word2");
        guessedWords.add("Word3");

        for (String word : guessedWords) {
            Tile wordButton = new Tile(word);
            wordButton.setDisable(true);
            flowPane.getChildren().add(wordButton);
        }

        return flowPane;
    }

    private void handleMousePressed(int row, int col) {
        tiles[row][col].setGreenState();
    }

    private void handleMouseDragged(int row, int col) {
        tiles[row][col].setGreenState();
    }

    private void handleMouseReleased(int row, int col) {
        startRow = -1;
        startCol = -1;
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
            double gridHeight = tiles.length * tiles[0][0].getHeight();
            double relativeY = y / gridHeight * GRID_SIZE;
            return (int) Math.min(Math.max(relativeY, 0), GRID_SIZE - 1);
        }
        
        private int getButtonColumn(MouseEvent event) {
            double x = event.getX();
            double gridWidth = tiles[0].length * tiles[0][0].getWidth();
            double relativeX = x / gridWidth * GRID_SIZE;
            return (int) Math.min(Math.max(relativeX, 0), GRID_SIZE - 1);
        }
    }
}

