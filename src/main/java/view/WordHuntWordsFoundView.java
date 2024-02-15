package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * WordHuntWordsFoundView class
 */
public class WordHuntWordsFoundView extends VBox {

    public ObservableList<Text> wordList; 
    protected ListProperty<Text> wordListProperty;
    protected ListView<Text> listView;

    /**
     * Constructor, binds wordsfound list. 
     */
    public WordHuntWordsFoundView() {
        wordList = FXCollections.observableArrayList();
        wordListProperty = new SimpleListProperty<>(wordList);
        listView = new ListView<>();
        listView.itemsProperty().bind(wordListProperty);
        listView.setPrefWidth(200);
        listView.setPrefHeight(300);

        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));
        setBackground(getCoolColorPattern());

        Text title = new Text("Words Found");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        title.setFill(Color.WHITE);

        getChildren().addAll(title, listView);
    }

    /**
     * Animate the added word
     */
    public void animateWordAddition() {
        int lastIndex = wordList.size() - 1;

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(wordList.get(lastIndex).translateYProperty(), 0.0);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     * Animate the words list
     */
    public void animateWordList() {
        Timeline timeline = new Timeline();
        for (int i = 0; i < wordList.size(); i++) {
            int index = i; 
            KeyValue keyValue = new KeyValue(wordList.get(index).translateYProperty(), 0.0);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();
    }

    /**
     * update the words list
     */
    public void update(ObservableList<String> updatedList) {
        wordList.clear(); 
        updatedList.forEach(word -> wordList.add(createStyledText(word)));

        animateWordList();
    }

    /**
     * created styled text
     * @param String text
     * @return Text styled text
     */
    public Text createStyledText(String text) {
        Text styledText = new Text(text);
        styledText.setFill(Color.BLACK); 
        return styledText;
    }

    /**
     * create styled text for bonus words
     * @param String text
     * @return Text
     */
    public Text bonusStyledText(String text) {
        Text styledText = new Text(text);
        LinearGradient rainbowGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.RED), new Stop(0.25, Color.ORANGE), new Stop(0.5, Color.YELLOW), new Stop(0.75, Color.GREEN), new Stop(1, Color.BLUE));
        styledText.setFill(rainbowGradient);
        return styledText;
    }
    
    /**
     * Set background
     */
    private Background getCoolColorPattern() {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#7EC8E3")),
                new Stop(0.5, Color.web("#5EA4C2")),
                new Stop(1, Color.web("#3B7FA1")));

        return new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY));
    }
}
