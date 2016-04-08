
package antgameproject;

/**
 *
 * @author wilki
 */
public class Rock  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.getTerrainAtPosition(sensePosition) == Terrain.ROCK;
    }
    
}
