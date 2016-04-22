
package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * Friend is used to sense a friendly ant.
 */
public class Friend implements Condition {

    /**
     * Sense a friendly ant in the given position.
     * @param sensePosition position to sense for friendly ant.
     * @param antColour colour of ant to find friendly ants of.
     * @param gameBoard the board to perform sense on.
     * @return true if an ant of the same colour is found in the given position and
     * false otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if(gameBoard.antInPosition(sensePosition)){
            return gameBoard.antAt(sensePosition).getAntColour() == antColour;
        }
        return false;
    }
    
}
