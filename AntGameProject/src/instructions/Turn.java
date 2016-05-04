package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.RandomNumber;

/**
 * @author Team18
 *
 * Provides implementation to the turn instruction that changes the direction
 * that the ant is facing.
 */
public class Turn extends DirectionalInstruction implements Instruction {

    private final TurnDirection directionToTurn;
    private final int nextState;

    /**
     * Constructor for the turn instruction.
     *
     * @param directionToTurn the direction for the ant to turn.
     * @param nextState the next state to set for the ant after is has turned.
     */
    public Turn(TurnDirection directionToTurn, int nextState) {
        assert nextState >= 0 && nextState < 10000;
        this.directionToTurn = directionToTurn;
        this.nextState = nextState;
    }

    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        currentAnt.setFacingDirection(turn(directionToTurn, currentAnt.getFacingDirection()));
        currentAnt.setBrainState(nextState);
    }

}
