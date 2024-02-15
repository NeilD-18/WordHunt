package viewmodel; 

import javafx.application.Platform;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.*;


import javafx.application.Platform;
import model.WordHuntGame;

public class WordHuntInGameMenuViewModel {
    
    private WordHuntBoardViewModel vm;

    public WordHuntInGameMenuViewModel(WordHuntBoardViewModel boardViewModel) { 
        vm = boardViewModel;
    }

    public void quitGame() {
        Platform.exit();
    }

    public void saveGame(String saveFile) { 
        
        vm.saveGame("src/main/resources/Save Files/" + saveFile + ".txt");
    }

    

}
