
package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import conditions.Condition;

/**
 *
 * @author wilki
 */
public class Food  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.numberOfFoodAt(sensePosition) > 0;
    }
    
}
