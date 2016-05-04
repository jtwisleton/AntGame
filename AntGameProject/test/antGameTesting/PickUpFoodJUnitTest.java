package antGameTesting;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import instructions.PickUpFood;
import antgameproject.Pos;
import antgameproject.RandomNumber;
import antgameproject.Terrain;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for PickUpFood class.
 *
 * @author Team18
 */
public class PickUpFoodJUnitTest {

    private Ant antOnTileWithFood;
    private Ant antOnTileWithoutFood;
    private Board testBoard;
    private RandomNumber randomNumberGen;

    // Create an ant and board for testing
    @Before
    public void setUp() {
        antOnTileWithFood = new Ant(Colour.RED, 1, new Pos(2, 2));
        antOnTileWithoutFood = new Ant(Colour.BLACK, 2, new Pos(3, 3));
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
        board[3][3] = new BoardTile(0, Terrain.GRASS);
        testBoard = new Board(board, "Board 6");
        randomNumberGen = new RandomNumber(1);
    }

    // Test that food can be found on the board correctly
    @Test
    public void testFoodFound() {
        int nextStateIfFoodFound = 12;
        int nextStateIfNoFood = 23;
        Pos antPosition = antOnTileWithFood.getBoardPosition();
        assertTrue(testBoard.numberOfFoodAt(antPosition) == 5);
        assertFalse(antOnTileWithFood.getCarryingFood());
        new PickUpFood(nextStateIfFoodFound, nextStateIfNoFood).execute(testBoard, antOnTileWithFood, randomNumberGen);
        assertTrue(testBoard.numberOfFoodAt(antPosition) == 4);
        assertTrue(antOnTileWithFood.getCarryingFood());
        assertTrue(antOnTileWithFood.getCurrentBrainState() == nextStateIfFoodFound);
    }

    // Test that food is not found where it shouldn't be
    @Test
    public void testFoodNotFound() {
        int nextStateIfFoodFound = 12;
        int nextStateIfNoFood = 23;
        Pos antPosition = antOnTileWithoutFood.getBoardPosition();
        assertTrue(testBoard.numberOfFoodAt(antPosition) == 0);
        assertFalse(antOnTileWithoutFood.getCarryingFood());
        new PickUpFood(nextStateIfFoodFound, nextStateIfNoFood).execute(testBoard,
                antOnTileWithoutFood, randomNumberGen);
        assertTrue(testBoard.numberOfFoodAt(antPosition) == 0);
        assertFalse(antOnTileWithoutFood.getCarryingFood());
        assertTrue(antOnTileWithoutFood.getCurrentBrainState() == nextStateIfNoFood);
    }

    // Test ant already carrying food
    @Test
    public void testAntAlreadyCarryingFood() {
        int nextStateIfFoodFound = 12;
        int nextStateIfNoFood = 23;
        PickUpFood testPickUpFood = new PickUpFood(nextStateIfFoodFound, nextStateIfNoFood);
        Pos antPosition = antOnTileWithFood.getBoardPosition();
        testPickUpFood.execute(testBoard, antOnTileWithFood, randomNumberGen);
        assertTrue(testBoard.numberOfFoodAt(antPosition) == 4);
        assertTrue(antOnTileWithFood.getCarryingFood());
        assertTrue(antOnTileWithFood.getCurrentBrainState() == nextStateIfFoodFound);
        testPickUpFood.execute(testBoard, antOnTileWithFood, randomNumberGen);
        assertTrue(testBoard.numberOfFoodAt(antPosition) == 4);
        assertTrue(antOnTileWithFood.getCarryingFood());
        assertTrue(antOnTileWithFood.getCurrentBrainState() == nextStateIfNoFood);
    }

}
