import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {
    Human human;

    @BeforeEach
    void setUp() {
        human = new Human(1,2);
    }

    @Test
    void humanAttackGoblin() {
    }

    @Test
    void getPosition() {
        assertEquals(1, human.getPosition()[0],"This is the X coordinate");
        assertEquals(1, human.getPosition()[0],"This is the ");
    }

    @Test
    void isDead() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getHealth() {
        assertEquals(100,human.getHealth(),"The correct health is 100");
        human.setHealth(10);
        assertEquals(10,human.getHealth(),"The correct health is 10");
        human.setHealth(0);
        assertEquals(0,human.getHealth(),"The correct health is 0");
        human.setHealth(100);
        assertEquals(0,human.getHealth(),"The correct health is 0");
    }

    @AfterEach
    void tearDown() {
    }
}