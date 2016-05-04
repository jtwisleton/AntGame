package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * @author Team18
 *
 * Home is used to sense a friendly ant hill.
 */
public class Home implements Condition {

    /**
     * Sense an ant hill in the given position of the same colour as the
     * antColour.
     *
     * @param sensePosition position to sense friendly ant hill.
     * @param antColour colour of ant to find friendly ants of.
     * @param gameBoard the board to perform the sense on.
     * @return true if an ant hill of the same colour as antColour is found as
     * the given position and false otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.anthillAt(sensePosition, antColour);
    }

}
