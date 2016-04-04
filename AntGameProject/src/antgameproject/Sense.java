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
    private final Direction senseDirection;
    private final Condition condition; 
    private final int nextStateIfConditionTrue;
    private final int nextStateIfConditionFalse;
    
    public Sense(Direction senseDirection, Condition condition, 
            int nextStateIfConditionTrue, int nextStateIfConditionFalse){
        this.senseDirection = senseDirection;
        this.condition = condition;
        this.nextStateIfConditionTrue = nextStateIfConditionTrue;
        this.nextStateIfConditionFalse = nextStateIfConditionFalse;
    }
    
    @Override
    public void execute(Board gameBoard, Ant currentAnt) {
        // let p' = sensed_cell(p, direction(a), sensedir) in
        // let st = if cell_matches(p', cond, color(a)) then st1 else st2 in
        // set_state(a, st)            
    }
}
