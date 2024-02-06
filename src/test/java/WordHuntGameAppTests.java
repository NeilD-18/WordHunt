import java.util.*; 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class WordHuntGameAppTests {
    //Delete all this for actual tests, just for setting up gradle. 
    private int[] test; 
    
    @Before
    public void setUp() { 
        test = new int[4]; 
    }

    @After
    public void tearDown(){
        test = null;
    }

    @Test
    public void testTest(){ 
        assertEquals("This is just a test", 1, 1); 
    }

    @Test
    public void testTest1(){
        assertEquals("This is going to fail", 0, 1);
    }


}


