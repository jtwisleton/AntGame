/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antGameTesting;

import antgameproject.AntWorldGenerator;
import antgameproject.Board;
import antgameproject.BoardTile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jamestwisleton
 */
public class WorldGenTest {

    public WorldGenTest() {
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

    @Test
    public void placeBordersAndGrass() {
        AntWorldGenerator wg = new AntWorldGenerator();
        BoardTile[][] bt = wg.placeBordersAndGrass();
        Board board = new Board(bt, "borders and grass");
        System.out.println("-----\nTest: placeBordersAndGrass\n-----");
        board.printBoardToASCII();
    }

    @Test
    public void placeAnthills() {
        AntWorldGenerator wg = new AntWorldGenerator();
        BoardTile[][] bt = wg.placeBordersAndGrass();
        bt = wg.placeAnthills(bt);
        Board board = new Board(bt, "borders and grass and anthills");
        System.out.println("\n-----\nTest: placeAnthills\n-----\n");
        board.printBoardToASCII();
    }

//    @Test
//    public void placeFood() {
//        AntWorldGenerator wg = new AntWorldGenerator();
//        BoardTile[][] bt = wg.placeBordersAndGrass();
//        bt = wg.placeAnthills(bt);
//        bt = wg.placeFood(bt);
//        Board board = new Board(bt, "borders and grass and anthills and food");
//        System.out.println("\n-----\nTest: placeFood\n-----\n");
//        board.printBoardToASCII();
//    }

    @Test
    public void placeRocks(){
        AntWorldGenerator wg = new AntWorldGenerator();
        BoardTile[][] bt = wg.placeBordersAndGrass();
        bt = wg.placeAnthills(bt);
        bt = wg.placeRocks(bt,14,20);
        Board board = new Board(bt, "borders and grass and anthills and rocks");
        System.out.println("\n-----\nTest: placeRocks\n-----\n");
        board.printBoardToASCII();
    }
    
    @After
    public void tearDown() {
    }
}
