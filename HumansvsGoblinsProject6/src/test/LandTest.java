import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LandTest {
    Land land1;
    Land land2;
    Land land3;
    @BeforeEach
    void setUp() {
        land1 = new Land(10,7);
        land2 = new Land(5,6);
    }

    @Test
    void getGridMatrix() {
        land2.createGridMatrix();
        for(int  i = 0; i < land2.getGridSize()[0]; i++){
            for( int j = 0; j < land2.getGridSize()[1]; j++){
                assertEquals(0,land2.getGridMatrix()[i][j]);
            }
        }
    }

    @Test
    void getGridSize() {
        assertEquals(10, land1.getGridSize()[0]);
        assertEquals(7, land1.getGridSize()[1]);
        assertEquals(5, land2.getGridSize()[0]);
        assertEquals(6, land2.getGridSize()[1]);
    }
    
    @AfterEach
    void tearDown() {
    }
}