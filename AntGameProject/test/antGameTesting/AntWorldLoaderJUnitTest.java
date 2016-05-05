package antGameTesting;

import antgameproject.AntWorldLoader;
import antgameproject.Board;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Test class for AntWorldLoader class.
 *
 * @author Team18
 */
public class AntWorldLoaderJUnitTest {

    // Test that the world syntax is checked correctly
    @Test
    public void checkWorldSyntaxTest() throws AntWorldLoader.AntWorldLoaderException, IOException {
        AntWorldLoader awl = new AntWorldLoader();
        String fn = "generatedWorlds//generatedWorld1998270173.world";
        Assert.assertTrue(awl.checkWorldSyntax(fn));
    }

    // Test that a world is loaded correctly
    @Test
    public void loadWorldTest() throws AntWorldLoader.AntWorldLoaderException, IOException {
        AntWorldLoader awl = new AntWorldLoader();
        String fn = "generatedWorlds//generatedWorld1998270173.world";
        Board loadedBoard = awl.loadWorld(fn, "1", false);
        assertTrue(loadedBoard != null);
        assertTrue(loadedBoard.toString().equals("1"));
    }
    
    // Tests that when ant hills are too large this is caught
    @Test
    public void testTooLargeSizeAntHill(){
        try {
            Board loadedBoard = new AntWorldLoader().loadWorld("testFiles//wrongSizedAntHill.txt", 
                    "1", true);
            assertTrue(false);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntWorldLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // tests that when ant hills are too small this is caught
    @Test
    public void testTooSmallAntHill(){
        try {
            Board loadedBoard = new AntWorldLoader().loadWorld("testFiles//tooSmallSizedAntHill.txt", 
                    "1", true);
            assertTrue(false);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntWorldLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // tests that when there is too much food on the map this is caught
    @Test
    public void testTooMuchFood(){
        try {
            Board loadedBoard = new AntWorldLoader().loadWorld("testFiles//tooMuchFood.txt", 
                    "1", true);
            assertTrue(false);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntWorldLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // tests that when there is not enough food on the map this is caught
    @Test
    public void testNotEnoughFood(){
        try {
            Board loadedBoard = new AntWorldLoader().loadWorld("testFiles//notEnoughFood.txt", 
                    "1", true);
            assertTrue(false);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntWorldLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Tests that when there are too many rocks this is caught
    @Test
    public void testTooManyRocks(){
        try {
            Board loadedBoard = new AntWorldLoader().loadWorld("testFiles//tooManyRocks.txt", 
                    "1", true);
            assertTrue(false);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntWorldLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // tests when there are not enough rocks this is caught
    @Test
    public void testNotEnoughRocks(){
        try {
            Board loadedBoard = new AntWorldLoader().loadWorld("testFiles//tooFewRocks.txt", 
                    "1", true);
            assertTrue(false);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntWorldLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // tests the case of when there is rock next to food this is caught
    @Test
    public void testRockNextToFood(){
        try {
            Board loadedBoard = new AntWorldLoader().loadWorld("testFiles//rockNextTofood.txt", 
                    "1", true);
            assertTrue(false);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntWorldLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // tests that a perfect world is allowed to load
    @Test
    public void testPerfectWorld(){
        try {
            Board loadedBoard = new AntWorldLoader().loadWorld("testFiles//1.world", 
                    "1", true);
            assertTrue(true);
        } catch (AntWorldLoader.AntWorldLoaderException ex) {
            assertTrue(false);
        } catch (IOException ex) {
            Logger.getLogger(AntWorldLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
