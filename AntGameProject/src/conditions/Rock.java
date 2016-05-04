package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import antgameproject.Terrain;

/**
 * @author Team18
 *
 * Rock is used to sense for a rock in the given position.
 */
public class Rock implements Condition {

    /**
     * Sense a rock in the given position on the given board.
     *
     * @param sensePosition position to sense for rock.
     * @param antColour
     * @param gameBoard board to sense for rock on.
     * @return true if a rock is found at the position given on the board given
     * and false otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.getTerrainAtPosition(sensePosition) == Terrain.ROCK;
    }

}
