package view;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class TestButtons extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        // Show open dialog
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);

        // Process selected files
        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                System.out.println("Selected file: " + file.getAbsolutePath());
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}