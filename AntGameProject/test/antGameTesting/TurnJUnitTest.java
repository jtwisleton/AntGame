package antGameTesting;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import antgameproject.RandomNumber;
import instructions.Turn;
import instructions.TurnDirection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Turn class.
 * 
 * @author Team18
 */
public class TurnJUnitTest {

    private Ant testAnt;
    private Board gameBoard;

    @Before
    public void setUp() {
        testAnt = new Ant(Colour.RED, 1, new Pos(2, 2));
    }

    @Test
    public void testTurnLeft() {
        Turn testTurn = new Turn(TurnDirection.LEFT, 26);
        int[] expectedResults = {0, 5, 4, 3, 2, 1, 0, 5};
        for (int i = 0; i < expectedResults.length; i++) {
            assertTrue(testAnt.getFacingDirection() == expectedResults[i]);
            testTurn.execute(gameBoard, testAnt, new RandomNumber(1));
        }
    }

    @Test
    public void testTurnRight() {
        Turn testTurn = new Turn(TurnDirection.RIGHT, 26);
        int[] expectedResults = {0, 1, 2, 3, 4, 5, 0, 1};
        for (int i = 0; i < expectedResults.length; i++) {
            assertTrue(testAnt.getFacingDirection() == expectedResults[i]);
            testTurn.execute(gameBoard, testAnt, new RandomNumber(1));
        }
    }

    @Test
    public void testPositionStaysTheSame() {
        Pos positionBeforeTurn = testAnt.getBoardPosition();
        new Turn(TurnDirection.RIGHT, 45).execute(gameBoard, testAnt, new RandomNumber(1));
        assertTrue(testAnt.getBoardPosition().getPosX() == positionBeforeTurn.getPosX());
        assertTrue(testAnt.getBoardPosition().getPosY() == positionBeforeTurn.getPosY());
    }

    @Test
    public void testAntStateUpdated() {
        int nextState = 99;
        assertTrue(testAnt.getCurrentBrainState() == 0);
        new Turn(TurnDirection.LEFT, nextState).execute(gameBoard, testAnt, new RandomNumber(1));
        assertTrue(testAnt.getCurrentBrainState() == nextState);
    }
}
