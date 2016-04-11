
package antGameTesting;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Marker;
import conditions.Foe;
import conditions.FoeHome;
import conditions.Food;
import conditions.Friend;
import conditions.FriendWithFood;
import conditions.Home;
import antgameproject.Pos;
import instructions.Sense;
import instructions.SenseDirection;
import antgameproject.Terrain;
import conditions.FoeMarker;
import conditions.FoeWithFood;
import conditions.Rock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wilki
 */
public class SenseJUnitTest {
    
    private Ant testAnt;
    private Ant blackAnt;
    private Ant secondAnt;
    private Board testBoard;
    private int nextStateIfConditionTrue;
    private int nextStateIfConditionFalse;
    
    @Before
    public void setUp() {
        nextStateIfConditionTrue = 22;
        nextStateIfConditionFalse = 35;
        testAnt = new Ant(Colour.RED, 1, new Pos(2,2));
        blackAnt = new Ant(Colour.BLACK, 2, new Pos(2,1));
        secondAnt = new Ant(Colour.RED, 3, new Pos(5,5));
        BoardTile[][] board = new BoardTile[20][20]; 
        
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(i == 0 || i == 19 || j == 0 || j == 19){
                    board[i][j] = new BoardTile(0, Terrain.ROCK);
                } else {
                    board[i][j] = new BoardTile(0, Terrain.GRASS);
                }
            }
        }
        board[2][2] = new BoardTile(0, Terrain.REDBASE);
        board[2][3] = new BoardTile(0, Terrain.BLACKBASE);
        board[3][2] = new BoardTile(5, Terrain.GRASS);
        
        testBoard = new Board(board);
        testBoard.setAntAt(new Pos(2,1), blackAnt);
        testBoard.setAntAt(new Pos(2, 2), testAnt);
        testBoard.setAntAt(new Pos(5,5), secondAnt);
        testBoard.printBoardToASCII();
    }
    
    // tests sense here and ahead and home and foe home
    @Test
    public void senseDirectionHere(){
        Sense senseHome = new Sense(SenseDirection.HERE, new Home(), 
                nextStateIfConditionTrue, nextStateIfConditionFalse);
        senseHome.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextStateIfConditionTrue);
        Sense foeHome = new Sense(SenseDirection.HERE, new FoeHome(),
                nextStateIfConditionTrue, nextStateIfConditionFalse);
        foeHome.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextStateIfConditionFalse);
        
        Sense senseAheadHome = new Sense(SenseDirection.AHEAD, new FoeHome(), 
                nextStateIfConditionTrue, nextStateIfConditionFalse);
        senseAheadHome.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextStateIfConditionTrue);
        Sense senseAheadFoeHome = new Sense(SenseDirection.AHEAD, new Home(),
                nextStateIfConditionTrue, nextStateIfConditionFalse);
        senseAheadFoeHome.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextStateIfConditionFalse);
    }

    // test sense left ahead and right ahead, foe and food
    @Test
    public void senseDirectionAhead(){
        Sense senseFoeLeftAhead = new Sense(SenseDirection.LEFTAHEAD, new Foe(), 
                nextStateIfConditionTrue, nextStateIfConditionFalse);
        senseFoeLeftAhead.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextStateIfConditionTrue);
        Sense senseFoodLeftAhead = new Sense(SenseDirection.LEFTAHEAD, new Food(),
                nextStateIfConditionTrue, nextStateIfConditionFalse);
        senseFoodLeftAhead.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextStateIfConditionFalse);
        
        Sense senseFoodRightAhead = new Sense(SenseDirection.RIGHTAHEAD, new Food(),
                nextStateIfConditionTrue, nextStateIfConditionFalse);
        senseFoodRightAhead.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextStateIfConditionTrue);
        Sense senseFoeRightAhead = new Sense(SenseDirection.RIGHTAHEAD, new Foe(),
                nextStateIfConditionTrue, nextStateIfConditionFalse);
        senseFoeRightAhead.execute(testBoard, testAnt);
        assertTrue(testAnt.getCurrentBrainState() == nextStateIfConditionFalse);
        
    }
    
    // test sense friend true and false
    @Test
    public void senseFriend(){
        Ant friend = new Ant(Colour.RED, 5, new Pos(6,5));
        testBoard.setAntAt(new Pos(6,5), friend);
        
        new Sense(SenseDirection.AHEAD, new Friend(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, secondAnt);
        assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionTrue);
        
        new Sense(SenseDirection.LEFTAHEAD, new Friend(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, secondAnt);
        assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionFalse);
    }
    
    // test sense friends with food true and false
    @Test
    public void senseFriendWithFood(){
        Ant friend = new Ant(Colour.RED, 5, new Pos(6,5));
        testBoard.setAntAt(new Pos(6,5), friend);
        
        new Sense(SenseDirection.AHEAD, new FriendWithFood(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, secondAnt);
        assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionFalse);
        
        friend.setCarryingFood(true);
        new Sense(SenseDirection.AHEAD, new FriendWithFood(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, secondAnt);
        assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionTrue);
    }
    
    // test sense foe with food true and false
    @Test
    public void senseFoeWithFood(){
        Ant foe = new Ant(Colour.BLACK, 5, new Pos(6,5));
        testBoard.setAntAt(new Pos(6,5), foe);
        
        new Sense(SenseDirection.AHEAD, new FoeWithFood(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, secondAnt);
        assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionFalse);
        
        foe.setCarryingFood(true);
        new Sense(SenseDirection.AHEAD, new FoeWithFood(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, secondAnt);
        assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionTrue);
    }
    
    // test sense rock true and false
    @Test
    public void senseRock(){
        new Sense(SenseDirection.LEFTAHEAD, new Rock(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, blackAnt);
        assertTrue(blackAnt.getCurrentBrainState() == nextStateIfConditionTrue);
        
        new Sense(SenseDirection.AHEAD, new Rock(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, blackAnt);
        assertTrue(blackAnt.getCurrentBrainState() == nextStateIfConditionFalse);
    }
    
    // test sense foe marker true and false
    @Test
    public void senseFoeMarker(){
        new Sense(SenseDirection.AHEAD, new FoeMarker(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, secondAnt);
        assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionFalse);
        
        testBoard.setMarker(new Pos(6,5), Colour.BLACK, 3);
        new Sense(SenseDirection.AHEAD, new FoeMarker(), nextStateIfConditionTrue,
                nextStateIfConditionFalse).execute(testBoard, secondAnt);
        assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionTrue);
    }
    
    // test sense marker true false for different markers
    @Test
    public void senseMarker(){
        for(int i = 0; i < 6; i++){
            Sense senseMarker = new Sense(SenseDirection.AHEAD, new Marker(i), nextStateIfConditionTrue,
                nextStateIfConditionFalse);
            senseMarker.execute(testBoard, secondAnt);
            assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionFalse);
            
            testBoard.setMarker(new Pos(6,5), Colour.RED, i);
            senseMarker.execute(testBoard, secondAnt);
            assertTrue(secondAnt.getCurrentBrainState() == nextStateIfConditionTrue);        
        }
    }
    
    
    
}
