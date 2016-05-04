package antGameTesting;

import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Terrain;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for BoardTile class.
 *
 * @author Team18
 */
public class BoardTileJUnitTest {

    private BoardTile rockTile;
    private BoardTile grassTile;
    private BoardTile blackBaseTile;
    private BoardTile redBaseTile;
    private int foodOnGrassTile;

    // Create board tiles for testing
    @Before
    public void setUp() {
        foodOnGrassTile = 5;
        rockTile = new BoardTile(0, Terrain.ROCK);
        grassTile = new BoardTile(5, Terrain.GRASS);
        blackBaseTile = new BoardTile(0, Terrain.BLACKBASE);
        redBaseTile = new BoardTile(0, Terrain.REDBASE);
    }

    // Test that board tiles are created correctly
    @Test
    public void testBoardTileCreation() {
        assertTrue(rockTile.getCellTerrain() == Terrain.ROCK);
        assertTrue(grassTile.getCellTerrain() == Terrain.GRASS);
        assertTrue(blackBaseTile.getCellTerrain() == Terrain.BLACKBASE);
        assertTrue(redBaseTile.getCellTerrain() == Terrain.REDBASE);
    }

    // Test that markers and set and retrieved correctly
    @Test
    public void testSettingAndGettingMarkers() {
        int blackMarkerToSet = 3;
        int redMarkerToSet = 2;
        assertTrue(grassTile.getMarkers(Colour.BLACK).isEmpty());
        assertTrue(grassTile.getMarkers(Colour.RED).isEmpty());
        grassTile.setMarker(Colour.BLACK, blackMarkerToSet);
        grassTile.setMarker(Colour.RED, redMarkerToSet);
        assertTrue(grassTile.getMarkers(Colour.BLACK).contains(blackMarkerToSet));
        assertTrue(grassTile.getMarkers(Colour.RED).contains(redMarkerToSet));
        assertTrue(grassTile.getMarkers(Colour.BLACK).size() == 1);
        assertTrue(grassTile.getMarkers(Colour.RED).size() == 1);
    }

    // Test that the board tiles are displayed correctly as a string
    @Test
    public void testToStringMethod() {
        assertTrue(blackBaseTile.toString().equals("-"));
        assertTrue(redBaseTile.toString().equals("+"));
        assertTrue(rockTile.toString().equals("#"));
        assertTrue(grassTile.toString().equals("5"));
    }

    // All other untested methods of BoardTile are getters and setters that use
    // no programming logic.
}
