
package antGameTesting;

import antgameproject.AntBrain;
import instructions.Instruction;
import instructions.Mark;
import instructions.Move;
import instructions.Turn;
import instructions.TurnDirection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for testing the AntBrain class.
 */
public class AntBrainJUnitTest {
    
    private AntBrain testAntBrain;
    private AntBrain testAntBrain2;
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
        testAntBrain2 = new AntBrain(brain, "testAntBrain2");
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
        
        testAntBrain.incrementGamesPlayedIn();
        testAntBrain.incrementGamesWon();
        testAntBrain.incrementGamesDrawn();
        testAntBrain.incrementGamesLost();
        testAntBrain.setTotalFoodInBase(foodInBaseToSet);
        testAntBrain.setTotalFoodInEnemyBase(foodInEnemyBase);
        
        assertTrue(testAntBrain.getPoints() == 3);  // 1 win and a draw
        assertTrue(testAntBrain.getGamesPlayedIn() == 1);
        assertTrue(testAntBrain.getGamesWon() == 1);
        assertTrue(testAntBrain.getGamesDrawn() == 1);
        assertTrue(testAntBrain.getGamesLost() == 1);
        assertTrue(testAntBrain.getTotalFoodInBase() == foodInBaseToSet);
        assertTrue(testAntBrain.getTotalFoodInEnemyBase() == foodInEnemyBase);
        
    }
    
    // Tests the comparable method that orders ants
    @Test
    public void testOrderAntBrains(){
        List<AntBrain> brainList = new ArrayList();
        brainList.add(testAntBrain);
        brainList.add(testAntBrain2);
        assertTrue(brainList.get(0) == testAntBrain);
        assertTrue(brainList.get(1) == testAntBrain2);
        
        // testAntBrain2 wins a game so should lead
        testAntBrain2.incrementGamesWon();
        Collections.sort(brainList);
        assertTrue(brainList.get(0) == testAntBrain2);
        assertTrue(brainList.get(1) == testAntBrain);
        
        // testantBrain wins a game however they have even points and no food in base has 
        // been recorded
        testAntBrain.incrementGamesWon();
        Collections.sort(brainList);
        assertTrue(brainList.get(0) == testAntBrain2);
        assertTrue(brainList.get(1) == testAntBrain);
        
        // testAntBrain now has some food in the bas so should now lead
        testAntBrain.setTotalFoodInBase(10);
        Collections.sort(brainList);
        assertTrue(brainList.get(0) == testAntBrain);
        assertTrue(brainList.get(1) == testAntBrain2);
    }
}
