import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DragonGameTryCatchTest {
    DragonGameTryCatch dragonGameTryCatch;


    @BeforeEach
    void setUp() {
        dragonGameTryCatch = new DragonGameTryCatch();
    }

    @Test
    void getOption() throws Exception {
        assertEquals("Y",dragonGameTryCatch.getOption("Y"),"That's not yes somethings wrong!!!");
        assertEquals("y",dragonGameTryCatch.getOption("y"),"That's not yes somethings wrong!!!");
        assertEquals("n",dragonGameTryCatch.getOption("n"),"That's not no somethings wrong!!!");
        assertEquals("N",dragonGameTryCatch.getOption("N"),"That's not no somethings wrong!!!");
    }


    @AfterEach
    void tearDown() {
        System.out.println("This is the only test we have for this one");
    }
}