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
public class Move implements Instruction {
    private int nextStateIfAheadIsClear;
    private int nextStateIfAheadIsBlocked;
    
    public Move(int nextStateIfAheadIsClear, int nextStateIfAheadIsBlocked){
        this.nextStateIfAheadIsClear = nextStateIfAheadIsClear;
        this.nextStateIfAheadIsBlocked = nextStateIfAheadIsBlocked;
    }
    
    public int getNextStateIfAheadIsClear(){
        return nextStateIfAheadIsClear;
    }
    
    public int getNextStateIfAheadIsBlocked(){
        return nextStateIfAheadIsBlocked;
    }
}
