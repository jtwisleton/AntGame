
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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wilki
 */
public class GameJUnitTest {
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testGameCreation(){
        AntBrain antBrainOne;
        AntBrain antBrainTwo;
        Board gameBoard;
        Game testGame;
        try {
            gameBoard = AntWorldLoader.loadWorld("src//antgameproject//testWorld.txt", "Test project", false);
            antBrainOne = AntBrainLoader.loadBrain("src//antgameproject//sampleAnt.txt", "sample");
            antBrainTwo = AntBrainLoader.loadBrain("src//antgameproject//sampleAnt.txt", "sample");
            testGame = new Game(antBrainOne, antBrainTwo, gameBoard);
            List<String> testDumps = getTestDumps();
            int count = 3;
            int num = 0;
            while(count < testDumps.size()){
                BoardTile[][] currentBoard = testGame.getGameBoard().getBoard();
                for(int j = 0; j < currentBoard.length; j++){
                    for(int k = 0; k < currentBoard[0].length; k++){
                        String boardTileAsString = "cell (" + k + ", " + j +"): ";
                        boardTileAsString += parseCellToDumpFileFormat(currentBoard[j][k]);
                        assertTrue(boardTileAsString.equals(testDumps.get(count)));
                        count++;
                    }
                }
                testGame.runRounds(1);
                count +=2;
            }
            assertTrue(testGame.getPlayerOneScore() == 7);
            assertTrue(testGame.getPlayerTwoScore() == 9);
            assertTrue(testGame.getRedAntsAlive() == 11);
            assertTrue(testGame.getBlackAntsAlive() == 16);
        } catch (IOException ex) {
            Logger.getLogger(GameJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AntBrainLoader.AntBrainLoaderException ex) {
            Logger.getLogger(GameJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            Logger.getLogger(GameJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private List<String> getTestDumps(){
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
    
    private String parseCellToDumpFileFormat(BoardTile cellToParse){
        String tileRepresentation = "";
        tileRepresentation += addFood(cellToParse);
        tileRepresentation += terrainToString(cellToParse.getCellTerrain());
        tileRepresentation += addMarkers(cellToParse, Colour.RED);
        tileRepresentation += addMarkers(cellToParse, Colour.BLACK);
        tileRepresentation += addAnt(cellToParse);
        
        return tileRepresentation;
    }
    
    private String terrainToString(Terrain cellTerrain){
        String terrain;
        if(cellTerrain == Terrain.REDBASE){
            terrain = "red hill; ";
        } else if(cellTerrain == Terrain.BLACKBASE){
            terrain = "black hill; ";
        } else if(cellTerrain == Terrain.ROCK){
            terrain = "rock";
        } else {
            terrain = "";
        }
        return terrain;
    }
    
    private String addFood(BoardTile cellToParse){
        if(cellToParse.getFoodInTile() == 0){
            return "";
        } 
        return cellToParse.getFoodInTile() + " food; ";
    }
    
    private String addMarkers(BoardTile cellToParse, Colour markerColour){
        String markers = "";
        if(cellToParse.getMarkers(markerColour).size() > 0){
            if(markerColour == Colour.RED){
                markers += "red marks: ";
            } else {
                markers += "black marks: ";
            }
            Iterator it = cellToParse.getMarkers(markerColour).iterator();
            while(it.hasNext()){
                markers += it.next();
            }
            markers += "; ";
        }
        return markers;
    }
    
    private String addAnt(BoardTile tileToCheckForAnt){
        String antRepresentation;
        if(tileToCheckForAnt.getAntOnTile() == null){
            antRepresentation = "";
        } else {
            Ant antToTransform = tileToCheckForAnt.getAntOnTile();
            if(antToTransform.getAntColour() == Colour.RED){
                antRepresentation = "red ant of id " + antToTransform.getId() + ", dir " + 
                        antToTransform.getFacingDirection() + ", food" + 
                        amountOfFoodCarried(antToTransform) + ", state " +
                        antToTransform.getCurrentBrainState() + ", resting " + 
                        antToTransform.getResting();
            } else {
                antRepresentation = "black ant of id " + antToTransform.getId() + ", dir " + 
                        antToTransform.getFacingDirection() + ", food" + 
                        amountOfFoodCarried(antToTransform) + ", state " +
                        antToTransform.getCurrentBrainState() + ", resting " + 
                        antToTransform.getResting();
            }
        }
        return antRepresentation;
    }

    private String amountOfFoodCarried(Ant antToTransform) {
        if(antToTransform.getCarryingFood()){
            return " 1";
        }
        return " 0";
    }
}
