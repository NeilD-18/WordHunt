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
        wordHuntWords.addFoundWord(false, "WORD");
        assertTrue("Found words list should contain added word", wordHuntScore.getFoundWords().contains("WORD"));
    }


    @Test
    public void testGetNumFoundWords() {
        assertEquals("Found words list should be initially empty", 0, wordHuntScore.getNumFoundWords());
        wordHuntWords.addFoundWord(false, "WORD");
        assertTrue("Found words list has length 1 once one is added", 1, wordHuntScore.getNumFoundWords());
    }


    @Test
    public void testTearDown() {
        wordHuntScore.tearDown();
        assertTrue("Found words list should be empty after teardown", wordHuntScore.getFoundWords().isEmpty());
        assertEquals("There should be zero found words after tearDown", 0, wordHuntScore.getNumFoundWords());
    }


}