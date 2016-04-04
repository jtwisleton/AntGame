/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgameproject;

/**
 *
 * @author wilki
 */
public class Turn implements Instruction{
    private final TurnDirection directionToTurn;
    private final int nextState;
    
    public Turn(TurnDirection directionToTurn, int nextState){
        this.directionToTurn = directionToTurn;
        this.nextState = nextState;
    }

    @Override
    public void execute(Board gameBoard, Ant currentAnt) {
        currentAnt.setTurnDirection(directionToTurn);
        currentAnt.setBrainState(nextState);
    }
    
}
