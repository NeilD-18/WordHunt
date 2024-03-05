package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;

public class TestButtons extends Application {

    @Override
    public void start(Stage primaryStage) {
        Tile myTile = new Tile("A"); 

        // Create a scene and add the button to it
        Scene scene = new Scene(new VBox(myTile), 200, 100);

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Set the title of the stage
        primaryStage.setTitle("Multi Text Button Example");

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 
    

