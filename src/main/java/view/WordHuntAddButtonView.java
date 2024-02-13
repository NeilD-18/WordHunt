package view;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

public class WordHuntAddButtonView extends Button {

    private WordHuntWordsFoundView parentView;

    public WordHuntAddButtonView(WordHuntWordsFoundView parentView) {
        this.parentView = parentView;

        setText("Add Word");
        setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        setOnMouseEntered(e -> setEffect(new DropShadow()));
        setOnMouseExited(e -> setEffect(null));

        setOnAction(event -> {
            parentView.wordList.add(parentView.createStyledText("Apple"));
            parentView.animateWordAddition();
        });
    }
}
