package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.RandomNumber;

/**
 * @author Team18
 *
 * Provides implementation to the flip instruction.
 */
public class Flip implements Instruction {

    private final int nextStateIfZero;
    private final int nextStateElse;
    private final int n;

    /**
     * Constructor for the flip instruction.
     *
     * @param upperBound is the upper bound for the random number.
     * @param nextStateIfZero is the next state that is set if the random number
     * if 0.
     * @param nextStateElse is the next state that is set if the random number
     * is greater than 0.
     */
    public Flip(int upperBound, int nextStateIfZero, int nextStateElse) {
        this.nextStateIfZero = nextStateIfZero;
        this.nextStateElse = nextStateElse;
        this.n = upperBound;
    }

    /**
     * Executes the flip instruction. If the random number generated is 0 got to
     * the nextstaeIfZero and if greater than 0 the other state.
     *
     * @param gameBoard the board that the flip instruction takes place on.
     * @param currentAnt the ant performing the flip instruction.
     * @param randomNumberGen random number generator used to choose outcome of
     * flip.
     */
    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        if (randomNumberGen.generateNumber(n) == 0) {
            currentAnt.setBrainState(nextStateIfZero);
        } else {
            currentAnt.setBrainState(nextStateElse);
        }
    }
}
