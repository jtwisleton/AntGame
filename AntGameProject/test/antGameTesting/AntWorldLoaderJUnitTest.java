package antGameTesting;

import antgameproject.AntWorldLoader;
import antgameproject.Board;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

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
        Board b = awl.loadWorld(fn, "1", false);
        b.printBoardToASCII();
    }

}
