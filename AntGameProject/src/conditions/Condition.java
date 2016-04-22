package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * Interface for all sense conditions.
 */
public interface Condition {
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard);
}
