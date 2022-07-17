import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    Inventory inventory1;
    Inventory inventory2;
    Inventory inventory3;

    @BeforeEach
    void setUp() {
        inventory1 = new Inventory(2,3);
        inventory2 = new Inventory(5,7,"Gun","30");
        inventory3 = new Inventory(9,6,100,"Grenade","70");
    }

    @Test
    void getHealth() {
        assertEquals(50,inventory1.getHealth());
        assertEquals(100,inventory3.getHealth());
    }

    @Test
    void getLocation() {
        assertEquals(2,inventory1.getLocation()[0]);
        assertEquals(3, inventory1.getLocation()[1]);
        assertEquals(5,inventory2.getLocation()[0]);
        assertEquals(7, inventory2.getLocation()[1]);
    }

    @Test
    void setLocation() {
        inventory1.setLocation(4,5);
        assertEquals(4,inventory1.getLocation()[0]);
        assertEquals(5, inventory1.getLocation()[1]);
    }

    @Test
    void getWeapons() {
        assertEquals("[Gun, Grenade]", inventory1.getWeapons().keySet().toString());
        assertEquals("[Gun]", inventory2.getWeapons().keySet().toString());
        assertEquals("[Grenade]", inventory3.getWeapons().keySet().toString());
        assertEquals(20, inventory1.getWeapons().get("Gun"));
        inventory1.setWeapons("Gun",10);
        assertEquals(30, inventory1.getWeapons().get("Gun"));
        inventory3.setWeapons("Gun",10);
        assertEquals("[Gun, Grenade]", inventory3.getWeapons().keySet().toString());

    }

    @AfterEach
    void tearDown() {
    }
}