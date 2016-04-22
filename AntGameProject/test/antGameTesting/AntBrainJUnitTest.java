
package antGameTesting;

import antgameproject.AntBrain;
import instructions.Instruction;
import instructions.Mark;
import instructions.Move;
import instructions.Turn;
import instructions.TurnDirection;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for testing the AntBrain class.
 */
public class AntBrainJUnitTest {
    
    private AntBrain testAntBrain;
    private String name;
    private List<Instruction> brain;
    
    @Before
    public void setUp() {
        brain = new ArrayList<>();
        brain.add(new Move(1, 2));
        brain.add(new Mark(3, 2));
        brain.add(new Turn(TurnDirection.LEFT, 0));
        name = "testAntBrain";
        testAntBrain = new AntBrain(brain, name);
    }
    
 
    // Test creation of the ant brain and the variables in initializeses.
    @Test
    public void testAntBrainCreation(){
        assertTrue(testAntBrain.toString() == name);
        for(int i = 0; i < 3; i++){
            assertTrue(brain.get(i) == testAntBrain.getInstruction(i));
        }
        assertTrue(testAntBrain.getPoints() == 0);
        assertTrue(testAntBrain.getGamesPlayedIn() == 0);
        assertTrue(testAntBrain.getGamesWon() == 0);
        assertTrue(testAntBrain.getGamesDrawn() == 0);
        assertTrue(testAntBrain.getGamesLost() == 0);
        assertTrue(testAntBrain.getTotalFoodInBase() == 0);
        assertTrue(testAntBrain.getTotalFoodInEnemyBase() == 0);
        
    }
    
    // Test the score values are updated correctly. 
    @Test
    public void testUpdatingAntBrainScores(){
        int foodInBaseToSet = 16;
        int foodInEnemyBase = 22;
        int pointsToSet = 2;
        testAntBrain.incrementGamesPlayedIn();
        testAntBrain.incrementGamesWon();
        testAntBrain.incrementGamesDrawn();
        testAntBrain.incrementGamesLost();
        testAntBrain.setTotalFoodInBase(foodInBaseToSet);
        testAntBrain.setTotalFoodInEnemyBase(foodInEnemyBase);
        testAntBrain.setPoints(pointsToSet);
        
        assertTrue(testAntBrain.getPoints() == pointsToSet);
        assertTrue(testAntBrain.getGamesPlayedIn() == 1);
        assertTrue(testAntBrain.getGamesWon() == 1);
        assertTrue(testAntBrain.getGamesDrawn() == 1);
        assertTrue(testAntBrain.getGamesLost() == 1);
        assertTrue(testAntBrain.getTotalFoodInBase() == foodInBaseToSet);
        assertTrue(testAntBrain.getTotalFoodInEnemyBase() == foodInEnemyBase);
        
    }
}
