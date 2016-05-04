package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Pos;
import antgameproject.RandomNumber;

/**
 * @author Team18
 * Provides implementation for the unmark instruction, which removes a given mark
 * from the ants position.
 */
public class Unmark implements Instruction{
    private final Integer markToClear;
    private final int nextState;
    
    /**
     * Constructor for the Unmark class, takes the mark to clear from the board
     * and the state to set after the mark has been removed.
     * @param markToClear the type of the mark to clear.
     * @param nextState the next state so set for the ant once the mark is cleared.
     */
    public Unmark(Integer markToClear, int nextState){
        this.markToClear = markToClear;
        this.nextState = nextState;
    }
    
    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        Pos antPosition = currentAnt.getBoardPosition();
        gameBoard.clearMarker(antPosition, currentAnt.getAntColour(), markToClear);
        currentAnt.setBrainState(nextState);
    }
}
