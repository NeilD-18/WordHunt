package viewmodel;

import javafx.beans.property.SimpleBooleanProperty;

public class WordHuntMenuViewModel {
    private SimpleBooleanProperty startNewGameRequested = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty loadGameRequested = new SimpleBooleanProperty(false);

    public SimpleBooleanProperty startNewGameRequestedProperty() {
        return startNewGameRequested;
    }

    public SimpleBooleanProperty loadGameRequestedProperty() {
        return loadGameRequested;
    }

    public void requestStartNewGame() {
        startNewGameRequested.set(true);
    }

    public void resetStartNewGameRequest() {
        startNewGameRequested.set(false);
    }

    public void requestLoadGame() {
        loadGameRequested.set(true);
    }

    public void resetLoadGameRequest() {
        startNewGameRequested.set(false);
    }

}