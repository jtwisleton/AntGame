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
        AntWorldGenerator wg = new AntWorldGenerator(10);
        BoardTile[][] bt = wg.placeBordersAndGrass();
        Board board = new Board(bt, "borders and grass");
        System.out.println("-----\nTest: placeBordersAndGrass\n-----");
        board.printBoardToASCII();
    }

    @Test
    public void placeAnthills() {
        AntWorldGenerator wg = new AntWorldGenerator(10);
        BoardTile[][] bt = wg.placeBordersAndGrass();
        bt = wg.placeAnthills(bt);
        Board board = new Board(bt, "borders and grass and anthills");
        System.out.println("\n-----\nTest: placeAnthills\n-----\n");
        board.printBoardToASCII();
    }

    @Test
    public void placeFood() {
        AntWorldGenerator wg = new AntWorldGenerator(10);
        BoardTile[][] bt = wg.placeBordersAndGrass();
        bt = wg.placeAnthills(bt);
        bt = wg.placeFood(bt);
        Board board = new Board(bt, "borders and grass and anthills and food");
        System.out.println("\n-----\nTest: placeFood\n-----\n");
        board.printBoardToASCII();
    }

    @Test
    public void placeRocks() {
        AntWorldGenerator wg = new AntWorldGenerator(10);
        BoardTile[][] bt = wg.placeBordersAndGrass();
        bt = wg.placeAnthills(bt);
        bt = wg.placeRocks(bt);
        Board board = new Board(bt, "borders and grass and anthills and rocks");
        System.out.println("\n-----\nTest: placeRocks\n-----\n");
        board.printBoardToASCII();
    }

    @Test
    public void createGaps() {
        AntWorldGenerator wg = new AntWorldGenerator(30);

        BoardTile[][] bt = wg.placeBordersAndGrass();

        bt = wg.placeAnthills(bt);
        bt = wg.placeFood(bt);

        bt = wg.placeRocks(bt);
        bt = wg.createGaps(bt);

        Board board = new Board(bt, "whole thing");
        System.out.println("\n-----\nTest: borders and grass and anthills and food and rocks with spaces\n-----\n");
        board.printBoardToASCII();
    }
    
    @Test
    public void createWorld(){
        AntWorldGenerator wg = new AntWorldGenerator(30);
        Board b = wg.generateWorld();
        b.printBoardToASCII();
    }

    @After
    public void tearDown() {
    }
}
