package view;


import javafx.animation.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import viewmodel.*;
import javafx.scene.control.TextInputDialog;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * InGameMenu View class
 */
public class WordHuntInGameMenuView extends VBox {


    protected ObservableList<Button> menuOptions;
    protected ListProperty<Button> menuOptionsProperty;
    private ToggleButton menuToggleButton;
    private WordHuntInGameMenuViewModel vm;
    private WordHuntNewGame ng;


    /**
     * Constructor that binds view to viewmodel
     * @param WordHuntInGameMenuViewModel 
     * @param WordHuntNewGame 
     */
    public WordHuntInGameMenuView(WordHuntInGameMenuViewModel viewmodel, WordHuntNewGame newGame) {
        vm = viewmodel; 
        ng = newGame;
        menuOptions = FXCollections.observableArrayList();
        menuOptionsProperty = new SimpleListProperty<>(menuOptions);
        setSpacing(10);
        setAlignment(Pos.TOP_LEFT);
        setPadding(new Insets(10));
        setBackground(Background.EMPTY);
        menuToggleButton = createMenuToggleButton("src/main/resources/images/InGameMenuButton.png");
        getChildren().add(menuToggleButton);
    }


    /**
     * Create the menu button
     * @param String imagepath for image of button
     */
    private ToggleButton createMenuToggleButton(String imagePath) {
        ToggleButton button = new ToggleButton();
        Image image;
        try {
            image = new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }


        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30); // Set the width as per your requirement
        imageView.setPreserveRatio(true);


        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnAction(e -> handleMenuButtonClicked());
        return button;
    }


    /**
     * Handle the click of the menu button
     */
    private void handleMenuButtonClicked() {
        if (menuToggleButton.isSelected()) {
            // Add menu options dynamically with fade-in effect
            addButtonWithTransition("New Game", this::handleNewGame);
            addButtonWithTransition("Save Game", this::handleSaveGame);
            addButtonWithTransition("Quit Game", this::handleQuitGame);
        } else {
            // Remove all menu options with fade-out effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), getChildren().get(1));
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(event -> getChildren().subList(1, getChildren().size()).clear());
            fadeOut.play();
        }
    }
   

    /**
     * Method to add button with a transition
     * @param String text
     * @param EventHandler<ActionEvent>
     */
    private void addButtonWithTransition(String text, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 10;");
        DropShadow shadow = new DropShadow();
        button.setEffect(shadow);

        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: #388E3C; -fx-background-radius: 10;");
            shadow.setColor(Color.BLACK);
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 10;");
            shadow.setColor(Color.BLACK);
        });


        button.setOnAction(eventHandler);

        getChildren().add(button);
        button.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), button);
        fadeIn.setToValue(1);
        fadeIn.play();
    }


    /**
     * Handle new game 
     */
    private void handleNewGame(ActionEvent event) {
       vm.newGame(ng); 
              
    }


    /**
     * Handle save game
     */
    private void handleSaveGame(ActionEvent event) {
        
        vm.getSavePath();

    }

    /**
     * Handle quit game
     */
    private void handleQuitGame(ActionEvent event) {
        vm.quitGame();
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
