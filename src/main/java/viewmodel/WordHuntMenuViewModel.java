package viewmodel;

import javafx.beans.property.SimpleBooleanProperty;

public class WordHuntMenuViewModel {
    private SimpleBooleanProperty startNewGameRequested = new SimpleBooleanProperty(false);

    public SimpleBooleanProperty startNewGameRequestedProperty() {
        return startNewGameRequested;
    }

    public void requestStartNewGame() {
        startNewGameRequested.set(true);
    }

    public void resetStartNewGameRequest() {
        startNewGameRequested.set(false);
    }

}