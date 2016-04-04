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
public class Mark implements Instruction {
    private final int nextState;
    private final Marker markToSet;
    
    public Mark(Marker markToSet, int nextState){
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
