package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Pos;
import antgameproject.RandomNumber;

/**
 * Provides implementation for the mark instruction, which sets the specified mark
 * in the ants current position.
 */
public class Mark implements Instruction {
    private final int nextState;
    private final Integer markToSet;
    
    /**
     * Constructor for the mark class.
     * @param markToSet the mark to be set.
     * @param nextState the next state to be set after the mark has been set.
     */
    public Mark(Integer markToSet, int nextState){
        assert markToSet >= 0 && markToSet < 6;
        assert nextState >= 0 && nextState < 10000;
        this.nextState = nextState;
        this.markToSet = markToSet;
    }

    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        Pos antPosition = currentAnt.getBoardPosition();
        gameBoard.setMarker(antPosition, currentAnt.getAntColour(), markToSet);
        currentAnt.setBrainState(nextState);
    }
    
}
