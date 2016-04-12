/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antGameTesting;

import antgameproject.Ant;
import antgameproject.Colour;
import antgameproject.Pos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wilki
 */
public class AntJUnitTest {
    
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
    
    // the rest of the ant methods are getters and setters and hold no program logic 
    // so were not tested.
}
