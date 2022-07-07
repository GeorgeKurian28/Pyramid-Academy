import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseStringTest {

    ReverseString reverseString;

    @BeforeEach
    void setUp() {
        reverseString = new ReverseString();
    }

    @Test
    void reverseString() {
        assertEquals("elppA",reverseString.reverseString("Apple"),"This is wrong");
        assertEquals("selppA",reverseString.reverseString("Apples"),"This is wrong");
        assertEquals("",reverseString.reverseString(""),"This is wrong");
    }

    @AfterEach
    void tearDown() {
    }
}