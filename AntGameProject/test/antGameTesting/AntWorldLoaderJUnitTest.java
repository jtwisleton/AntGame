package antGameTesting;

import antgameproject.AntWorldLoader;
import antgameproject.Board;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Team18
 */
public class AntWorldLoaderJUnitTest {
    
    public AntWorldLoaderJUnitTest() {
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
    public void checkWorldSyntaxTest() throws AntWorldLoader.AntWorldLoaderException, IOException {
        AntWorldLoader awl = new AntWorldLoader();
        String fn = "generatedWorlds//generatedWorld1998270173.world";
        Assert.assertEquals(true,awl.checkWorldSyntax(fn));
    }

    @Test
    public void loadWorldTest() throws AntWorldLoader.AntWorldLoaderException, IOException{
        AntWorldLoader awl = new AntWorldLoader();
        String fn = "generatedWorlds//generatedWorld1998270173.world";
        Board b = awl.loadWorld(fn, "1", false);
        b.printBoardToASCII();        
    }            
    
}
