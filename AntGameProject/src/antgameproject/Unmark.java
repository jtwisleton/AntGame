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
    private Marker markToClear;
    private int nextState;
    
    public Unmark(Marker markToClear, int nextState){
        this.markToClear = markToClear;
        this.nextState = nextState;
    }
    
    public Marker getMarkToClear(){
        return markToClear;
    }
    
    public int getNextState(){
        return nextState;
    }
}
