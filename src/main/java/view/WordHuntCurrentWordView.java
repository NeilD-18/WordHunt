package view; 

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox; 
import javafx.scene.control.Button;
import javafx.geometry.Pos;

import viewmodel.*; 


/**
 * WordHuntCurrentWordView class
 */
public class WordHuntCurrentWordView extends BorderPane {
    private HBox buttonContainer; 
    private WordHuntCurrentWordViewModel vm; 

    /**
     * Constructor, binds view to viewmodel 
     */
    public WordHuntCurrentWordView(WordHuntCurrentWordViewModel viewmodel) { 
        vm = viewmodel;
        buttonContainer = new HBox(); 
        buttonContainer.setSpacing(10);
        
        updateButtons(vm.getCurrentWord()); 

        vm.currentWordProperty().addListener((observer, oldWord, newWord) -> updateButtons(newWord));  
        
        getChildren().add(buttonContainer);
    }

    /**
     * Return Hbox which shows the buttons
     */
    public HBox getCurrentWordView() { return buttonContainer; }

    /**
     * Update button container
     * @param String newWord which the buttons will show
     */
    private void updateButtons(String newWord) { 
        buttonContainer.getChildren().clear();
        for(char letter : newWord.toCharArray()) { 
            Tile tile = new Tile(Character.toString(letter));
            tile.setMinSize(50,50); 
            tile.setBlueState(); 
            buttonContainer.getChildren().add(tile);
            buttonContainer.setAlignment(Pos.CENTER); 
            buttonContainer.setTranslateX(170);
            buttonContainer.setTranslateY(60);
            
        }
    }
}
