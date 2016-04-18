package antGameTesting;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import instructions.Mark;
import antgameproject.Pos;
import antgameproject.RandomNumber;
import antgameproject.Terrain;
import instructions.Unmark;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wilki
 */
public class UnmarkJUnitTest {
    
    private Ant testAnt;
    private Ant testAntBlack;
    private Board testBoard;
    
    @Before
    public void setUp() {
        testAnt = new Ant(Colour.RED, 1, new Pos(2,2));
        testAntBlack = new Ant(Colour.BLACK, 2, new Pos(5,5));
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
        
        testBoard = new Board(board, "Board 8");
    }
    
    // test unmark when mark set
    @Test
    public void testUnmarkWhenMarkSet(){
        int markToSet = 2;
        int markToClear = markToSet;
        int nextState = 13;
        new Mark(markToSet, 13).execute(testBoard, testAnt, new RandomNumber(1));
        assertTrue(testBoard.checkMarker(testAnt.getBoardPosition(), testAnt.getAntColour(),
                markToSet));
        new Unmark(markToClear, 14).execute(testBoard, testAnt, new RandomNumber(1));
        assertFalse(testBoard.checkMarker(testAnt.getBoardPosition(), testAnt.getAntColour(),
                markToClear));
    }
    
    // test unmark when mark not set in board tile
    @Test
    public void testUnmarkWhenMarkNotSet(){
        int markToClear = 3;
        int nextState = 13;
        assertFalse(testBoard.checkMarker(testAnt.getBoardPosition(), testAnt.getAntColour(),
                markToClear));
        new Unmark(markToClear, 14).execute(testBoard, testAnt, new RandomNumber(1));
        assertFalse(testBoard.checkMarker(testAnt.getBoardPosition(), testAnt.getAntColour(),
                markToClear));
    }
    
    // test unmark only affects the given colour
    @Test
    public void testUnmarkAffectsOnlyGivenColour(){
        int redMarkToSet = 3;
        int redMarkToClear = redMarkToSet;
        int blackMarkToSet = 5;
        
        new Mark(redMarkToSet, 5).execute(testBoard, testAnt, new RandomNumber(1));
        new Mark(blackMarkToSet, 18).execute(testBoard, testAntBlack, new RandomNumber(1));
        assertTrue(testBoard.checkMarker(testAnt.getBoardPosition(), testAnt.getAntColour(),
                redMarkToSet));
        assertTrue(testBoard.checkMarker(testAntBlack.getBoardPosition(), testAntBlack.getAntColour(),
                blackMarkToSet));
        new Unmark(redMarkToClear, 29).execute(testBoard, testAnt, new RandomNumber(1));
        assertFalse(testBoard.checkMarker(testAnt.getBoardPosition(), testAnt.getAntColour(),
                redMarkToSet));
        assertTrue(testBoard.checkMarker(testAntBlack.getBoardPosition(), testAntBlack.getAntColour(),
                blackMarkToSet));
    }
    
    // check that the ant has the correct next state set
    @Test
    public void testAntStateUpdated(){
        int markToClearValue = 2;
        int nextState = 23;
        Unmark testUnmark = new Unmark(markToClearValue, nextState);
        assertTrue(testAnt.getCurrentBrainState() == 0);
        testUnmark.execute(testBoard, testAnt, new RandomNumber(1));
        assertTrue(testAnt.getCurrentBrainState() == nextState);
    }
    
}
