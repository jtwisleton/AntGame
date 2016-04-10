package antGameTesting;


import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Mark;
import antgameproject.Pos;
import antgameproject.Terrain;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jw478
 */
public class MarkJUnitTest {
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
        
        testBoard = new Board(board);
    }
    
    @Test 
    public void testBadCreation(){
        int markValueTooBig = 6;
        int markValueTooSmall = -1;
        try{
            Mark testMark = new Mark(markValueTooBig, 3);
        } catch (AssertionError e) {
            assertTrue(true);
        }
        
        try{
            Mark testMark = new Mark(markValueTooSmall, 3);
        } catch (AssertionError e) {
            assertTrue(true);
        }  
        
    }

    @Test
    public void testBoardUpdated(){
        int markValue = 2;
        int nextState = 5;
        Mark testMark = new Mark(markValue, nextState);
        
        assertFalse(testBoard.checkMarker(testAnt.getBoardPosition(), testAnt.getAntColour(),
                markValue));
        testMark.execute(testBoard, testAnt);
        assertTrue(testBoard.checkMarker(testAnt.getBoardPosition(), testAnt.getAntColour(),
                markValue)); 
    }
    
    @Test
    public void testAntStateUpdated(){
        int markValue = 2;
        int nextState = 5;
        Mark testMark = new Mark(markValue, nextState);
        assertTrue(testAnt.getCurrentBrainState() == 0);
        testMark.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextState);
    }
}
