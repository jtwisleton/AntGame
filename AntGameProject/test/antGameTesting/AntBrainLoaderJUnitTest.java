package antGameTesting;

import antgameproject.AntBrain;
import antgameproject.AntBrainLoader;
import antgameproject.AntBrainLoader.AntBrainLoaderException;
import static antgameproject.AntBrainLoader.loadBrain;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Team18
 */
public class AntBrainLoaderJUnitTest {

    public AntBrainLoaderJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loadBrainTest() throws IOException, FileNotFoundException, AntBrainLoader.AntBrainLoaderException {
        String fn = "src//antgameproject//testBrain.txt";
        AntBrain ab = loadBrain(fn, "Test Brain");
        for (int i = 0; i < 16; i++) {
            System.out.println(ab.getInstruction(i).toString());
        }
    }

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
