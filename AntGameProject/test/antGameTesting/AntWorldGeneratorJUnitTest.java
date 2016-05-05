package antGameTesting;

import antgameproject.AntWorldGenerator;
import antgameproject.BoardTile;
import antgameproject.Terrain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AntWorldGenerator class.
 *
 * @author Team18
 */
public class AntWorldGeneratorJUnitTest {
    
    private BoardTile[][] generatedBoard;
    
    @Before
    public void setUp(){
        try {
            generatedBoard = new AntWorldGenerator(1).generateAntWorld().getBoard();
        } catch (IOException ex) {
            Logger.getLogger(AntWorldGeneratorJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Tests the generated board is of a correct size
    @Test
    public void checkBoardSize(){
        assertTrue(generatedBoard.length == 150);
        assertTrue(generatedBoard[0].length == 150);
    }
    
    // checks the board outside is rocky
    @Test
    public void checkBorderIsRocky(){
        for(int i = 0; i < generatedBoard.length; i++){
            for(int j = 0; j < generatedBoard[0].length; j++){
                if(i == 0 || i == 149 || j == 0 || j == 149){
                    assertTrue(generatedBoard[j][i].getCellTerrain() == Terrain.ROCK);
                }
            }
        }
    }
    
    // checks that food placed onto the map is portions of 5
    @Test
    public void checkNumberOfFood(){
        for(int i = 0; i < generatedBoard.length; i++){
            for(int j = 0; j < generatedBoard[0].length; j++){
                if(generatedBoard[j][i].getFoodInTile() > 0){
                    assertTrue(generatedBoard[j][i].getFoodInTile() == 5);
                }
            }
        }
    }
    
    
}
