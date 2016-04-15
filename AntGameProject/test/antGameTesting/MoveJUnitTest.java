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
import instructions.Move;
import antgameproject.Pos;
import antgameproject.Terrain;
import instructions.Turn;
import instructions.TurnDirection;
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
    private int nextStateIfAheadClear;
    private int nextStateIfAheadBlocked;
    
    @Before
    public void setUp() {
        nextStateIfAheadClear = 22;
        nextStateIfAheadBlocked = 9;
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
        board[17][5] = new BoardTile(0, Terrain.REDBASE);
        board[17][4] = new BoardTile(0, Terrain.REDBASE);        
        testBoard = new Board(board, "Board 5");
        testBoard.setAntAt(new Pos(2, 2), testAnt);
        testBoard.setAntAt(new Pos(5, 5), testOddYAnt);
    }
    
    // test a clear move from an even y position in all directions
    @Test
    public void testNormalMoveFromEvenY(){
        Pos[] expectedPositions = {new Pos(3,2), new Pos(2,3), new Pos(1,3), new Pos(1,2), 
            new Pos(1,1), new Pos(2,1)};
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
    
    // test a clear move from an odd y position in all directions
    @Test
    public void testNormalMoveFromOddY(){
        Pos[] expectedPositions = {new Pos(6,5), new Pos(6,6), new Pos(5,6), new Pos(4,5), 
            new Pos(5,4), new Pos(6,4)};
        Move testMove = new Move(nextStateIfAheadClear, nextStateIfAheadBlocked);
                
        for(int i = 0; i < 6; i++){
            testBoard.setAntAt(new Pos(5,5), testOddYAnt);
            testOddYAnt.setFacingDirection(0);
            for(int j = 0; j < i; j++){
                new Turn(TurnDirection.RIGHT, 1).execute(testBoard, testOddYAnt);
            }
            testMove.execute(testBoard, testOddYAnt);
            assertTrue(testOddYAnt.getBoardPosition().getPosX() == expectedPositions[i].getPosX()
                    && testOddYAnt.getBoardPosition().getPosY() == expectedPositions[i].getPosY()); 
            assertTrue(testBoard.antInPosition(expectedPositions[i])); 
            assertFalse(testBoard.antInPosition(new Pos(5,5)));
        }
    }
    
    // test rest value is reducing
    @Test
    public void testResting(){
        Move testMove = new Move(nextStateIfAheadClear, nextStateIfAheadBlocked);
        testMove.execute(testBoard, testAnt);
        for(int i = 0; i < 14; i++){
            assertTrue(testAnt.antIsResting());
            testAnt.decreaseAntResting();        
        }
        assertFalse(testAnt.antIsResting());
    }
    
    // test blocked by rock
    @Test
    public void antBlockedByRock(){
        Pos antPositionBeforeMove = new Pos(1, 1);
        Ant blockedAnt = new Ant(Colour.RED, 3, antPositionBeforeMove);
        blockedAnt.setFacingDirection(3); // set ant to face a rock
        testBoard.setAntAt(antPositionBeforeMove, blockedAnt);
        testBoard.printBoardToASCII();
        new Move(nextStateIfAheadClear, nextStateIfAheadBlocked).execute(testBoard, 
                blockedAnt);
        assertTrue(blockedAnt.getFacingDirection() == 3);
        assertTrue(blockedAnt.getBoardPosition() == antPositionBeforeMove);
        assertTrue(blockedAnt.getCurrentBrainState() == nextStateIfAheadBlocked);
        assertTrue(testBoard.antAt(antPositionBeforeMove) == blockedAnt);
    }
    
    // test blocked by ant
    @Test
    public void antBlockedByAnt(){
        Pos antPositionBeforeMove = new Pos(1, 1);
        Ant blockedAnt = new Ant(Colour.RED, 3, antPositionBeforeMove);
        blockedAnt.setFacingDirection(1); // set ant to face an ant
        testBoard.setAntAt(antPositionBeforeMove, blockedAnt);
        testBoard.printBoardToASCII();
        new Move(nextStateIfAheadClear, nextStateIfAheadBlocked).execute(testBoard, 
                blockedAnt);
        assertTrue(blockedAnt.getFacingDirection() == 1);
        assertTrue(blockedAnt.getBoardPosition() == antPositionBeforeMove);
        assertTrue(blockedAnt.getCurrentBrainState() == nextStateIfAheadBlocked);
        assertTrue(testBoard.antAt(antPositionBeforeMove) == blockedAnt);
    }
    
    // test moving into surrounded
    @Test
    public void testAntSurroundedAfterMove(){
        Pos antsNewPosition = new Pos(3, 2);
        Pos[] killingAntPositions = {new Pos(2,1), new Pos(3,1), new Pos(4,2), 
            new Pos(2,3), new Pos(3,3)};
        for(int i = 0; i < killingAntPositions.length; i++){
            Ant killingAnt = new Ant(Colour.BLACK, i + 3, killingAntPositions[i]);
            testBoard.setAntAt(killingAntPositions[i], killingAnt);
        }
        assertTrue(testAnt.getAntIsAlive());
        assertTrue(testBoard.numberOfFoodAt(testAnt.getBoardPosition()) == 0);
        new Move(nextStateIfAheadClear, nextStateIfAheadBlocked).execute(testBoard, testAnt);
        assertFalse(testAnt.getAntIsAlive());
        assertFalse(testBoard.antInPosition(antsNewPosition)); 
        assertTrue(testBoard.numberOfFoodAt(antsNewPosition) == 3);   
    }
    
    // test moving into surrounded carrying food
    @Test
    public void testAntSurroundedAfterMoveCarryingFood(){
        Pos antsNewPosition = new Pos(3, 2);
        testAnt.setCarryingFood(true);
        Pos[] killingAntPositions = {new Pos(2,1), new Pos(3,1), new Pos(4,2), 
            new Pos(2,3), new Pos(3,3)};
        for(int i = 0; i < killingAntPositions.length; i++){
            Ant killingAnt = new Ant(Colour.BLACK, i + 3, killingAntPositions[i]);
            testBoard.setAntAt(killingAntPositions[i], killingAnt);
        }
        assertTrue(testAnt.getAntIsAlive());
        assertTrue(testBoard.numberOfFoodAt(testAnt.getBoardPosition()) == 0);
        new Move(nextStateIfAheadClear, nextStateIfAheadBlocked).execute(testBoard, testAnt);
        assertFalse(testAnt.getAntIsAlive());
        assertFalse(testBoard.antInPosition(antsNewPosition)); 
        assertTrue(testBoard.numberOfFoodAt(antsNewPosition) == 4);   
    }
    
    
    // surrounded ant by moving
    @Test 
    public void testAntSurroundingWithMove(){
        Pos antsNewPosition = new Pos(3, 2);
        Pos[] killingAntPositions = {new Pos(1,1), new Pos(3,1), new Pos(4,2), 
            new Pos(2,3), new Pos(3,3)};
        for(int i = 0; i < killingAntPositions.length; i++){
            Ant killingAnt = new Ant(Colour.BLACK, i+3, killingAntPositions[i]);
            testBoard.setAntAt(killingAntPositions[i], killingAnt);    
        }
        new Move(nextStateIfAheadClear, nextStateIfAheadBlocked).execute(testBoard, testAnt);
        assertTrue(testAnt.getAntIsAlive());
        assertTrue(testBoard.numberOfFoodAt(testAnt.getBoardPosition()) == 0);
        Ant antThatMovesToKill = testBoard.antAt(new Pos(1,1));
        new Move(nextStateIfAheadClear, nextStateIfAheadBlocked).execute(testBoard, 
                antThatMovesToKill);
        assertFalse(testAnt.getAntIsAlive());
        assertFalse(testBoard.antInPosition(antsNewPosition)); 
        assertTrue(testBoard.numberOfFoodAt(antsNewPosition) == 3);
    }
}
