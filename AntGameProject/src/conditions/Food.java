
package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * Food is used to sense for food on the board.
 */
public class Food  implements Condition {
    
    /**
     * Sense food at the given position on the given board.
     * @param sensePosition position to sense for food.
     * @param antColour
     * @param gameBoard board to sense for food on.
     * @return true if food found in the given position on the board;
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.numberOfFoodAt(sensePosition) > 0;
    }
    
}
