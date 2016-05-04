package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * @author Team18
 *
 * FoeMarker is used to sense for any marker of an enemy ant in a position.
 */
public class FoeMarker implements Condition {

    /**
     * Sense if there is any marker of a colour different to the ant specified
     * in the given position.
     *
     * @param sensePosition position to sense for enemy markers.
     * @param antColour the ant colour to find enemies of.
     * @param gameBoard the board to perform sense on.
     * @return true is any markers on the tile of colour that is not the given
     * ant colour and false otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        boolean foeMarker = false;
        for (Colour col : Colour.values()) {
            if (antColour != col) {
                foeMarker = gameBoard.checkAnyMarker(sensePosition, col);
            }
        }
        return foeMarker;
    }

}
