package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Pos;
import antgameproject.RandomNumber;

/**
 * Provides implementation for the PickUpFood instruction, that picks up food in
 * the current ants cell.
 *
 * @author Team18
 */
public class PickUpFood implements Instruction {

    private final int nextStateIfFoodFound;
    private final int nextStateIfNoFoodFound;

    /**
     * Constructor for the PickUpFood class.
     *
     * @param nextStateIfFoodFound the next state if food is found in the cell.
     * @param nextStateIfNoFoodFound the next cell if no food is found in the
     * cell.
     */
    public PickUpFood(int nextStateIfFoodFound, int nextStateIfNoFoodFound) {
        this.nextStateIfFoodFound = nextStateIfFoodFound;
        this.nextStateIfNoFoodFound = nextStateIfNoFoodFound;
    }

    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        Pos antPosition = currentAnt.getBoardPosition();
        int noFoodAtAntsPosition = gameBoard.numberOfFoodAt(antPosition);
        if (currentAnt.getCarryingFood() || noFoodAtAntsPosition == 0) {
            currentAnt.setBrainState(nextStateIfNoFoodFound);
        } else {
            gameBoard.setFoodAt(antPosition, noFoodAtAntsPosition - 1);
            currentAnt.setCarryingFood(true);
            currentAnt.setBrainState(nextStateIfFoodFound);
        }
    }

}
