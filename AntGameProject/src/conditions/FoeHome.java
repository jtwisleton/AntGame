
package conditions;

import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import conditions.Condition;

/**
 *
 * @author wilki
 */
public class FoeHome  implements Condition {
    
    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        boolean foeBase = false;
        for(Colour col: Colour.values()){
            if(antColour != col){
                foeBase = gameBoard.anthillAt(sensePosition, col);
            }
        }
        return foeBase;
    }
    
}
