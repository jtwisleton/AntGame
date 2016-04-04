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
public class Unmark implements Instruction{
    private final Marker markToClear;
    private final int nextState;
    
    public Unmark(Marker markToClear, int nextState){
        this.markToClear = markToClear;
        this.nextState = nextState;
    }
    
    @Override
    public void execute(Board gameBoard, Ant currentAnt) {
        Pos antPosition = currentAnt.getBoardPosition();
        gameBoard.clearMarker(antPosition, currentAnt.getAntColour(), markToClear);
        currentAnt.setBrainState(nextState);
    }
}
