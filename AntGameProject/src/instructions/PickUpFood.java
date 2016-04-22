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
public class PickUpFood implements Instruction{
    private final int nextStateIfFoodFound;
    private final int nextStateIfNoFoodFound;
    
    public PickUpFood(int nextStateIfFoodFound, int nextStateIfNoFoodFound){
        this.nextStateIfFoodFound = nextStateIfFoodFound;
        this.nextStateIfNoFoodFound = nextStateIfNoFoodFound;
    }

    @Override
    public void execute(Board gameBoard, Ant currentAnt, RandomNumber randomNumberGen) {
        Pos antPosition = currentAnt.getBoardPosition();
        int noFoodAtAntsPosition = gameBoard.numberOfFoodAt(antPosition);
        if(currentAnt.getCarryingFood() || noFoodAtAntsPosition == 0){
            currentAnt.setBrainState(nextStateIfNoFoodFound);
        } else {
            gameBoard.setFoodAt(antPosition, noFoodAtAntsPosition - 1);
            currentAnt.setCarryingFood(true);
            currentAnt.setBrainState(nextStateIfFoodFound);
        }
    }
   
}
