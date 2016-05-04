package antGameTesting;

import antgameproject.AntBrain;
import antgameproject.AntBrainLoader;
import antgameproject.AntBrainLoader.AntBrainLoaderException;
import static antgameproject.AntBrainLoader.loadBrain;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;

/**
 * Test class for AntBrainLoader class.
 *
 * @author Team18
 */
public class AntBrainLoaderJUnitTest {

    // Test that a valid ant brain is loaded correctly
    @Test
    public void loadBrainTest() throws IOException, FileNotFoundException, AntBrainLoader.AntBrainLoaderException {
        //String fn = "src//antgameproject//testBrain.txt";
        String fn = "src//antgameproject//AntBrain.txt";
        AntBrain ab = loadBrain(fn, "Test Brain");
        for (int i = 0; i < 16; i++) {
            System.out.println(ab.getInstruction(i).toString());
        }
    }

    // Test that an invalid brain throws an exception when loaded
    @Test
    public void loadInvalidBrainTest() throws IOException, FileNotFoundException, AntBrainLoader.AntBrainLoaderException {
        String fn = "src//antgameproject//invalidBrain.txt";
        try {
            AntBrain ab = loadBrain(fn, "Test Brain");
        } catch (AntBrainLoaderException able) {
            System.out.println("Failed as expected.");
        }
    }
}
