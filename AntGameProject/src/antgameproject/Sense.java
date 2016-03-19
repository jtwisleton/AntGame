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
public class Sense implements Instruction {
    private Direction senseDirection;
    private Condition condition; 
    private int nextStateIfConditionTrue;
    private int nextStateIfConditionFalse;
    
    public Sense(Direction senseDirection, Condition condition, 
            int nextStateIfConditionTrue, int nextStateIfConditionFalse){
        this.senseDirection = senseDirection;
        this.condition = condition;
        this.nextStateIfConditionTrue = nextStateIfConditionTrue;
        this.nextStateIfConditionFalse = nextStateIfConditionFalse;
    }
    
    public Direction getSenseDirection(){
        return senseDirection;
    }
    
    public Condition getCondition(){
        return condition;
    }
    
    public int getNextStateIfConditionTrue(){
        return nextStateIfConditionTrue;
    }
    
    public int getNextStateIfConditionFalse(){
        return nextStateIfConditionFalse;
    }
}
