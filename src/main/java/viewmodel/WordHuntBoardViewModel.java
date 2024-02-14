package viewmodel;

import java.util.Stack;
import view.Tile;

public class WordHuntBoardViewModel {

    private Stack<Tile> selectedTilesStack;
    private WordHuntCurrentWordViewModel currentWordBuilder; 

    public WordHuntBoardViewModel() {
        selectedTilesStack = new Stack<>();
        currentWordBuilder = new WordHuntCurrentWordViewModel(selectedTilesStack); 
    }

    public boolean isAdjacent(Tile tile) {
        if (!selectedTilesStack.isEmpty()) {
            Tile topOfStack = selectedTilesStack.peek();
            return areAdjacent(topOfStack, tile) && !selectedTilesStack.contains(tile);
        } else {
            return true;
        }
    }

    private void notifyObserver() { 
        currentWordBuilder.updateStack(selectedTilesStack);
    }

    private boolean areAdjacent(Tile tile1, Tile tile2) {
        int row1 = tile1.getRow();
        int col1 = tile1.getCol();
        int row2 = tile2.getRow();
        int col2 = tile2.getCol();

        return Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1;
    }

    public void addToSelectedTilesStack(Tile tile) {
        selectedTilesStack.push(tile);
        printSelectedTilesStack();
        notifyObserver();
    }

    public void removeFromSelectedTilesStack(Tile tile) {
        selectedTilesStack.remove(tile);
        printSelectedTilesStack();
        notifyObserver();
    }

    public void clearSelectedTilesStack() {
        selectedTilesStack.clear();
        printSelectedTilesStack();
        notifyObserver();
    }

    public Stack<Tile> getSelectedTilesStack() {
        return selectedTilesStack;
    }

    private void printSelectedTilesStack() {
        System.out.print("Selected Tiles Stack: ");
        for (Tile tile : selectedTilesStack) {
            System.out.print(tile.getLetter()); // Using getLetter() to get the letter
        }
        System.out.println(); // Print a newline for better readability
    }

    // Add this method to get the string representation of the selectedTilesStack
    public String getTileStackAsString() {
        StringBuilder builder = new StringBuilder();
        for (Tile tile : selectedTilesStack) {
            builder.append(tile.getLetter()); // Using getLetter() to get the letter
        }
        return builder.toString();
    }
}
