/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgameproject.Ant;
import antgameproject.Board;

/**
 *
 * @author wilki
 */
public class Turn extends DirectionalInstruction implements Instruction{
    private final TurnDirection directionToTurn;
    private final int nextState;
    
    public Turn(TurnDirection directionToTurn, int nextState){
        assert nextState >= 0 && nextState < 10000;
        this.directionToTurn = directionToTurn;
        this.nextState = nextState;
    }

    @Override
    public void execute(Board gameBoard, Ant currentAnt) {
        currentAnt.setFacingDirection(turn(directionToTurn, currentAnt.getFacingDirection()));
        currentAnt.setBrainState(nextState);
    }
    
}
