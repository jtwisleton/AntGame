/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antGameTesting;

import antgameproject.AntWorldLoader;
import antgameproject.Board;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jamestwisleton
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

    @Test
    public void checkWorldSyntaxTest() throws AntWorldLoader.AntWorldLoaderException, IOException{
        AntWorldLoader awl = new AntWorldLoader();
        String fn = "src//antgameproject//1.world";
        
        Board b = awl.loadWorld(fn, "1", false);
        b.printBoardToASCII();
    }
}
