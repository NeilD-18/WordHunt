package viewmodel;

import java.util.Stack;

// Import the Tile class
import view.*;
import model.*;

public class WordHuntBoardViewModel {

    private WordHuntGame game;

    public WordHuntBoardViewModel()
    {
        this.game = new WordHuntGame();
    }

    public static boolean isAdjacent(Tile tile1, Tile tile2, Stack<Tile> selectedTilesStack) {
        int row1 = tile1.getRow();
        int col1 = tile1.getCol();
        int row2 = tile2.getRow();
        int col2 = tile2.getCol();

        if (!selectedTilesStack.isEmpty()) {
            Tile topOfStack = selectedTilesStack.peek();
            int topRow = topOfStack.getRow();
            int topCol = topOfStack.getCol();

            return (Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1) &&
                    (Math.abs(row1 - topRow) <= 1 && Math.abs(col1 - topCol) <= 1);
        } else {
            return Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1;
        }
    }

public void handleWord(String word) {
    int validity = this.game.isValidWord(word.toLowerCase());
    System.out.println("Pre-executing handleWord");
    System.out.println(validity);
    if (validity == 1) {
        // add in a wordFound tile with no effect
        this.game.addFoundWord(false, word);
    } else if (validity == 2) {
        // add in the word found tile with a special word found effect
        this.game.addFoundWord(true, word);
    } else {
        // don't do anything or show it's an invalid word
    }
    System.out.println("Received string in WordHuntBoardViewModel: " + word);
}

}
