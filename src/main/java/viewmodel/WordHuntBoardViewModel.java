package viewmodel;

import java.util.Stack;

// Import the Tile class
import view.Tile;

public class WordHuntBoardViewModel {

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
}
