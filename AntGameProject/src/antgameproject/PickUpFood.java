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
public class PickUpFood implements Instruction{
    private int nextStateIfFoodFound;
    private int nextStateIfNoFoodFound;
    
    public PickUpFood(int nextStateIfFoodFound, int nextStateIfNoFoodFound){
        this.nextStateIfFoodFound = nextStateIfFoodFound;
        this.nextStateIfNoFoodFound = nextStateIfNoFoodFound;
    }
    
    public int getNextStateIfFoodFound(){
        return nextStateIfFoodFound;
    }
    
    public int getNextStateIfNoFoodFound(){
        return nextStateIfNoFoodFound;
    }
}
