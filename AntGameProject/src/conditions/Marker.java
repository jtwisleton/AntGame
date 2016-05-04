package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;

/**
 * Marker is used to sense for a specific friendly marker at a given position.
 *
 * @author Team18
 */
public class Marker implements Condition {

    private final int markerType;

    /**
     * Constructor for marker class with the specific marker.
     *
     * @param markerType marker type to sense for.
     */
    public Marker(int markerType) {
        assert markerType < 6 && markerType >= 0;
        this.markerType = markerType;
    }

    /**
     * Sense for the specific marker in the given position for the given colour.
     *
     * @param sensePosition position to sense the marker.
     * @param antColour colour of the marker to sense.
     * @param gameBoard board to performs sense on.
     * @return true if a marker of the given type is found for the antColour in
     * the given position and false otherwise.
     */
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        return gameBoard.checkMarker(sensePosition, antColour, markerType);
    }

}
