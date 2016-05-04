package conditions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * @author Team18
 *
 * FreindwithFood is used to sense for a friend carrying food.
 */
public class FriendWithFood implements Condition {

    /**
     * Sense a friendly ant carrying food in the given position.
     *
     * @param sensePosition the position to sense friend carrying food.
     * @param antColour the colour of the ant to sense the friend of.
     * @param gameBoard the board to perform the sense on.
     * @return true if an ant of the same colour is found in the given position
     * carrying food and false otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if (gameBoard.antInPosition(sensePosition)) {
            Ant antInSensePos = gameBoard.antAt(sensePosition);
            return (antInSensePos.getAntColour() == antColour)
                    && (antInSensePos.getCarryingFood());
        }
        return false;
    }

}
