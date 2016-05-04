package antGameTesting;

import antgameproject.Pos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Pos class.
 * @author Team18
 */
public class PosJUnitTest {

    /*
     * Create an instance on the Pos class and test the accessor methods.
     */
    @Test
    public void testPosCreation() {
        int x = 3;
        int y = 6;
        Pos testPos = new Pos(x, y);
        assertTrue(testPos.getPosX() == x);
        assertTrue(testPos.getPosY() == y);
    }
}
