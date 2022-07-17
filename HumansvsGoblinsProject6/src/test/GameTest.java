import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;
    Goblin goblin1;
    Goblin goblin2;
    Goblin goblin3;
    Goblin goblin4;
    Human human1;
    Human human2;
    Human human3;
    Human human4;
    Land land;
    Inventory inventory1;
    Inventory inventory2;
    Inventory inventory3;
    HashMap<Integer,Inventory> inventoryHashMap = new HashMap<>();
    HashMap<Integer,TreasureChest> treasureChestHashMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        game = new Game();
        goblin1 = new Goblin(5,4);
        goblin2 = new Goblin(1,4);
        goblin3 = new Goblin(2,3);
        goblin4 = new Goblin(3,2);
        human1  = new Human (7,7);
        human2  = new Human (4,4);
        human3  = new Human (2,5);
        human4  = new Human (1,5);
        inventory1 = new Inventory(7,7);
        inventory2 = new Inventory(4,4);
        inventory3 = new Inventory(2,5);
    }

    @Test
    void getGoblines(){
        int[][] position = {{7,7},{4,4},{2,5},{6,8},{5,4},{1,4},{2,3},{3,2}};
        int[] numOfCharacters = {4,4};
        assertEquals(goblin2.getPosition()[1],game.getGoblines(numOfCharacters,position)[1].getPosition()[1]);
        assertEquals(goblin1.getPosition()[0],game.getGoblines(numOfCharacters,position)[0].getPosition()[0]);
    }

    @Test
    void humanLookForInventory(){
        inventoryHashMap.put(77,inventory1);
        assertEquals(inventory1,game.humanLookForInventory(human1.getPosition(),inventoryHashMap));
        assertEquals(null,game.humanLookForInventory(human2.getPosition(),inventoryHashMap));
        inventoryHashMap.put(44,inventory2);
        assertEquals(inventory2,game.humanLookForInventory(human2.getPosition(),inventoryHashMap));
        inventoryHashMap.put(25,inventory3);
        assertEquals(null,game.humanLookForInventory(human4.getPosition(),inventoryHashMap));

    }

    @Test
    void getHumans(){
        int[][] position = {{7,7},{4,4},{2,5},{6,8},{5,4},{1,4},{2,3},{3,2}};
        assertEquals(human2.getPosition()[1],game.getHumans(4,position)[1].getPosition()[1]);
        assertEquals(human1.getPosition()[0],game.getHumans(4,position)[0].getPosition()[0]);
    }

    @Test
    void combat(){
        land = new Land(10,10);
        HashMap<Integer,TreasureChest> treasureChestHashMap = new HashMap<>();
        assertTrue(treasureChestHashMap.isEmpty());
        assertFalse(game.combat(human4,goblin2,land,treasureChestHashMap).isEmpty());
    }

    @Test
    void generateTreasureChest(){
        int[] gridSize = {10,10};
        HashMap<Integer,TreasureChest> treasureChestHashMap = new HashMap<>();
        assertTrue(treasureChestHashMap.isEmpty());
        assertFalse(game.generateTreasureChest(treasureChestHashMap,gridSize).isEmpty());
    }

    @Test
    void noOfHumans(){
        Human[] humans = {human1, human2, human3, human4};
        assertEquals(4, game.noOfHumans(humans));
        human4.setHealth(0);
        assertEquals(3,game.noOfHumans(humans));
    }


    @Test
    void noOfGoblins(){
        Goblin[] goblins = {goblin1,goblin2,goblin3,goblin4};
        assertEquals(4, game.noOfGoblins(goblins));
        goblin1.setHealth(0);
        assertEquals(3,game.noOfGoblins(goblins));
    }

    @Test
    void correctInput() {
        assertTrue(game.correctInput("String", "Grenade","Grenade","Gun"));
        assertTrue(game.correctInput("int", "5","0","10"));
        assertFalse(game.correctInput("String", "Apple","Grenade","Gun"));
        assertFalse(game.correctInput("int", "15","0","10"));
        assertFalse(game.correctInput("int", "-1","0","10"));
    }

    @AfterEach
    void tearDown(){

    }
}