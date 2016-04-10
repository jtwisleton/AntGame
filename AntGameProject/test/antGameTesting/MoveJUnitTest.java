/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antGameTesting;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Move;
import antgameproject.Pos;
import antgameproject.Terrain;
import antgameproject.Turn;
import antgameproject.TurnDirection;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author wilki
 */
public class MoveJUnitTest {
    
    private Ant testAnt;
    private Ant testOddYAnt;
    private Board testBoard;
    
    @Before
    public void setUp() {
        testAnt = new Ant(Colour.RED, 1, new Pos(2,2));
        testOddYAnt = new Ant(Colour.BLACK, 2, new Pos(5,5));
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
        testBoard.setAntAt(new Pos(2, 2), testAnt);
        testBoard.setAntAt(new Pos(5, 5), testOddYAnt);
    }
    
    // test clear move from even y
    @Test
    public void testNormalMoveFromEvenY(){
        Pos[] expectedPositions = {new Pos(3,2), new Pos(2,3), new Pos(1,3), new Pos(1,2), 
            new Pos(1,1), new Pos(2,1)};
        int nextStateIfAheadClear = 22;
        int nextStateIfAheadBlocked = 9;
        Move testMove = new Move(nextStateIfAheadClear, nextStateIfAheadBlocked);
                
        for(int i = 0; i < 6; i++){
            testBoard.setAntAt(new Pos(2,2), testAnt);
            testAnt.setFacingDirection(0);
            for(int j = 0; j < i; j++){
                new Turn(TurnDirection.RIGHT, 1).execute(testBoard, testAnt);
            }
            testMove.execute(testBoard, testAnt);
            assertTrue(testAnt.getBoardPosition().getPosX() == expectedPositions[i].getPosX() && 
                testAnt.getBoardPosition().getPosY() == expectedPositions[i].getPosY()); 
            assertTrue(testBoard.antInPosition(expectedPositions[i])); 
            assertFalse(testBoard.antInPosition(new Pos(2,2)));
        }
    }
    
    // test clear move from odd y
    @Test
    public void testNormalMoveFromOddY(){
        Pos[] expectedPositions = {new Pos(6,5), new Pos(6,6), new Pos(5,6), new Pos(4,5), 
            new Pos(5,4), new Pos(6,4)};
        int nextStateIfAheadClear = 22;
        int nextStateIfAheadBlocked = 9;
        Move testMove = new Move(nextStateIfAheadClear, nextStateIfAheadBlocked);
                
        for(int i = 0; i < 6; i++){
            testBoard.setAntAt(new Pos(5,5), testOddYAnt);
            testOddYAnt.setFacingDirection(0);
            for(int j = 0; j < i; j++){
                new Turn(TurnDirection.RIGHT, 1).execute(testBoard, testOddYAnt);
            }
            testMove.execute(testBoard, testOddYAnt);
            assertTrue(testOddYAnt.getBoardPosition().getPosX() == expectedPositions[i].getPosX() && 
                testOddYAnt.getBoardPosition().getPosY() == expectedPositions[i].getPosY()); 
            assertTrue(testBoard.antInPosition(expectedPositions[i])); 
            assertFalse(testBoard.antInPosition(new Pos(5,5)));
        }
    }
    
    // test blocked by rock
    
    // test blocked by ant
    
    // test surrounded
    
    // check if can be surrounded in a corner
}
