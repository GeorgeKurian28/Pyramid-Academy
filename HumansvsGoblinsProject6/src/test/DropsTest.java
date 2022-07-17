import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DropsTest {
    Drops drops1;
    Drops drops2;
    @BeforeEach
    void setUp() {
        drops1 = new Drops(1,4);
        drops2 = new Drops(50,6,8);
    }

    @Test
    void getNum(){
        assertEquals(20,drops1.getNum());
        assertEquals(50,drops2.getNum());
    }

    @AfterEach
     void tearDown(){

    }
}
