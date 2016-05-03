package antGameTesting;

import antgameproject.AntWorldLoader;
import antgameproject.Board;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author JTwisleton
 */
public class WorldLoadTest {
    
    public WorldLoadTest() {
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

//    @Test
//    public void checkWorldSyntaxTest() throws AntWorldLoader.AntWorldLoaderException, IOException{
//        AntWorldLoader awl = new AntWorldLoader();
//        String fn = "generatedWorlds//generatedWorld1998270173.world";        
//        Board b = awl.loadWorld(fn, "1", false);                    
//    }
//    
    @Test
    public void validAmountOfAntsTest() throws AntWorldLoader.AntWorldLoaderException, IOException{
        AntWorldLoader awl = new AntWorldLoader();
        String fn = "generatedWorlds//generatedWorld1998270173.world";        
        Board b = awl.loadWorld(fn, "1", false);                    
        awl.validAnthills(b);
        
    }
    
    
}
