
package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import conditions.Condition;

/**
 *
 * @author wilki
 */
public class Foe  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if(gameBoard.antInPosition(sensePosition)){
            return gameBoard.antAt(sensePosition).getAntColour() != antColour;
        }
        return false;
    }
    
}
