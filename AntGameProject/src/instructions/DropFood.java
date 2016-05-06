package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Pos;
import antgameproject.RandomNumber;

/**
 * Provides implementation to the drop food instruction.
 *
 * @author Team18
 */
public class DropFood implements Instruction {

    private final int nextState;

    /**
     * Constructor for the drop food class, takes as an argument the next state
     * to set after instruction is completed.
     *
     * @param nextState State to set after instruction is completed.
     */
    public DropFood(int nextState) {
        this.nextState = nextState;
    }

    /**
     * Executes the drop food instruction by placing one extra food in the ants
     * board tile if its carrying food and sets the ants next state.
     *
     * @param gameBoard The board to perform the drop food on.
     * @param currentAnt The ant that is carrying the food.
     * @param randomNumberGen Random number generator.
     */
    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        if (currentAnt.getCarryingFood()) {
            Pos antPosition = currentAnt.getBoardPosition();
            gameBoard.setFoodAt(antPosition, gameBoard.numberOfFoodAt(antPosition) + 1);
            currentAnt.setCarryingFood(false);
        }
        currentAnt.setBrainState(nextState);
    }
}
