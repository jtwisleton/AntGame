/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import conditions.Condition;
import antgameproject.Pos;
import antgameproject.RandomNumber;

/**
 *
 * @author wilki
 */
public class Sense extends DirectionalInstruction implements Instruction {
    private final SenseDirection senseDirection;
    private final Condition condition; 
    private final int nextStateIfConditionTrue;
    private final int nextStateIfConditionFalse;
    
    public Sense(SenseDirection senseDirection, Condition condition, 
            int nextStateIfConditionTrue, int nextStateIfConditionFalse){
        this.senseDirection = senseDirection;
        this.condition = condition;
        this.nextStateIfConditionTrue = nextStateIfConditionTrue;
        this.nextStateIfConditionFalse = nextStateIfConditionFalse;
    }
    
    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        Pos cellToSense;
        Pos currentAntPosition = currentAnt.getBoardPosition();
        int currentAntFacingDirection = currentAnt.getFacingDirection();
        switch(senseDirection){
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
        
        if(cellMatchesCondition){
            currentAnt.setBrainState(nextStateIfConditionTrue);
        } else {
            currentAnt.setBrainState(nextStateIfConditionFalse);
        }
         
    }


}
