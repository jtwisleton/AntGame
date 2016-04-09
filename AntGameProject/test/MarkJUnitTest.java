
import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Mark;
import antgameproject.Pos;
import antgameproject.Terrain;
import org.junit.Test;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jw478
 */
public class MarkJUnitTest {
    
    public MarkJUnitTest() {
    }

    @Test
    public void testCreation(){
        Ant testAnt = new Ant(Colour.RED, 1, new Pos(2,2));
        BoardTile[][] board = new BoardTile[20][20]; 
        Board testBoard = new Board(board);
        
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(i == 0 || i == 19 || j == 0 || j == 19){
                    board[i][j] = new BoardTile(0, Terrain.ROCK);
                } else {
                    board[i][j] = new BoardTile(0, Terrain.GRASS);
                }
            }
        }
        
        Mark testMark = new Mark(3, 5);
        testMark.execute(null, testAnt);
    }
}
