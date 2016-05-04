package antGameTesting;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Pos;
import antgameproject.Terrain;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Board class.
 *
 * @author Team18
 */
public class BoardJUnitTest {

    private Board testBoard;
    private Pos[] redBasePositions = {new Pos(3, 2), new Pos(4, 2), new Pos(2, 3), new Pos(3, 3),
        new Pos(4, 3), new Pos(3, 4), new Pos(4, 4)};
    private Pos[] blackBasePositions = {new Pos(13, 10), new Pos(14, 10), new Pos(12, 11),
        new Pos(13, 11), new Pos(14, 11), new Pos(13, 12), new Pos(14, 12)};

    
    // Create a new board for testing
    @Before
    public void setUp() {
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

        // Add anthills
        for (int i = 0; i < redBasePositions.length; i++) {
            board[redBasePositions[i].getPosY()][redBasePositions[i].getPosX()]
                    = new BoardTile(0, Terrain.REDBASE);
            board[blackBasePositions[i].getPosY()][blackBasePositions[i].getPosX()]
                    = new BoardTile(0, Terrain.BLACKBASE);
        }

        board[16][16] = new BoardTile(5, Terrain.GRASS);

        testBoard = new Board(board, "Board 3");
        //testBoard.printBoardToASCII();
    }

    // Test that a board is created correctly
    @Test
    public void testBoardCreation() {
        assertTrue(testBoard.getNumberOfAntsAlive(Colour.RED) == redBasePositions.length);
        assertTrue(testBoard.getNumberOfAntsAlive(Colour.BLACK) == blackBasePositions.length);

        for (int i = 0; i < redBasePositions.length; i++) {
            assertTrue(testBoard.getTerrainAtPosition(redBasePositions[i]) == Terrain.REDBASE);
            assertTrue(testBoard.getTerrainAtPosition(blackBasePositions[i]) == Terrain.BLACKBASE);
            assertTrue(testBoard.antAt(redBasePositions[i]).getAntColour() == Colour.RED);
            assertTrue(testBoard.antAt(blackBasePositions[i]).getAntColour() == Colour.BLACK);
        }

    }

    // Test that an ant is at a certain cell
    @Test
    public void testAntInPosition() {
        assertTrue(testBoard.antInPosition(redBasePositions[0]));
    }

    // Test that ant can be placed and that it is in the right position
    @Test
    public void testSetAntAtAndAntAt() {
        Pos antPos = new Pos(9, 9);
        int antId = 22;
        Ant placedAnt = new Ant(Colour.BLACK, antId, antPos);
        testBoard.setAntAt(antPos, placedAnt);
        assertTrue(testBoard.antAt(antPos) == placedAnt);
    }

    // Test that ant is in right position
    @Test
    public void testAntPosition() {
        assertTrue(testBoard.getAntPosition(0).getPosX() == redBasePositions[0].getPosX());
        assertTrue(testBoard.getAntPosition(0).getPosY() == redBasePositions[0].getPosY());
    }

    // Test that ant can be removed from position
    @Test
    public void testClearAntAt() {
        Ant antInPositionToBeCleared = testBoard.antAt(blackBasePositions[0]);
        testBoard.clearAntAt(blackBasePositions[0]);
        assertFalse(testBoard.antInPosition(blackBasePositions[0]));
        assertTrue(antInPositionToBeCleared.getBoardPosition() == null);
    }

    // Test that ant can be killed at a position
    @Test
    public void testKillAntAt() {
        Ant antToKill = testBoard.antAt(blackBasePositions[0]);
        testBoard.killAntAt(blackBasePositions[0]);
        assertFalse(antToKill.getAntIsAlive());
        assertTrue(antToKill.getBoardPosition() == null);
        assertTrue(testBoard.getNumberOfAntsAlive(Colour.BLACK) == blackBasePositions.length - 1);
        assertFalse(testBoard.antInPosition(blackBasePositions[0]));
    }
    
    // Test that food is correctly set at position
    @Test
    public void testSetFoodAt() {
        Pos pos = new Pos(16, 16);
        testBoard.setFoodAt(pos, 5);
        assertTrue(testBoard.numberOfFoodAt(pos) == 5);
        assertFalse(testBoard.numberOfFoodAt(pos) == 7);
    }
   
    // Test that number of food can be retrieved given base colour
    @Test
    public void testNumberOfFoodInBase() {
        Pos pos = new Pos(3, 2);
        testBoard.setFoodAt(pos, 2);
        assertTrue(testBoard.getNumberOfFoodInBase(Colour.RED) == 2);
    }
    
    // Test that anthill is at certain position
    @Test
    public void testAnthillAt() {
        Pos pos = new Pos(3, 2);
        assertTrue(testBoard.anthillAt(pos, Colour.RED));
        pos = new Pos(13, 10);
        assertTrue(testBoard.anthillAt(pos, Colour.BLACK));
    }
    
    // Test that terrain can be checked at any position
    @Test
    public void testGetTerrainAtPosition() {
        Pos pos = new Pos(0, 0);
        assertTrue(testBoard.getTerrainAtPosition(pos) == Terrain.ROCK);
        pos = new Pos(16, 16);
        assertTrue(testBoard.getTerrainAtPosition(pos) == Terrain.GRASS);
    }
}
