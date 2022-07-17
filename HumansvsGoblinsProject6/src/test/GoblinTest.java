import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.tools.FileObject;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {

    Goblin goblin1;
    Goblin goblin2;
    Goblin goblin3;
    Human[] humans = new Human[5];
    Land land = new Land(10,10);

    @BeforeEach
    void setUp() {
        goblin1 = new Goblin(1,3,10);
        goblin2 = new Goblin(4,7,1);
        goblin3 = new Goblin(0,0,4,50);
        humans[0]   = new Human(4,8,70,1);
        humans[1]   = new Human(0,1,70,2);
        humans[2]   = new Human(9,9,70,3);
        humans[3]   = new Human(9,8,70,4);
        humans[4]   = new Human(5,5,70,5);
    }

    @Test
    void getiD() {
        assertEquals(10,goblin1.getiD(),"The Id should be 10");
        assertEquals(1, goblin2.getiD(),"The Id should be 1");
        assertEquals(4, goblin3.getiD(),"The Id should be 4");
    }

    @Test
    void attackHuman() {
        assertEquals(60, goblin2.attackHuman(humans[0]).getHealth(),"The human is weaker so should loose health");
        assertEquals(55,goblin3.attackHuman(humans[0]).getHealth(),"The goblin is weaker so the goblin does not loose health");
    }

    @Test
    void getPosition() {
        int [] arr = {10,10};
        assertEquals(1, goblin1.getPosition()[0],"The X coordinate is 1");
        assertEquals(7, goblin2.getPosition()[1],"The Y coordinate is 7");
        goblin2.setPosition(3,3, arr);
        assertEquals(3, goblin2.getPosition()[1],"The Y coordinate is 3");
        assertEquals(0, goblin3.getPosition()[0],"THe X coordinate is 0");
    }

    @Test
    void getHealth() {
        assertEquals(100, goblin1.getHealth(), "The goblin health is 100");
        goblin1.setHealth(40);
        assertEquals(40, goblin1.getHealth(), "Now the goblin health is 40");
        assertEquals(100, goblin2.getHealth(),"The goblin health is 100");
        goblin2.setHealth(0);
        assertEquals(0, goblin2.getHealth(), "Now the goblin health is 0");
        assertEquals(50, goblin3.getHealth(), "The goblin health is 100");
    }

    @Test
    void isDead() {
        assertFalse(goblin1.isDead());
        goblin1.setHealth(0);
        assertTrue(goblin1.isDead());
        assertFalse(goblin2.isDead());
        goblin2.setHealth(-10);
        assertTrue(goblin2.isDead());
        assertFalse(goblin3.isDead());
        goblin3.setHealth(1);
        assertFalse(goblin3.isDead());
    }

    @Test
    void getPrey() {
        assertEquals(2,goblin3.getPrey(humans, land).getiD(),"THis is the 2nd human for the 3rd goblin");
        assertEquals(1,goblin2.getPrey(humans, land).getiD(),"THis is the 2nd human for the 3rd goblin");
    }

    @Test
    void getDistance() {
        assertEquals(1, goblin3.getDistance(humans[1].getPosition(), goblin3.getPosition()),"This should be 1");
        assertEquals(12, goblin3.getDistance(humans[2].getPosition(), goblin3.getPosition()),"This should be 1");
    }

    @Test
    void takeDrops(){
        assertEquals(100, goblin1.getHealth());
        goblin1.takeDrops(10);
        assertEquals(200,goblin1.getHealth());
    }

    @AfterEach
    void tearDown() {
    }

}