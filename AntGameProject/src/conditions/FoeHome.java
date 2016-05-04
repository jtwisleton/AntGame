package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * FoeHome class used to sense for an enemy ant hill.
 *
 * @author Team18
 */
public class FoeHome implements Condition {

    /**
     * Sense if an ant hill of different colour to that of the ant given is in
     * the sense position on the given board.
     *
     * @param sensePosition position on the board to sense.
     * @param antColour the colour of the ant to sense enemies of.
     * @param gameBoard the board to sense on.
     * @return true in an ant hill is in the given position that is not the
     * colour of the given ant and false otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        boolean foeBase = false;
        for (Colour col : Colour.values()) {
            if (antColour != col) {
                foeBase = gameBoard.anthillAt(sensePosition, col);
            }
        }
        return foeBase;
    }

}
