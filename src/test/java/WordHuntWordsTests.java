import static org.junit.Assert.*;


import java.beans.Transient;


import org.junit.Before;
import org.junit.Test;
import model.*;
public class WordHuntWordsTests {


    private WordHuntGame wordHuntGame;
    private WordHuntWords wordHuntWords;


    @Before
    public void setUp() {
        wordHuntGame = new WordHuntGame(4);
        wordHuntWords = new WordHuntWords(wordHuntGame, 4);
        wordHuntWords.initializeWordLists();
    }


    @Test
    public void testIsValidWord() {
        assertEquals("Invalid word should return 0", 0, wordHuntWords.isValidWord("XYZ"));
    }


    @Test
    public void testGetPossibleWords() {
        assertNotNull("Possible words list should not be null", wordHuntGame.getPossibleWords());
        assertTrue("Possible words list should be initially empty", wordHuntWords.getPossibleWords().isEmpty());
    }


    @Test
    public void testGetFoundWords() {
        assertNotNull("Found words list should not be null", wordHuntWords.getFoundWords());
        assertTrue("Found words list should be initially empty", wordHuntWords.getFoundWords().isEmpty());
    }

    @Test
    public void testGetNumFoundWords() {
        assertEquals("Initially there should be no found words", 0, wordHuntWords.getNumFoundWords());
    }


    @Test
    public void testGetFoundBonusWords() {
        assertNotNull("Found bonus words list should not be null", wordHuntWords.getFoundBonusWords());
        assertTrue("Found bonus words list should be initially empty", wordHuntWords.getFoundBonusWords().isEmpty());
    }


    @Test
    public void testAddFoundWord() {
        wordHuntWords.addFoundWord(false, "WORD");
        assertTrue("Found words list should contain added word", wordHuntWords.getFoundWords().contains("WORD"));
        assertEquals("There is one found word once one is added", 1,  wordHuntWords.getNumFoundWords());
    }


    @Test
    public void testFoundWordBonusFlag() {
        wordHuntWords.addFoundWord(true, "BONUS");
        assertTrue("Found bonus words list should contain added bonus word", wordHuntWords.getFoundBonusWords().contains("BONUS"));
        assertFalse("Found words list should not contain bonus word", wordHuntWords.getFoundWords().contains("BONUS"));
    }


    @Test
    public void testTearDown() {
        wordHuntGame.generateRandomBoard();
        wordHuntWords.findWords();
        wordHuntWords.tearDown();
        assertTrue("Possible words list should be empty after tearDown", wordHuntWords.getPossibleWords().isEmpty());
        assertTrue("Found words list should be empty after tearDown", wordHuntWords.getFoundWords().isEmpty());
        assertTrue("Bonus words list should be empty after tearDown", wordHuntWords.getFoundBonusWords().isEmpty());
    }


}