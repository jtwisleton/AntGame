package antGameTesting;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Pos;
import antgameproject.RandomNumber;
import antgameproject.Terrain;
import instructions.Flip;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Team18
 *
 * Test class for the Flip instruction.
 */
public class FlipJUnitTest {

    private Ant testAnt;
    private Board testBoard;

    // Set up a board and ant to test the instruction on.
    @Before
    public void setUp() {
        testAnt = new Ant(Colour.RED, 1, new Pos(2, 2));
        BoardTile[][] board = new BoardTile[20][20];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == 0 || i == 19 || j == 0 || j == 19) {
                    board[i][j] = new BoardTile(0, Terrain.ROCK);
                } else {
                    board[i][j] = new BoardTile(0, Terrain.GRASS);
                }
            }
        }
        board[2][2] = new BoardTile(5, Terrain.GRASS);
        testBoard = new Board(board, "Board 2");
    }

    // Tests the flip instruction sets the correctstate for the random number generator
    // giving a vlaue of greater than zero and zero.
    @Test
    public void testFlip() {
        Flip testFlip = new Flip(2, 3, 4);
        RandomNumber randomGen = new RandomNumber(12345);
        // first call to random gen with seed 12345 and argument 2 gives 1 second call 0
        testFlip.execute(testBoard, testAnt, randomGen);
        assertTrue(testAnt.getCurrentBrainState() == 4);
        testFlip.execute(testBoard, testAnt, randomGen);
        assertTrue(testAnt.getCurrentBrainState() == 3);
    }
}
