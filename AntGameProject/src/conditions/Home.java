
package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import conditions.Condition;

/**
 *
 * @author wilki
 */
public class Home  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.anthillAt(sensePosition, antColour);
    }
    
}
