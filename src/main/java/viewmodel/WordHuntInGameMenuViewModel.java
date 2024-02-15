package viewmodel; 

import javafx.application.Platform;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.*;
import view.*;
import java.util.*;
import javafx.scene.control.TextInputDialog;


import javafx.application.Platform;
import model.WordHuntGame;

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
        newGame.initializeComponents("null");
        newGame.setupLayout();
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
