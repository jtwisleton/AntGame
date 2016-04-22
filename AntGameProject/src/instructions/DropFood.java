/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgameproject.Ant;
import antgameproject.Board;
import antgameproject.Pos;
import antgameproject.RandomNumber;

/**
 *
 * @author wilki
 */
public class DropFood implements Instruction{
    private final int nextState;
    
    public DropFood(int nextState){
        this.nextState = nextState;
    }
    
    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        if(currentAnt.getCarryingFood()){
            Pos antPosition = currentAnt.getBoardPosition();
            gameBoard.setFoodAt(antPosition, gameBoard.numberOfFoodAt(antPosition) + 1);
            currentAnt.setCarryingFood(false);
        }
        currentAnt.setBrainState(nextState);
    }
}
