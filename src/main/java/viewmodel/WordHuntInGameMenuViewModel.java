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

public class WordHuntInGameMenuViewModel {
    
    private WordHuntBoardViewModel vm;

    public WordHuntInGameMenuViewModel(WordHuntBoardViewModel boardViewModel) { 
        vm = boardViewModel;
    }

    public void saveGame(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Save File");
        dialog.setHeaderText("Enter Save File Name:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait(); 
        result.ifPresent(name -> {vm.saveGame(name);});
    }

    public void newGame(WordHuntNewGame newGame) { 
        newGame.initializeComponents("null");
        newGame.setupLayout();
    }
    
    public void quitGame() {
        Platform.exit();
    }

    public void saveGame(String saveFile) { 
        
        vm.saveGame("src/main/resources/Save Files/" + saveFile + ".txt");
    }

    

}
