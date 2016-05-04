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

    
    @Test
    public void testNumberOfFoodAt() {
        testBoard.numberOfFoodAt(new Pos(16, 16));
    }
    
    /*
                    
     public void setFoodAt(Pos foodPosition, int amountOfFood){
     int oldAmountOfFood = board[foodPosition.getPosY()][foodPosition.getPosX()].getFoodInTile();
     int difference = oldAmountOfFood - amountOfFood;
     if(board[foodPosition.getPosY()][foodPosition.getPosX()].getCellTerrain() ==
     Terrain.BLACKBASE){
     int totalBlackFood = numberOfFoodInBase.get(Colour.BLACK);
     int newTotalFood = totalBlackFood + difference;
     numberOfFoodInBase.put(Colour.BLACK, newTotalFood);
     } else if(board[foodPosition.getPosY()][foodPosition.getPosX()].getCellTerrain() ==
     Terrain.REDBASE){
     int totalRedFood = numberOfFoodInBase.get(Colour.RED);
     int newTotalFood = totalRedFood + difference;
     numberOfFoodInBase.put(Colour.RED, newTotalFood);
     }
     board[foodPosition.getPosY()][foodPosition.getPosX()].setFoodInTile(amountOfFood);
     }
    
     public int getNumberOfAntsAlive(Colour colourOfTeam){
     return numberOfAntsAlive.get(colourOfTeam);
     }
    
     public int getNumberOfFoodInBase(Colour colourOfTeam){
     return numberOfFoodInBase.get(colourOfTeam);
     }
    
     public boolean anthillAt(Pos anthillPos, Colour anthillColour){
     return board[anthillPos.getPosY()][anthillPos.getPosX()].getCellTerrain()
     == colourToBaseMatch.get(anthillColour);
     }
    
     public void setMarker(Pos markerPos, Colour markerCol, int mark){
     board[markerPos.getPosY()][markerPos.getPosX()].setMarker(markerCol, mark); 
     }
    
     public void clearMarker(Pos markerPos, Colour markerCol, int mark){
     if(board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol) != null){
     int markInTile = board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol);
     if(markInTile == mark){
     board[markerPos.getPosY()][markerPos.getPosX()].setMarker(markerCol, null);
     }
     }    
     }
    
     public boolean checkMarker(Pos markerPos, Colour markerCol, int mark){
     if(board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol) == null){
     return false;
     }
     return board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol) == mark;
     }
    
     public boolean checkAnyMarker(Pos markerPos, Colour markerCol){
     return board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol) != null;

     }
    
     public int getNumberOfAnts(){
     return antsOnBoard.size();
     }
    
     public Terrain getTerrainAtPosition(Pos positionOfTerrain){
     return board[positionOfTerrain.getPosY()][positionOfTerrain.getPosX()].getCellTerrain();
     }
    
     public void printBoardToASCII(){
     for(int i = 0; i < board.length; i++){            
     for(int j = 0; j < board[0].length; j++){
     System.out.print(board[i][j]);
     }
     System.out.print("\n");
     }
     }
    
     */
}
