package view;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import viewmodel.WordHuntBoardViewModel;

public class WordHuntAddButtonView extends Button {

    private WordHuntWordsFoundView parentView;
    private WordHuntBoardViewModel boardViewModel;

    public WordHuntAddButtonView(WordHuntWordsFoundView parentView, WordHuntBoardViewModel boardViewModel) {
        this.parentView = parentView;
        this.boardViewModel = boardViewModel;

        setText("Add Word");
        setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        setOnMouseEntered(e -> setEffect(new DropShadow()));
        setOnMouseExited(e -> setEffect(null));

        setOnAction(event -> {
            parentView.wordList.add(parentView.createStyledText("Apple"));
            parentView.animateWordAddition();

            // Print the string representation of the tileStack
            String tileStackString = boardViewModel.getTileStackAsString();
            System.out.println("Tile Stack: " + tileStackString);
        });
    }
}
