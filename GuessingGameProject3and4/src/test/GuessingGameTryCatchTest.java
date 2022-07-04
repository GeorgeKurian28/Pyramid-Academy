import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessingGameTryCatchTest {
    GuessingGameTryCatch gameTryCatch;
    @BeforeEach
    void setUp() {
        gameTryCatch = new GuessingGameTryCatch();
        gameTryCatch.setSystemGuess(10);
    }

    @Test
    void game() {
        assertEquals(false,gameTryCatch.game(7,4),"This should be correct or else there is something wrong with the code");
        assertEquals(true,gameTryCatch.game(10,2),"This should be correct or else there is something wrong with the code");
        assertEquals(false,gameTryCatch.game(15,6),"This should be correct or else there is something wrong with the code");
    }

    @AfterEach
    void tearDown() {
    }
}