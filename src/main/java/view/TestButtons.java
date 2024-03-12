package view; 

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class TestButtons extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create top and bottom Text nodes
        Text topText = new Text("A");
        topText.setStyle("-fx-font-size: 20;"); 
       
        Text bottomText = new Text("X");
        bottomText.setStyle("-fx-font-size: 10;"); // Set font size for the bottom text

        // Create an HBox for the bottom Text
        HBox bottomHBox = new HBox(bottomText);
        bottomHBox.setAlignment(Pos.BASELINE_RIGHT); // Right alignment
        bottomHBox.setTranslateY(5);
        bottomHBox.setTranslateX(5); 
        
   

   
        // Create a BorderPane to hold the VBox and HBox
        VBox root = new VBox(topText, bottomHBox);
        root.setAlignment(Pos.CENTER);
        root.setTranslateY(5);
   
        // Create a button and set the layout as its graphic
        Button button = new Button();
        button.setGraphic(root);
        button.setPrefWidth(65); // Set preferred width
        button.setPrefHeight(65); // Set preferred height

        // Create the scene and set it on the stage
        Scene scene = new Scene(new BorderPane(button), 400, 300);
        primaryStage.setScene(scene);

        // Set the title of the stage and show it
        primaryStage.setTitle("Button with Text Alignment Example");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

