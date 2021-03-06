package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Colour;
import antgameproject.Pos;
import antgameproject.RandomNumber;
import antgameproject.Terrain;

/**
 * Provides implementation for the move instruction. The ant moves in the
 * direction it is currently facing if the way isn't blocked.
 *
 * @author Team18
 */
public class Move extends DirectionalInstruction implements Instruction {

    private final int nextStateIfAheadIsClear;
    private final int nextStateIfAheadIsBlocked;
    private final int restDuration;

    /**
     * Constructor for the move class.
     *
     * @param nextStateIfAheadIsClear the next state if the ant can make its
     * move.
     * @param nextStateIfAheadIsBlocked the next state if the ant can't move.
     */
    public Move(int nextStateIfAheadIsClear, int nextStateIfAheadIsBlocked) {
        this.nextStateIfAheadIsClear = nextStateIfAheadIsClear;
        this.nextStateIfAheadIsBlocked = nextStateIfAheadIsBlocked;
        restDuration = 14;
    }
    
    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        Pos antPosition = currentAnt.getBoardPosition();
        Pos newPosition = getAdjacentCell(antPosition, currentAnt.getFacingDirection());
        if (gameBoard.getTerrainAtPosition(newPosition) == Terrain.ROCK
                || gameBoard.antInPosition(newPosition)) {
            currentAnt.setBrainState(nextStateIfAheadIsBlocked);
        } else {
            gameBoard.clearAntAt(antPosition);
            gameBoard.setAntAt(newPosition, currentAnt);
            currentAnt.setBoardPosition(newPosition);
            currentAnt.setBrainState(nextStateIfAheadIsClear);
            currentAnt.setResting(restDuration);
            checkForSurroundedAnts(newPosition, gameBoard);
        }
    }

    // Returns tje number of adjacent enemy ants.
    private int numAdjacentEnemyAnts(Pos position, Colour antColour, Board gameBoard) {
        int numberOfAdjacentEnemies = 0;
        for (int i = 0; i < 6; i++) {
            Pos adjacentPosition = getAdjacentCell(position, i);
            if (gameBoard.antAt(adjacentPosition) != null
                    && gameBoard.antAt(adjacentPosition).getAntColour() != antColour) {
                numberOfAdjacentEnemies++;
            }
        }
        return numberOfAdjacentEnemies;
    }

    // Checks if an ant at the given position is surrounded.
    private void checkForSurroundedAntAt(Pos antPosition, Board gameBoard) {
        if (gameBoard.antInPosition(antPosition)) {
            Ant currentAnt = gameBoard.antAt(antPosition);
            if (numAdjacentEnemyAnts(antPosition, currentAnt.getAntColour(), gameBoard) >= 5) {
                gameBoard.killAntAt(antPosition);
                int foodInCell = gameBoard.numberOfFoodAt(antPosition);
                if (currentAnt.getCarryingFood()) {
                    foodInCell += 4;
                } else {
                    foodInCell += 3;
                }
                gameBoard.setFoodAt(antPosition, foodInCell);
            }
        }
    }

    // Checks if this ant or any adjacent ant is now surrounded.
    private void checkForSurroundedAnts(Pos position, Board gameBoard) {
        checkForSurroundedAntAt(position, gameBoard);
        for (int i = 0; i < 6; i++) {
            Pos adjacentCell = getAdjacentCell(position, i);
            checkForSurroundedAntAt(adjacentCell, gameBoard);
        }
    }

}
