
package conditions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import conditions.Condition;

/**
 *
 * @author wilki
 */
public class FriendWithFood implements Condition {

    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if(gameBoard.antInPosition(sensePosition)){
            Ant antInSensePos = gameBoard.antAt(sensePosition);
            return (antInSensePos.getAntColour() == antColour) &&
                                (antInSensePos.getCarryingFood());
        }
        return false;
    }
    
}
