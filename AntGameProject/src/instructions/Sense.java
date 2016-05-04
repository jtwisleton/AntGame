package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import conditions.Condition;
import antgameproject.Pos;
import antgameproject.RandomNumber;

/**
 * Provides implementation to the Sense instruction that senses one of the given
 * conditions in a given direction from an ant.
 *
 * @author Team18
 */
public class Sense extends DirectionalInstruction implements Instruction {

    private final SenseDirection senseDirection;
    private final Condition condition;
    private final int nextStateIfConditionTrue;
    private final int nextStateIfConditionFalse;

    /**
     * Constructor for the Sense instruction.
     *
     * @param senseDirection the direction to sense in.
     * @param condition the condition to sense.
     * @param nextStateIfConditionTrue the next state for the ant if the
     * condition is true in the given direction.
     * @param nextStateIfConditionFalse the next state for the ant if the
     * condition is false in the given direction.
     */
    public Sense(SenseDirection senseDirection, Condition condition,
            int nextStateIfConditionTrue, int nextStateIfConditionFalse) {
        this.senseDirection = senseDirection;
        this.condition = condition;
        this.nextStateIfConditionTrue = nextStateIfConditionTrue;
        this.nextStateIfConditionFalse = nextStateIfConditionFalse;
    }

    // Get the cell in the specified direction and then test the condition on this cell. 
    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        Pos cellToSense;
        Pos currentAntPosition = currentAnt.getBoardPosition();
        int currentAntFacingDirection = currentAnt.getFacingDirection();
        switch (senseDirection) {
            case HERE:
                cellToSense = currentAnt.getBoardPosition();
                break;
            case AHEAD:
                cellToSense = getAdjacentCell(currentAntPosition, currentAntFacingDirection);
                break;
            case LEFTAHEAD:
                int facingL = turn(TurnDirection.LEFT, currentAntFacingDirection);
                cellToSense = getAdjacentCell(currentAntPosition, facingL);
                break;
            case RIGHTAHEAD:
                int facingR = turn(TurnDirection.RIGHT, currentAntFacingDirection);
                cellToSense = getAdjacentCell(currentAntPosition, facingR);
                break;
            default:
                // add default
                cellToSense = null;
                break;
        }

        boolean cellMatchesCondition = condition.testCondition(cellToSense,
                currentAnt.getAntColour(), gameBoard);

        if (cellMatchesCondition) {
            currentAnt.setBrainState(nextStateIfConditionTrue);
        } else {
            currentAnt.setBrainState(nextStateIfConditionFalse);
        }

    }

}
