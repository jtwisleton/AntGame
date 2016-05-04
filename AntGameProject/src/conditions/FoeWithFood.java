package conditions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * FoeWithFood is used to sense a enemy carrying food.
 *
 * @author Team18
 */
public class FoeWithFood implements Condition {

    /**
     * Sense an enemy ant carrying food at the given position.
     *
     * @param sensePosition the position to sense.
     * @param antColour the ant colour to find enemies of.
     * @param gameBoard the game board to perform sense on.
     * @return true if an enemy ant is found in the given position carrying food
     * and false otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if (gameBoard.antInPosition(sensePosition)) {
            Ant antInSensePos = gameBoard.antAt(sensePosition);
            return (antInSensePos.getAntColour() != antColour)
                    && (antInSensePos.getCarryingFood());
        }
        return false;
    }

}
