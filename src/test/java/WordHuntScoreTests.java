import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.Before;
import org.junit.Test;
import model.*;
public class WordHuntScoreTests {


    private WordHuntGame wordHuntGame;
    private WordHuntWords wordHuntWords;
    private WordHuntScore wordHuntScore;


    @Before
    public void setUp() {
        wordHuntGame = new WordHuntGame();
        wordHuntWords = new WordHuntWords(wordHuntGame);
        wordHuntScore = new WordHuntScore(wordHuntWords);
    }


    @Test
    public void testGetFoundWords() {
        assertNotNull("Found words list should not be null", wordHuntScore.getFoundWords());
        assertTrue("Found words list should be initially empty", wordHuntScore.getFoundWords().isEmpty());
        wordHuntWords.addFoundWord(true, "BONUS");
        assertTrue("Found words list should contain added word", wordHuntWords.getFoundWords().contains("WORD"));
    }


    @Test testGetNumFoundWords() {
        assertEquals("There is one found word once one is added", 1,  wordHuntScore.getNumFoundWords());
    }


    @Test testTearDown() {
        wordHuntGame.tearDown();
        assertTrue("Found words list should be empty after teardown", wordHuntWords.getFoundWords().isEmpty());
        assertTrue("There should be zero  found words after tearDown", 1, wordHuntScore.getNumFoundWords());
    }


}