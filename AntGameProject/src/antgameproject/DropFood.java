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
public class DropFood implements Instruction{
    private int nextState;
    
    public DropFood(int nextState){
        this.nextState = nextState;
    }
    
    public int getNextState(){
        return nextState;
    }
}
