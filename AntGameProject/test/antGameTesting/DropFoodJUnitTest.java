package antGameTesting;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import instructions.DropFood;
import antgameproject.Pos;
import antgameproject.RandomNumber;
import antgameproject.Terrain;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Team18
 */
public class DropFoodJUnitTest {
    private Ant testAnt;
    private Board testBoard;
    
    @Before
    public void setUp(){
        testAnt = new Ant(Colour.RED, 1, new Pos(2,2));
        BoardTile[][] board = new BoardTile[20][20]; 
        
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(i == 0 || i == 19 || j == 0 || j == 19){
                    board[i][j] = new BoardTile(0, Terrain.ROCK);
                } else {
                    board[i][j] = new BoardTile(0, Terrain.GRASS);
                }
            }
        }
        board[2][2] = new BoardTile(5, Terrain.GRASS);
        testBoard = new Board(board, "Board 2");
    }
    
    // drop food when ant has food
    @Test
    public void testDropFoodWhenAntHasfood(){
        int nextState = 22;
        testAnt.setCarryingFood(true);
        assertTrue(testBoard.numberOfFoodAt(testAnt.getBoardPosition()) == 5);
        new DropFood(nextState).execute(testBoard, testAnt, new RandomNumber(1));
        assertTrue(testBoard.numberOfFoodAt(testAnt.getBoardPosition()) == 6);
        assertFalse(testAnt.getCarryingFood());
        assertTrue(testAnt.getCurrentBrainState() == nextState);
    }
    
    // drop food when ant doesn't have food
    @Test
    public void testDropFoodWhenAntNoFood(){
        int nextState = 22;
        assertTrue(testBoard.numberOfFoodAt(testAnt.getBoardPosition()) == 5);
        new DropFood(nextState).execute(testBoard, testAnt, new RandomNumber(1));
        assertTrue(testBoard.numberOfFoodAt(testAnt.getBoardPosition()) == 5);
        assertFalse(testAnt.getCarryingFood());
        assertTrue(testAnt.getCurrentBrainState() == nextState);
    }
}
