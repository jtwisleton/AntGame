
package antgameproject;

/**
 *
 * @author wilki
 */
public class Move extends DirectionalInstruction implements Instruction {
    private final int nextStateIfAheadIsClear;
    private final int nextStateIfAheadIsBlocked;
    private final int restDuration;
    
    public Move(int nextStateIfAheadIsClear, int nextStateIfAheadIsBlocked){
        this.nextStateIfAheadIsClear = nextStateIfAheadIsClear;
        this.nextStateIfAheadIsBlocked = nextStateIfAheadIsBlocked;
        restDuration = 14;
    }

    @Override
    public void execute(Board gameBoard, Ant currentAnt) {
        Pos antPosition = currentAnt.getBoardPosition();
        Pos newPosition = getAdjacentCell(antPosition, currentAnt.getFacingDirection());
        if(gameBoard.getTerrainAtPosition(antPosition) == Terrain.ROCK){
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
    
    private int numAdjacentEnemyAnts(Pos position, Colour antColour, Board gameBoard){
        int numberOfAdjacentEnemies = 0;
        for(int i = 0; i < 5; i++){
            Pos adjacentPosition = getAdjacentCell(position, i);
            if(gameBoard.antAt(adjacentPosition) != null && 
                    gameBoard.antAt(adjacentPosition).getAntColour() == antColour){ //is this right?
                numberOfAdjacentEnemies++;
            }
        }
        return numberOfAdjacentEnemies;
    }

    private void checkForSurroundedAntAt(Pos antPosition, Board gameBoard){
        if(gameBoard.antInPosition(antPosition)){
            Ant currentAnt = gameBoard.antAt(antPosition);
            if(numAdjacentEnemyAnts(antPosition, currentAnt.getAntColour(), gameBoard) >= 5){
                gameBoard.killAntAt(antPosition);
                int foodInCell = gameBoard.getFoodAtint(antPosition);
                if(currentAnt.getCarryingFood()){
                    foodInCell += 4;
                } else {
                    foodInCell += 3;
                }
                gameBoard.setFoodAt(antPosition, foodInCell);
            }
        }
    }
 
    private void checkForSurroundedAnts(Pos position, Board gameBoard){
        checkForSurroundedAntAt(position, gameBoard);
        for(int i = 0; i < 6; i++){
            Pos adjacentCell = getAdjacentCell(position, i);
            checkForSurroundedAntAt(adjacentCell, gameBoard);        
        }
    }
       
}
