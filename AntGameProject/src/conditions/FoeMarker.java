
package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import conditions.Condition;

/**
 *
 * @author wilki
 */
public class FoeMarker  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        boolean foeMarker = false;
        for(Colour col: Colour.values()){
            if(antColour != col){
                foeMarker = gameBoard.checkAnyMarker(sensePosition, col);
            }
        }
        return foeMarker;
    }
    
}
