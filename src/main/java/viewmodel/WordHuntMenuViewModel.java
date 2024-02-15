package viewmodel;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Menu ViewModel Class
 */
public class WordHuntMenuViewModel {
    private SimpleBooleanProperty startNewGameRequested = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty loadGameRequested = new SimpleBooleanProperty(false);

    /**
     * Request new game property
     */
    public SimpleBooleanProperty startNewGameRequestedProperty() {
        return startNewGameRequested;
    }

    /**
     * Request load game property
     */
    public SimpleBooleanProperty loadGameRequestedProperty() {
        return loadGameRequested;
    }

    
    /**
     * Request new game
     */
    public void requestStartNewGame() {
        startNewGameRequested.set(true);
    }

    /**
     * Reset request for new game
     */
    public void resetStartNewGameRequest() {
        startNewGameRequested.set(false);
    }

    /**
     * Request Load Game
     */
    public void requestLoadGame() {
        loadGameRequested.set(true);
    }

    /**
     * Reset Load Game Request
     */
    public void resetLoadGameRequest() {
        startNewGameRequested.set(false);
    }

}