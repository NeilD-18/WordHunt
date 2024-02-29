import static org.junit.Assert.*;


import java.beans.Transient;


import org.junit.Before;
import org.junit.Test;
import model.*;
public class WordHuntGameAppTests {


    private WordHuntGame wordHuntGame;


    @Before
    public void setUp() {
        wordHuntGame = new WordHuntGame(4);
    }


    @Test
    public void testGenerateRandomBoard() {
        wordHuntGame.generateRandomBoard();
        assertEquals("Generated board has the correct number of rows", 4, wordHuntGame.getBoard().size());


        for (int i = 0; i < 4; i++) {
            assertEquals("Generated board has the correct number of columns", 4, wordHuntGame.getBoard().get(i).size());
        }
    }


    @Test
    public void testIsValidMove() {
        assertTrue("Valid move should return true", wordHuntGame.isValidMove(0, 0, 1, 1));
        assertFalse("Invalid move should return false", wordHuntGame.isValidMove(0, 0, 2, 2));
    }


    @Test
    public void testGetFoundWords() {
        assertNotNull("Found words list should not be null", wordHuntGame.getFoundWords());
        assertTrue("Found words list should be initially empty", wordHuntGame.getFoundWords().isEmpty());
    }


    @Test
    public void testGetFoundBonusWords() {
        assertNotNull("Found bonus words list should not be null", wordHuntGame.getFoundBonusWords());
        assertTrue("Found bonus words list should be initially empty", wordHuntGame.getFoundBonusWords().isEmpty());
    }


    @Test
    public void testIsValidWord() {
        assertEquals("Invalid word should return 0", 0, wordHuntGame.isValidWord("XYZ"));
    }


    @Test
    public void testAddFoundWord() {
        wordHuntGame.addFoundWord(false, "WORD");
        assertTrue("Found words list should contain added word", wordHuntGame.getFoundWords().contains("WORD"));
    }


    @Test
    public void testGetPossibleWords() {
        assertNotNull("Possible words list should not be null", wordHuntGame.getPossibleWords());
    }


    @Test
    public void testTearDown() {
        wordHuntGame.generateRandomBoard();
        wordHuntGame.addFoundWord(false, "WORD");
        wordHuntGame.tearDown();
        assertTrue("Board should be empty after tearDown", wordHuntGame.getBoard().isEmpty());
        assertTrue("Found words list should be empty after tearDown", wordHuntGame.getFoundWords().isEmpty());
    }


    @Test
    public void testFoundWordBonusFlag() {
        wordHuntGame.addFoundWord(true, "BONUS");
        assertTrue("Found bonus words list should contain added bonus word", wordHuntGame.getFoundBonusWords().contains("BONUS"));
        assertFalse("Found words list should not contain bonus word", wordHuntGame.getFoundWords().contains("BONUS"));
    }


    @Test
    public void testGetBoardAfterGenerateRandomBoard() {
        wordHuntGame.generateRandomBoard();
        assertNotNull("Board should not be null after generateRandomBoard", wordHuntGame.getBoard());
    }
   
}



