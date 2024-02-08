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
    private static final int GRID_SIZE = 4;
    private Button[][] buttons;
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
    }

    private void initializeComponents() {
        buttons = new Button[GRID_SIZE][GRID_SIZE];
        scoreLabel = new Label("Words Found: 0");
        scoreLabel.setStyle("-fx-font-size: 16;");

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j] = new Button("Button " + (i * GRID_SIZE + j + 1));
                buttons[i][j].setMinSize(80, 80);
                buttons[i][j].setOnMousePressed(event -> handleMousePressed(row, col));
                buttons[i][j].addEventHandler(MouseEvent.MOUSE_DRAGGED, new ButtonDragListener());
                buttons[i][j].setOnMouseReleased(event -> handleMouseReleased(row, col));
            }
        }
    }

    private void setupLayout(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add buttons to the grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridPane.add(buttons[i][j], j, i);
            }
        }

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scoreLabel, gridPane, createGuessedWordsBank());

        Scene scene = new Scene(vBox, 400, 400);
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
            Button wordButton = new Button(word);
            wordButton.setDisable(true);
            flowPane.getChildren().add(wordButton);
        }

        return flowPane;
    }

    private void handleMousePressed(int row, int col) {
        buttons[row][col].setStyle("-fx-background-color: lightblue;");
    }

    private void handleMouseDragged(int row, int col) {
        buttons[row][col].setStyle("-fx-background-color: lightblue;");
    }

    private void handleMouseReleased(int row, int col) {
        startRow = -1;
        startCol = -1;
        for (Button[] buttonRow : buttons) {
            for (Button button : buttonRow) {
                button.setStyle("");
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
            double buttonHeight = buttons[0][0].getHeight();
            return (int) (y / buttonHeight);
        }

        private int getButtonColumn(MouseEvent event) {
            double x = event.getX();
            double buttonWidth = buttons[0][0].getWidth();
            return (int) (x / buttonWidth);
        }
    }
}

