import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangManTest {

    HangMan hangMan;

    @BeforeEach
    void setUp() {
        hangMan = new HangMan();
    }


    @Test
    void isValidGuess() {
        assertEquals(true, hangMan.isValidGuess("a"),"This is not incorrect guess");
        assertTrue(hangMan.isValidGuess("b"));
        assertFalse(hangMan.isValidGuess("1"));
        assertFalse(hangMan.isValidGuess(";"));
        assertFalse(hangMan.isValidGuess("adf"));
    }

    @Test
    void getWord() {
        boolean once = false;
        String word = "Mango";
        for(int i = 0; i < 7; i++)
            if(hangMan.getWord().equals(word)){
                once =  true;
            }
        assertTrue(once,"Cound not find " + word);

    }

    @Test
    void setProgress() {
        assertEquals("___",hangMan.setProgress(' ',"fly","",""),"The progress method is not correct");
        assertEquals("_l_",hangMan.setProgress('l',"fly","___","a"),"The progress method is not correct");
        assertEquals("_____",hangMan.setProgress('z',"Mango","_____","a"),"The progress method is not correct");
        assertEquals("M____",hangMan.setProgress('M',"Mango","_____","a"),"The progress method is not correct");

    }

    @AfterEach
    void tearDown() {
    }
}