
package antGameTesting;

import antgameproject.Ant;
import antgameproject.Colour;
import antgameproject.Pos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Team18
 * Test class to test the ant class.
 */
public class AntJUnitTest {
    
    // Test creation of an ant.
    @Test
    public void testAntCreation(){
        Pos testAntPosition = new Pos(2,2);
        Colour testAntColour = Colour.RED;
        int initialBrainState = 0;
        int initialFacingDirection = 0;
        Ant testAnt = new Ant(testAntColour, 1, testAntPosition);
        
        assertTrue(testAnt.getFacingDirection() == initialFacingDirection);
        assertTrue(testAnt.getBoardPosition() == testAntPosition);
        assertTrue(testAnt.getAntIsAlive());
        assertTrue(testAnt.getCurrentBrainState() == initialBrainState);
        assertTrue(testAnt.getAntColour() == testAntColour);
        
        assertFalse(testAnt.getCarryingFood());
        assertFalse(testAnt.antIsResting()); 
    }
    
    // Test creation of a black ant.
    @Test
    public void testBlackAntCreation(){
        Pos testAntPosition = new Pos(3,18);
        Colour testAntColour = Colour.BLACK;
        Ant testAnt = new Ant(testAntColour, 2, testAntPosition);
        assertTrue(testAnt.getAntColour() == testAntColour);       
    }
    
    /* the rest of the ant methods are getters and setters and hold no program logic 
    * so were not tested. The general behaviour of the ant is tested through the board
    * and game tests
    */ 
}
