package antGameTesting;

import antgameproject.AntBrain;
import antgameproject.AntBrainLoader;
import antgameproject.AntBrainLoader.AntBrainLoaderException;
import instructions.Instruction;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * Test class for AntBrainLoader class.
 *
 * @author Team18
 */
public class AntBrainLoaderJUnitTest {

    // Test that a valid ant brain is loaded correctly
    @Test
    public void loadBrainTest()  {
        try {
            String fn = "testFiles//testBrain.txt";
            AntBrain ab = new AntBrainLoader().loadAntBrain(fn, "Test Brain");
            for (int i = 0; i < 16; i++) {
                assertTrue(ab.getInstruction(i) instanceof Instruction);
            }
        } catch (IOException ex) {
            Logger.getLogger(AntBrainLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AntBrainLoaderException ex) {
            Logger.getLogger(AntBrainLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Test that an invalid brain throws an exception when loaded
    @Test
    public void loadInvalidBrainTest() {
        String fn = "testFiles//invalidBrain.txt";
        try {
            AntBrain ab = new AntBrainLoader().loadAntBrain(fn, "Test Brain");
            assertTrue(false);
        } catch (AntBrainLoaderException able) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntBrainLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // tests that very long ant brains still under 9999 are allowed
    @Test
    public void testLongAntBrain(){
        String fn = "testFiles//longBrain.txt";
        try {
            AntBrain ab = new AntBrainLoader().loadAntBrain(fn, "Test Brain");
            assertTrue(false);
        } catch (AntBrainLoaderException able) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntBrainLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // Tests that ant brains over 9999 states are caught
    @Test
    public void tooLongAntBrain(){
        String fn = "testFiles//tooLongBrain.txt";
        try {
            AntBrain ab = new AntBrainLoader().loadAntBrain(fn, "Test Brain");
            assertTrue(false);
        } catch (AntBrainLoaderException able) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntBrainLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // Tests that instructions with incorrect case useage are caught
    @Test
    public void wrongCaseInstructions(){
        String fn = "testFiles//lowercaseInstructionBrain.txt";
        try {
            AntBrain ab = new AntBrainLoader().loadAntBrain(fn, "Test Brain");
            assertTrue(false);
        } catch (AntBrainLoaderException able) {
            assertTrue(true);
        } catch (IOException ex) {
            Logger.getLogger(AntBrainLoaderJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
