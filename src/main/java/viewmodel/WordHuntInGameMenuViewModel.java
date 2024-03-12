package viewmodel; 

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import view.*;
import java.util.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * In game menu ViewModel
 */
public class WordHuntInGameMenuViewModel {
    
    private WordHuntBoardViewModel vm;

    /**
     * Constructor that allows communication with boardViewModel
     */
    public WordHuntInGameMenuViewModel(WordHuntBoardViewModel boardViewModel) { 
        vm = boardViewModel;
    }

    /**
     * get save path
     */
    public void getSavePath(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Save File");
        dialog.setHeaderText("Enter Save File Name:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait(); 
        result.ifPresent(name -> {saveGame(name);});
    }

    /**
     * New Game
     */
    public void newGame(WordHuntNewGame newGame) { 
         Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Select Grid Size");
    
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
    
        Button size4Button = new Button("4x4");
        size4Button.setOnAction(e -> {
            newGame.initializeComponents("null", 4);
            newGame.setupLayout();
            popupStage.close();
        });
    
        Button size5Button = new Button("5x5");
        size5Button.setOnAction(e -> {
            newGame.initializeComponents("null", 5);
            newGame.setupLayout();
            popupStage.close();
        });
    
        Button size6Button = new Button("6x6");
        size6Button.setOnAction(e -> {
            newGame.initializeComponents("null", 6);
            newGame.setupLayout();
            popupStage.close();
        });

        Button size7Button = new Button("7x7");
        size7Button.setOnAction(e -> {
            newGame.initializeComponents("null", 7);
            newGame.setupLayout();
            popupStage.close();
        });
    
        gridPane.add(size4Button, 0, 0);
        gridPane.add(size5Button, 1, 0);
        gridPane.add(size6Button, 2, 0);
        gridPane.add(size7Button, 3, 0);
    
        Scene scene = new Scene(gridPane, 300, 100);
        popupStage.setScene(scene);
        popupStage.showAndWait(); 
        
        
        
        
    }
    
    /**
     * Quit game
     */
    public void quitGame() {
        Platform.exit();
    }

    /**
     * Save game at a given path
     */
    public void saveGame(String saveFile) { 
        
        vm.saveGame("src/main/resources/Save Files/" + saveFile + ".txt");
    }

    

}
