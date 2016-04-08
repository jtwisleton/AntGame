
package antgameproject;

/**
 *
 * @author wilki
 */
public class Marker implements Condition{
    private final int markerType;
    
    public Marker(int markerType){
        assert markerType < 6 && markerType >= 0;
        this.markerType = markerType;
    }

    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.checkMarker(sensePosition, antColour, markerType);
    }
    
}
