import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {
    Human human1;
    Human human2;
    Human human3;
    Goblin goblin;
    Weapon weapon;
    HashMap<String,Integer> weaponHashMap = new HashMap<>();


    @BeforeEach
    void setUp() {
        human1 = new Human(1,2, 10);
        human2 = new Human(2,4,15);
        human3 = new Human(6,5,50,17);
        goblin = new Goblin(2,5,10);
        weapon = new Weapon("Grenade",10);
        weaponHashMap.put(weapon.getName(), weapon.getStrength());
    }

    @Test
    void humanAttackGoblin() {
        assertEquals(90, human2.attackGoblin(goblin).getHealth(),"The goblin is weaker so should loose health");
        assertEquals(90,human3.attackGoblin(goblin).getHealth(),"The human is weaker so the goblin does not loose health");
    }

    @Test
    void getPosition() {
        assertEquals(1, human1.getPosition()[0],"This is the X coordinate should be 1");
        assertEquals(4, human2.getPosition()[1],"This is the Y coordinate should be 4");
        assertEquals(6, human3.getPosition()[0],"This is the X coordinate should be 6");
    }

    @Test
    void isDead() {
        assertTrue(!human1.isDead());
        human1.setHealth(-10);
        assertTrue(human1.isDead());
        assertFalse(human2.isDead());
        human2.setHealth(0);
        assertTrue(human2.isDead());
        assertFalse(human3.isDead());
        human3.setHealth(1);
        assertFalse(human3.isDead());
    }

    @Test
    void testToString() {
        assertEquals("Human - "+10+"\nHealth - "+110+"\n Position (x,y)  - ("+1+","+2+")", human1.toString(),"This cant be wrong");
        assertEquals("Human - "+17+"\nHealth - "+50+"\n Position (x,y)  - ("+6+","+5+")", human3.toString(),"This cant be wrong");
    }

    @Test
    void getHealth() {
        assertEquals(110,human1.getHealth(),"The correct health is 100");
        human1.setHealth(10);
        assertEquals(10,human1.getHealth(),"The correct health is 10");
        human1.setHealth(0);
        assertEquals(0,human1.getHealth(),"The correct health is 0 Human is dead");
        human1.setHealth(100);
        assertEquals(0,human1.getHealth(),"The human is dead so no health will bring him back");
    }

    @Test
    void getPoints(){
        assertEquals(0,human1.getPoints(), "There are no points for this human1");
        human1.setPoints(10);
        assertEquals(10,human1.getPoints(), "The points for this human1 has been changed to 10");
        human1.setPoints(-5);
        assertEquals(10,human1.getPoints(), "The points cannot be negative");

    }

    @Test
    void getWeaponsInStock(){

        assertEquals("{}", human1.getWeaponInStock().toString(),"The stock is empty");
        human1.takeWeapons(weaponHashMap);
        assertEquals("{"+weapon.getName()+ "=" + weapon.getStrength() + "}",human1.getWeaponInStock().toString(),"Human1 took teh weapon");
    }

    @Test
    void hasWeaponInStock(){
        assertFalse(human2.hasWeaponInStock());
        human2.takeWeapons(weaponHashMap);
        assertTrue(human2.hasWeaponInStock());
        human2.useWeapon(weapon.getName());
        assertFalse(human2.hasWeaponInStock());
    }

    @Test
    void hasWeaponInHand(){
        assertFalse(human1.hasWeaponInHand());
        human1.takeWeapons(weaponHashMap);
        human1.useWeapon(weapon.getName());
        assertTrue(human1.hasWeaponInHand());
    }


    @AfterEach
    void tearDown() {
    }
}