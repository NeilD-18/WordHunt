package viewmodel;

import view.Tile;
import java.util.Stack;


public class WordHuntCurrentWordViewModel {

    private Stack<Tile> selectedTilesStack;
    private String currentWord;

    public WordHuntCurrentWordViewModel(Stack<Tile> selectedTilesStack) {
        this.selectedTilesStack = selectedTilesStack;
        updateCurrentWord();
    }

    // Update the current word based on the selected tiles stack
    private void updateCurrentWord() {
        StringBuilder builder = new StringBuilder();
        for (Tile tile : selectedTilesStack) {
            builder.append(tile.getLetter()); // Using getLetter() to get the letter
        }
        currentWord = builder.toString();
        System.out.println("This is test" + currentWord);
    }

    // Get the current word
    public String getCurrentWord() {
        return currentWord;
    }

    // Method to be called by the observer when the stack changes
    public void updateStack(Stack<Tile> newStack) {
        this.selectedTilesStack = newStack;
        updateCurrentWord();
    }
}