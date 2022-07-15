import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void getInventory() {
        int[] arr = {6,7};
        Weapon[] weapons = new Weapon[2];
        HashMap<Integer,Inventory> inventory = game.getInventory(arr);
        for(int i :(inventory.keySet())) {
            assertEquals("Gun",inventory.get(i).showWeapons().toArray(weapons)[0].getName());
            assertEquals("Grenade",inventory.get(i).showWeapons().toArray(weapons)[1].getName());
            assertEquals(20,inventory.get(i).showWeapons().toArray(weapons)[0].getStrength());
            assertEquals(10,inventory.get(i).showWeapons().toArray(weapons)[1].getStrength());
            assertEquals("Grenade",inventory.get(i).showWeapons().toArray(weapons)[1].getName());
            assertEquals("Grenade",inventory.get(i).showWeapons().toArray(weapons)[1].getName());
            assertEquals(50, inventory.get(i).getHealth());
            assertEquals("Gun",inventory.get(i).getWeapons(0).getName());
            assertEquals("Grenade",inventory.get(i).getWeapons(0).getName());
            assertTrue(inventory.get(i).showWeapons().isEmpty());

        }

    }

    @AfterEach
    void tearDown(){

    }
}