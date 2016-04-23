package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.RandomNumber;

/**
 * The interface that is implemented by the instruction classes. 
 */
public interface Instruction {

    /**
     * Execute is the method that performs the instruction.
     * @param gameBoard the board to execute the instruction on.
     * @param currentAnt the ant that performs the instruction.
     * @param numberGen the random number generator used by the instruction.
     */
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber numberGen);
}
