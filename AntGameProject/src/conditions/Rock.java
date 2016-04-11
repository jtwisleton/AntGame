
package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import antgameproject.Terrain;
import conditions.Condition;

/**
 *
 * @author wilki
 */
public class Rock  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.getTerrainAtPosition(sensePosition) == Terrain.ROCK;
    }
    
}
