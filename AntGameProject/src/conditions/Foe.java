package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * @author Team18
 *
 * Foe class used to to sense an enemy.
 */
public class Foe implements Condition {

    /**
     * Tests for a foe at the given position.
     *
     * @param sensePosition position to sense.
     * @param antColour colour of ant to sense enemies of;
     * @param gameBoard board to perform sense on.
     * @return true if an enemy ant is in the sensed position and false
     * otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if (gameBoard.antInPosition(sensePosition)) {
            return gameBoard.antAt(sensePosition).getAntColour() != antColour;
        }
        return false;
    }

}
