
package antgameproject;

/**
 *
 * @author wilki
 */
public class FoeWithFood implements Condition {

    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if(gameBoard.antInPosition(sensePosition)){
            Ant antInSensePos = gameBoard.antAt(sensePosition);
            return (antInSensePos.getAntColour() != antColour) &&
                    (antInSensePos.getCarryingFood());
        }
        return false;
    }
    
}