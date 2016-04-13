/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antGameTesting;

import antgameproject.Pos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wilki
 */
public class PosJUnitTest {
    
    @Test
    public void testPosCreation(){
        int x = 3;
        int y = 6;
        Pos testPos = new Pos(x,y);
        assertTrue(testPos.getPosX() == x);
        assertTrue(testPos.getPosY() == y);
    }
}
