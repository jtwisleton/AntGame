/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Pos;

/**
 *
 * @author wilki
 */
public class Mark implements Instruction {
    private final int nextState;
    private final Integer markToSet;
    
    public Mark(Integer markToSet, int nextState){
        assert markToSet >= 0 && markToSet < 6;
        assert nextState >= 0 && nextState < 10000;
        this.nextState = nextState;
        this.markToSet = markToSet;
    }

    @Override
    public void execute(Board gameBoard, Ant currentAnt) {
        Pos antPosition = currentAnt.getBoardPosition();
        gameBoard.setMarker(antPosition, currentAnt.getAntColour(), markToSet);
        currentAnt.setBrainState(nextState);
    }
    
}
