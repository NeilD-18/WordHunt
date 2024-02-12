import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WordHuntScoreView extends VBox {

    private Label totalWordsFoundLabel;
    private Label totalPossibleWordsLabel;

    public WordHuntScoreView() {
        totalWordsFoundLabel = new Label();
        totalWordsFoundLabel.setStyle("-fx-text-fill: rgba(171, 82, 54, 1);");
        totalPossibleWordsLabel = new Label();
        totalPossibleWordsLabel.setStyle("-fx-text-fill: rgba(171, 82, 54, 1);");

        getChildren().addAll(totalWordsFoundLabel, totalPossibleWordsLabel);
    }

    public void bindTotalWordsFound(IntegerProperty totalWordsFoundProperty) {
        totalWordsFoundLabel.textProperty().bind(Bindings.createStringBinding(() ->
                "Total Words Found: " + totalWordsFoundProperty.get(), totalWordsFoundProperty));
    }

    public void bindTotalPossibleWords(IntegerProperty totalPossibleWordsProperty) {
        totalPossibleWordsLabel.textProperty().bind(Bindings.createStringBinding(() ->
                "Total Possible Words: " + totalPossibleWordsProperty.get(), totalPossibleWordsProperty));
    }

    public void setTotalWordsFound(int totalWordsFound) {
        totalWordsFoundLabel.setText("Total Words Found: " + totalWordsFound);
    }

    public void setTotalPossibleWords(int totalPossibleWords) {
        totalPossibleWordsLabel.setText("Total Possible Words: " + totalPossibleWords);
    }
}
