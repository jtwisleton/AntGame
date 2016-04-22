/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.RandomNumber;

/**
 *
 * @author wilki
 */
public class Flip implements Instruction {
    private final int nextStateIfZero;
    private final int nextStateElse;
    private final int n;
    
    public Flip(int n, int nextStateIfZero, int nextStateElse){
        this.nextStateIfZero = nextStateIfZero;
        this.nextStateElse = nextStateElse;
        this.n = n;
    }
    
    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        if(randomNumberGen.generateNumber(n) == 0){
            currentAnt.setBrainState(nextStateIfZero);
        } else {
            currentAnt.setBrainState(nextStateElse);
        }
    }
}
