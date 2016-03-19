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
public class Flip implements Instruction {
    private int nextStateIfZero;
    private int nextStateElse;
    
    public Flip(int nextStateIfZero, int nextStateElse){
        this.nextStateIfZero = nextStateIfZero;
        this.nextStateElse = nextStateElse;
    }
    
    public int getNextStateIfZero(){
        return nextStateIfZero;
    }
    
    public int getNestStateElse(){
        return nextStateElse;
    }
}
