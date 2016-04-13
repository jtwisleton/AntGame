/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antGameTesting;

import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Terrain;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wilki
 */
public class BoardTileJUnitTest {
    
    private BoardTile rockTile;
    private BoardTile grassTile;
    private BoardTile blackBaseTile;
    private BoardTile redBaseTile;
    private int foodOnGrassTile;
    
    @Before
    public void setUp() {
        foodOnGrassTile = 5;
        rockTile = new BoardTile(0, Terrain.ROCK);
        grassTile = new BoardTile(5, Terrain.GRASS);
        blackBaseTile = new BoardTile(0, Terrain.BLACKBASE);
        redBaseTile = new BoardTile(0, Terrain.REDBASE);
    }
    
    @Test
    public void testBoardTileCreation(){
        assertTrue(grassTile.getCellTerrain() == Terrain.GRASS);
        assertTrue(rockTile.getCellTerrain() == Terrain.ROCK);
        assertTrue(blackBaseTile.getCellTerrain() == Terrain.BLACKBASE);
        assertTrue(redBaseTile.getCellTerrain() == Terrain.REDBASE);
    }
    
    @Test
    public void testSettingAndGettingMarkers(){
        int blackMarkerToSet = 3;
        int redMarkerToSet = 2;
        assertTrue(grassTile.getMarker(Colour.BLACK) == null);
        assertTrue(grassTile.getMarker(Colour.RED) == null);
        grassTile.setMarker(Colour.BLACK, blackMarkerToSet);
        grassTile.setMarker(Colour.RED, redMarkerToSet);
        assertTrue(grassTile.getMarker(Colour.BLACK) == blackMarkerToSet);
        assertTrue(grassTile.getMarker(Colour.RED) == redMarkerToSet);
    }
    
    @Test
    public void testToStringMethod(){
        assertTrue(blackBaseTile.toString().equals("-"));
        assertTrue(redBaseTile.toString().equals("+"));
        assertTrue(rockTile.toString().equals("#"));
        assertTrue(grassTile.toString().equals("5"));
    }
    
    // All other untested methods of BoardTile are getters and setters that use
    // no programming logic.
}
