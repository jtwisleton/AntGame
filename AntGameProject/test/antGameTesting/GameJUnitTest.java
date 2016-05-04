package antGameTesting;

import antgameproject.Ant;
import antgameproject.AntBrain;
import antgameproject.AntBrainLoader;
import antgameproject.AntWorldLoader;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Game;
import antgameproject.Terrain;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class for Game class.
 *
 * @author Team18
 */
public class GameJUnitTest {

    private AntBrain antBrainOne;
    private AntBrain antBrainTwo;
    private Board gameBoard;
    private Game testGame;

    // Set up a new game for testing
    @Before
    public void setUp() {
        try {
            gameBoard = new AntWorldLoader().loadWorld("src//antgameproject//testWorld.txt",
                    "Test project", false);
            antBrainOne = AntBrainLoader.loadBrain("src//antgameproject//sample.htm", "sample");
            antBrainTwo = AntBrainLoader.loadBrain("src//antgameproject//sample.htm", "sample");
            testGame = new Game(antBrainOne, antBrainTwo, gameBoard, 12345);
        } catch (IOException ex) {
            Logger.getLogger(GameJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AntBrainLoader.AntBrainLoaderException ex) {
            Logger.getLogger(GameJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            Logger.getLogger(GameJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Tests a game against the provided test dumps that can be found at 
    // http://users.sussex.ac.uk/~mfb21/se/project/dump/index.html
    @Test
    public void testGameCreation() {
        List<String> testDumps = getTestDumps();    //load test dumps into list
        int count = 3;
        int num = 0;
        while (count < testDumps.size()) {
            BoardTile[][] currentBoard = testGame.getGameBoard().getBoard();
            for (int j = 0; j < currentBoard.length; j++) {
                for (int k = 0; k < currentBoard[0].length; k++) {
                    // check that the cell matches the dump string

                    String boardTileAsString = "cell (" + k + ", " + j + "): ";
                    boardTileAsString += parseCellToDumpFileFormat(currentBoard[j][k]);
                    assertTrue(boardTileAsString.equals(testDumps.get(count)));
                    count++;
                }
            }
            testGame.runRounds(1);
            count += 2;
        }
        // check the scores at the end of the game. These were manually calculated.
        assertTrue(testGame.getPlayerOneScore() == 7);
        assertTrue(testGame.getPlayerTwoScore() == 9);
        assertTrue(testGame.getRedAntsAlive() == 11);
        assertTrue(testGame.getBlackAntsAlive() == 16);
    }

    // Loads the test dumps
    private List<String> getTestDumps() {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("src//antgameproject//test.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(GameJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lines;
    }

    // Parses a board cell to match the form of the test dumps
    private String parseCellToDumpFileFormat(BoardTile cellToParse) {
        String tileRepresentation = "";
        tileRepresentation += addFood(cellToParse);
        tileRepresentation += terrainToString(cellToParse.getCellTerrain());
        tileRepresentation += addMarkers(cellToParse, Colour.RED);
        tileRepresentation += addMarkers(cellToParse, Colour.BLACK);
        tileRepresentation += addAnt(cellToParse);

        return tileRepresentation;
    }

    // Converts a cell terrain to the appropriate string
    private String terrainToString(Terrain cellTerrain) {
        String terrain;
        if (cellTerrain == Terrain.REDBASE) {
            terrain = "red hill; ";
        } else if (cellTerrain == Terrain.BLACKBASE) {
            terrain = "black hill; ";
        } else if (cellTerrain == Terrain.ROCK) {
            terrain = "rock";
        } else {
            terrain = "";
        }
        return terrain;
    }

    // Adds food to the parse string if there is food in the cell
    private String addFood(BoardTile cellToParse) {
        if (cellToParse.getFoodInTile() == 0) {
            return "";
        }
        return cellToParse.getFoodInTile() + " food; ";
    }

    // Adds the markers in the cell to the parse string
    private String addMarkers(BoardTile cellToParse, Colour markerColour) {
        String markers = "";
        if (cellToParse.getMarkers(markerColour).size() > 0) {
            if (markerColour == Colour.RED) {
                markers += "red marks: ";
            } else {
                markers += "black marks: ";
            }
            Iterator it = cellToParse.getMarkers(markerColour).iterator();
            while (it.hasNext()) {
                markers += it.next();
            }
            markers += "; ";
        }
        return markers;
    }

    // Adds ant and ant state to the parse string
    private String addAnt(BoardTile tileToCheckForAnt) {
        String antRepresentation;
        if (tileToCheckForAnt.getAntOnTile() == null) {
            antRepresentation = "";
        } else {
            Ant antToTransform = tileToCheckForAnt.getAntOnTile();
            if (antToTransform.getAntColour() == Colour.RED) {
                antRepresentation = "red ant of id " + antToTransform.getId() + ", dir "
                        + antToTransform.getFacingDirection() + ", food"
                        + amountOfFoodCarried(antToTransform) + ", state "
                        + antToTransform.getCurrentBrainState() + ", resting "
                        + antToTransform.getResting();
            } else {
                antRepresentation = "black ant of id " + antToTransform.getId() + ", dir "
                        + antToTransform.getFacingDirection() + ", food"
                        + amountOfFoodCarried(antToTransform) + ", state "
                        + antToTransform.getCurrentBrainState() + ", resting "
                        + antToTransform.getResting();
            }
        }
        return antRepresentation;
    }

    // Adds the food being carried by an ant to the parse string
    private String amountOfFoodCarried(Ant antToTransform) {
        if (antToTransform.getCarryingFood()) {
            return " 1";
        }
        return " 0";
    }

    // Tests that no more rounds can be run in a game after is has reached 300000 steps
    @Test
    public void testGameWontRunMoreThan300000() {
        testGame.run();
        assertFalse(testGame.runRounds(1));
    }

    // Test two games created with the same parameters return the same score after 
    // completion
    @Test
    public void test2GamesWithSameSeedAreTheSame() {
        testGame.run();
        int playerOneScore = testGame.getPlayerOneScore();
        int playerTwoScore = testGame.getPlayerTwoScore();
        int redAntsAlive = testGame.getRedAntsAlive();
        int blackAntsAlive = testGame.getBlackAntsAlive();

        setUp();
        testGame.run();
        assertTrue(playerOneScore == testGame.getPlayerOneScore());
        assertTrue(playerTwoScore == testGame.getPlayerTwoScore());
        assertTrue(redAntsAlive == testGame.getRedAntsAlive());
        assertTrue(blackAntsAlive == testGame.getBlackAntsAlive());
    }

}
