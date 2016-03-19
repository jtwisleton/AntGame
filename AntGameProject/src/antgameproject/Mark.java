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
    private int nextState;
    private Marker markToSet;
    
    public Mark(Marker markToSet, int nextState){
        this.nextState = nextState;
        this.markToSet = markToSet;
    }
    
    public int getNextState(){
        return nextState;
    }
    
    public Marker getMarkToSet(){
        return markToSet;
    }
    
}
