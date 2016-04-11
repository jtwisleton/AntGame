
package antgameproject;

/**
 *
 * @author wilki
 */
public class Food  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.numberOfFoodAt(sensePosition) > 0;
    }
    
}
