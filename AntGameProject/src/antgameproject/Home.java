
package antgameproject;

/**
 *
 * @author wilki
 */
public class Home  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.anthillAt(sensePosition, antColour);
    }
    
}
