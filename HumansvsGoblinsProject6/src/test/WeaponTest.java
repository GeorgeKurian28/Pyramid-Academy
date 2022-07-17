import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    Weapon weapon1;
    Weapon weapon2;

    @BeforeEach
    void setUp() {
        weapon1 = new Weapon("Gun",40);
        weapon2 = new Weapon("Grenade", 70);
    }


    @Test
    void getName() {
        assertEquals("Gun", weapon1.getName());
        assertEquals("Grenade", weapon2.getName());
    }


    @Test
    void getStrength() {
        assertEquals(40, weapon1.getStrength());
        weapon1.setStrength(90);
        assertEquals(90, weapon1.getStrength());
    }

    @AfterEach
    void tearDown() {
    }
}