package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 *
 * @author wilki
 */
public interface Condition {
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard);
}
