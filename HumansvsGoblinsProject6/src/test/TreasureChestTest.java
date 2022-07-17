import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
class TreasureChestTest {

    TreasureChest treasureChest1;
    TreasureChest treasureChest2;

    @BeforeEach
    void setUp() {
        treasureChest1 = new TreasureChest(3,4);
        treasureChest2 = new TreasureChest(400,5,7);
    }

    @Test
    void getPoints() {
        assertEquals(100,treasureChest1.getPoints());
        assertEquals(400,treasureChest2.getPoints());
        treasureChest1.setPoints(1000);
        assertEquals(1000, treasureChest1.getPoints());
    }

    @Test
    void getPosition(){
        assertEquals(3,treasureChest1.getPosition()[0]);
        assertEquals(4,treasureChest1.getPosition()[1]);
        assertEquals(7,treasureChest2.getPosition()[1]);
    }

    @AfterEach
    void tearDown(){

    }
}
